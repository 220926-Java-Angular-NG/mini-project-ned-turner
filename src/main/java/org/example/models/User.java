package org.example.models;

public class User {

    int id;
    String firstname;
    String lastname;
    String username;
    String password;
    String zodiac;
    String mood;



    public User() {
    }

    // login constructor
    public User(String firstname, String lastname, String username, String password, String zodiac) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.zodiac = zodiac;
    }

    //all value constructor
    public User(int id, String firstname, String lastname, String username, String password, String zodiac, String mood) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.zodiac = zodiac;
        this.mood = mood;
    }

    //id constructor
    public User(int id) {
        this.id = id;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getZodiac() {
        return zodiac;
    }

    public void setZodiac(String zodiac) {
        this.zodiac = zodiac;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + username + '\'' +
                ", password='" + password + '\'' +
                ", zodiac='" + zodiac + '\'' +
                ", mood='" + mood + '\'' +
                '}';
    }
}
