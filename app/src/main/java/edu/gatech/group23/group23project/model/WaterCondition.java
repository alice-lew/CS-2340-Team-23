package edu.gatech.group23.group23project.model;

/**
 * An enum of all of the possible water conditions
 */
public enum WaterCondition {
    WASTE("Waste"), TREATABLE_CLEAR("Treatable-Clear"), TREATABLE_MUDDY("Treatable-Muddy"),
    POTABLE("Potable");

    private final String typeString;
    WaterCondition(String s) {
        typeString = s;
    }

    /**
     * gets the water condition as a string for another class
     * @return the water condition as a string
     */
    public String getConditionString() {
        return typeString;
    }
}
