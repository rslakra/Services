# Docker Employee Service

## Create Network
```shell
docker network create mysql-network
```

```shell
docker network ls
```

## Pull and run mysql image
```
docker container run --network mysql-network --name mysqldb -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=employeedb -d mysql:latest

#docker container run --network mysql-network --name mysqldb -p 3306:3306 -e MYSQL_ROOT_HOST=localhost -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=employeedb -d mysql:latest
```

```shell
docker stop mysqldb
docker rm mysqldb
```

## Can use below command to get into mysql bash and run queries
```
docker exec -it mysqldb mysql -uroot -proot
use employeedb;
show databases;
show tables;
exit;
```

## Create a spring boot project at start.spring.io and write a little rest api to fetch employees

## Compile Application
```shell
mvn clean package -DskipTests
```

## Test your application on localhost:8080

## Create Dockerfile under the project directory and put below lines in it
```
FROM openjdk:11
VOLUME /tmp
ADD ./target/advice-service-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT exec java -Djava.security.egd=file:/dev/./urandom -jar /app.jar
```

## And build docker image
```
docker build -t dockerlakra/advice-service .
```

## Run your docker image and the microservice is up
```
docker run -p 8080:8080 -d --name advice-service dockerlakra/advice-service
```

## Check Logs
```shell
docker container logs -f advice-service
```

## Or instead of creating Dockerfile we can also use docker-maven-plugin the pom

## Add docker-maven-plugin to your pom
```
<properties>
   <docker.image.prefix>dockerlakra</docker.image.prefix>
</properties>
<build>
    <plugins>
    <plugin>
      <groupId>com.spotify</groupId>
      <artifactId>docker-maven-plugin</artifactId>
      <version>0.4.13</version>
      <configuration>
        <imageName>${docker.image.prefix}/advice-service</imageName>
        <baseImage>java:8</baseImage>
        <entryPoint>["java", "-jar", "/${project.build.finalName}.jar"]</entryPoint>
        <resources>
          <resource>
            <targetPath>/</targetPath>
            <directory>${project.build.directory}</directory>
            <include>${project.build.finalName}.jar</include>
          </resource>
        </resources>
      </configuration>
    </plugin>
    </plugins>
</build>
```

## And build your application using which will also build the docker image
```
./mvnw clean package docker:build
```

## Run your docker image and the microservice is up
```
docker run -p 8080:8080 -d --name advice-service dockerlakra/advice-service
```

## If we want to run your microservice container in docker by linking mysql container in docker instead of directly pointing to url we can do that by below command
```shell
docker run --link mysqldb --name advice-service -d dockerlakra/advice-service
```

OR

```shell
docker container run --network=mysql-network --name advice-service -p 8080:8080 -d dockerlakra/advice-service
```

OR

```shell
docker container run --network=mysql-network --link mysqldb --name advice-service -p 8080:8080 -d dockerlakra/advice-service
```

## Remove Docker Services
```shell
docker rm advice-service
```

## References
- [Docker MySQL](https://www.javainuse.com/devOps/docker/docker-mysql)
