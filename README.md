# menscreate-spring-security

## Endpoints

### GET /api/bookings/all

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
      **Content:** `{ [{"userId":1,"bookingId":1,"boxName":"work space","bookingDate":"2021-05-29"},
      {"userId":1,"bookingId":2,"boxName":"meeting area","bookingDate":"2021-05-29"},
      {"userId":3,"bookingId":3,"boxName":"work space","bookingDate":"2021-05-29"}] }`

* **Error Response:**

    * **Code:** 404 NOT FOUND <br />
      **Content:** `{ error : "Cannot find specified record" }`
      
  OR

    * **Code:** 401 UNAUTHORIZED <br />
      **Content:** `{ error : "You are unauthorized to make this request." }`
      "Unauthorized error: {}"
* **Sample Json:**

```Json
[
    {
        "userId": 1,
        "bookingId": 1,
        "boxName": "work space",
        "bookingDate": "2021-05-29"
    },
    {
        "userId": 1,
        "bookingId": 2,
        "boxName": "meeting area",
        "bookingDate": "2021-05-29"
    },
    {
        "userId": 3,
        "bookingId": 3,
        "boxName": "work space",
        "bookingDate": "2021-05-29"
    }
]
```

TEMPLATE 

**Show User**
----
Returns json data about a single user.

* **URL**

  /users/:id

* **Method:**

  `GET`

*  **URL Params**

   **Required:**

   `id=[integer]`

* **Headers**

  Content-Type: Application-Json
  Authorization: Bearer token
  
* **Request Body**

  None

* **Success Response:**

    * **Code:** 200 <br />
      **Content:** `{ id : 12, name : "Michael Bloom" }`

* **Error Response:**

    * **Code:** 404 NOT FOUND <br />
      **Content:** `{ error : "User doesn't exist" }`

  OR

    * **Code:** 401 UNAUTHORIZED <br />
      **Content:** `{ error : "You are unauthorized to make this request." }`

* **Sample Call:**

  ```javascript
    $.ajax({
      url: "/users/1",
      dataType: "json",
      type : "GET",
      success : function(r) {
        console.log(r);
      }
    });
  ```