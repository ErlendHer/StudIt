package studit.core;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserManager {

    //The current list of users. todo: make this saving to json file
    private static ArrayList<User> users = new ArrayList<>();

    public static void initialize() {
        User user = new User("John Appleseed", "user", "user@studit.com", "password");
        User admin = new User("Mark Brownie", "admin", "admin@studit.com", "password1");
        addUser(user);
        addUser(admin);
    }

    /**
    * returns true if successfully adds user, returns false if username is taken
    */
    public static boolean addUser(User user) {
        if (!containsUser(user.getUsername())) {
            ArrayList<User> users = getUsersFromDB();
            users.add(user);

            return true;
        }
        else {
            System.out.println("Username already in use");
        } return false;
    }

    /**
     * Checks if the user exist in the database, to login
     * @param username Username of the user to be checked
     * @param password Password of the username to be checked
     * @return true if the user exists
     */
    public static boolean containsUser(String username, String password){
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                if (user.getPassword().equals(password)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks if the user exist in the database, to login
     * @param username Username of the user to be checked
     * @return true if the user exists
     */
    public static boolean containsUser(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    private static void addUsersToDB(ArrayList<User> users) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            mapper.writeValue(Paths.get("StudIt/src/main/resources/studit/db/userDB.json").toFile(), users);
        
        } catch (IOException e) {
            System.out.println("Error occured while printing user json to file");
            e.printStackTrace();
        }
        
    }

    private static ArrayList<User> getUsersFromDB() {
        ObjectMapper mapper = new ObjectMapper();

        try {
            ArrayList<User> users = mapper.readValue(Paths.get("StudIt/src/main/resources/studit/db/userDB.json").toFile()
            , new TypeReference<ArrayList<User>>(){});  

            
        } catch (IOException e) {
            System.out.println("Error occured while loading from json file");
            e.printStackTrace();
        }
        return users;
        
    }

    public static void main(String[] args) {
        //initialize();
        //System.out.println(users);
        ArrayList<User> users = new ArrayList<>();
        User user = new User("p","p", "p", "p");
        User user2 = new User("a","b", "c", "d");
        addUsersToDB(users);
        ArrayList<User> loadedUsers = getUsersFromDB();
        System.out.print(loadedUsers.get(0).getName());
    }
}
