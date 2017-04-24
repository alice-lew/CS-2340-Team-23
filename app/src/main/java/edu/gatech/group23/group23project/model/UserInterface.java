package edu.gatech.group23.group23project.model;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Mbrune on 4/24/2017.
 */

public interface UserInterface {

    /**
     * Gets the user's type for another class
     * @return the user's type
     */
    public UserType getUserType();

    /**
     * Gets the user's name for another class
     * @return the user's name
     */
    public String getName();

    /**
     * Gets the user's email for another class
     * @return the user's email
     */
    public CharSequence getEmail();

    /**
     * Changes the user's email
     * @param email the String that will be set as the user's new email
     */
    public void setEmail(String email);

    /**
     * Gets the user's home address for another class
     * @return the user's home address
     */
    public CharSequence getHome();

    /**
     * Changes the user's home address
     * @param home String that will be set as the user's new this.home
     */
    public void setHome(String home);

    /**
     * Gets the user's title for another class
     * @return the user's title
     */
    public CharSequence getTitle();

    /**
     * Changes the user's title
     * @param aTitle String that will be set as the user's new title
     */
    public void setTitle(String aTitle);

    /**
     * Gets the user's username and password for another class
     * @return a String consisting of the user's username and password
     */
    public String getCredentials();

    /**
     * Gets the user's username and password for another class
     * @return a String consisting of the user's username and password
     */
    public String getUsername();

    public void ban();

    public void unban();

    public boolean getIsBanned();

    public void incrementLoginAttempts();

    public void resetLoginAttempts();

}
