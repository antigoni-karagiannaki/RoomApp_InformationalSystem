
function Show_Password() {
    var Pass = document.getElementById("password");

    if (Pass.type === 'password') {
        Pass.type = "text";
    } else if (Pass.type === 'text') {
        Pass.type = "password";
    }

}

function User_Create() {
    window.location.href = "http://localhost:8080/CS351_web_app/form.html";
}

function log_in() {
    var username = document.getElementById('username').value;
    var password = document.getElementById('password').value;

    if ((username === "admin123") && (password === "admin123")) {
        window.location.href = "http://localhost:8080/CS351_web_app/admin.html";
        return;
    }

    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            console.log("eyeeeeeeeeeeeeeeeeeeeeeeee\n" + xhr.responseText);

            window.location.href = "http://localhost:8080/CS351_web_app/employee.html";
        } else if(xhr.status !== 200) {
            console.log('Error with password or username!!!');
        }
    };

    xhr.open('POST', 'LOGINservlet?username=' + username + '&password=' + password);
    xhr.send();
}

function go_back() {
    window.location.href = "http://localhost:8080/CS351_web_app/";
}

