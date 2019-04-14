# JWTREST

Basic RESTful server with JWT authentication built in Java using Spring

## Endpoints:

### Signup:
Allows a user to signup simply by sending a post request with the desired 
username and password in the header. If a user with the given username already 
exists, the server responds with a response code of `400`.

`Method: POST`\n
`URL: api/auth/signup`\n
`Data:`
`{`
`  username: [string]`
`  password: [string]`
`}`

### Login:
Allows a user to login to the server by sending a post request with the
username and password. This endpoint will only work once a user has successfully
signed up. Note that because users are stroed in memory, all users are wiped 
upon server restart.

`Method: POST`
`api/auth/login`
`Data:`
`{`
`  id: [string]`
`  username: [string]`
`  password: [string]`
`}`

### Token (TODO):
Not yet implemented. 

`Method: GET`
`api/auth/token`
`Data:`
`{`
`  username: [string]`
`  token: [string]`
`}`

### Access (TODO):
Not yet implemented. 

`Method: GET`
`api/access`
`Data:`
`{`
`  token: [string]`
`}`