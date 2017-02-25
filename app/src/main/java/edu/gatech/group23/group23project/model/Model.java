package edu.gatech.group23.group23project.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

/**
 * The model singleton object for the application
 *
 * Created by Noah Blume on 2/19/2017.
 */
public class Model {
    private static HashSet<WaterSourceReport> repSet = new HashSet<>();
    private static HashSet<User> userSet = new HashSet<>();
    private static int numRepCreated;
    private static ArrayList<User> userList = new ArrayList<>(); //stores a list of the users
    private static ArrayList<WaterSourceReport> repList = new ArrayList<>(); //stores a list of the users
    private static Model modelSingleton;    //the model singleton

    private User currentUser;    //keeps track of the user who is currently signed in

    /**
     * An enum of all of the possible user types
     */
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

    /**
     * An enum of all of the possible water types
     */
    public enum WaterType {
        BOTTLED("Bottled"), WELL("Well"), STREAM("Stream"), LAKE("Lake"), SPRING("Spring"), OTHER("Other");

        private String typeString;
        WaterType(String s) {
            typeString = s;
        }

        public String getTypeString() {
            return typeString;
        }
    }

    /**
     * An enum of all of the possible water conditions
     */
    public enum WaterCondition {
        WASTE("Waste"), TREATABLECLEAR("Treatable-Clear"), TREATABLEMUDDY("Treatable-Muddy"), POTABLE("Potable");

        private String typeString;
        WaterCondition(String s) {
            typeString = s;
        }

        public String getConditionString() {
            return typeString;
        }
    }

    /**
     * constructor for the model, sets up necessary parts of the model
     */
    private Model() {
        User defaultAdmin = new User("admin", "admin", "admin", "Admin@admin.com", "admin drive", "admin title", UserType.ADMIN);
        userList.add(defaultAdmin);
        userSet.add(defaultAdmin);
    }

    /**
     * Gets the instance of the model for another class.
     * @return an instance of the model
     */
    public static Model getInstance() {
        if (modelSingleton == null) {
            modelSingleton = new Model();
        }
        return modelSingleton;
    }

    /**
     * Registers a user and adds them to the user list
     * @param name the name of the user
     * @param user the username of the user
     * @param pass the user's password
     * @param email the user's email address
     * @param address the user's home address
     * @param title the user's title
     * @param type the user's type (basic, manager, worker, or administrator)
     * @return the new user that was created and added to the user list
     */
    public User registerUser(String name, String user, String pass, String email, String address, String title, UserType type) {
        User newUser = new User(name, user, pass, email, address, title, type);
        userList.add(newUser);
        userSet.add(newUser);
        return newUser;
    }

    /**
     * Gets the current user for another class
     * @return the current user
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * Changes the current user
     * @param u the user to be set as the new current user
     */
    public void setCurrentUser(User u) {
        currentUser = u;
    }


    /**
     * Gets the user associated with a certain username and password combo
     * @param creds the credentials of the user being looked for
     * @return the user asociated with the credentials passed in
     */
    public User getUserWithCredentials(String creds) {
        for (User u: userList) {
            if (u.getCredentials().equals(creds)) {
                return u;
            }
        }
        //the user was not found, returns null - maybe look into a better solution
        return null;
    }

    /**
     * Gets a list of all registered users
     * @return the list of registered users
     */
    public ArrayList<User> getUserList() {
        return userList;
    }
    public ArrayList<WaterSourceReport> getReportList(){ return repList;}

    public WaterSourceReport submitWaterReport(User sub, Date subDate, String lat, String lng, Model.WaterType type, Model.WaterCondition condition) {
        numRepCreated++;
        WaterSourceReport newRep = new WaterSourceReport(sub, subDate, lat, lng, type, condition, numRepCreated);
        repSet.add(newRep);
        repList.add(newRep);
        return newRep;
    }
}
