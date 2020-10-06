package studit.core;

public class LoginManager {

    public LoginManager() {
    }

    /**
     * Sets a database with username and passwords
     */
    public static void initialize() {
        UserManager.startStuff();
    }

    /*
     * Checks if the username and password exists together in the database
     * 
     * @param username - The username to be checked
     * 
     * @param password - The password to be checked
     * 
     * @return true if the username matches the password in the database, else false
     */
    public static boolean match(String username, String password) {
        return UserManager.containsUser(username, password);
    }
}
