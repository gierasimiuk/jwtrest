var resultDiv = $("#output");

    $.ajaxSetup({
        contentType: "application/json"
    });    
    $.ajax({
        url: "http://localhost:8080/api/users/signup",
        type: "POST",
        data: JSON.stringify({
            "username": "Michael233",
            "password": "password"
        }),
        dataType: "json",
        success: function (result, message) {
            let html = "";
            html += "Message: " + message + "<br/>"; 
            html += "Result: " + JSON.stringify(result);
            resultDiv.html(html);
        },
        error: function (xhr, ajaxOptions) {
            if (ajaxOptions == "error") {
                let html = "";
                html += "Error<br/>"
                html += "Message: " + xhr.responseJSON.message; 
                resultDiv.html(html);
            }
        }
    });