FROM tomcat:10.1.23-jdk21-temurin-jammy

ADD *.war /usr/local/tomcat/webapps/

ENV SUTTON_DB_HOST_PORT="localhost:3306"

EXPOSE 8080

CMD ["catalina.sh", "run"]

FROM sgrio/java-oracle
RUN apt-get update
RUN apt-get install -y maven
COPY pom.xml /usr/local/service/pom.xml
COPY src /usr/local/service/src
WORKDIR /usr/local/service
RUN mvn package
CMD ["java","-cp","target/docker-service"