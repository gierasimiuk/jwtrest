# JWTREST

Basic RESTful server with JWT authentication built in Java using the Spring
Framework.

## Endpoints:

Data for all endpoints is in JSON format.

### Signup:
Endpoint to process a signup action. If a user with the given username already 
exists, the server will respond with an error code of `400`.

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
successfully signed up. If a user with the given id is not found, the server will 
respond with an error code of `400`. Note that because users are stored in 
memory, all users are wiped upon server restart. 

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
if and only if a valid refresh token is passed through. If the given token is
expired, the server will respond with an error code of `400`

**URL:** `/api/auth/token`

**Method:** `GET`

**Example Data:**
```json
{
    "user_id": "9e255df7-dee9-489f-9bbe-b96d8e7598e0",
    "refresh_token": "eyJ0eXAiO.iLCJpYXQiO.0JowFv7QaI" 
}
```

### Access:
Endpoint to test user authentication by returning an "Access Granted!" string if 
the user sends through a valid token. If the user with the given id is not 
authenticated with the system, the server will respond with an error code of 
`401`

**URL:** `/api/access`

**Method:** `GET`

**Example Data:**
```json
{
    "user_id": "9e255df7-dee9-489f-9bbe-b96d8e7598e0",
    "access_token": "eyJ0eXAiOi.iLCJpYXQiO.hT8b5fx9Pc"
}
```