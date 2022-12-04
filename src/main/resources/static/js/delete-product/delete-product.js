function deleteProduct(id) {
    var xhr = new XMLHttpRequest();

    var body = JSON.stringify({
        id: id
    })

    xhr.open("DELETE", '/product', true);
    xhr.setRequestHeader('Content-Type', 'application/json');

    xhr.send(body);
}