'use strict';

let currentUser = localStorage.getItem("currentUser");
currentUser = JSON.parse(currentUser);
console.log(currentUser);

let nameDisplay = document.getElementById("name-display");
nameDisplay.innerHTML = `Seasoned Graetyngs, ${currentUser.firstname} ${currentUser.lastname}!`;

let starDisplay = document.getElementById("star-sign-display");
starDisplay.innerHTML = `Thy starres signe art ${currentUser.zodiac}!`;


