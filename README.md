# 2025 씨앗 프로그램 파이널 프로젝트 - 타코야키 팀 백엔드 레포

# 개발 환경
- JAVA 17/spring boot 3.5.0
- gradle 8.14
- util dependency : p6spy, slf4j

# 협업을 위한 규칙
- 테스트
1. 유닛테스트는 필수인 서비스(ExtendWith)와 컨트롤러(WebMvcTest)만 진행
> 매퍼의 경우 백엔드 팀 전체가 이해하기 어려울 수 있기에 생략
2. 프론트 팀과의 협의 이후 통합테스트를 작성

- 예외 처리
1. 모든 예외는 서비스 단에서 처리하며 커스텀 예외를 발생시키도록 함
2. 모든 예외는 GlobalExceptionHandler를 통해 핸들링
3. 예외 코드는 정확히 구분해 줄 것

- 로그
1. 기본적으로 로그의 경우 디버깅을 위해 dev profile에선 on
2. SQL 디버깅을 위해 p6spy를 사용하여 포매팅

- DB
1. 배포 DB는 PostgreSQL이나 개발을 위해 h2 mode 변경으로 작업 권장
> postgreSQL의 경우 h2와 완벽히 호환은 안되므로 주의

# 인증
1. 회원가입 완료
2. 로그인 완료
3. 토큰 인증 완료
4. 내 정보 조회 완료
5. 내 정보 수정 완료 
6. * 토큰 인가 프론트 완성 후 수정 예정 
7. 유닛테스트 작성 완료

# 트러블슈팅 로그
@태현
- 250614 @MockBean deprecated > @MockitoBean
- 250614 컨트롤러 단위테스트 ExtendWith > WebMvcTest(엄밀하게 단위테스트는 아닐 수 있지만 컨트롤러의 경우 단위테스트시 @WebMvcTest를 주로 사용)
@수현
@영석

