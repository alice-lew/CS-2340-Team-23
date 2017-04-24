package edu.gatech.group23.group23project.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * An object to represent a registered user
 *
 * Created by Noah Blume on 2/19/2017.
 */
public class User implements Serializable {
    private String email;            //the user's email address
    private String home;             //the user's home address
    private String title;                   //the user's title
    private final String username;                //the user's username
    private final String password;                //the user's password
    private final String name;                    //the user's name
    private final UserType userType;        //the type of user
    private boolean isBanned = false;

    /**
     * A list of the possible userTypes a user can be
     */
     public static final List<String> legalUserTypes = Arrays.asList(
            UserType.BASIC.getTypeString(),
            UserType.WORKER.getTypeString(),
            UserType.MANAGER.getTypeString(),
            UserType.ADMIN.getTypeString()
    );

    /**
     * Given the string associated with a userType, returns the UserTpe
     * @param strType the string associated with the userType
     * @return the userType associated with the passed in String
     */
    public static UserType getTypeFromString(String strType) {
        for (UserType t: UserType.values()) {
            String type = t.getTypeString();
            if (type.equals(strType)) {
                return t;
            }
        }
        return UserType.BASIC;
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
    public User(String aName, String user, String pass, String email, String address, String aTitle,
                UserType type) {
        name = aName;
        username = user;
        password = pass;
        this.email = email;
        this.home = address;
        title = aTitle;
        userType = type;
    }

    /**
     * Gets the user's type for another class
     * @return the user's type
     */
    public UserType getUserType() {
        return userType;
    }

    /**
     * Gets the user's name for another class
     * @return the user's name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the user's email for another class
     * @return the user's email
     */
    public CharSequence getEmail() {
        return this.email;
    }

    /**
     * Changes the user's email
     * @param email the String that will be set as the user's new email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the user's home address for another class
     * @return the user's home address
     */
    public CharSequence getHome() {
        return this.home;
    }

    /**
     * Changes the user's home address
     * @param home String that will be set as the user's new this.home
     */
    public void setHome(String home) {
        this.home = home;
    }

    /**
     * Gets the user's title for another class
     * @return the user's title
     */
    public CharSequence getTitle() {
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
        return username + " " + password;
    }

    /**
     * Gets the user's username and password for another class
     * @return a String consisting of the user's username and password
     */
    public String getUsername() { return username;}

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) { return false;}
        if (this == o) { return true;}
        if (!(o instanceof User)) {
            return false;
        }
        String username = ((User) o).getUsername();
        return (username.equals(this.username));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() { return username.hashCode();}

    public void ban() {
        isBanned = true;
    }

    public void unban() {
        isBanned = false;
    }

    public boolean getIsBanned() {
        return isBanned;
    }
}
