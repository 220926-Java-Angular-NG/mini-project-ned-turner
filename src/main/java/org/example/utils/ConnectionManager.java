package org.example.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {

    private static Connection conn;
    private static Properties prop;

    private ConnectionManager(){}



    //create connection
    public static Connection getConnection() throws SQLException {
        //ensure that the properties are in the prop object
        if(prop == null){
            prop = loadProp();
        }

        if(conn == null || conn.isClosed()){
            conn = DriverManager.getConnection(
                    prop.getProperty("url"),
                    prop.getProperty("username"),
                    prop.getProperty("password")
            );
        }

        return conn;
    }




    //find properties file and get info
    private static Properties loadProp(){
        //create an object instance of properties
        Properties properties = new Properties();

        try{
            //connect to the file
            FileInputStream fileInputStream = new FileInputStream("src/main/resources/jdbc.properties");
            //load info into the properties object
            properties.load(fileInputStream);
        }catch (IOException ioException){
            ioException.getMessage();
        }
        return properties;
    }
}
