package studit.core;

import java.util.ArrayList;

public class UserManager {

    private static ArrayList<User> users = new ArrayList<>();

    public static void addUser(User user) {
        if (!users.contains(user)) {
            users.add(user);
        }
        System.out.println("User already exists");
        //todo: Make this an error message on the ui
    }

    public static void main(String[] args) {
        System.out.println(users);
    }
}
