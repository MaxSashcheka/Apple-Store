function updateProduct(id) {
    var xhr = new XMLHttpRequest();

    var body = JSON.stringify({
        id: id
    })

    xhr.open("POST", '/navigate-edit-product', true);
    xhr.setRequestHeader('Content-Type', 'application/json');

    xhr.send(body);
}