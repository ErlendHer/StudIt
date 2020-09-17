package studit.core;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

public class LoginManager{

    private  static HashMap<String, String>  DB = new HashMap<String, String>();

    public LoginManager(){
        initialize();
    }

    /*
    * Sets a database with username and passwords
    */
    public static void initialize(){
        DB.put("user", "password");
        DB.put("admin", "password1");
    }

    /*
     * Checks if the username and password exists together in the database
     * @param username - The username to be checked
     * @param password - The password to be checked
     * @return true if the username matches the password in the database, else false
     */
    public static boolean match(String username, String password){
        if (DB.containsKey(username)){
            return DB.get(username).equals(password);
        }
        return false;
    }

    /*
    * Writes a string (for example a password) to a simple text file
    * @param string - The string that will be written to the text file
    */
    public static void writeToFile(String string, String filename) throws FileNotFoundException {
        PrintWriter outFile = new PrintWriter(filename);
        outFile.println(string);
        outFile.close();
    }
    /*
     * Reads a simple line from a text file
     * @return the string from the text file
     */
    public static String readFromFile(String filename) throws FileNotFoundException {
        Scanner in = new Scanner(new FileReader(filename));
        String string = in.nextLine();
        in.close();
        return string;
    }

    /*
    * Testing
    */
    public static void main(String[] args) {
        try {
            String password = readFromFile("keys.txt");
            System.out.println(password);
        } catch (Exception e){ System.out.println("Failure..."); }
    }



    

    

}