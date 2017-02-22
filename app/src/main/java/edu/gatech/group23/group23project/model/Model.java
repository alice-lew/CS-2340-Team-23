package edu.gatech.group23.group23project.model;

import java.util.ArrayList;

/**
 * Created by Mbrune on 2/19/2017.
 */

public class Model {
    private static ArrayList<User> userList = new ArrayList<>();
    private static Model modelSingleton;

    private User currentUser;

    public enum UserType {
        BASIC("Basic User"), WORKER("Worker"), MANAGER("Manager"), ADMIN("Administrator");

        private String typeString;
        UserType(String s) {
            typeString = s;
        }

        public String getTypeString() {
            return typeString;
        }
    }

    private Model() {
        //private constructor for the model
        //Model.registerUser("user", "pass");
    }

    public static Model getInstance() {
        if (modelSingleton == null) {
            modelSingleton = new Model();
        }
        return modelSingleton;
    }
    public User registerUser(String name, String user, String pass, String email, String address, String title, UserType type) {
        User newUser = new User(name, user, pass, email, address, title, type);
        userList.add(newUser);
        return newUser;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User u) {
        currentUser = u;
    }

    public User getUserWithCredentials(String creds) {
        for (User u: userList) {
            if (u.getCredentials().equals(creds)) {
                return u;
            }
        }
        //the user was not found, returns null - maybe look into a better solution
        return null;
    }

    public ArrayList<User> getUserList() {
        return userList;
    }
}
