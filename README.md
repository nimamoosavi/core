##### This is the home of the ladder project: the foundation for all ladder projects And includes Some Library
- [Audit Project](https://github.com/nimamoosavi/Audit)
- [CRUD Project](https://github.com/nimamoosavi/crud)
- [JSON-WEB Project](https://github.com/nimamoosavi/json-web)
- [Kafka Client Project](https://github.com/nimamoosavi/kafka-client)
- [Redis Client Project](https://github.com/nimamoosavi/redis-client)
- Elasticsearch Client Project
- [Mongo Client Project](https://github.com/nimamoosavi/mongo-client)
- [JDBC Client Project](https://github.com/nimamoosavi/jdbc-client)
- [Crud Client Project](https://github.com/nimamoosavi/client)
- Oauth Client Project
- [Swagger Project](https://github.com/nimamoosavi/swagger)

# Structure
##### All Project Used JDK 11 and in Spring Frame Work version 2.3.0 And other Library
- All Project depends On Core and You Must add It to Pom File

![Core](https://github.com/nimamoosavi/core/wiki/images/core-diagram.png)
[[images/core-diagram.png]]


# Maven Central
~~~
**Spring boot**

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.3.0.RELEASE</version>
</dependency>

**Lombok**

<dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
</dependency>

**Jackson**
<dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-core</artifactId>
            <version>2.9.8</version>
</dependency>
~~~

## [Audit Project](https://github.com/nimamoosavi/Audit)
this project used for log all data in all Microservices

## [CRUD Project](https://github.com/nimamoosavi/crud)
this project used for generate All crud Default Services in three layer
- Controller
- Service
- Repository

Repository Layer Implement in Some Other Library JDBC client , Mongo client , Microservice client

## [JSON-WEB Project](https://github.com/nimamoosavi/json-web)
this project used for work with JWT,JWE,JWS, ...

## [Kafka Client Project](https://github.com/nimamoosavi/kafka-client)
this project used for Produce and Consume From Kafka

## [Redis Client Project](https://github.com/nimamoosavi/redis-client)
this project used For connect to Redis

## Elasticsearch Client Project
this project used For connect to Elasticsearch and impl repository of Crud Project

## [Mongo Client Project](https://github.com/nimamoosavi/mongo-client)
this project used For connect to Mongo and impl repository of Crud Project

## [JDBC Client Project](https://github.com/nimamoosavi/jdbc-client)
this project used For connect to Jdbc and impl repository of Crud Project

## [Crud Client Project](https://github.com/nimamoosavi/client)
this project used For connect to Another Microservice And Create Crud Project Structure

## Oauth Client Project
thi project Used for connect to standard Oath2 Project

## [Swagger Project](https://github.com/nimamoosavi/swagger)
this project used For create Swagger For your Project

# Releases
##### Last Release Version in Frame Work 1.0.1-Released

~~~
<dependency>
            <groupId>app.ladderproject</groupId>
            <artifactId>core</artifactId>
            <version>1.0.1-Released</version>
</dependency>
~~~

# Sample Project
this project used Crud Project And Jdbc Project And Core And ...

- [Sample Project](https://github.com/nimamoosavi/sample-project-crud)

# License
The Core is released version 1.0 of the Apache License.
