FROM gradle:jdk11 AS build

COPY --chown=gradle:gradle . /home/gradle/src

WORKDIR /home/gradle/src

RUN gradle build --no-daemon 

RUN ls /home/gradle/src/

FROM adoptopenjdk/openjdk11:alpine-jre

EXPOSE 8080

RUN mkdir /app

COPY --from=build /home/gradle/src/build/libs/*SNAPSHOT.jar /app/spring-boot-application.jar

RUN ls /app/

CMD ["java", "-jar","/app/spring-boot-application.jar"]