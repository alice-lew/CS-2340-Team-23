package edu.gatech.group23.group23project.model;

import java.util.ArrayList;

/**
 * Created by Mbrune on 2/19/2017.
 */

public class Model {
    private static ArrayList<User> userList = new ArrayList<>();

    public static void registerUser(String user, String pass) {
        User newUser = new User(user, pass);
        userList.add(newUser);
    }

    public static ArrayList<User> getUserList() {
        return userList;
    }
}
