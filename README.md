##### Core of ladder project:The foundation of all ladder projects.
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
##### All Projects Used JDK 11 and in Spring Frame Work version 2.3.0 And other Libraries
- All Projects depend on core, and You Must add It to Pom File.

![](/images/core-diagram.png "Framework Diagram")


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
This project is used for log all data in all Microservices

## CRUD Project
This project is used for generating all default crud Services in three layers
- Controller
- Service
- Repository

Repository Layer Implements in Some other Libraries such as, JDBC client , Mongo client , Microservice client.

## JSON-WEB Project
This project is used for work with JWT,JWE,JWS, ...

## Kafka Client Project
This project is used for producing and consuming data from Kafka.

## Redis Client Project
This project is used for connecting to Redis.

## Elasticsearch Client Project
This project is used for connecting to Elasticsearch and implement repository layer in Crud Project.

## Mongo Client Project
This project is used for connecting to Mongo and implement repository layer in Crud Project.

## JDBC Client Project
This project is used for connecting to Jdbc and implementing repository layer in Crud Project.

## Microservice Client Project
This project is used for connecting to crud project, and also create Crud for Aggregation structure.

## Oauth Client Project
This project is used for connecting to the Oath2 standard  Project.

## Swagger Project
This project is used for creating Swagger in your Project.

# Releases
##### Last Version in Core 1.0.1-Released 

~~~
<dependency>
            <groupId>app.ladderproject</groupId>
            <artifactId>core</artifactId>
            <version>1.0.1-Released</version>
</dependency>
~~~

# License
The Core is released version 1.0 of the Apache License.