function deleteBasket(id) {
    var xhr = new XMLHttpRequest();

    var body = JSON.stringify({
        productId: id
    })

    xhr.open("DELETE", '/basket', true);
    xhr.setRequestHeader('Content-Type', 'application/json');

    xhr.send(body);
}