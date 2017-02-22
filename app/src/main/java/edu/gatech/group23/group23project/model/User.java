package edu.gatech.group23.group23project.model;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Mbrune on 2/19/2017.
 */

public class User {
    private String emailAddress;
    private String homeAddress;
    private String title;
    private String username;
    private String password;
    private String name;
    private Model.UserType userType;

    public static List<String> legalUserTypes = Arrays.asList(
            Model.UserType.BASIC.getTypeString(),
            Model.UserType.WORKER.getTypeString(),
            Model.UserType.MANAGER.getTypeString(),
            Model.UserType.ADMIN.getTypeString()
    );

    public static Model.UserType getTypeFromString(String strType) {
        for (Model.UserType t: Model.UserType.values()) {
            if (t.getTypeString().equals(strType)) {
                return t;
            }
        }
        return Model.UserType.BASIC;
    }
    public User(String aName, String user, String pass, String email, String address, String aTitle, Model.UserType type) {
        name = aName;
        username = user;
        password = pass;
        emailAddress = email;
        homeAddress = address;
        title = aTitle;
        userType = type;
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

    public Model.UserType getUserType() {
        return userType;
    }

    public void setUserType(Model.UserType type) {
        userType = type;
    }

    public String getCredentials() {
        return username + ":" + password;
    }
}
