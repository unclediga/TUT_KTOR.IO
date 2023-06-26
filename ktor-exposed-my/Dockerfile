FROM bellsoft/liberica-openjdk-alpine
EXPOSE 8080:8080
RUN mkdir /app
COPY ./target/my-expose-jar-with-dependencies.jar /app/ktor-docker-my-expose.jar
ENTRYPOINT ["java","-jar","/app/ktor-docker-my-expose.jar"]