# JWTREST

Basic RESTful server with JWT authentication built in Java using the Spring
Framework.

## Endpoints:

Data for all endpoints should be sent as JSON.

### Signup:
Allows a user to signup simply by sending a post request with the desired 
username and password in the header. If a user with the given username already 
exists, the server responds with a response code of `400`.

**URL:** `/api/users/signup`

**Method:** `POST` 

**Data (JSON):**
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

**URL:** `/api/users/login`

**Method:** `POST`

**Data:**
```json
{
  "username": "28b8252b-b818-4153-855c-b803093407fa",
  "username": "username",
  "password": "password"
}
```

### Token (TODO):
This endpoint is yet to be implemented. 

**URL:** `/api/auth/token`

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