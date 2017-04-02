package edu.gatech.group23.group23project.model;

import android.icu.text.SimpleDateFormat;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Noah Blume on 2/24/2017.
 */

public class WaterSourceReport extends WaterReport {
    private SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy HH:mm:ss");
    private Model.WaterType waterType;
    private Model.WaterCondition waterCondition;

    public static List<String> legalWaterTypes = Arrays.asList(
            Model.WaterType.BOTTLED.getTypeString(),
            Model.WaterType.WELL.getTypeString(),
            Model.WaterType.STREAM.getTypeString(),
            Model.WaterType.LAKE.getTypeString(),
            Model.WaterType.SPRING.getTypeString(),
            Model.WaterType.OTHER.getTypeString()
    );

    /**
     * Given the string associated with a userType, returns the UserTpe
     * @param strType the string associated with the userType
     * @return the userType associated with the passed in String
     */
    public static Model.WaterType getTypeFromString(String strType) {
        for (Model.WaterType t: Model.WaterType.values()) {
            String type = t.getTypeString();
            if (type.equals(strType)) {
                return t;
            }
        }
        return Model.WaterType.BOTTLED;
    }

    public static List<String> legalWaterConditions = Arrays.asList(
            Model.WaterCondition.WASTE.getConditionString(),
            Model.WaterCondition.TREATABLECLEAR.getConditionString(),
            Model.WaterCondition.TREATABLEMUDDY.getConditionString(),
            Model.WaterCondition.POTABLE.getConditionString()
    );

    /**
     * Given the string associated with a userType, returns the UserTpe
     * @param strCondition the string associated with the userType
     * @return the userType associated with the passed in String
     */
    public static Model.WaterCondition getConditionFromString(String strCondition) {
        for (Model.WaterCondition t: Model.WaterCondition.values()) {
            String condition = t.getConditionString();
            if (condition.equals(strCondition)) {
                return t;
            }
        }
        return Model.WaterCondition.WASTE;
    }

    /**
     * Constructs a new water source report
     * @param sub the user who submitted the report
     * @param subDate the date the report was submitted
     * @param lat the latitude of the water
     * @param lng the longitude of the water
     * @param type the type of water
     * @param condition the condition the water is in
     * @param number the number of the report
     */
    public WaterSourceReport(User sub, Date subDate, double lat, double lng, Model.WaterType type, Model.WaterCondition condition, int number) {
        super(sub, subDate, lat, lng, number);
        waterType = type;
        waterCondition = condition;
    }

    /**
     * Gets the type of water reported for another class
     * @return the type of the water
     */
    public Model.WaterType getWaterType() { return waterType;}

    /**
     * gets the condition of the water reported for another class
     * @return the condition of the water
     */
    public Model.WaterCondition getWaterCondition() { return waterCondition;}

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) { return false;}
        if (this == o) { return true;}
        if (!(o instanceof WaterSourceReport)) {
            return false;
        }
        WaterSourceReport oRep = (WaterSourceReport) o;
        return (oRep.getReportNumber() == getReportNumber());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return getReportNumber();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Report number: " + getReportNumber() + ", Submitted by: " + getReporterName()
                + "\nLocated at latitude: " + getLatitude() + ", longitude: " + getLongitude()
                + "\nWater Type: " + waterType.getTypeString() + ", Water Condition: " + waterCondition.getConditionString()
                + "\nDate submitted: " + sdf.format(getDateSubmitted());
    }

    /**
     * Gets the snippet about the report for another class
     * @return a string containing information to be displayed in the map snippet
     */
    public String getSnippet() {
        return "Water Type: " + waterType.getTypeString() + ", Water Condition: " + waterCondition.getConditionString();
    }
}
