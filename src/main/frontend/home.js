'use strict';

let currentUser = localStorage.getItem("currentUser");
currentUser = JSON.parse(currentUser);
console.log(currentUser);

let nameDisplay = document.getElementById("name-display");
nameDisplay.innerHTML = `Seasoned Graetyngs, ${currentUser.firstname} ${currentUser.lastname}!`;

let starDisplay = document.getElementById("star-sign-display");
starDisplay.innerHTML = `Thy starres signe art ${currentUser.zodiac}!`;




//create Horoscope button
let button = document.getElementById("get-horo");
button.addEventListener('click',async ()=>{

    //get the value from our user input field (ie the text box)
    let inputValue = currentUser.zodiac;
    

    //send HTTP request to the Pokemon API
        //to do  that, we have to wrap our code in a try-catch
        try{
            //this fetch method implicitly returns a promise
            const raw_response = await fetch(`http://sandipbgt.com/theastrologer/api/horoscope/${inputValue}/today`);

            if(!raw_response.ok){
                // throw new Error(raw_response.status)
                alert(`Error Status: ${raw_response.status}`)

            }

            //creates JSON data
            const json_data = await raw_response.json();

            console.log(json_data);

            addHoroToPage(json_data);

            currentUser.mood = json_data.meta.mood;
            console.log(currentUser);

            updateMood(currentUser);


        }catch(error){
            console.log(error);
        }


});


//add Horoscope to page function
function addHoroToPage(json_data){
    //this is where we will manipulate our DOM
    var input = document.getElementById("today-zodiac");
    var todayHoro = document.createElement('p');
    input.innerHTML = '';
    //note: append vs appendChild
    todayHoro.innerHTML = `Wyrd of Thys Daye: ${json_data.horoscope}`
    input.append(todayHoro);

    var br = document.createElement('br');
    input.append(br);


}


//update mood within database and update currentUser
async function updateMood(json_data){


    let inputMood = {
        id: json_data.id,
        firstname: json_data.firstname,
        lastname: json_data.lastname,
        username: json_data.username, 
        password: json_data.password,
        zodiac: json_data.zodiac,
        mood: json_data.mood
    }

    let json = JSON.stringify(inputMood);

    try{
        const raw_response = await fetch('http://localhost:8080/user',
        {
            method:"PUT",
            body: json
        })

        if(!raw_response.ok){
            throw new Error(raw_response.status)
        }

        //update currentUser
        raw_response.json().then( (data)=>{

            let loggedInUser = JSON.stringify(data)

            localStorage.setItem("currentUser", loggedInUser);

        })

    }catch(error){
        console.log(error);
    }
}





















//get mood button
let buttonMood = document.getElementById("get-mood");
buttonMood.addEventListener('click',async ()=>{

    addMoodToPage();
});







function addMoodToPage(){
    //this is where we will manipulate our DOM
    var input = document.getElementById("current-mood");
    var todayMood = document.createElement('p');
    input.innerHTML = '';
    //note: append vs appendChild
    todayMood.innerHTML = `Thyne Moode: ${currentUser.mood}`
    input.append(todayMood);

    var br = document.createElement('br');
    input.append(br);


}






let logoutButton = document.getElementById("logout-button");

logoutButton.addEventListener('click', (event)=>{
    event.preventDefault();
    currentUser = null;
    window.location.replace("index.html")
})