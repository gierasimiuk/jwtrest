console.log("Testing API endpoints ...");

$.ajax({
    type: "POST",
    url: "localhost:8080/api/auth/signup",
    crossDomain: true,
    data: {
        username: "user",
        password: "pass"
    },
    success: function(data) {
        console.log(data)
    }
  });