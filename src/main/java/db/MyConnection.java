package db;

import java.sql.Connection; //connection (session) to a specific database.
import java.sql.DriverManager;//manages JDBC drivers and establish a connection to the database.JDBC (Java Database Connectivity) driver is a software component that enables Java applications to interact with database.It acts as a translator between the Java code and the database management system (DBMS).3 steps: Establish a connection to db, Send SQL queries,Process the results.
import java.sql.SQLException;


public class MyConnection {
    public static Connection connection=null; //here Connection is variable type like int or String. connection will hold database connection.
    public static Connection getConnection(){ //here Connection is a return type
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); //Loading the Type 4 JDBC driver. com.mysql.cj.jdbc.Driver: This is the fully qualified class name of the MySQL JDBC driver, where cj stands for Connector/J, a JDBC driver provided by MySQL.

            connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/file_hider?useSSL=false", "root", "nida2003"); //Establishing connection providing the database URL, username, and password.
            //note in the url given.. the name of database stored in MYSQL and here should be same.
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Connnection made");
        return connection;
    }

    public static void closeConnection(){
        if(connection!=null){
            try{
                connection.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        MyConnection.getConnection();
    }


}
