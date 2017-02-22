package edu.gatech.group23.group23project.model;

import java.util.Arrays;
import java.util.List;

/**
 * An object to represent a registered user
 *
 * Created by Noah Blume on 2/19/2017.
 */
public class User {
    private String emailAddress;            //the user's email address
    private String homeAddress;             //the user's home address
    private String title;                   //the user's title
    private String username;                //the user's username
    private String password;                //the user's password
    private String name;                    //the user's name
    private Model.UserType userType;        //the type of user

    /**
     * A list of the possible userTypes a user can be
     */
    public static List<String> legalUserTypes = Arrays.asList(
            Model.UserType.BASIC.getTypeString(),
            Model.UserType.WORKER.getTypeString(),
            Model.UserType.MANAGER.getTypeString(),
            Model.UserType.ADMIN.getTypeString()
    );

    /**
     * Given the string associated with a userType, returns the UserTpe
     * @param strType the string associated with the userType
     * @return the userType associated with the passed in String
     */
    public static Model.UserType getTypeFromString(String strType) {
        for (Model.UserType t: Model.UserType.values()) {
            if (t.getTypeString().equals(strType)) {
                return t;
            }
        }
        return Model.UserType.BASIC;
    }

    /**
     * Constructs a user with all the information associated with a user
     * @param aName the user's name
     * @param user the user's username
     * @param pass the user's password
     * @param email the user's email address
     * @param address the user's address
     * @param aTitle the user's title
     * @param type the user's type
     */
    public User(String aName, String user, String pass, String email, String address, String aTitle, Model.UserType type) {
        name = aName;
        username = user;
        password = pass;
        emailAddress = email;
        homeAddress = address;
        title = aTitle;
        userType = type;
    }

    /**
     * Gets the user's email for another class
     * @return the user's email
     */
    public String getEmail() {
        return emailAddress;
    }

    /**
     * Changes the user's email
     * @param email the String that will be set as the user's new email
     */
    public void setEmail(String email) {
        emailAddress = email;
    }

    /**
     * Gets the user's home address for another class
     * @return the user's home address
     */
    public String getHome() {
        return homeAddress;
    }

    /**
     * Changes the user's home address
     * @param home String that will be set as the user's new homeAddress
     */
    public void setHome(String home) {
        homeAddress = home;
    }

    /**
     * Gets the user's title for another class
     * @return the user's title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Changes the user's title
     * @param aTitle String that will be set as the user's new title
     */
    public void setTitle(String aTitle) {
        title = aTitle;
    }

    /**
     * Gets the user's username and password for another class
     * @return a String consisting of the user's username and password
     */
    public String getCredentials() {
        return username + ":" + password;
    }
}
