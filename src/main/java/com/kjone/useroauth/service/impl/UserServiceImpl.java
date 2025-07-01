package com.kjone.useroauth.service.impl;

import com.kjone.useroauth.entity.UserEntity;
import com.kjone.useroauth.repository.UserRepository;
import com.kjone.useroauth.request.LoginRequest;
import com.kjone.useroauth.request.SignRequest;
import com.kjone.useroauth.response.LoginData;
import com.kjone.useroauth.response.LoginResponse;
import com.kjone.useroauth.response.SignResponse;
import com.kjone.useroauth.security.jwt.JwtTokenProvider;
import com.kjone.useroauth.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // 추가

    @Autowired
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    @Transactional
    public boolean signUp(SignRequest signRequest) throws Exception {

        try {
            //email 중복 확인
            Optional<UserEntity> exitingUser = userRepository.findByEmail(signRequest.getEmail());
            if(exitingUser.isPresent()) {
                throw new Exception("이미 사용 중인 이메일입니다.");
            }
            //이름 중복 확인
            Optional<UserEntity> exisingName = userRepository.findByUsername(signRequest.getUsername());
            if(exisingName.isPresent()) {
                throw new Exception("이미 사용 중인 이름입니다.");
            }

            //비밀번호 제한
            if (!isValidPassword(signRequest.getPassword())) {
                throw new IllegalArgumentException("비밀번호는 대문자, 소문자, 숫자, 특수문자를 모두 포함해야 합니다.");
            }

            // 이메일 형식
            if (!signRequest.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                throw new IllegalArgumentException("올바른 이메일 형식이 아닙니다.");
            }


            UserEntity user = UserEntity.builder()
                    .email(signRequest.getEmail())
                    .password(passwordEncoder.encode(signRequest.getPassword())) // 수정
                    .username(signRequest.getUsername())
                    .phone(signRequest.getPhone())
                    .age(signRequest.getAge())
                    .sex(signRequest.getSex())
                    .image(signRequest.getImage())
                    .build();
            userRepository.save(user);

        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new Exception(e.getMessage());
        }

        return true;
    }

    public boolean isValidPassword(String password) {
        // 길이도 조건 8자 이상 20이하 추가
        // 특수 문자 조건
        String pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[~!@#$%^&*()_+\\-={}\\[\\]:;\"'<>,.?/]).{8,20}$";
        return password != null && password.matches(pattern);
    }

    // 로그인 메서드
    @Override
    public LoginResponse signIn(LoginRequest loginRequest) throws Exception {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );

        if (!authentication.isAuthenticated()) {
            throw new IllegalArgumentException("인증이 완료되지 않았습니다.");
        }

        UserEntity user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("해당 사용자가 존재하지 않습니다."));

        String accessToken = jwtTokenProvider.createAccessToken(user);
        String refreshToken = jwtTokenProvider.createRefreshToken(user);

        LoginData loginData = new LoginData(accessToken, refreshToken);
        return new LoginResponse("OK", 200, loginData);
    }
}
