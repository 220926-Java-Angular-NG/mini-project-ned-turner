package org.example.repos;

import org.example.models.User;
import org.example.utils.CRUDDaoInterface;
import org.example.utils.ConnectionManager;

import java.sql.*;
import java.util.List;

public class UserRepo implements CRUDDaoInterface<User> {

    public Connection conn;

    public UserRepo() {
        try{
            conn = ConnectionManager.getConnection();
        }catch (SQLException sqlException){
            sqlException.getMessage();
        }
    }

    @Override
    public int create(User user) {
        String sql = "INSERT INTO users (id, firstname, lastname, username,pass_word,zodiac) VALUES (default, ?,?,?,?,?)";

        try{
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, user.getFirstname());
            pstmt.setString(2, user.getLastname());
            pstmt.setString(3, user.getUsername());
            pstmt.setString(4, user.getPassword());
            pstmt.setString(5, user.getZodiac());

            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            rs.next();

            return rs.getInt("id");

        }catch (SQLException sqlException){
            sqlException.getMessage();
        }

        return 0;
    }


    @Override
    public User getById(int id) {

        try{
            String sql = "SELECT * FROM users WHERE id = ?";

            PreparedStatement ptsmt = conn.prepareStatement(sql);
            ptsmt.setInt(1, id);

            ResultSet rs = ptsmt.executeQuery();

            //we are returning a user
            //therefore we have to create a new instance of a user from the database

            User user = new User();

            while(rs.next()){
                user.setId(rs.getInt("id"));
                user.setFirstname(rs.getString("firstname"));
                user.setLastname(rs.getString("lastname"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("pass_word"));
                user.setZodiac(rs.getString("zodiac"));
                user.setMood(rs.getString("mood"));
            }

            return user;


        }catch(SQLException sqlException){
            sqlException.getMessage();
        }
        return null;
    }

    public User getUserByUsername(String username){
        try{
            String sql = "SELECT * FROM users WHERE username = ?";

            PreparedStatement ptsmt = conn.prepareStatement(sql);
            ptsmt.setString(1, username);

            ResultSet rs = ptsmt.executeQuery();

            //return rs.getInt("id");
            User user = new User();

            while(rs.next()){
                user.setId(rs.getInt("id"));
                user.setFirstname(rs.getString("firstname"));
                user.setLastname(rs.getString("lastname"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("pass_word"));
                user.setZodiac(rs.getString("zodiac"));
                user.setMood(rs.getString("mood"));
            }

            return user;

        }catch(SQLException sqlException){
            sqlException.getMessage();
        }
        return null;
    }


    @Override
    public User update(User user) {
        try{

            String sql = "UPDATE users SET mood = ? WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, user.getMood());
            pstmt.setInt(2, user.getId());

            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();

            while(rs.next()){
                user.setId(rs.getInt("id"));
                user.setFirstname(rs.getString("firstname"));
                user.setLastname(rs.getString("lastname"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("pass_word"));
                user.setZodiac(rs.getString("zodiac"));
                user.setMood(rs.getString("mood"));
            }

            return user;

        }catch(SQLException sqlException){
            sqlException.getMessage();
        }
        return null;
    }

    public User loginUser(User user){

        try {

            String sql = "SELECT * FROM users WHERE username = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getUsername());

            ResultSet rs = pstmt.executeQuery();

            if(rs.next() && rs.getString("pass_word").equals(user.getPassword())){

                return new User(
                        rs.getInt("id"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getString("username"),
                        rs.getString("pass_word"),
                        rs.getString("zodiac"),
                        rs.getString("mood"));
            }


        }catch(Exception e){
            System.out.println("This is the userDAO: " + e.getMessage());
        }

        return null;
    }

}
