package edu.gatech.group23.group23project.model;

/**
 * Created by Mbrune on 2/19/2017.
 */

public class User {
    private String emailAddress;
    private String homeAddress;
    private String title;
    private String username;
    private String password;

    public User(String user, String pass) {
        username = user;
        password = pass;
    }

    public String getEmail() {
        return emailAddress;
    }

    public void setEmail(String email) {
        emailAddress = email;
    }

    public String getHome() {
        return homeAddress;
    }

    public void setHome(String home) {
        homeAddress = home;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String aTitle) {
        title = aTitle;
    }

    public String getCredentials() {
        return username + ":" + password;
    }
}
