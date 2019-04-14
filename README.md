# JWTREST

Basic RESTful server with JWT authentication built in Java using the Spring
Framework.

## Endpoints:

Data for all endpoints is in JSON format.

### Signup:
Endpoint to process a signup action.

**URL:** `/api/users/signup`

**Method:** `POST` 

**Example Data:**
```json
{
  "username": "admin",
  "password": "pass123"
}
```

### Login:
Endpoint to process a login action. This endpoint will only work once a user has 
successfully signed up. Note that because users are stored in memory, all users 
are wiped upon server restart. 

**URL:** `/api/users/login`

**Method:** `POST`

**Example Data:**
```json
{
  "id": "9e255df7-dee9-489f-9bbe-b96d8e7598e0", 
  "username": "admin",
  "password": "pass123"
}
```

### Token:
Endpoint to process a token refresh. Returns a new access token to the client 
if and only if a valid refresh token is passed through. If the refresh token is
expired, the user must login again.

**URL:** `/api/auth/token`

**Method:** `POST`

**Example Data:**
```json
{
    "user_id": "9e255df7-dee9-489f-9bbe-b96d8e7598e0",
    "refresh_token": "1QiLCJhbGciOiJIUzI1NiJ.RdwleyJ0eXAiOiJKVeXAiO.iLCJpYXQiO0JowFv7QaIer" 
}
```

### Access:
Endpoint to test user authentication by returning an "Access Granted!" string if
and only if the user has sent through a valid JWT access token. If the token is 
expired, the endpoint will return "Access Denied!" with a `401` code.

**URL:** `/api/access`

**Method:** `GET`

**Example Data:**
```json
{
    "user_id": "9e255df7-dee9-489f-9bbe-b96d8e7598e0",
    "access_token": "OeyJ0eXAiOiukzI1NixiLC.JpYXQiOhT8b5fx9Pc9kfYm.jowUyR6MgDf09KopER3wDu"
}
```