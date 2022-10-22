package org.example.controllers;

import io.javalin.http.Handler;
import org.example.models.CurrentUser;
import org.example.models.User;
import org.example.services.UserService;

public class UserController {
    private UserService userService;


    public UserController(){
        userService = new UserService();
    }

    public UserController(UserService userService){
        this.userService = userService;
    }




    public Handler createNewUser = context -> {
        //grab the object from the request body (postman)
        //send the incoming user to our service, so it can
        //reach out to our repo layer and execute the request
        User user = context.bodyAsClass(User.class); // change the JSON from postman to object

        //search to see if the username assigned to the object through JSON is already taken
//        int extantID = userService.getUserIdByUsername(user).getId();
//
//        if(extantID != 0) {
//            context.result("Username already exists. Input a different username");
//        }else {

            int id = userService.createUser(user);

            if (id > 0) {
                //ie it is a valid number (represents the user primary key)
//                user.setId(id);
                user = userService.loginUser(user);

                context.result("User " + user.getUsername() + " successfully created. User Id is " + user.getId());
                context.json(user).status(200);
            } else {
                //something went wrong
                context.result("User not created.").status(400);
            }
//        }
    };



    public Handler updateMood = context -> {

        User user = context.bodyAsClass(User.class);

        user = userService.updateUser(user);

        if(user != null){
            context.json(user).status(202);
        } else {
            context.result("Could not update user").status(400);
        }
    };

    public Handler getMood = context -> {
        User user = context.bodyAsClass(User.class);

        user = userService.getUserById(user.getId());


        if(user != null){

            CurrentUser.currentUser = user;
//            System.out.println(CurrentUser.currentUser.getFirstname());
            context.json(user);
        } else {
            context.result("Sorry no mood found").status(404);
        }
    };


    public Handler loginUser = context -> {



        User user = context.bodyAsClass(User.class);

        user = userService.loginUser(user);

        if(user != null){

            CurrentUser.currentUser = user;
            System.out.println(CurrentUser.currentUser.getFirstname());
            context.json(user);
//            context.result("User has been successfully logged in").status(202);
        } else {
            context.result("Sorry Wrong user name or password").status(404);
        }

    };


}
