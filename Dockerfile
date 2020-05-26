FROM adoptopenjdk/openjdk11:alpine-jre
ARG JAR_FILE=web/target/web-1.0-SNAPSHOT.jar
WORKDIR /opt/app
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]

#multi stage option
#FROM adoptopenjdk/openjdk11:alpine as mvn-builder
#WORKDIR /workspace/app
#COPY mvnw .
#COPY .mvn .mvn
#COPY pom.xml .
#COPY common common
#COPY core core
#COPY mail-lib mail-lib
#COPY security-lib security-lib
#COPY web web
#RUN ./mvnw package -DskipTests
#
#FROM adoptopenjdk/openjdk11:alpine-jre
#WORKDIR /opt/app
#COPY --from=mvn-builder /workspace/app/web/target/web-1.0-SNAPSHOT.jar /opt/app
#ENTRYPOINT ["java","-jar","web-1.0-SNAPSHOT.jar"]
