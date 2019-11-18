# Web REST service

REST / JSON web service in Java using Spring Boot (RestController) with an API that supports basic CRUD.
Application uses embedded pre populated H2 database.

## Getting Started

### Running

In project root run using spring boot maven plugin:
```
mvn spring-boot:run
```
or execute jar file:

```
java jar testapp-1.0.jar
```

or create package with spring boot maven plugin and run it

```
mvn clean package spring-boot:repackage
java jar target/testapp-1.0-spring-boot.jar
```



## Api documentation

After server startup you can find it here:
* http://localhost:8080/swagger-ui.html

## Running the tests

In project root run:

```
mvn test
```

## TODO

* Implement Spring Security with JWT.
* Remake app as a micro service.
* Create docker image

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Author

* **Ilia Khatko** - [ikhatko](https://github.com/ikhatko)

