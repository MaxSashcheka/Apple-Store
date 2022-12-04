function createNewProduct() {
    var xhr = new XMLHttpRequest();

    var name = document.getElementById('name-text-field');
    var price = document.getElementById('price-text-field');
    var typeId = document.getElementById('type-select');

    var body = JSON.stringify({
        name: name.value,
        price: parseInt(price.value),
        productTypeId: parseInt(typeId.value)
    })

    xhr.open("POST", '/compose-product', true);
    xhr.setRequestHeader('Content-Type', 'application/json');

    xhr.send(body);
}