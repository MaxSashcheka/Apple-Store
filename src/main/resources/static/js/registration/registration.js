function registrateNewUser() {
    var xhr = new XMLHttpRequest();

    var firstName = document.getElementById('name-text-field');
    var lastName = document.getElementById('last-name-text-field');
    var login = document.getElementById('login-text-field');
    var password = document.getElementById('password-text-field');

    var body = JSON.stringify({
        first_name: firstName.value,
        last_name: lastName.value,
        login: login.value,
        password: password.value
    })

    xhr.open("POST", '/registration', true);
    xhr.setRequestHeader('Content-Type', 'application/json');


    xhr.send(body);
}