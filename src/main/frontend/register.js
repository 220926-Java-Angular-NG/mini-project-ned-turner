'use strict';

let registerButton = document.getElementById("register-button");

registerButton.addEventListener('click', async(event)=>{
    event.preventDefault();

    let fname = document.getElementById("fname-reg").value;
    let lname = document.getElementById("lname-reg").value;
    let uname = document.getElementById("uname-reg").value;
    let pword = document.getElementById("pword-reg").value;
    let zodiac = document.getElementById("zodiac-reg").value;


    let registerInfo = {
        firstname: fname,
        lastname: lname,
        username: uname, 
        password: pword,
        zodiac: zodiac
    }

    let json = JSON.stringify(registerInfo);


    try{
        const raw_response = await fetch('http://localhost:8080/register',
        {
            method:"POST",
            body: json
        })

        if(!raw_response.ok){
            throw new Error(raw_response.status)
        }

        raw_response.json().then( (data)=>{
            let loggedInUser = JSON.stringify(data)

            localStorage.setItem("currentUser", loggedInUser);

        })

        setTimeout(()=>{
            window.location.replace("home.html")
        }, 500)

    }catch(error){
        console.log(error);
    }
    
})



let backButton = document.getElementById("back-button");

backButton.addEventListener('click', (event)=>{
    event.preventDefault();
    window.location.replace("index.html")
})