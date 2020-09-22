package studit.core;

import java.util.ArrayList;

public class UserManager {

    //The current list of users. todo: make this saving to json file
    private static ArrayList<User> users = new ArrayList<>();

    public static void initialize() {
        User user = new User("John Appleseed", "user", "user@studit.com", "password");
        User admin = new User("Mark Brownie", "admin", "admin@studit.com", "password1");
        addUser(user);
        addUser(admin);
    }

    /*
    * returns true if successfully adds user, returns false if username is taken
    */
    public static boolean addUser(User user) {
        if (!containsUser(user.getUsername())) {
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
     * @return @true if the user exists
     */
    public static boolean containsUser(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        initialize();
        System.out.println(users);
    }
}
