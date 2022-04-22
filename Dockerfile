FROM openjdk:latest AS videojuego_grupo_03
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
COPY ./data ./data
ENTRYPOINT ["java","-jar","/app.jar"]
