'use strict';

///access signup button

let signUpButton = document.getElementById("sign-up");

signUpButton.addEventListener('click',()=>{
    event.preventDefault();
    window.location.replace("register.html");
});


let loginButton = document.getElementById("login")

loginButton.addEventListener('click', async(event)=>{
    event.preventDefault();

    let uname = document.getElementById("user-sign-in").value;
    let pword = document.getElementById("password-sign-in").value;

    let loginInfo = {
        username:  uname,
        password: pword
    }

    let json = JSON.stringify(loginInfo);

    
    try{

        const raw_response = await fetch('http://localhost:8080/login',
        {method:"POST",
        body: json
    })
        if(!raw_response.ok){
            throw new Error(raw_response.status)
        }

        raw_response.json().then( (data)=>{

            let loggedInUser = JSON.stringify(data)

            localStorage.setItem("currentUser", loggedInUser);

            // console.log(data)
        })

        setTimeout(()=>{
            window.location.replace("home.html")
        }, 500)

    }catch(error){
        console.log(error);
    }
})