##### This is the home of the ladder project: the foundation for all ladder projects And includes Some Library
- Audit Project
- CRUD Project
- JSON-WEB Project
- Kafka Client Project
- Redis Client Project
- Elasticsearch Client Project
- Mongo Client Project
- JDBC Client Project
- Microservice Client Project
- Oauth Client Project
- Swagger Project

# Structure
##### All Project Used JDK 11 and in Spring Frame Work version 2.3.0 And other Library
- All Project depends On FrameWork and You Must add It to Pom File

[[/images/core-diagram.png |"Framework Diagram"]]


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

## Audit Project
this project used for log all data in all Microservices

## CRUD Project
this project used for generate All crud Default Services in three layer
- Controller
- Service
- Repository

Repository Layer Implement in Some Other Library JDBC client , Mongo client , Microservice client

## JSON-WEB Project
this project used for work with JWT,JWE,JWS, ...

## Kafka Client Project
this project used for Produce and Consume From Kafka

## Redis Client Project
this project used For connect to Redis

## Elasticsearch Client Project
this project used For connect to Elasticsearch and impl repository of Crud Project

## Mongo Client Project
this project used For connect to Mongo and impl repository of Crud Project

## JDBC Client Project
this project used For connect to Jdbc and impl repository of Crud Project

## Microservice Client Project
this project used For connect to Another Microservice And Create Crud For Aggregation Structure

## Oauth Client Project
thi project Used for connect to standard Oath2 Project

## Swagger Project
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

# License
The Core is released version 1.0 of the Apache License.
