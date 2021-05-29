# MenS Create Spring-Security

>> _**MenS Create** is a Full Stack Web Application._ 
>>
>> _Created by Sharon Rosa Wijnberg, May 2021_ 
> 
> >> Full Stack Software Development Bootcamp Final Assignment.
> >
>> MENS-CREATE portrays a creative workspace or box booking system for authenticated user.
>
>This back end app is written in Java, Spring Boot Security with JPA, Hibernate as a part of the
final assessment for the Bootcamp Full Stack Software Developer.
>
>This app consists of authentication and authorization for user login and registration.
A file upload for Blob data type to show picture in userprofile and a booking system to reserve a box or space.

---

![image](https://user-images.githubusercontent.com/66298438/120052943-029cfe00-c028-11eb-8648-048025deb101.png)

---

## Table of Contents

- [Features for users](https://github.com/ShaRosaW/menscreate-spring-security#features-for-users)
- [Requirements you need for building and running the application](https://github.com/ShaRosaW/menscreate-spring-security#requirements-you-need-for-building-and-running-the-application)
- [Installation guide](https://github.com/ShaRosaW/menscreate-spring-security#installation-guide)
- [User-dummies](https://github.com/ShaRosaW/menscreate-spring-security#user-dummies)
- [Rest-Endpoints](https://github.com/ShaRosaW/menscreate-spring-security#rest-endpoints)
- [Testing with JUnit and Mocking](https://github.com/ShaRosaW/menscreate-spring-security#testing-with-junit-and-mocking)

## Features for users
* authentication and authorization for login and registration (CR)
* file upload for Blob data type and download (CR)
* booking system to reserve a box or space (with all CRUD)
* admin functionality to get list of all bookings and option to delete
* admin functionality to get list of all users

## Requirements you need for building and running the application:

- [Java Development Kit 14.0.2](https://adoptopenjdk.net)
- [Maven 3.6.3](https://maven.apache.org)
- [IntelliJ IDEA 2021.1](https://www.jetbrains.com/idea)
- [PostgreSQL 13.2](https://www.postgresql.org)
- [PgAdmin 4.5.2](https://www.pgadmin.org)
- [Postman 8.4](https://www.postman.com) (optional, when not using the corresponding frontend)
- [MenS Create Front-end Project](https://github.com/ShaRosaW/menscreate-reactjs)

## Installation guide
#### After installing all the tools on your Operating System, we can get started to run the Spring Boot application on your local machine:

* **Start up your IDEA**
    * Open the project
        * clone the repository
        * File -> New -> Project from Version Control
        * paste the cloned Repository URL 
        * if git is not installed, choose download and install and proceed
        
    * Set up Software Development Kit
        * File -> Project Structure
        * Project Settings -> Project
          * -> Project SDK: choose the SDK you just downloaded (version 14). 
          Make sure it corresponds with the version you download.
          * -> Project language level: choose SDK default and click OK
          
* **Set up Database**
    * Start up Postgres and log in on PGAdmin with you master password.
    * Open left tab **>Servers**
        * Right mouse click on Login/Group Roles and choose **Create**
            * under tab general you fill in the name: **spring-boot-security**
            * under tab definition you fill in the password: **postgres** and save
            * under tab privileges toggle can login? to yes.
        * Right mouse click on Databases and choose **Create**
            * for the name of the database you fill in: **spring-boot-security**
            * as owner of this database you fill in the one you just created: **spring-boot-security** and save
            
This way you can run the application from this cloned repository, just as it is set up in 
[application.properties](src/main/resources/application.properties).
You can choose to change it and create a different database and login name and password as above described and change it in the app properties as well to connect them.
```properties
spring.datasource.url= jdbc:postgresql://localhost:5432/spring-boot-security
spring.datasource.username= spring-boot-security
spring.datasource.password= postgres
```
* **Run the application**
    * Run with the IDEA:
        * In your IDEA you open the tab: project.
        * menscreate -> src -> main -> java -> 
          [MensCreateApplication](src/main/java/nl/wijnberg/menscreate/MensCreateApplication.java)
        * to run go to the green arrow next to public class: MensCreateApplication on line 7
        * right mouse click and choose run to compile and build the application
        * on your menu bar above you will see a hammer (build tool), MensCreateApplication and the green arrow. 
          this means first time build is complete. and for next times to run the application you can use that green arrow above (and red square to stop).
        * to check if your database is initialized and with the correct information corresponding with app.propperties the console will show you : **Spring Data repositories initialized** after your build. 
    * Run with maven in command line:
```shell
$ mvn spring-boot:run
```

## User-Dummies:
To test all endpoints in postman or with the connected front end: 

These users are hardcoded in [data.sql](src/main/resources/data.sql) file in resourses, along with user bookings.

| id | username | email | password | role | 
|----|----------|-------|----------|------|
|1|sharonr|sharon@gmail.com|password|ROLE_USER|
|2|user|user@mail.nl|password|ROLE_USER|
|3|admin|admin@mail.nl|password|ROLE_ADMIN|

If you want to test with your own user details, register as a new user. 
After you are logged in, your JWT bearer token is limited for 24 hours to perform all http methods.
In [Jwt Utils Class](src/main/java/nl/wijnberg/menscreate/service/security/jwt/JwtUtils.java) the Jwt token is build with user details and sets the expiration time and signs it with the secret key. 

----
----
## Rest-Endpoints

> ### Postman:
>
> Next up are the rest-endpoints below, without a frontend it is easy to use Postman for testing the endpoints.
> In that case instead of writing all the endpoints yourself, you can simply import my postman collection.
> 
> When you open Postman you find Scratch Pad above the collections.
> next to Scratch Pad you will see option new and import. Choose the last one.
> You can either copy and paste the 
> [postman-collection.json](src/main/resources/MenS-Create.postman_collection.json) from this folder. Or save it on 
> your directory and import it from your files.


 ---
## *AuthController*
#### POST /api/auth/register

----
Returns User registered successfully! *or gives an error response...*

* **URL**

  /api/auth/register

* **Method:**

  `POST`
  
* **Headers**

  Content-Type: Application-Json<br/>
  Authorization: Bearer token

* **Request Body**

    SignupRequest:
  `{
  "username": "sjaak",
  "email" : "sjaak@user.nl",
  "password" : "123456"
  }`

* **Success Response:**

    * **Code:** 200 OK <br />
      **Content:** `{ "message": "User registered successfully!" }`
      
* **Error Response:**

    * **Code:** 400 BAD REQUEST <br />
      **Content:** `{ error : "Error: Username is already taken!" }`

  OR

    * **Code:** 400 BAD REQUEST <br />
      **Content:** `{ error : "Error: Email is already in use!" }`
      
* **Sample Json:**

```Json
{
  "message": "User registered successfully!"
}
```
----
### POST /api/auth/login

----
Returns json data with user login details.

* **URL**

  /api/auth/login

* **Method:**

  `POST`

* **Headers**

  Content-Type: Application-Json<br/>
  Authorization: Bearer token
  
* **Request Body**

  LoginRequest:
  `{
  "username": "sharonr",
  "password" : "password"
  }`
  
* **Success Response:**

    * **Code:** 200 OK <br />
      **Content:** `{ "User registered successfully!" }`

* **Error Response:**

    * **Code:** 401 UNAUTHORIZED <br />
      **Content:** `{ error : "You are unauthorized to make this request." }`
      "Unauthorized error: {}"

  OR

    * **Code:** 400 BAD REQUEST <br />
      **Content:** `{ error : "Error: Email is already in use!" }`

* **Sample Json:**

```Json
{
"id": 1,
"username": "sharonr",
"email": "sharon@gmail.com",
"roles": [
"ROLE_USER"
],
"accessToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzaGFyb25yIiwiaWF0IjoxNjIwNjgwMDYxLCJleHAiOjE2MjA3NjY0NjF9.p7kLlgNNGP3An2JZXvSmf2OY8LuikoiR1Pp9okwlcLnrQJe89kMNtYoj7thekkhVV4xUZx7nz7Fp7ORljqvN0A",
"tokenType": "Bearer"
}
```
----
 Login as admin

* **Request Body**

  LoginRequest:
  `{
  "username": "admin",
  "password" : "password"
  }`

* **Success Response:**

    * **Code:** 200 OK <br />
      **Content:** `{ 
      "username": "admin",
      "password" : "password"
      }`

* **Error Response:**

    * **Code:** 401 UNAUTHORIZED <br />
      **Content:** `{ error : "You are unauthorized to make this request." }`
      "Unauthorized error: {}"

  OR

    * **Code:** 400 BAD REQUEST <br />
      **Content:** `{ error : "Error: Email is already in use!" }`

* **Sample Json:**

```Json
{
  "id": 3,
  "username": "admin",
  "email": "admin@mail.nl",
  "roles": [
    "ROLE_ADMIN"
  ],
  "accessToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTYyMDY4MzY2MCwiZXhwIjoxNjIwNzcwMDYwfQ.ZocFdccj_Tb8LnMBMvB4-dTGtQmajUswUvwYfqM9ZfPJBo2rK684jEf-VHUT1DdLQVfFUy2UqDOpr9myAY5iPA",
  "tokenType": "Bearer"
}
```
----
----

## *BookingController*

----

### POST /api/bookings/new
###### @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")

----
Creates a booking and returns with new bookingId.

* **URL**

  /api/bookings/new

* **Method:**

  `POST`

* **Headers**

  Content-Type: Application-Json<br/>
  Authorization: Bearer token

* **Request Body**

  Booking:
  `{
  "boxName": "cake box",
  "bookingDate": "2021-06-17"
  }`

* **Success Response:**

    * **Code:** 201 CREATED<br />
      **Content:** `{
      "message": "Booking with bookingId: 6 was created successfully!"
      }`

* **Error Response:**

    * **Code:** 401 UNAUTHORIZED <br />
      **Content:** `{ "Unauthorized error : "Unauthorized" }`
<!--- **Content:** `{ "Unauthorized error : "You are unauthorized to make this request." }` -->
Unauthorized error: Full authentication is required to access this resource

----
----

### GET /api/bookings/user
###### @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")

----
Returns json data with user bookings by token.

* **URL**

  /api/bookings/user

* **Method:**
  
  `GET`
  
* **Headers**

  Content-Type: Application-Json<br/>
  Authorization: Bearer token

* **Success Response:**

    * **Code:** 200 OK<br />
      **Content:** `{ [
      {
      "userId": 1,
      "bookingId": 1,
      "boxName": "meeting area",
      "bookingDate": "2021-10-29"
      },
      {
      "userId": 1,
      "bookingId": 4,
      "boxName": "work space",
      "bookingDate": "2021-07-03"
      },
      {
      "userId": 1,
      "bookingId": 6,
      "boxName": "cake box",
      "bookingDate": "2021-06-17"
      }
      ] }`

* **Error Response:**

    * **Code:** 404 NOT FOUND <br />
      **Content:** `{ error : "Cannot find specified record" }`

  OR

    * **Code:** 401 UNAUTHORIZED <br />
      **Content:** `{ Unauthorized error : "You are unauthorized to make this request." }`
    
* **Sample Json:**

```Json
[
  {
    "userId": 1,
    "bookingId": 1,
    "boxName": "meeting area",
    "bookingDate": "2021-05-29"
  },
  {
    "userId": 1,
    "bookingId": 4,
    "boxName": "work space",
    "bookingDate": "2021-05-29"
  },
  {
    "userId": 1,
    "bookingId": 6,
    "boxName": "cake box",
    "bookingDate": "2021-06-17"
  }
]
```
----
### GET /api/bookings/{bookingId}
###### @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")

----
Returns json data with booking by booking id.

* **URL**

  /api/bookings/:bookingId 
    6

* **Method:**

  `GET`

*  **URL Params**

   **Required:**

   `id=[long]`

* **Headers**

  Content-Type: Application-Json<br/>
  Authorization: Bearer token

* **Success Response:**

    * **Code:** 200 OK<br />
      **Content:** `{ {
      "headers": {},
      "body": {
      "userId": 1,
      "bookingId": 6,
      "boxName": "cake box",
      "bookingDate": "2021-06-17"
      },
      "statusCode": "OK",
      "statusCodeValue": 200
      } }`

* **Error Response:**

    * **Code:** 404 NOT FOUND <br />
      **Content:** `{ error : "There was no booking found with this id " + bookingId + "." }`

  OR

    * **Code:** 401 UNAUTHORIZED <br />
      **Content:** `{ Unauthorized error : "You are unauthorized to make this request." }`
      
* **Sample Json:**

```Json
{
  "headers": {},
  "body": {
    "userId": 1,
    "bookingId": 6,
    "boxName": "cake box",
    "bookingDate": "2021-06-17"
  },
  "statusCode": "OK",
  "statusCodeValue": 200
}
```
----
### GET /api/bookings/user/{bookingId}
###### @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")

----
Returns json data with user booking by booking id.

* **URL**

  /api/bookings/user/:bookingId
    6
* **Method:**

  `GET`

*  **URL Params**

   **Required:**

   `id=[long]`

* **Headers**

  Content-Type: Application-Json<br/>
  Authorization: Bearer token

* **Success Response:**

    * **Code:** 200 OK<br />
      **Content:** `{ {
      "userId": 1,
      "bookingId": 6,
      "boxName": "cake box",
      "bookingDate": "2021-06-17"
      } }`

* **Error Response:**

    * **Code:** 404 NOT FOUND <br />
      **Content:** `{ error : "There was no booking found with this id " + bookingId + "." }`

  OR

    * **Code:** 401 UNAUTHORIZED <br />
      **Content:** `{ Unauthorized error : "You are unauthorized to make this request." }`
      
* **Sample Json:**

```Json
{
  "userId": 1,
  "bookingId": 6,
  "boxName": "cake box",
  "bookingDate": "2021-06-17"
}
```
----
----

> ### Note:
> 
> After creating a new booking, logged in as a user, it returns bookingId: 6. 
> That is because in the [data.sql](src/main/resources/data.sql)
> we insert 5 bookings for the 3 hardcoded users and with 2 different user roles. 
>
> Now if you create a new booking, logged in as admin, bookingId: 7 is returned. 
> 
> Proceed the following request for *update (put) booking* with bookingId: 7.
>
> Perform the *delete request* for bookingId 6, and *get all user bookings* still logged in as admin. 
> You will see all user bookings except for deleted bookingId 4.
>
> 
> Only as admin you get a list of all user bookings. 
> 
> Delete a bookingId can be performed by both roles, except admin is able to delete any users bookingId.


----
### PUT /api/bookings/{bookingId}
###### @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")

----
Returns json data with updated booking.

* **URL**

  /api/bookings/:bookingId

* **Method:**

  `PUT`

*  **URL Params**

   **Required:**

   `id=[long]`

* **Headers**

  Content-Type: Application-Json<br/>
  Authorization: Bearer token

* **Request Body**

  BookingRequest:
  `{
  "boxName": "meeting area",
  "bookingDate": "2021-08-20"
  }`

* **Success Response:**

    * **Code:** 200 OK<br />
      **Content:** `{ {
      "bookingId": 7,
      "bookingDate": "2021-08-20",
      "boxName": "meeting area"
      } }`

* **Error Response:**

    * **Code:** 404 NOT FOUND <br />
      **Content:** `{ error : "Cannot find specified record" }`

  OR

    * **Code:** 403 FORBIDDEN <br />
      **Content:** `{ error : "This booking with id " + bookingId + "does not belong to you." }`
      
* **Sample Json:**

```Json
{
  "bookingId": 7,
  "bookingDate": "2021-08-02",
  "boxName": "meeting area"
}
```
----
----

### DELETE /api/bookings/{bookingId}
###### @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")

----
Returns message of deleted booking.

* **URL**

  /api/bookings/:bookingId
    6
* **Method:**

  `DELETE`

*  **URL Params**

   **Required:**

   `id=[long]`

* **Headers**

  Content-Type: Application-Json<br/>
  Authorization: Bearer token

* **Success Response:**

    * **Code:** 200 OK<br />
      **Content:** `{ "Booking with id number: " + bookingId + " was deleted with success" }`

* **Error Response:**

    * **Code:** 404 NOT FOUND <br />
      **Content:** `{ error : "There was no booking found with this id " + bookingId + "." }`

  OR

    * **Code:** 403 FORBIDDEN <br />
      **Content:** `{ error : "This booking with id " + bookingId + "does not belong to you." }`

* **Sample Json:**

```Json
{
  "message": "Booking with id number: 6 was deleted with success"
}
```
----
----

### GET /api/bookings/all
###### @PreAuthorize("hasRole('ADMIN')")

----
Returns json data with a list of all bookings.

* **URL**

  /api/bookings/all

* **Method:**

  `GET`
* **Headers**

  Content-Type: Application-Json<br/>
  Authorization: Bearer token

* **Success Response:**

    * **Code:** 200 OK<br />
      **Content:** `{ [{
      "userId": 1,
      "bookingId": 1,
      "boxName": "meeting area",
      "bookingDate": "2021-10-29"
      },
      {
      "userId": 3,
      "bookingId": 2,
      "boxName": "meeting area",
      "bookingDate": "2021-12-22"
      },
      {
      "userId": 2,
      "bookingId": 3,
      "boxName": "cake box",
      "bookingDate": "2021-11-25"
      },
      {
      "userId": 1,
      "bookingId": 4,
      "boxName": "work space",
      "bookingDate": "2021-07-03"
      },
      {
      "userId": 3,
      "bookingId": 5,
      "boxName": "meeting area",
      "bookingDate": "2021-08-08"
      },
      {
      "userId": 3,
      "bookingId": 7,
      "bookingDate": "2021-08-02",
      "boxName": "meeting area"
      }] }`

* **Error Response:**

    * **Code:** 404 NOT FOUND <br />
      **Content:** `{ error : "Cannot find specified record" }`

  OR

    * **Code:** 403 FORBIDDEN <br />
      **Content:** `{ error : "You are unauthorized to make this request." }`

* **Sample Json:**

```Json
[
  {
    "userId": 1,
    "bookingId": 1,
    "boxName": "meeting area",
    "bookingDate": "2021-10-29"
  },
  {
    "userId": 3,
    "bookingId": 2,
    "boxName": "meeting area",
    "bookingDate": "2021-12-22"
  },
  {
    "userId": 2,
    "bookingId": 3,
    "boxName": "cake box",
    "bookingDate": "2021-11-25"
  },
  {
    "userId": 1,
    "bookingId": 4,
    "boxName": "work space",
    "bookingDate": "2021-07-03"
  },
  {
    "userId": 3,
    "bookingId": 5,
    "boxName": "meeting area",
    "bookingDate": "2021-08-08"
  },
  {
    "userId": 3,
    "bookingId": 7,
    "bookingDate": "2021-08-20",
    "boxName": "meeting area"
  }
]
```
----
----

## *FileController*

### GET /api/files

----
Returns Json with a list of all files.

* **URL**

  /api/files

* **Method:**

  `GET`
* **Headers**

  Content-Type: multipart/form-data<br/>
  Authorization: Bearer token

* **Success Response:**

    * **Code:** 200 OK<br />
      **Content:** `{ [
      {
      "name": "baker-1194428__340.webp",
      "url": "http://localhost:8080/api/files/f39dd44e-0d59-48df-9a4b-c0c3ecf0981f",
      "type": "image/webp",
      "size": 30912
      },
      {
      "name": "profile-pic-srw.jpg",
      "url": "http://localhost:8080/api/files/12b2fca8-2d52-486c-84a4-a821ac1d5818",
      "type": "image/jpeg",
      "size": 184693
      }
      ] }`

* **Error Response:**

    * **Code:** 401 UNAUTHORIZED <br />
      **Content:** `{ error : "You are unauthorized to make this request." }`
      "Unauthorized error: {}"
      
* **Sample Json:**

```Json
[
  {
    "name": "baker-1194428__340.webp",
    "url": "http://localhost:8080/api/files/f39dd44e-0d59-48df-9a4b-c0c3ecf0981f",
    "type": "image/webp",
    "size": 30912
  },
  {
    "name": "profile-pic-srw.jpg",
    "url": "http://localhost:8080/api/files/12b2fca8-2d52-486c-84a4-a821ac1d5818",
    "type": "image/jpeg",
    "size": 184693
  }
]
```
----
### POST /api/files/upload

----
Returns message file upload success or error.

* **URL**

  /api/files/upload

* **Method:**

  `POST`
* **Headers**

  Content-Type: multipart/form-data<br/>
  Authorization: Bearer token

* **Success Response:**

    * **Code:** 200 OK<br />
      **Content:** `{ "Uploaded the file successfully: " + file.getOriginalFilename() }`

* **Error Response:**

    * **Code:** 417 EXPECTATION_FAILED <br />
      **Content:** `{ error : "Could not upload the file: " + file.getOriginalFilename() + "!" }`
      
    OR

    * **Code:** 417 EXPECTATION_FAILED <br />
      **Content:** `{ error : "File is too large!" }`

* **Sample Json:**

```Json
{
  "message": "Uploaded the file successfully: baker-1194428__340.webp"
}
```
----
### GET /api/files/{id}

----
Returns file by id.

* **URL**

  /api/files/:id
  
*  **URL Params**

   **Required:**

   `id=[String]`

* **Method:**

  `GET`
  
* **Headers**
  
  Content-Type: multipart/form-data<br/>
  Authorization: Bearer token

* **Success Response:**

    * **Code:** 200 OK<br />
      **Content:** `{ HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\""}`

* **Error Response:**

    * **Code:** 404 NOT FOUND <br />
      **Content:** `{ error : "Cannot find specified record" }`

* **Sample Json:**

```Json
[
  {
    "name": "baker-1194428__340.webp",
    "url": "http://localhost:8080/api/files/f39dd44e-0d59-48df-9a4b-c0c3ecf0981f",
    "type": "image/webp",
    "size": 30912
  }
]
```
----
---
## *UserController*

----

### GET /api/users/all
###### @PreAuthorize("hasRole('ADMIN')")

----
Returns json data with a list of all users.

* **URL**

  /api/users/all

* **Method:**

  `GET`
  
* **Headers**

  Content-Type: Application-Json<br/>
  Authorization: Bearer token

* **Success Response:**

    * **Code:** 200 OK<br />
      **Content:** `{ {
      "id": 1,
      "username": "sharonr",
      "email": "sharon@gmail.com",
      "password": "$2a$10$44azppeekabMTwZZ1vtq4OWr/SboMJh4KxUOepB8EFkEJH6gbfeQq",
      "roles": [{"id": 1,"name": "ROLE_USER"}],
      "bookings": [{"bookingId": 1,"bookingDate": "2021-10-29","boxName": "meeting area"}, 
      {"bookingId": 4,"bookingDate": "2021-07-03","boxName": "work space"}, 
      ]},
      {"id": 2,
      "username": "user",
      "email": "user@mail.nl",
      "password": "$2a$10$44azppeekabMTwZZ1vtq4OWr/SboMJh4KxUOepB8EFkEJH6gbfeQq",
      "roles": [{"id": 1,"name": "ROLE_USER"}],
      "bookings": [{"bookingId": 3,"bookingDate": "2021-11-25","boxName": "cake box"}]
      }, 
      {"id": 3,
      "username": "admin",
      "email": "admin@mail.nl",
      "password": "$2a$10$44azppeekabMTwZZ1vtq4OWr/SboMJh4KxUOepB8EFkEJH6gbfeQq",
      "roles": [{"id": 2,"name": "ROLE_ADMIN"}],
      "bookings": [{"bookingId": 2,"bookingDate": "2021-12-22","boxName": "meeting area"},
      {"bookingId": 5,"bookingDate": "2021-08-08","boxName": "meeting area"}]},
      {"id": 4,
      "username": "sjaak",
      "email": "sjaak@user.nl",
      "password": "$2a$10$Z9tuTpPX7axAoDLdsnJKt.u5ur8IHLiffyUPiBOqH.0ipo6SabRSO",
      "roles": [{"id": 1,"name": "ROLE_USER"}],
      "bookings": []
      } }`

* **Error Response:**

    * **Code:** 404 NOT FOUND <br />
      **Content:** `{ error : "Cannot find specified record" }`

  OR

    * **Code:** 401 UNAUTHORIZED <br />
      **Content:** `{ Unauthorized error : "You are unauthorized to make this request." }`
      
* **Sample Json:**

```Json
[
  {
    "id": 1,
    "username": "sharonr",
    "email": "sharon@gmail.com",
    "password": "$2a$10$44azppeekabMTwZZ1vtq4OWr/SboMJh4KxUOepB8EFkEJH6gbfeQq",
    "roles": [{"id": 1,"name": "ROLE_USER"}],
    "bookings": [{"bookingId": 1,"bookingDate": "2021-10-29","boxName": "meeting area"},
      {"bookingId": 4,"bookingDate": "2021-07-03","boxName": "work space"}
    ]},
  {"id": 2,
    "username": "user",
    "email": "user@mail.nl",
    "password": "$2a$10$44azppeekabMTwZZ1vtq4OWr/SboMJh4KxUOepB8EFkEJH6gbfeQq",
    "roles": [{"id": 1,"name": "ROLE_USER"}],
    "bookings": [{"bookingId": 3,"bookingDate": "2021-11-25","boxName": "cake box"}]
  },
  {"id": 3,
    "username": "admin",
    "email": "admin@mail.nl",
    "password": "$2a$10$44azppeekabMTwZZ1vtq4OWr/SboMJh4KxUOepB8EFkEJH6gbfeQq",
    "roles": [{"id": 2,"name": "ROLE_ADMIN"}],
    "bookings": [{"bookingId": 2,"bookingDate": "2021-12-22","boxName": "meeting area"},
      {"bookingId": 5,"bookingDate": "2021-08-08","boxName": "meeting area"}]},
  {"id": 4,
    "username": "sjaak",
    "email": "sjaak@user.nl",
    "password": "$2a$10$Z9tuTpPX7axAoDLdsnJKt.u5ur8IHLiffyUPiBOqH.0ipo6SabRSO",
    "roles": [{"id": 1,"name": "ROLE_USER"}],
    "bookings": []
  }
]
```
----
### GET /api/users/user
###### @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")

----
Returns json data with user by token.

* **URL**

  /api/users/user

* **Method:**

  `GET`

* **Headers**

  Content-Type: Application-Json<br/>
  Authorization: Bearer token

* **Success Response:**

    * **Code:** 200 OK<br />
      **Content:** `{
      "user": {
      "id": 1,
      "username": "sharonr",
      "email": "sharon@gmail.com",
      "password": "$2a$10$44azppeekabMTwZZ1vtq4OWr/SboMJh4KxUOepB8EFkEJH6gbfeQq",
      "roles": [
      {
      "id": 1,
      "name": "ROLE_USER"
      }
      ],
      "bookings": [
      {
      "bookingId": 1,
      "bookingDate": "2021-10-29",
      "boxName": "meeting area"
      },
      {
      {"bookingId": 4,
      "bookingDate": "2021-07-03",
      "boxName": "work space"
      }
      ]
      },
      "userFiles": [
      {
      "name": "baker-1194428__340.webp",
      "url": "http://localhost:8080/api/files/f39dd44e-0d59-48df-9a4b-c0c3ecf0981f",
      "type": "image/webp",
      "size": 30912
      },
      {
      "name": "profile-pic-srw.jpg",
      "url": "http://localhost:8080/api/files/12b2fca8-2d52-486c-84a4-a821ac1d5818",
      "type": "image/jpeg",
      "size": 184693
      }
      ]
      }`

* **Error Response:**

    * **Code:** 404 NOT FOUND <br />
      **Content:** `{ error : "Cannot find specified record" }`

  OR

    * **Code:** 401 UNAUTHORIZED <br />
      **Content:** `{ Unauthorized error : "You are unauthorized to make this request." }`
      
* **Sample Json:**

```Json
{
  "user": {
    "id": 1,
    "username": "sharonr",
    "email": "sharon@gmail.com",
    "password": "$2a$10$44azppeekabMTwZZ1vtq4OWr/SboMJh4KxUOepB8EFkEJH6gbfeQq",
    "roles": [
      {
        "id": 1,
        "name": "ROLE_USER"
      }
    ],
    "bookings": [
      {
        "bookingId": 1,
        "bookingDate": "2021-10-29",
        "boxName": "meeting area"
      },
      {
        "bookingId": 4,
        "bookingDate": "2021-07-03",
        "boxName": "work space"
      }
    ]
  },
  "userFiles": [
    {
      "name": "baker-1194428__340.webp",
      "url": "http://localhost:8080/api/files/f39dd44e-0d59-48df-9a4b-c0c3ecf0981f",
      "type": "image/webp",
      "size": 30912
    },
    {
      "name": "profile-pic-srw.jpg",
      "url": "http://localhost:8080/api/files/12b2fca8-2d52-486c-84a4-a821ac1d5818",
      "type": "image/jpeg",
      "size": 184693
    }
  ]
}
```
----
### GET /api/users/{id}
###### @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")

----
Returns json data with user by id.

* **URL**

  /api/users/:id

* **Method:**

  `GET`

*  **URL Params**

   **Required:**

   `id=[long]`

* **Headers**

  Content-Type: Application-Json<br/>
  Authorization: Bearer token

* **Success Response:**

    * **Code:** 200 OK<br />
      **Content:** `{ {
      "id": 1,
      "username": "sharonr",
      "email": "sharon@gmail.com",
      "password": "$2a$10$44azppeekabMTwZZ1vtq4OWr/SboMJh4KxUOepB8EFkEJH6gbfeQq",
      "roles": [
      {
      "id": 1,
      "name": "ROLE_USER"
      }
      ],
      "bookings": [
      {
      "bookingId": 1,
      "bookingDate": "2021-10-29",
      "boxName": "meeting area"
      },
      {
      "bookingId": 2,
      "bookingDate": "2021-07-03",
      "boxName": "work space"
      }
      ]
      } }`
      
* **Error Response:**

    * **Code:** 404 NOT FOUND <br />
      **Content:** `{ error : "Cannot find specified record" }`

  OR

    * **Code:** 401 UNAUTHORIZED <br />
      **Content:** `{ Unauthorized error : "You are unauthorized to make this request." }`

* **Sample Json:**

```Json 
{
  "id": 1,
  "username": "sharonr",
  "email": "sharon@gmail.com",
  "password": "$2a$10$44azppeekabMTwZZ1vtq4OWr/SboMJh4KxUOepB8EFkEJH6gbfeQq",
  "roles": [
    {
      "id": 1,
      "name": "ROLE_USER"
    }
  ],
  "bookings": [
    {
      "bookingId": 1,
      "bookingDate": "2021-10-29",
      "boxName": "meeting area"
    },
    {
      "bookingId": 2,
      "bookingDate": "2021-07-03",
      "boxName": "work space"
    }
  ]
}
```
----
### GET /api/users/{username}
###### @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")

----
Returns json data with user by username.

* **URL**

  /api/users/:username

* **Method:**

  `GET`

*  **URL Params**

   **Required:**

   `username=[String]`

* **Headers**

  Content-Type: Application-Json<br/>
  Authorization: Bearer token

* **Success Response:**

    * **Code:** 200 OK<br />
      **Content:** `{ {
      "id": 1,
      "username": "sharonr",
      "email": "sharon@gmail.com",
      "password": "$2a$10$44azppeekabMTwZZ1vtq4OWr/SboMJh4KxUOepB8EFkEJH6gbfeQq",
      "roles": [
      {
      "id": 1,
      "name": "ROLE_USER"
      }
      ],
      "bookings": [
      {
      "bookingId": 1,
      "bookingDate": "2021-10-29",
      "boxName": "meeting area"
      },
      {
      "bookingId": 4,
      "bookingDate": "2021-07-03",
      "boxName": "work space"
      }
      ]
      } }`

* **Error Response:**

    * **Code:** 404 NOT FOUND <br />
      **Content:** `{ error : "Cannot find specified record" }`

  OR

    * **Code:** 401 UNAUTHORIZED <br />
      **Content:** `{ Unauthorized error : "You are unauthorized to make this request." }`

* **Sample Json:**

```Json
{
  "id": 1,
  "username": "sharonr",
  "email": "sharon@gmail.com",
  "password": "$2a$10$44azppeekabMTwZZ1vtq4OWr/SboMJh4KxUOepB8EFkEJH6gbfeQq",
  "roles": [
    {
      "id": 1,
      "name": "ROLE_USER"
    }
  ],
  "bookings": [
    {
      "bookingId": 1,
      "bookingDate": "2021-05-29",
      "boxName": "meeting area"
    },
    {
      "bookingId": 4,
      "bookingDate": "2021-07-03",
      "boxName": "work space"
    }
  ]
}
```
----
----

## Testing with JUnit and Mocking:

---

A few tests are implemented and covered in the test-folder. \
The set up to run them all at once and see the total outcome and test-coverage is a few mouse clicks away. 

- Go to the test folder in IntelliJ.

- Open java folder and right mouse click on the next folder -->
[nl.wijnberg.menscreate](src/test/java/nl/wijnberg/menscreate).

- You will see the option to _Run 'Tests in 'nl.wijnberg.menscreate''_ next to the green arrow. \
  - Choose the option _More Run/Debug_.

- Click on the one that says _Run 'Tests in 'nl.wijnberg.menscreate'' with Coverage_. 

This will run all the tests that are created in this folder and display the test-coverage as well. 

:white_check_mark:
- :white_check_mark: [Test Results](TestResults-nl_wijnberg_menscreate.xml).