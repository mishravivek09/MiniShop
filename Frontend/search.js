let searchData = JSON.parse(localStorage.getItem("query")) || [];
function mySearchFunction() {
  let query = document.querySelector("#query").value;
  if(query==""){
    query=document.querySelector("#query2").value;
  }
  let url = `https://e-commerce-4pkg.onrender.com:443/product/search?query=${query}`;
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
document.querySelector("#select_sort").addEventListener("change", sortFun);
function showSearchResult(e) {
  if (e.keyCode == 13) {
    window.location.href = "search.html";
  }
}
function searchPage() {
  window.location.href = "search.html";
}
if (searchData.message == "No products exist...") {
  let div = document.querySelector("#error");
  let p = document.createElement("p");
  p.innerHTML = `Oops! No Results were Found! </br> Check Your Spelling If Incorrect. </br>
  Search The Correct Word To Get Desired Results! </br>
  Use Google To Search The Product Correct Name. </br>
  Search The Exact Name To Get Better Filter Results!`;
  div.append(p);
} else {
  getSearchData(searchData);
}
function sortFun() {
  let selected = document.querySelector("#select_sort").value;
  if (selected == "rating") {
    searchData.sort(function (a, b) {
      if (a.rating < b.rating) {
        return 1;
      }
      if (a.rating > b.rating) {
        return -1;
      }
      return 0;
    });
    getSearchData(searchData);
  } else if (selected === "high price") {
    searchData.sort(function (a, b) {
      if (a.price > b.price) {
        return -1;
      }
      if (a.price < b.price) {
        return 1;
      }
      return 0;
    });
    getSearchData(searchData);
  } else {
    searchData.sort(function (a, b) {
      if (a.price < b.price) {
        return -1;
      }
      if (a.price > b.price) {
        return 1;
      }
      return 0;
    });
    getSearchData(searchData);
  }
}
function getSearchData(searchData) {
  let container = document.querySelector("#searchContainer");
  container.innerHTML = "";
  searchData.forEach((elem) => {
    let div = document.createElement("div");
    let name = document.createElement("p");
    let cdiv = document.createElement("div");
    let rating = document.createElement("p");
    let price = document.createElement("p");
    price.setAttribute("class", "price");
    let img = document.createElement("img");
    let p = document.createElement("p");
    p.innerText = "FREE Delivery by MiniShop";
    name.innerText = elem.productName;
    img.src = elem.image;
    rating.innerText = `Rating : ${elem.rating}`;
    let rs = elem.price.toLocaleString();
    price.innerText = `INR : ${rs}`;
    cdiv.append(name, price, rating, p);
    div.append(img, cdiv);
    container.append(div);
  });
}

let usrInput = localStorage.getItem("usrquery");
document.querySelector(
  "#searchInput"
).innerHTML = `Search results for ${usrInput}`;
window.onload = getSearchData(searchData);
let loginData = JSON.parse(localStorage.getItem("session")) || [];
if (loginData.length == 0) {
  document.querySelector("#profileName").innerHTML = "Profile";
} else {
  document.querySelector("#profileName").innerHTML = `Hi, ${loginData.name}`;
}
function profileFun() {
  if (loginData.length == 0) {
    window.location.href = "signup.html";
  } else {
    window.location.href = "profile.html";
  }
}
function verifyAdmin() {
  let email = prompt("Enter your email", "abc@gmail.com");
  let passwd = prompt("Enter your password", "John@1947");

  let data = {
    email: email,
    password: passwd
  }
  let admin = JSON.stringify(data);
  fetch("https://e-commerce-4pkg.onrender.com:443/admin/login", {
    method: "POST",
    body: admin,
    headers: {
      "content-type": "application/json",
    }
  }).then((res) => {
    return res.json();
  }).then((res) => {
    if (res.message == null) {
      alert("Verified successfully..");
      window.location.href = "admin.html";
    } else {
      alert(res.message);
    }
  }).catch((err) => {
    console.log(err);
  })
}
function getCartItems() {
  fetch(`https://e-commerce-4pkg.onrender.com:443/cart/get?cartId=${loginData.cartId}`).then((res) => {
    return res.json();
  }).then((res) => {
    if (res.message == null) {
      let crt = document.querySelector("#cart_value");
      crt.append(res.length);
    } else {
      let crt = document.querySelector("#cart_value");
      crt.append("0");
    }
  }).catch((err) => {
    console.log(err);
  })
}
window.onload = getCartItems();