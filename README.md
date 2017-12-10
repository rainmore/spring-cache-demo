# REST API CACHE DEMO

REST API CACHE DEMO is a demo to the usage of Spring Cache. The project contains `elements`, `server` and `client` 3 sub-projects.

`elements` hold the entities to be used by `server` and `client` projects.  

`server` provides a REST endpoint, which uses a Spring Cache enabled the server.

`client` is simply consume the api with multiple requests.

Spring Cache has been configured in `server` to avoid requests to the persistent layer. 

## Developer Guide

### Preliminary

The project is developed on top JVM with combine Java 8 code. It requires following tools on the developer work station to develop/debug/test application.

* GIT
* JDK 8
* Gradle 4+

### Initialize Project
 
Clone the project code from github repository
```
git clone git@github.com:rainmore/spring-cache-demo.git
```

Go to the project folder
```
cd spring-cache-demo
```

Setup `gradle wrapper`
```
gradle wrapper --gradle-version 4.3.1 --distribution-type bin
```

### Run Server

The server REST API can be accessed to from 
`http://localhost:8080/api/v1/simple-cab/medallionId/%s/pickupDate/%s/ignoreCache/%s`

To run REST API in development mode

```
./gradlew server:bootRun
```

### Run Client

To run Client in development mode

```
./gradlew client:run
```

## Design

The server project contains 5 layers

* Persistent
* Services
* Controllers
* Caching 
* Others

### Persistent

Persistent layer is built on top Hibernate 5, Spring Data JPA and QueryDSL. It contains Domains and Repositories.

**Domains**

The database connection is set at `server/src/main/resources/application.properties`. It can been customised to other type database. H2 memory database has been used for this project to avoid extra dependency.

All domain classes are located in `elements` gradle sub project for following benefits

* the domains can be reused for other project
* the generated QueryDSL Q classes are decoupled from the core codebase

The QueryDSL code are generated automatically in the gradle sub project 

Java Persistent Validation has been applied to the domain to ensure the data persist correctly.

NOTE: All domain classes are written in Java as Spring Data JPA only supports QueryDSL Q* class built from Java.

**Repositories**

Repositories are simplified by Spring Data JPA. All repositories are just interface and all CRUD methods have been implemented through Spring Data JPA.
Customised queries are also support but they won't be covered in this project.

### Services
Services layer contains the business logic. As there are no complicated business logic covered in this project, most services are just proxy to repository.

### Controllers
Controllers provide REST JSON endpoints. It contains endpoints, validation and data conversion. To provide an extensible API, versioning has been introduced.  

Current version is `v1`. There could be 2 solutions when more versions are required. The first one is to introduce `v2` package, duplicate all existing end point from `v1`, and, then, modify logic in `v2`. 

The second solution is to split persistent and service layer from controllers as independent micro services. Service discovery should be introduced to automatically connect the service layer with each controller versions. 

Also, DTOs and data conversion have been introduced to avoid direct accessing domain entities. The domain entity modification won't have side effect to API, vice versa.

**Endpoints**
It is built with Spring MVC framework to handle HTTP request and response. Versioning has been applied to each API endpoint through `RequestMapping`.

**Validation**

Following validation has been introduced. `org.springframework.validation.Validator` should be bound to the controller to evaluate each POST/PUT request data.

* `@PathVariable` instead of `@RequestParam` is used to specific required API request parameters.
* Spring Web MVC build in type check for the request avoid extra parsing logic 


**Data Conversion**

Data Conversion has been introduced to convert between domain entity and DTO. An independent converter can be introduced to hold advanced logic. At the moment, the conversion logic is simplified in each DTO.

**Cache**

Cache has been simply introduced with `ConcurrentMapCacheManager` to avoid heavy request to the service layer. More advanced cache configuration should be introduced for better cache control.

Furthermore, E-Tag, Last-Modified and Cache-Control headers can be introduced to specific in the response header. 

### Security
Security is not enabled in this project.  

### Swagger
Swagger module in not enabled in this project 

### Others

#### Test

Test is not introduced in this project.

#### Build 

**_TBD_**

#### I18N 

**_TBD_**



