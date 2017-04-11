package edu.gatech.group23.group23project.model;

/**
 * An enum of all of the possible water types
 */
public enum WaterType {
    BOTTLED("Bottled"), WELL("Well"), STREAM("Stream"), LAKE("Lake"), SPRING("Spring"),
    OTHER("Other");

    private final String typeString;
    WaterType(String s) {
        typeString = s;
    }

    /**
     * gets the type of water as a string for another class
     * @return the water type as a string
     */
    public String getTypeString() {
        return typeString;
    }
}
