package studit.core;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class UserManager {

    //The current list of users. todo: make this saving to json file
    //private static ArrayList<User> users = new ArrayList<>();

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
            mapper.writeValue(Paths.get("StudIt/src/main/resources/studit/db/userDB.json").toFile(), users);
            mapper.writeValue(Paths.get("StudIt/src/main/resources/studit/db/userDB.json").toFile(), "");

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
            List<User> usersList = Arrays.asList(mapper.readValue(Paths.get("StudIt/src/main/resources/studit/db/userDB.json").toFile(), User[].class));
            return new ArrayList<User>(usersList);

        } catch (IOException e) {
            System.out.println("Error occured while fetching users from json file");
            e.printStackTrace();
            return null;
        }

    }

    public static void main(String[] args) {
        ArrayList<User> users = new ArrayList<>();
        User user = new User("1","2", "3", "4");
        User user2 = new User("a","b", "c", "d");
        users.add(user);
        users.add(user2);
        addUsersToDB(users);
        ArrayList<User> loadedUsers = getUsersFromDB();
        System.out.println(loadedUsers);
        System.out.println();
    }
}
