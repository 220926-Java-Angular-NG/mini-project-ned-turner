package org.example;

import io.javalin.Javalin;
import org.example.controllers.UserController;
import org.example.services.UserService;

public class Driver {

    public static void main(String[] args) {

        Javalin app = Javalin.create(config -> {
            config.enableCorsForAllOrigins();
        }).start(8080);

        UserService userService = new UserService();
        UserController userController = new UserController(userService);



        app.post("/register", userController.createNewUser);
        app.post("/login", userController.loginUser);
        app.put("/user",userController.updateMood);
        app.get("/user",userController.getMood);




    }
}
