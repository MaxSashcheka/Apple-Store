function addToBasket(id) {
    var xhr = new XMLHttpRequest();

    var body = JSON.stringify({
        id: id
    })

    xhr.open("POST", '/basket', true);
    xhr.setRequestHeader('Content-Type', 'application/json');

    xhr.send(body);
}