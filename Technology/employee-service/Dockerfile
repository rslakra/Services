FROM openjdk:11
VOLUME /tmp
ADD ./target/employee-service-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
#ENTRYPOINT exec java -Djava.security.egd=file:/dev/./urandom -jar /app.jar
#CMD ["java","-jar","app.jar"]
CMD ["java","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar"]
