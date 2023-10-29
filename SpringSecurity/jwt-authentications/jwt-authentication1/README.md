# Spring Boot JWT Authentication
 
 example with Spring Security & Spring Data JPA

For more detail, please visit:
> [Spring Boot JWT Authentication](https://github.com/rslakra/Spring.git)

– MySQL
```xml
<dependency>
  <groupId>mysql</groupId>
  <artifactId>mysql-connector-java</artifactId>
  <scope>runtime</scope>
</dependency>
```
## DataSource Configuration
Open `src/main/resources/application.properties`

- MySQL
```
spring.datasource.url= jdbc:mysql://localhost:3306/testdb?useSSL=false
spring.datasource.username= root
spring.datasource.password= 123456

spring.jpa.properties.hibernate.dialect= org.hibernate.dialect.MySQL5InnoDBDialect
spring.jpa.hibernate.ddl-auto= update

# App Properties
app.jwtSecret= rlakra
app.jwtExpirationMs= 86400000
```

## Run Spring Boot application

```
mvn spring-boot:run
```

## Run following SQL insert statements
```
INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_MODERATOR');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');
```


## Author
- Rohtash Singh Lakra