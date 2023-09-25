let images = [
  { img_link: "Images/img.jpg" },
  { img_link: "Images/img1.jpg" },
  { img_link: "Images/img2.jpg" },
  { img_link: "Images/img3.jpg" },
  { img_link: "Images/img4.jpg" },
  { img_link: "Images/img5.jpg" },
  { img_link: "Images/img6.jpg" },
  { img_link: "Images/img7.jpg" },
  { img_link: "Images/img8.jpg" },
  { img_link: "Images/bg.jpg" },
];

function slideShow() {
  let i = 0;
  let container = document.querySelector("#slideshow");
  setInterval(function () {
    if (i == images.length) {
      i = 0;
    }
    container.innerHTML = "";
    let img = document.createElement("img");
    img.src = images[i].img_link;
    container.append(img);
    i++;
  }, 7000);
}
window.onload = slideShow();

function trendingWomen() {
  let url =
    "https://e-commerce-4pkg.onrender.com:443/product/category?cat=WOMEN";
  fetch(url)
    .then((res) => {
      return res.json();
    })
    .then((res) => {
      showWomenTrend(res);
    })
    .catch((err) => {
      console.log(err);
    });
}
window.onload = trendingWomen();
function trendingMen() {
  let url =
    "https://e-commerce-4pkg.onrender.com:443/product/category?cat=MEN";
  fetch(url)
    .then((res) => {
      return res.json();
    })
    .then((res) => {
      showMenTrend(res);
    })
    .catch((err) => {
      console.log(err);
    });
}
window.onload = trendingMen();

function trendingKids() {
  let url =
    "https://e-commerce-4pkg.onrender.com:443/product/category?cat=KIDS";
  fetch(url)
    .then((res) => {
      return res.json();
    })
    .then((res) => {
      showKidsTrend(res);
    })
    .catch((err) => {
      console.log(err);
    });
}
window.onload = trendingKids();

function trendingSport() {
  let url =
    "https://e-commerce-4pkg.onrender.com:443/product/category?cat=SPORT";
  fetch(url)
    .then((res) => {
      return res.json();
    })
    .then((res) => {
      showSportTrend(res);
    })
    .catch((err) => {
      console.log(err);
    });
}
window.onload = trendingSport();
function trendingBooks() {
  let url =
    "https://e-commerce-4pkg.onrender.com:443/product/category?cat=STATIONARY";
  fetch(url)
    .then((res) => {
      return res.json();
    })
    .then((res) => {
      showStationaryTrend(res);
    })
    .catch((err) => {
      console.log(err);
    });
}
window.onload = trendingBooks();
function trendingMobiles() {
  let url =
    "https://e-commerce-4pkg.onrender.com:443/product/category?cat=MOBILE";
  fetch(url)
    .then((res) => {
      return res.json();
    })
    .then((res) => {
      showMobileTrend(res);
    })
    .catch((err) => {
      console.log(err);
    });
}
window.onload = trendingMobiles();
function trendingLaptops() {
  let url =
    "https://e-commerce-4pkg.onrender.com:443/product/category?cat=LAPTOP";
  fetch(url)
    .then((res) => {
      return res.json();
    })
    .then((res) => {
      showLaptopsTrend(res);
    })
    .catch((err) => {
      console.log(err);
    });
}
window.onload = trendingLaptops();
function trendingCosmetics() {
  let url =
    "https://e-commerce-4pkg.onrender.com:443/product/category?cat=COSMETICS";
  fetch(url)
    .then((res) => {
      return res.json();
    })
    .then((res) => {
      showCosmeticsTrend(res);
    })
    .catch((err) => {
      console.log(err);
    });
}
window.onload = trendingCosmetics();

function showWomenTrend(data) {
  let container = document.createElement("div");
  for (let i = 0; i < 4; i++) {
    let img = document.createElement("img");
    let div = document.createElement("div");
    let name = document.createElement("h3");
    let rating = document.createElement("p");
    name.innerText = `${data[i].productName.substring(0, 24)}...`;
    rating.innerText = `Rating : ${data[i].rating}`;
    img.src = data[i].image;
    div.append(img, name, rating);
    container.append(div);
    div.addEventListener("click", function () {
      userClicked(data[i]);
    });
  }
  document.querySelector("#women").append(container);
}
function showMenTrend(data) {
  let container = document.createElement("div");
  for (let i = 0; i < 4; i++) {
    let img = document.createElement("img");
    let div = document.createElement("div");
    let name = document.createElement("h3");
    let rating = document.createElement("p");
    name.innerText = `${data[i].productName.substring(0, 24)}...`;
    rating.innerText = `Rating : ${data[i].rating}`;
    img.src = data[i].image;
    div.append(img, name, rating);
    container.append(div);
    div.addEventListener("click", function () {
      userClicked(data[i]);
    });
  }
  document.querySelector("#men").append(container);
}
function showSportTrend(data) {
  let container = document.createElement("div");
  for (let i = 0; i < 4; i++) {
    let img = document.createElement("img");
    let div = document.createElement("div");
    let name = document.createElement("h3");
    let rating = document.createElement("p");
    name.innerText = `${data[i].productName.substring(0, 24)}...`;
    rating.innerText = `Rating : ${data[i].rating}`;
    img.src = data[i].image;
    div.append(img, name, rating);
    container.append(div);
    div.addEventListener("click", function () {
      userClicked(data[i]);
    });
  }
  document.querySelector("#sports").append(container);
}
function showKidsTrend(data) {
  let container = document.createElement("div");
  for (let i = 0; i < 4; i++) {
    let img = document.createElement("img");
    let div = document.createElement("div");
    let name = document.createElement("h3");
    let rating = document.createElement("p");
    name.innerText = `${data[i].productName.substring(0, 24)}...`;
    rating.innerText = `Rating : ${data[i].rating}`;
    img.src = data[i].image;
    div.append(img, name, rating);
    container.append(div);
    div.addEventListener("click", function () {
      userClicked(data[i]);
    });
  }
  document.querySelector("#kids").append(container);
}
function showCosmeticsTrend(data) {
  let container = document.createElement("div");
  for (let i = 0; i < 4; i++) {
    let img = document.createElement("img");
    let div = document.createElement("div");
    let name = document.createElement("h3");
    let rating = document.createElement("p");
    name.innerText = `${data[i].productName.substring(0, 24)}...`;
    rating.innerText = `Rating : ${data[i].rating}`;
    img.src = data[i].image;
    div.append(img, name, rating);
    container.append(div);
    div.addEventListener("click", function () {
      userClicked(data[i]);
    });
  }
  document.querySelector("#cosmetics").append(container);
}
function showMobileTrend(data) {
  let container = document.createElement("div");
  for (let i = 0; i < 4; i++) {
    let img = document.createElement("img");
    let div = document.createElement("div");
    let name = document.createElement("h3");
    let rating = document.createElement("p");
    name.innerText = `${data[i].productName.substring(0, 24)}...`;
    rating.innerText = `Rating : ${data[i].rating}`;
    img.src = data[i].image;
    div.append(img, name, rating);
    container.append(div);
    div.addEventListener("click", function () {
      userClicked(data[i]);
    });
  }
  document.querySelector("#mobiles").append(container);
}
function showLaptopsTrend(data) {
  let container = document.createElement("div");
  for (let i = 0; i < 4; i++) {
    let img = document.createElement("img");
    let div = document.createElement("div");
    let name = document.createElement("h3");
    let rating = document.createElement("p");
    name.innerText = `${data[i].productName.substring(0, 24)}...`;
    rating.innerText = `Rating : ${data[i].rating}`;
    img.src = data[i].image;
    div.append(img, name, rating);
    container.append(div);
    div.addEventListener("click", function () {
      userClicked(data[i]);
    });
  }
  document.querySelector("#laptops").append(container);
}
function showStationaryTrend(data) {
  let container = document.createElement("div");
  for (let i = 0; i < 4; i++) {
    let img = document.createElement("img");
    let div = document.createElement("div");
    let name = document.createElement("h3");
    let rating = document.createElement("p");
    name.innerText = `${data[i].productName.substring(0, 24)}...`;
    rating.innerText = `Rating : ${data[i].rating}`;
    img.src = data[i].image;
    div.append(img, name, rating);
    container.append(div);
    div.addEventListener("click", function () {
      userClicked(data[i]);
    });
  }
  document.querySelector("#stationary").append(container);
}

function userClicked(data) {
  localStorage.setItem("clicked", JSON.stringify(data));
  window.location.href = "description.html";
}

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
document.querySelector("#query2").addEventListener("keypress", showSearchResult);
function showSearchResult(e) {
  if (e.keyCode == 13) {
    window.location.href = "search.html";
  }
}
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

function categories() {
  let data = event.target.innerHTML;
  let pagecategory = data.toUpperCase();
  localStorage.setItem("pagecategory", pagecategory);
  window.location.href = "category.html";
}

function searchPage() {
  window.location.href = "search.html";
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