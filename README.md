# User [Spring Boot](http://projects.spring.io/spring-boot/) sample app.

This application is using JHipster 7.9.3 web framework, you can find documentation and help at [https://www.jhipster.tech/documentation-archive/v7.9.3](https://www.jhipster.tech/documentation-archive/v7.9.3).

## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.user.userapp.UserApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

## Using Docker

To start a MySQL database in a docker container, run:

```
docker-compose -f src/main/docker/database.yml up -d
``` 
