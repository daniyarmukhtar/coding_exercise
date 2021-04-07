# Spring Boot "Coding Exercise" Project

This is Kotlin / Gradle / Spring Boot (version 2.2.11) application

## How to Run

* Clone this repository
* Make sure you are using JDK 1.8 and Maven 6.8.x
* You can build the project by bootJar task of gradle
* Once successfully built, you can run the service by:
```
        java -jar coding_exercise-0.0.1-SNAPSHOT.jar
```
Once the application runs you should see something like this

```
2021-04-07 14:19:25.287  INFO 16580 --- [           main] c.e.c.CodingExerciseApplicationKt        : Started CodingExerciseApplicationKt in 3.48 seconds (JVM running for 4.425)
```

## About the Service

The project is simple Contact REST service. It uses an in-memory database (H2) to store the data. You can use MySQL. You can call some REST endpoints defined in ```com.example.coding_exercise.ui.controller.ContactController``` on default **port 8080** by application.properties configuration.

### REST information

```
GET http://localhost:8080/contacts
GET http://localhost:8080/contacts/{id}
POST http://localhost:8080/contacts
PUT http://localhost:8080/contacts/{id}
DELETE http://localhost:8080/contacts/{id}
```

### Create content

```
POST http://localhost:8080/contacts
Accept: application/json
Content-Type: application/json

{
    "fullName": "Example Name",
    "birthDate": "1993-04-09T14:29:48.391+00:00",
    "postal_code": "746845",
    "city": "Example City"
}

RESPONSE: HTTP 201 (Contact is created successfully)
Location header: http://localhost:8080/contacts/{id}
```

### Retrieve contact or contacts

```
http://localhost:8080/contacts
http://localhost:8080/contacts/{id}

Response: HTTP 200
Content: contact or contacts in json 
```

### Update a hotel resource

```
PUT http://localhost:8080/contacts
Accept: application/json
Content-Type: application/json

{
    "fullName": "Example Updated Name",
    "birthDate": "1993-04-09T14:29:48.391+00:00",
    "postal_code": "746845",
    "city": "Example Updated City"
}

RESPONSE: HTTP 204 (No Content)
```
# Questions and Comments: m.daniyar95@gmail.com


