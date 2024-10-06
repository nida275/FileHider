package views;

import dao.userDAO;
import model.User;
import service.generateOTP;
import service.sendOTPService;
import service.userService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Scanner;

public class Welcome {
    public void welcomeScreen(){
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in)); //he BufferedReader efficiently reads text from an input stream, while InputStreamReader(System.in) converts byte streams to character streams. Byte streams are taken from input.
        System.out.println("Welcome to the app");
        System.out.println("Press 1 to login");
        System.out.println("Press 2 to signup");
        System.out.println("Press 0 to exit");
        int choice=0;
        try{
            choice=Integer.parseInt(br.readLine()); // br.readline() returns a String. Integer.parseInt() converts the String input into an int
        } catch(IOException ex){
            ex.printStackTrace();
        }
        switch(choice){
            case 1-> login();
            case 2-> signUp();
            case 3-> System.exit(0); //System.exit(0) command is a way to exit the Java application cleanly without errors and frees up the resources it was using, including RAM
        }

    }

    private void login() {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter Email");
        String email= sc.nextLine();
        try{
            if(userDAO.ifExists(email)){
                String genOTP= generateOTP.getOTP();
                sendOTPService.sendOTP(email,genOTP);
                System.out.println("Enter the OTP");
                String otp= sc.nextLine();
                if(otp.equals(genOTP)){
                    new userView(email).home();
                }
                else{
                    System.out.println("Wrong OTP");
                }
            }else{
                System.out.println("User not found. Please Sign up first.");
            }
        } catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    private void signUp() {
        Scanner sc= new Scanner(System.in);

        System.out.println("Enter name");
        String name=sc.nextLine();
        System.out.println("Enter email");
        String email=sc.nextLine();

        String genOTP= generateOTP.getOTP();
        sendOTPService.sendOTP(email, genOTP);
        System.out.println("Enter the OTP sent for verification: ");
        String otp=sc.nextLine();
        if(otp.equals(genOTP)){
            User user=new User(name, email);
            int response= userService.saveUser(user); //We didnt directly called userDAO.saveUser() cz then we will have to add try catch SQL exception. In this userService that is already handled along with checking if user already exist or not.

            switch(response){
                case 0-> System.out.println("User resgistered");
                case 1-> System.out.println("User already exists");
            }
        }else{
            System.out.println("Wrong OTP");
        }

    }

}
