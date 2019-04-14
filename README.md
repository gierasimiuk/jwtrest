# JWTREST

Basic RESTful server with JWT authentication built in Java using Spring

## Endpoints:

Data for all endpoints should be sent as key/value pairs in the header of the
request.

### Signup:
Allows a user to signup simply by sending a post request with the desired 
username and password in the header. If a user with the given username already 
exists, the server responds with a response code of `400`.

**URL:** `api/auth/signup`

**Method:** `POST` 

**Data:**
```json
{
  "username": "username",
  "password": "password"
}
```

### Login:
Allows a user to login to the server by sending a post request with the
username and password. This endpoint will only work once a user has successfully
signed up. Note that because users are stroed in memory, all users are wiped 
upon server restart.

**URL:** `/api/auth/login`

**Method:** `POST`

**Data:**
```json
{
  "username": "<Unique identifier provided upon login>",
  "username": "username",
  "password": "password"
}
```

### Token (TODO):
This endpoint is yet to be implemented. 

**URL:** /api/auth/token

**Method:** `GET`

**Data:**
```json
{
  "username": "<Unique identifier provided upon login>",
  "token": "<Refresh token as a string>",
}
```

### Access (TODO):
This endpoint is yet to be implemented. 

**URL:** `/api/access`

**Method:** `GET`

**Data:**
```json
{
  "token": "<Full token as a string>",
}
```