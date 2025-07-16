package com.kjone.useroauth.global.security.cookie;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CookieUtil {

    public static void addCookie(HttpServletResponse response, String name, String value, int maxAge,
                                 boolean httpOnly, boolean secure, String path, String sameSite) {
        Cookie cookie = new Cookie(name, value);
        cookie.setHttpOnly(httpOnly);
        cookie.setSecure(secure);
        cookie.setPath(path);
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);

        // SameSite 직접 헤더 추가
        String cookieHeader = String.format("%s=%s; Max-Age=%d; Path=%s; %s %s SameSite=%s",
                name, value, maxAge, path,
                httpOnly ? "HttpOnly;" : "",
                secure ? "Secure;" : "",
                sameSite);
        response.setHeader("Set-Cookie", cookieHeader);
    }

    public static void deleteCookie(HttpServletResponse response, String name, String path) {
        Cookie cookie = new Cookie(name, null);
        cookie.setPath(path);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

    public static String getCookieValue(HttpServletRequest request, String name) {
        if (request.getCookies() == null) {
            return null;
        }
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals(name)) {
                return cookie.getValue();
            }
        }
        return null;
    }
}

