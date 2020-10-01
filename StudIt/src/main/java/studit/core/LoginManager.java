package studit.core;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class LoginManager{

    public LoginManager(){
    }

    /**
     * Checks if the username and password exists together in the database
     * @param username - The username to be checked
     * @param password - The password to be checked
     * @return true if the username matches the password in the database, else false
     */
    public static boolean match(String username, String password){
        return UserManager.containsUser(username, password);
    }

    /**
    * Testing
    */
    public static void main(String[] args) {

    }
    

}