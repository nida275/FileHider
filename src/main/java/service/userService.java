package service;

import dao.userDAO;
import model.User;

import java.sql.SQLException;
// This class is created to check if user already exist or not along with handling SQL exception(instead of doing in other class)
public class userService {
    public static Integer saveUser(User user){ // responsible for saving a user to the database, but only if the user doesn't already exist
        try{
            if(userDAO.ifExists(user.getEmail())){ // Check if the user already exists by email.
                return 0; //// Return 0 if the user already exists
            } else{
                 return userDAO.saveUser(user); // Save the new user and return the number of rows affected.
            }
        }catch(SQLException ex){
              ex.printStackTrace();
        }
        return null; // If an exception occurs, return null.
    }
}

