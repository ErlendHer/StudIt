package studit.core;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class UserManager {

    public static void startStuff() {
        //clearDB();
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
            addUsersToDB(users);

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
        ArrayList<User> users = getUsersFromDB();
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
        ArrayList<User> users = getUsersFromDB();
        if (users == null){
            return false;
        }
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Saves the users to json file
     * @param users - The users to be saved to the database (json file)
     */
    private static void addUsersToDB(List<User> users) {
        
        ObjectMapper mapper = new ObjectMapper();
        try {
            //Note: When running localy, we need start the path with "Studit/src/..."
            //But, when using mvn javafx:run from cd Studit, the path should start with "src/..."
            mapper.writeValue(Paths.get("src/main/resources/studit/db/userDB.json").toFile(), users);

        } catch (IOException e) {
            System.out.println("Error occured while saving users to json file");
            e.printStackTrace();
        }

    }

    /**
     * Loads the users from database (the json file)
     * @return The List of users that is fetched from the file
     */
    private static ArrayList<User> getUsersFromDB() {
        
        ObjectMapper mapper = new ObjectMapper();

        try {
            //Note: When running localy, we need start the path with "Studit/src/..."
            //But, when using mvn javafx:run from cd Studit, the path should start with "src/..."
            List<User> usersList = Arrays.asList(mapper.readValue(Paths.get(
            "src/main/resources/studit/db/userDB.json").toFile(), User[].class));
            
            return new ArrayList<User>(usersList);

        } catch (IOException e) {
            System.out.println("Error occured while fetching users from json file");
            e.printStackTrace();
            return null;
        }

    }

    /**
    * Should clear the user database, making it empty
    */
    private static void clearDB() {
        addUsersToDB(null);
    }

    public static void main2(String[] args) {
        User user = new User("x", "x", "x", "x");
        User user2 = new User("y", "y", "y", "y");
        ArrayList<User> users = new ArrayList<>();
        users.add(user);
        users.add(user2);
        addUsersToDB(users);
        ArrayList<User> users2 = getUsersFromDB();
        for (User u : users2) {
            System.out.println(u.getName() + " + " + u.getUsername());
        }
    }

    public static void main(String[] args) {
        startStuff();
    }
}
