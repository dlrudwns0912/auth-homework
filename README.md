# auth-homework

Spring Boot와 Java로 구현한 회원 인증(Auth)·게시글(Blog) 백엔드입니다. Spring Security와 JWT로 인증하고, 인증된 사용자는 자신의 게시글을 작성·수정·삭제할 수 있습니다.

## 기술 스택

- Java 21, Spring Boot 3.2.11
- Spring Security, JJWT 0.12.5
- Spring Data JPA, MySQL
- springdoc-openapi (Swagger UI)

## 실행 방법

DB와 JWT 값은 기본값 없이 환경 변수로 받아야 합니다.

| 환경 변수 | 설명 |
| --- | --- |
| `DB_URL` | MySQL 접속 URL (예: `jdbc:mysql://localhost:3306/homework`) |
| `DB_USERNAME` | MySQL 계정 |
| `DB_PASSWORD` | MySQL 비밀번호 |
| `JWT_SECRET` | 32자 이상 랜덤 JWT 서명 키 |

```bash
export DB_URL='jdbc:mysql://localhost:3306/homework'
export DB_USERNAME='root'
export DB_PASSWORD='your-password'
export JWT_SECRET='change-this-to-a-random-string-of-32-chars-or-more'
./gradlew bootRun
```

`spring.jpa.hibernate.ddl-auto`는 `validate`입니다. 실행 전 MySQL에 `users`와 게시글 테이블을 준비해야 합니다.

## 주요 API

| 기능 | 메서드·경로 | 인증 | 성공 응답 |
| --- | --- | --- | --- |
| 회원가입 | `POST /api/auth/signup` | 불필요 | 201 Created |
| 로그인 | `POST /api/auth/login` | 불필요 | 200 OK, access token |
| 게시글 작성 | `POST /api/blogs` | 필요 | 201 Created |
| 게시글 전체/단건 조회 | `GET /api/blogs`, `GET /api/blogs/{id}` | 불필요 | 200 OK |
| 게시글 수정 | `PUT /api/blogs/{id}` | 작성자 | 200 OK |
| 게시글 삭제 | `DELETE /api/blogs/{id}` | 작성자 | 204 No Content |

인증이 필요한 요청에는 아래 헤더를 포함합니다.

```http
Authorization: Bearer {accessToken}
```

## 인증 구조

- 회원가입 시 비밀번호는 `BCryptPasswordEncoder`로 해시화합니다.
- 로그인 시 이메일·비밀번호를 검증한 뒤, 이메일을 subject로 하는 JWT를 발급합니다.
- `JwtAuthenticationFilter`가 토큰을 검증하고 현재 사용자와 역할 권한을 `SecurityContext`에 등록합니다.
- 세션은 사용하지 않는 `STATELESS` 방식이며, 회원가입·로그인·게시글 조회·Swagger만 공개합니다.

API 문서는 `/swagger-ui/index.html`에서, OpenAPI JSON은 `/v3/api-docs`에서 확인할 수 있습니다.
