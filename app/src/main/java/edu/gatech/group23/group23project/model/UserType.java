package edu.gatech.group23.group23project.model;

/**
 * An enum of all of the possible user types
 */
public enum UserType {
    BASIC("Basic User"), WORKER("Worker"), MANAGER("Manager"), ADMIN("Administrator");

    private final String typeString;
    UserType(String s) {
        typeString = s;
    }

    /**
     * gets the type of user as a string for another class
     * @return the user type as a string
     */
    public String getTypeString() {
        return typeString;
    }
}