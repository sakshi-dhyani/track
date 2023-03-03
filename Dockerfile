FROM openjdk:8
ADD target/track-demo-spring.jar track-demo-spring.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar" , "track-demo-spring.jar"]