# Currency tracker

Currency tracker is REST API which can be used to query currency rates

### Getting started with Docker
```sh
$ docker-compose up --build
```
Note: Admin user admin/Admin@123 added by default

### Features
 - API to register user
 - API to get the latest exchange rates for USD and EURO
 - Api for range query
 - Basic Authentication
 - JWT Authentication
 - Synchronizes exchange rates via scheduled task

### Tech
 - Java 8
 - Spring Boot, JPA
 - Postgres
 - Docker

### TODO
 - Time series database is better solution to store exchange rates
 - Group API urls in constants
 - Add tests for security
 
### Known issues
 - JPA/Postgres primary index track mismatch   
