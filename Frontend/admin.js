function addCustomer() {
    window.location.href = "signup.html";
}
function getCustomer() {
    let url = "https://mini-project-production.up.railway.app:443/customer/get/all";
    fetch(url).then((res) => {
        return res.json();
    }).then((res) => {
        if (res.message == null) {
            showCustomers(res);
        } else {
            alert(res.message);
        }
    }).catch((err) => {
        console.log(err);
    })
}
function showCustomers(data) {
    document.querySelector("#containers").innerHTML = "";
    let container = document.querySelector("#container");
    container.innerHTML = "";
    let heading = document.createElement("h2");
    heading.innerHTML = "Customers";
    heading.style.opacity = 1;
    container.append(heading);
    let table = document.createElement("table");
    let thead = document.createElement("thead");
    let th1 = document.createElement("th");
    let th2 = document.createElement("th");
    let th3 = document.createElement("th");
    let th4 = document.createElement("th");
    let th5 = document.createElement("th");
    let th6 = document.createElement("th");
    th1.innerHTML = "ID";
    th2.innerHTML = "Name";
    th3.innerHTML = "Email";
    th4.innerHTML = "Password";
    th5.innerHTML = "Gender";
    th6.innerHTML = "Address";
    thead.append(th1, th2, th3, th4, th5, th6);
    table.append(thead);
    data.forEach(elem => {
        let tbody = document.createElement("tbody");
        let td1 = document.createElement("td");
        let td2 = document.createElement("td");
        let td3 = document.createElement("td");
        let td4 = document.createElement("td");
        let td5 = document.createElement("td");
        let td6 = document.createElement("td");

        td1.innerHTML = elem.id;
        td2.innerHTML = elem.name;
        td3.innerHTML = elem.email;
        td4.innerHTML = elem.password;
        td5.innerHTML = elem.gender;
        td6.innerHTML = `${elem.address.city}, ${elem.address.state} - ${elem.address.pincode}`;
        tbody.append(td1, td2, td3, td4, td5, td6);
        table.append(tbody);
        container.append(table);
    });
}
function deleteCustomer() {
    let email = prompt("Enter customer email address", "abc@gmail.com");
    let url = `https://mini-project-production.up.railway.app:443/customer/remove?email=${email}`;
    fetch(url, {
        method: "DELETE",
    }).then((res) => {
        return res.json();
    }).then((res) => {
        alert(res.message);
        window.location.reload();
    }).catch((err) => {
        console.log(err);
    })
}
function deleteAll() {
    alert("All customers will be deleted");
    let email = prompt("Enter your email", "abc@gmail.com");
    let passwd = prompt("Enter your password", "John@1947");
    let url = "https://mini-project-production.up.railway.app:443/customer/delete/all";
    let data = {
        email: email,
        password: passwd
    }
    let admin = JSON.stringify(data);
    fetch("https://mini-project-production.up.railway.app:443/admin/login", {
        method: "POST",
        body: admin,
        headers: {
            "content-type": "application/json",
        }
    }).then((res) => {
        return res.json();
    }).then((res) => {
        if (res.message == null) {
            fetch(url, {
                method: "DELETE",
            }).then((res) => {
                return res.text();
            }).then((res) => {
                alert(res);
                window.location.reload();
            }).catch((err) => {
                console.log(err);
            })
        } else {
            alert(res.message);
        }
    }).catch((err) => {
        console.log(err);
    })
}
function getOrderList() {
    let url = `https://mini-project-production.up.railway.app:443/orders/get/all`;
    fetch(url).then((res) => {
        return res.json();
    }).then((res) => {
        if (res.message == null) {
            showOrders(res);
        } else {
            alert(res.message);
        }
    }).catch((err) => {
        console.log(err);
    })
}
function showOrders(data) {
    document.querySelector("#containers").innerHTML = "";
    let container = document.querySelector("#container");
    container.innerHTML = "";
    let heading = document.createElement("h2");
    heading.innerHTML = "Order List";
    heading.style.opacity = 1;
    container.append(heading);
    let table = document.createElement("table");
    let thead = document.createElement("thead");
    let th1 = document.createElement("th");
    let th2 = document.createElement("th");
    let th3 = document.createElement("th");
    let th4 = document.createElement("th");
    let th5 = document.createElement("th");
    let th6 = document.createElement("th");
    th1.innerHTML = "ID";
    th2.innerHTML = "Date";
    th3.innerHTML = "Status";
    th4.innerHTML = "Payment Mode";
    th5.innerHTML = "Quantity";
    th6.innerHTML = "Total Price";
    thead.append(th1, th2, th3, th4, th5, th6);
    table.append(thead);
    data.forEach((elem) => {
        let tbody = document.createElement("tbody");
        let td1 = document.createElement("td");
        let td2 = document.createElement("td");
        let td3 = document.createElement("td");
        let td4 = document.createElement("td");
        let td5 = document.createElement("td");
        let td6 = document.createElement("td");

        td1.innerHTML = elem.id;
        td2.innerHTML = elem.dateTime;
        td3.innerHTML = elem.status;
        td4.innerHTML = elem.payMethod;
        td5.innerHTML = elem.quantity;
        td6.innerHTML = elem.totalPrice;

        tbody.append(td1, td2, td3, td4, td5, td6);
        table.append(tbody);
        container.append(table);
    })
}
function cancelOrder() {
    event.preventDefault();
    let id = prompt("Please enter order id", "0");
    let email = prompt("Enter customer email", "john@gmail.com");
    let url = `https://mini-project-production.up.railway.app:443/orders/cancel/${email}?orderid=${id}`;

    fetch(url, {
        method: "PUT",
    })
        .then((res) => {
            return res.json();
        })
        .then((res) => {
            if (res.message == "Invalid order id..") {
                alert(res.message);
            } else if (res.message == "Customer not registered..") {
                alert(res.message);
            } else {
                alert(res.status);
            }
        })
        .catch((err) => {
            console.log(err);
        });
}
function updateStatus() {
    document.querySelector("#containers").innerHTML = "";
    let container = document.querySelector("#container");
    container.innerHTML = "";
    let heading = document.createElement("h2");
    heading.innerHTML = "Update Order Status";
    heading.style.opacity = 1;
    container.append(heading);
    let table = document.createElement("table");
    let thead = document.createElement("thead");
    let th1 = document.createElement("th");
    let th2 = document.createElement("th");
    let th3 = document.createElement("th");
    th1.innerHTML = "Order ID";
    th2.innerHTML = "Select Status";
    th3.innerHTML = "Execute";
    thead.append(th1, th2, th3);
    table.append(thead);
    let tbody = document.createElement("tbody");
    let td1 = document.createElement("td");
    let td2 = document.createElement("td");
    let td3 = document.createElement("td");
    let input = document.createElement("input");
    input.type = "number";
    let select = document.createElement("select");
    let opt1 = document.createElement("option");
    let opt2 = document.createElement("option");
    let opt3 = document.createElement("option");
    opt1.innerHTML = "COMPLETED";
    opt2.innerHTML = "PENDING";
    opt3.innerHTML = "CANCELLED";
    opt1.value = "COMPLETED";
    opt2.value = "PENDING";
    opt3.value = "CANCELLED";
    select.append(opt1, opt2, opt3);
    td2.append(select);
    td1.append(input);
    let btn = document.createElement("button");
    btn.innerHTML = "Submit";
    btn.addEventListener("click", function () {
        statusUpdate(input.value, select.value);
    })
    td3.append(btn);
    tbody.append(td1, td2, td3);
    table.append(tbody);
    container.append(table);
}
function statusUpdate(id, status) {
    let data = {
        orderid: id,
        status: status
    }
    let jsonData = JSON.stringify(data);

    let url = "https://mini-project-production.up.railway.app:443/orders/update/status";
    fetch(url, {
        method: "PUT",
        body: jsonData,
        headers: {
            "content-type": "application/json"
        }
    }).then((res) => {
        return res.json();
    }).then((res) => {
        if (res.message == null) {
            alert(`Status : ${res.status}`);
        } else {
            alert(res.message);
        }
    }).catch((err) => {
        console.log(err);
    })
}
function customerOrder() {
    let email = prompt("Enter customer email", "john@gmail.com");
    let url = `https://mini-project-production.up.railway.app:443/orders/customer?email=${email}`;
    fetch(url).then((res) => {
        return res.json();
    }).then((res) => {
        if (res.message == null) {
            showCustomerOrder(res);
        } else {
            alert(res.message);
        }
    }).catch((err) => {
        console.log(err);
    })
}
function showCustomerOrder(data) {
    let container = document.querySelector("#container");
    container.innerHTML = "";
    document.querySelector("#containers").innerHTML = "";
    let heading = document.createElement("h2");
    heading.innerHTML = "Order maked by customer";
    heading.style.opacity = 1;
    container.append(heading);
    data.forEach((elem) => {
        let main = document.createElement("div");
        let div = document.createElement("div");
        let img = document.createElement("img");
        let name = document.createElement("p");
        let rating = document.createElement("p");
        let cat = document.createElement("p");
        img.src = elem.image;
        name.innerHTML = elem.productName;
        rating.innerHTML = `Rating : ${elem.rating}`;
        cat.innerHTML = `Category : ${elem.category}`;

        let div1 = document.createElement("div");
        let price = document.createElement("p");
        let quantity = document.createElement("p");
        let id = document.createElement("p");
        let date = document.createElement("p");
        let status = document.createElement("p");
        let paymod = document.createElement("p");
        let tp=document.createElement("p");
        let totalPrice=elem.price*elem.quantity;
        price.innerHTML = `Price : ${elem.price.toLocaleString()}`;
        tp.innerHTML = `Total price : ${totalPrice.toLocaleString()}`;
        date.innerHTML = elem.dateTime;
        paymod.innerHTML = `Pay Method : ${elem.payMethod}`;
        quantity.innerHTML = `Quantity : ${elem.quantity}`;
        id.innerHTML = `Order id : ${elem.orderid}`;
        if (elem.status == "PENDING") {
            status.innerHTML = "Order status : placed.";
        } else {
            status.innerHTML = `Order status : ${elem.status}`;
        }

        div.append(img);
        div1.append(id, name, rating, cat, status, quantity, paymod, date, price,tp);
        main.append(div, div1);
        document.querySelector("#containers").append(main);
    });
}
function productOrder() {
    let id = prompt("Enter order id", "0");
    let url = `https://mini-project-production.up.railway.app:443/orders/products?orderid=${id}`;
    fetch(url).then((res) => {
        return res.json();
    }).then((res) => {
        if (res.message == null) {
            let text = "Product order";
            showProductOrder(res, text);
        } else {
            alert(res.message);
        }
    })
}
function showProductOrder(data, text) {
    let cont = document.querySelector("#container");
    cont.innerHTML = "";
    document.querySelector("#containers").innerHTML = "";
    let heading = document.createElement("h2");
    heading.innerHTML = text;
    heading.style.opacity = 1;
    cont.append(heading);
    data.forEach((elem) => {
        let main = document.createElement("div");
        let div = document.createElement("div");
        let img = document.createElement("img");
        let name = document.createElement("p");
        let rating = document.createElement("p");
        let cat = document.createElement("p");
        img.src = elem.image;
        name.innerHTML = elem.productName;
        rating.innerHTML = `Rating : ${elem.rating}`;
        cat.innerHTML = `Category : ${elem.category}`;

        let div1 = document.createElement("div");
        let price = document.createElement("p");
        let quantity = document.createElement("p");
        let id = document.createElement("p");

        price.innerHTML = `Price : ${elem.price.toLocaleString()}`;
        quantity.innerHTML = `Quantity : ${elem.quantity}`;
        id.innerHTML = `Product id : ${elem.productId}`;

        div.append(img);
        div1.append(id, name, rating, cat, quantity, price);
        main.append(div, div1);
        document.querySelector("#containers").append(main);
    })
}
function deleteOrder() {
    let id = prompt("Enter order id :", "0");
    let url = `https://mini-project-production.up.railway.app:443/orders/delete?orderid=${id}`;
    fetch(url, {
        method: "DELETE",
    }).then((res) => {
        return res.json();
    }).then((res) => {
        alert(res.message);
        window.location.reload();
    }).catch((err) => {
        console.log(err);
    })
}
function deleteAllOrders() {
    alert("All orders will be deleted");
    let email = prompt("Enter your email", "abc@gmail.com");
    let passwd = prompt("Enter your password", "John@1947");
    let url = "https://mini-project-production.up.railway.app:443/orders/delete/all";
    let data = {
        email: email,
        password: passwd
    }
    let admin = JSON.stringify(data);
    fetch("https://mini-project-production.up.railway.app:443/admin/login", {
        method: "POST",
        body: admin,
        headers: {
            "content-type": "application/json",
        }
    }).then((res) => {
        return res.json();
    }).then((res) => {
        if (res.message == null) {
            fetch(url, {
                method: "DELETE",
            }).then((res) => {
                return res.text();
            }).then((res) => {
                alert(res);
                window.location.reload();
            }).catch((err) => {
                console.log(err);
            })
        } else {
            alert(res.message);
        }
    }).catch((err) => {
        console.log(err);
    })
}
function addProduct() {
    document.querySelector("#containers").innerHTML = "";
    let container = document.querySelector("#container");
    container.innerHTML = "";
    let heading = document.createElement("h2");
    heading.innerHTML = "ADD Product";
    heading.style.opacity = 1;
    container.append(heading);

    let form = document.createElement("form");
    let input1 = document.createElement("input");
    let input2 = document.createElement("input");
    let input3 = document.createElement("input");
    let input4 = document.createElement("input");
    let input5 = document.createElement("input");
    let select = document.createElement("select");
    let opt1 = document.createElement("option");
    let opt2 = document.createElement("option");
    let opt3 = document.createElement("option");
    let opt4 = document.createElement("option");
    let opt5 = document.createElement("option");
    let opt6 = document.createElement("option");
    let opt7 = document.createElement("option");
    let opt8 = document.createElement("option");

    form.setAttribute("id", "productForm");

    input1.type = "text";
    input1.placeholder = "Enter product name";
    input2.type = "text";
    input2.placeholder = "Enter image address";
    input3.type = "text";
    input3.placeholder = "Enter rating";
    input4.type = "number";
    input4.placeholder = "Enter quantity";
    input5.type = "text";
    input5.placeholder = "Enter price";

    opt1.innerHTML = "Category : Men";
    opt1.value = "MEN";
    opt2.innerHTML = "Women";
    opt2.value = "WOMEN";
    opt3.innerHTML = "Kids";
    opt3.value = "KIDS";
    opt4.innerHTML = "Cosmetics";
    opt4.value = "COSMETICS";
    opt5.innerHTML = "Sport";
    opt5.value = "SPORT";
    opt6.innerHTML = "Stationary";
    opt6.value = "STATIONARY";
    opt7.innerHTML = "Mobile";
    opt7.value = "MOBILE";
    opt8.innerHTML = "Laptop";
    opt8.value = "LAPTOP";
    let btn = document.createElement("button");
    btn.innerHTML = "Submit";
    btn.addEventListener("click", function () {
        let data = {
            productName: input1.value,
            image: input2.value,
            rating: input3.value,
            quantity: input4.value,
            price: input5.value,
            category: select.value
        }
        let jsonData = JSON.stringify(data);
        addProductItem(jsonData);
    })
    select.append(opt1, opt2, opt3, opt4, opt5, opt6, opt7, opt8);
    form.append(input1, input2, input3, input4, input5, select, btn);
    container.append(form);
}
function addProductItem(data) {
    event.preventDefault();
    fetch("https://mini-project-production.up.railway.app:443/product/add", {
        method: "POST",
        body: data,
        headers: {
            "content-type": "application/json"
        }
    }).then((res) => {
        return res.json();
    }).then((res) => {
        if (res.message == null) {
            alert("Poduct added successfully!");
        } else {
            alert(res.message);
        }
    }).catch((err) => {
        console.log(err);
    })
}
function getAllProducts() {
    fetch("https://mini-project-production.up.railway.app:443/product/all")
        .then((res) => {
            return res.json();
        }).then((res) => {
            if (res.message == null) {
                showAllProducts(res);
            } else {
                alert(res.message);
            }
        })
        .catch((err) => {
            console.log(err);
        })
}
function showAllProducts(data) {
    document.querySelector("#containers").innerHTML = "";
    let container = document.querySelector("#container");
    container.innerHTML = "";
    let heading = document.createElement("h2");
    heading.innerHTML = "Product List";
    heading.style.opacity = 1;
    container.append(heading);
    let table = document.createElement("table");
    let thead = document.createElement("thead");
    let th1 = document.createElement("th");
    let th2 = document.createElement("th");
    let th3 = document.createElement("th");
    let th4 = document.createElement("th");
    let th5 = document.createElement("th");
    let th6 = document.createElement("th");
    let th7 = document.createElement("th");
    th1.innerHTML = "ID";
    th2.innerHTML = "Product Name";
    th3.innerHTML = "Image Link";
    th4.innerHTML = "Quantity";
    th5.innerHTML = "Price";
    th6.innerHTML = "Rating";
    th7.innerHTML = "Category";
    thead.append(th1, th2, th3, th4, th5, th6, th7);
    table.append(thead);
    data.forEach(elem => {
        let tbody = document.createElement("tbody");
        let td1 = document.createElement("td");
        let td2 = document.createElement("td");
        let td3 = document.createElement("td");
        let td4 = document.createElement("td");
        let td5 = document.createElement("td");
        let td6 = document.createElement("td");
        let td7 = document.createElement("td");

        td1.innerHTML = elem.productId;
        td2.innerHTML = elem.productName;
        td3.innerHTML = elem.image;
        td4.innerHTML = elem.quantity;
        td5.innerHTML = elem.price;
        td6.innerHTML = elem.rating;
        td7.innerHTML = elem.category;
        tbody.append(td1, td2, td3, td4, td5, td6, td7);
        table.append(tbody);
        container.append(table);
    });
}
function updateProduct() {
    document.querySelector("#containers").innerHTML = "";
    let container = document.querySelector("#container");
    container.innerHTML = "";
    let heading = document.createElement("h2");
    heading.innerHTML = "Update Product";
    heading.style.opacity = 1;
    container.append(heading);

    let form = document.createElement("form");
    let input = document.createElement("input");
    let input1 = document.createElement("input");
    let input2 = document.createElement("input");
    let input3 = document.createElement("input");
    let input4 = document.createElement("input");
    let input5 = document.createElement("input");
    let select = document.createElement("select");
    let opt1 = document.createElement("option");
    let opt2 = document.createElement("option");
    let opt3 = document.createElement("option");
    let opt4 = document.createElement("option");
    let opt5 = document.createElement("option");
    let opt6 = document.createElement("option");
    let opt7 = document.createElement("option");
    let opt8 = document.createElement("option");

    form.setAttribute("id", "productForm");

    input.type = "number";
    input.placeholder = "Enter product id";
    input1.type = "text";
    input1.placeholder = "Enter product name";
    input2.type = "text";
    input2.placeholder = "Enter image address";
    input3.type = "text";
    input3.placeholder = "Enter rating";
    input4.type = "number";
    input4.placeholder = "Enter quantity";
    input5.type = "text";
    input5.placeholder = "Enter price";

    opt1.innerHTML = "Category : Men";
    opt1.value = "MEN";
    opt2.innerHTML = "Women";
    opt2.value = "WOMEN";
    opt3.innerHTML = "Kids";
    opt3.value = "KIDS";
    opt4.innerHTML = "Cosmetics";
    opt4.value = "COSMETICS";
    opt5.innerHTML = "Sport";
    opt5.value = "SPORT";
    opt6.innerHTML = "Stationary";
    opt6.value = "STATIONARY";
    opt7.innerHTML = "Mobile";
    opt7.value = "MOBILE";
    opt8.innerHTML = "Laptop";
    opt8.value = "LAPTOP";
    let btn = document.createElement("button");
    btn.innerHTML = "Submit";
    btn.addEventListener("click", function () {
        let data = {
            productId: input.value,
            productName: input1.value,
            image: input2.value,
            rating: input3.value,
            quantity: input4.value,
            price: input5.value,
            category: select.value
        }
        let jsonData = JSON.stringify(data);
        updateProductItem(jsonData);
    })
    select.append(opt1, opt2, opt3, opt4, opt5, opt6, opt7, opt8);
    form.append(input, input1, input2, input3, input4, input5, select, btn);
    container.append(form);
}
function updateProductItem(data) {
    event.preventDefault();
    fetch("https://mini-project-production.up.railway.app:443/product/update", {
        method: "PUT",
        body: data,
        headers: {
            "content-type": "application/json"
        }
    }).then((res) => {
        return res.json();
    }).then((res) => {
        if (res.message == null) {
            alert("Product updated successfully!");
        } else {
            alert(res.message);
        }
    }).catch((err) => {
        console.log(err);
    })
}
function getProductById() {
    let id = prompt("Please enter product id", "0");
    fetch(`https://mini-project-production.up.railway.app:443/product/get?id=${id}`)
        .then((res) => {
            return res.json();
        }).then((res) => {
            if (res.message == null) {
                showProduct(res);
            } else {
                alert(res.message);
            }
        }).catch((err) => {
            console.log(err);
        })
}
function showProduct(elem) {
    let cont = document.querySelector("#container");
    cont.innerHTML = "";
    document.querySelector("#containers").innerHTML = "";
    let heading = document.createElement("h2");
    heading.innerHTML = "Product";
    heading.style.opacity = 1;
    cont.append(heading);
    let main = document.createElement("div");
    let div = document.createElement("div");
    let img = document.createElement("img");
    let name = document.createElement("p");
    let rating = document.createElement("p");
    let cat = document.createElement("p");
    img.src = elem.image;
    name.innerHTML = elem.productName;
    rating.innerHTML = `Rating : ${elem.rating}`;
    cat.innerHTML = `Category : ${elem.category}`;

    let div1 = document.createElement("div");
    let price = document.createElement("p");
    let quantity = document.createElement("p");
    let id = document.createElement("p");

    price.innerHTML = `Price : ${elem.price.toLocaleString()}`;
    quantity.innerHTML = `Quantity : ${elem.quantity}`;
    id.innerHTML = `Product id : ${elem.productId}`;

    div.append(img);
    div1.append(id, name, rating, cat, quantity, price);
    main.append(div, div1);
    document.querySelector("#containers").append(main);
}
function getProductByCategory() {
    let cat = prompt("Enter product category", "Men");
    let category = cat.toUpperCase();
    fetch(`https://mini-project-production.up.railway.app:443/product/category?cat=${category}`)
        .then((res) => {
            return res.json();
        }).then((res) => {
            if (res.message == null) {
                let text = `Category : ${category}`;
                showProductOrder(res, text);
            } else {
                alert(res.message);
            }
        }).catch((err) => {
            console.log(err);
        })
}
function deleteProduct() {
    let id = prompt("Enter product id", "0");
    fetch(`https://mini-project-production.up.railway.app:443/product/remove?id=${id}`, {
        method: "DELETE"
    }).then((res) => {
        return res.json();
    }).then((res) => {
        if (res.message == null) {
            alert("Product deleted successfully!");
        } else {
            alert(res.message);
        }
    }).catch((err) => {
        console.log(err);
    })
}
function getAccountById() {
    let id = prompt("Enter account no.", "0");
    fetch(`https://mini-project-production.up.railway.app:443/account/find/${id}`)
        .then((res) => {
            return res.json();
        }).then((res) => {
            if (res.message == null) {
                displayAccount(res);
            } else {
                alert(res.message);
            }
        }).catch((err) => {
            console.log(err);
        })
}
function displayAccount(elem) {
    document.querySelector("#containers").innerHTML = "";
    let container = document.querySelector("#container");
    container.innerHTML = "";
    let heading = document.createElement("h2");
    heading.innerHTML = "Wallet Account";
    heading.style.opacity = 1;
    container.append(heading);
    let table = document.createElement("table");
    let thead = document.createElement("thead");
    let th1 = document.createElement("th");
    let th2 = document.createElement("th");
    let th3 = document.createElement("th");
    let th4 = document.createElement("th");
    let th5 = document.createElement("th");

    th1.innerHTML = "Account NO.";
    th2.innerHTML = "Available Balance";
    th3.innerHTML = "IFSC";
    th4.innerHTML = "Pin number";
    th5.innerHTML = "Bank name";

    thead.append(th1, th2, th3, th4, th5);
    table.append(thead);
    let tbody = document.createElement("tbody");
    let td1 = document.createElement("td");
    let td2 = document.createElement("td");
    let td3 = document.createElement("td");
    let td4 = document.createElement("td");
    let td5 = document.createElement("td");

    td1.innerHTML = elem.accountNo;
    td2.innerHTML = elem.balance;
    td3.innerHTML = elem.ifsc;
    td4.innerHTML = elem.pin_number;
    td5.innerHTML = elem.bank_Name;

    tbody.append(td1, td2, td3, td4, td5);
    table.append(tbody);
    container.append(table);
}
function getAllAccount() {
    fetch("https://mini-project-production.up.railway.app:443/account/get/all")
        .then((res) => {
            return res.json();
        }).then((res) => {
            if (res.message == null) {
                displayAllAccount(res);
            } else {
                alert(res.message);
            }
        }).catch((err) => {
            console.log(err);
        })
}
function displayAllAccount(data) {
    document.querySelector("#containers").innerHTML = "";
    let container = document.querySelector("#container");
    container.innerHTML = "";
    let heading = document.createElement("h2");
    heading.innerHTML = "Wallet Account List";
    heading.style.opacity = 1;
    container.append(heading);
    let table = document.createElement("table");
    let thead = document.createElement("thead");
    let th1 = document.createElement("th");
    let th2 = document.createElement("th");
    let th3 = document.createElement("th");
    let th4 = document.createElement("th");
    let th5 = document.createElement("th");

    th1.innerHTML = "Account NO.";
    th2.innerHTML = "Available Balance";
    th3.innerHTML = "IFSC";
    th4.innerHTML = "Pin number";
    th5.innerHTML = "Bank name";

    thead.append(th1, th2, th3, th4, th5);
    table.append(thead);

    data.forEach((elem) => {
        let tbody = document.createElement("tbody");
        let td1 = document.createElement("td");
        let td2 = document.createElement("td");
        let td3 = document.createElement("td");
        let td4 = document.createElement("td");
        let td5 = document.createElement("td");

        td1.innerHTML = elem.accountNo;
        td2.innerHTML = elem.balance;
        td3.innerHTML = elem.ifsc;
        td4.innerHTML = elem.pin_number;
        td5.innerHTML = elem.bank_Name;

        tbody.append(td1, td2, td3, td4, td5);
        table.append(tbody);
        container.append(table);
    })
}
function deleteAccount() {
    let id = prompt("Enter account no.", "0");
    fetch(`https://mini-project-production.up.railway.app:443/account/delete?id=${id}`, {
        method: "DELETE"
    }).then((res) => {
        return res.json();
    }).then((res) => {
        if (res.message == null) {
            alert("Account deleted successfully !");
        } else {
            alert(res.message);
        }
    }).catch((err) => {
        console.log(err);
    })
}
function registerAdmin(data) {
    event.preventDefault();

    fetch("https://mini-project-production.up.railway.app:443/admin/register", {
        method: "POST",
        body: data,
        headers: {
            "content-type": "application/json"
        }
    }).then((res) => {
        return res.json();
    }).then((res) => {
        if (res.message == null) {
            alert("Admin register successfully !");
        } else {
            alert(res.message);
        }
    }).catch((err) => {
        console.log(err);
    })
}
function registerAdminForm() {
    document.querySelector("#containers").innerHTML = "";
    let container = document.querySelector("#container");
    container.innerHTML = "";
    let heading = document.createElement("h2");
    heading.innerHTML = "Register Admin";
    heading.style.opacity = 1;
    container.append(heading);

    let form = document.createElement("form");
    let input1 = document.createElement("input");
    let input2 = document.createElement("input");
    let input3 = document.createElement("input");
    form.setAttribute("id", "productForm");

    input1.type = "text";
    input1.placeholder = "Enter name";
    input2.type = "email";
    input2.placeholder = "Enter email";
    input3.type = "password";
    input3.placeholder = "Enter password";

    let btn = document.createElement("button");
    btn.innerHTML = "Register";
    btn.addEventListener("click", function () {
        let data = {
            name: input1.value,
            email: input2.value,
            password: input3.value
        }
        let jsonData = JSON.stringify(data);
        registerAdmin(jsonData);
    })
    form.append(input1, input2, input3, btn);
    container.append(form);
}
function updateAdmin() {
    document.querySelector("#containers").innerHTML = "";
    let container = document.querySelector("#container");
    container.innerHTML = "";
    let heading = document.createElement("h2");
    heading.innerHTML = "Update Admin";
    heading.style.opacity = 1;
    container.append(heading);

    let form = document.createElement("form");
    let input = document.createElement("input");
    let input1 = document.createElement("input");
    let input2 = document.createElement("input");
    let input3 = document.createElement("input");
    form.setAttribute("id", "productForm");

    input.type = "number";
    input.placeholder = "Enter id";
    input1.type = "text";
    input1.placeholder = "Enter name";
    input2.type = "email";
    input2.placeholder = "Enter email";
    input3.type = "password";
    input3.placeholder = "Enter password";

    let btn = document.createElement("button");
    btn.innerHTML = "Update Admin";
    btn.addEventListener("click", function () {
        let data = {
            id: input.value,
            name: input1.value,
            email: input2.value,
            password: input3.value
        }
        let jsonData = JSON.stringify(data);
        updateAdminHandler(jsonData);
    })
    form.append(input, input1, input2, input3, btn);
    container.append(form);
}
function updateAdminHandler(data) {
    event.preventDefault();
    fetch("https://mini-project-production.up.railway.app:443/admin/update", {
        method: "PUT",
        body: data,
        headers: {
            "content-type": "application/json"
        }
    }).then((res) => {
        return res.json();
    }).then((res) => {
        if (res.message == null) {
            alert("Admin updated successfully !");
        } else {
            alert(res.message);
        }
    }).catch((err) => {
        console.log(err);
    })
}
function getAllAdmin() {
    fetch("https://mini-project-production.up.railway.app:443/admin/all").then((res) => {
        return res.json();
    }).then((res) => {
        if (res.message == null) {
            displayAllAdmin(res);
        } else {
            alert(res.message);
        }
    }).catch((err) => {
        console.log(err);
    })
}
function displayAllAdmin(data) {
    document.querySelector("#containers").innerHTML = "";
    let container = document.querySelector("#container");
    container.innerHTML = "";
    let heading = document.createElement("h2");
    heading.innerHTML = "Admin List";
    heading.style.opacity = 1;
    container.append(heading);
    let table = document.createElement("table");
    let thead = document.createElement("thead");
    let th1 = document.createElement("th");
    let th2 = document.createElement("th");
    let th3 = document.createElement("th");
    let th4 = document.createElement("th");

    th1.innerHTML = "ID";
    th2.innerHTML = "Name";
    th3.innerHTML = "Email";
    th4.innerHTML = "Password";

    thead.append(th1, th2, th3, th4);
    table.append(thead);

    data.forEach((elem) => {
        let tbody = document.createElement("tbody");
        let td1 = document.createElement("td");
        let td2 = document.createElement("td");
        let td3 = document.createElement("td");
        let td4 = document.createElement("td");

        td1.innerHTML = elem.id;
        td2.innerHTML = elem.name;
        td3.innerHTML = elem.email;
        td4.innerHTML = elem.password;

        tbody.append(td1, td2, td3, td4);
        table.append(tbody);
        container.append(table);
    })
}
function deleteAdmin() {
    let id = prompt("Enter admin email id", "john@gmail.com");
    fetch(`https://mini-project-production.up.railway.app:443/admin/delete?email=${id}`, {
        method: "DELETE"
    }).then((res) => {
        return res.json();
    }).then((res) => {
        if (res.message == null) {
            alert("Admin deleted successfully !");
        } else {
            alert(res.message);
        }
    }).catch((err) => {
        console.log(err);
    })
}
function mySearchFunction() {
    let query = document.querySelector("#query").value;
    let url = `https://mini-project-production.up.railway.app:443/product/search?query=${query}`;
    localStorage.setItem("usrquery", query);
    fetch(url)
        .then((res) => {
            return res.json();
        })
        .then((res) => {
            localStorage.setItem("query", JSON.stringify(res));
        })
        .then((err) => {
            console.log(err);
        });
}
document.querySelector("#query").addEventListener("keypress", showSearchResult);

function showSearchResult(e) {
    if (e.keyCode == 13) {
        window.location.href = "search.html";
    }
}
function searchPage() {
    window.location.href = "search.html";
}