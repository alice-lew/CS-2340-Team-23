package edu.gatech.group23.group23project.model;

import android.icu.text.DateFormat;
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
            if (t.getTypeString().equals(strType)) {
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
            if (t.getConditionString().equals(strCondition)) {
                return t;
            }
        }
        return Model.WaterCondition.WASTE;
    }

    public WaterSourceReport(User sub, Date subDate, double lat, double lng, Model.WaterType type, Model.WaterCondition condition, int number) {
        super(sub, subDate, lat, lng, number);
        waterType = type;
        waterCondition = condition;
    }

    public Model.WaterType getWaterType() { return waterType;}
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
        if (oRep.getReportNumber() == getReportNumber()) {
            return true;
        }
        return false;
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
