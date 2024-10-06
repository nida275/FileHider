package dao;

import db.MyConnection;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class userDAO {
    public static boolean ifExists(String email) throws SQLException {
        Connection connection= MyConnection.getConnection(); // Establishes a connection to the database.

        PreparedStatement ps= connection.prepareStatement("select email from users");// SQL query to fetch all emails from the 'users' table.

        ResultSet rs= ps.executeQuery(); // Executes the SQL query and returns the result set.

        while(rs.next()){
            String e= rs.getString(1); // Retrieves the first column (email) from the current row.
            if(e.equals(email)){
                 return true;
            }
        }
        return false;
    }

    public static int saveUser(User user) throws SQLException{ //Here User refers to class User in model package , acting as parameter type(like int, string) for its parameter "user". "user" is object of "User" class. //This methods inserts a new user into the user table using their name and email.
        Connection connection= MyConnection.getConnection();
        PreparedStatement ps=connection.prepareStatement("insert into users values(default,?,?)"); // there are rows in table users : id, name ,email. default: This is likely used for an auto-incrementing primary key, allowing the database to assign a unique ID.
        ps.setString(1,user.getName()); // Sets the first placeholder (?) to the user's name. //getName is function of class User in model package. We are able to access it bcz of "user" object.
        ps.setString(2, user.getEmail()); // Sets the second placeholder (?) to the user's email.
        return ps.executeUpdate(); // Executes the SQL insert query and returns the number of rows affected. if 0 then not inserted, 1 if inserted.

    }
}
