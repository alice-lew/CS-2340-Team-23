package edu.gatech.group23.group23project.model;

/**
 * An enum of all of the possible overall water conditions
 */
public enum WaterOverallCondition {
    SAFE("Safe"), TREATABLE("Treatable"), UNSAFE("Unsafe");

    private final String typeString;
    WaterOverallCondition(String s) {
        typeString = s;
    }

    /**
     * gets the overall water condition as a string for another class
     * @return the overall water condition as a string
     */
    public String getOverallConditionString() {
        return typeString;
    }
}
