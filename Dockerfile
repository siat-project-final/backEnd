# 베이스 이미지 pull
FROM eclipse-temurin:17-jdk-alpine

# jar 위치 지정
ARG JAR_FILE=build/libs/*.jar

# 카피
COPY ${JAR_FILE} /backend.jar

# 컨테이너 실행 환경에서 앱 실행
ENTRYPOINT ["java", "-jar", "/backend.jar"]