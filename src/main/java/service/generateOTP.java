package service;

import java.util.Random;

public class generateOTP {
    public static String getOTP(){
        Random random= new Random();
        return String.format("%04d", random.nextInt(10000)); //random.nextInt(10000) generates a random integer between 0 and 9999.
        // String.format() is used to format the random integer as a string with leading zeros if necessary.
        //"%04d" is the format specifier: %: Indicates the start of the format.04: Ensures the integer will be padded with leading zeros to make it 4 digits wide.
        //d: Stands for a decimal integer.
    }
}
