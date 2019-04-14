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
  "username": "admin",
  "password": "pass123"
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
  "id": "9e255df7-dee9-489f-9bbe-b96d8e7598e0",
  "username": "admin",
  "password": "pass123"
}
```

### Token:
Endpoint to process a token refresh. Returns a new access token to the client 
assuming a valid refresh token is passed through.

**URL:** `/api/auth/token`

**Method:** `GET`

**Data:**
```json
{
    "user_id": "9e255df7-dee9-489f-9bbe-b96d8e7598e0",
    "refresh_token": "eyJ0eXAiO.iLCJpYXQiO.0JowFv7QaI"
}
```

### Access:
Endpoint to test user authentication by returning an "Access Granted!" string if 
the user sends through a valid token.

**URL:** `/api/access`

**Method:** `GET`

**Data:**
```json
{
    "user_id": "9e255df7-dee9-489f-9bbe-b96d8e7598e0",
    "access_token": "eyJ0eXAiOi.iLCJpYXQiO.hT8b5fx9Pc"
}
```