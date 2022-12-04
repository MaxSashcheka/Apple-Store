function updateUser(id) {
    var xhr = new XMLHttpRequest();

    var firstName = document.getElementById('name-text-field');
    var lastName = document.getElementById('last-name-text-field');

    var body = JSON.stringify({
        id: id,
        firstName: firstName.value,
        lastName: lastName.value
    })

    xhr.open("PUT", '/profile', true);
    xhr.setRequestHeader('Content-Type', 'application/json');


    xhr.send(body);
}