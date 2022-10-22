package org.example.models;

public class CurrentUser {
    public static User currentUser;

    private CurrentUser(){
    }

    public  CurrentUser(User user){
        this.currentUser = user;
    }
}
