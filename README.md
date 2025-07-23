## 핵심 기술 스택

- **Spring Boot**  
- **Spring Data JPA**
- **MariaDB** (개발 환경에 따라)
- **Lombok**
- **Spring Web**
- **Spring WebSocket**



## 주요 패키지 설명

| 패키지 | 설명 |
|--------|------|
| `domain` | 비즈니스 도메인별 패키지 (Entity, Repository, Service, Controller 등) |
| `global` | 전역 설정, 공통 예외 처리, 공통 유틸 등 모든 도메인에서 공통으로 사용하는 모듈 |

---

## 실행 방법

1. **Clone**
   ```bash
   git clone https://github.com/your-repo/project-name.git
   cd project-name
   ./gradlew BootRun
   ```
