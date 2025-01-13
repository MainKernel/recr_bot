FROM openjdk:21-jdk
WORKDIR /app
COPY /build/libs/recrbot-0.0.3.jar app.jar
COPY /keystore.p12 app/
EXPOSE 443
ENTRYPOINT [ "java", "-jar", "app.jar" ]