package org.example.services;

import org.example.models.User;
import org.example.repos.UserRepo;

public class UserService {


    private UserRepo userRepo;


    public UserService(){
        this.userRepo = new UserRepo();
    }

    public UserService(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    //create user
    public int createUser(User user){
        return userRepo.create(user);
    }

    //get user by ID
    public User getUserById(int id){
        return userRepo.getById(id);
    }

    public User getUserByUsername(String username){
        return userRepo.getUserByUsername(username);
    }

    public User getUserByUser(User user){

        String username = user.getUsername();
        return userRepo.getUserByUsername(username);
    }

    //update user
    public User updateUser(User user){
        return userRepo.update(user);
    }

//
//    public User getLoggedUser() {
//        return loggedUser;
//    }


    public User loginUser(User user){
        return userRepo.loginUser(user);
    }

}
