package edu.gatech.group23.group23project.model;

import android.icu.text.SimpleDateFormat;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * This is the class for water source reports created by any type of user
 * Created by Noah Blume on 2/24/2017.
 */

public class WaterSourceReport extends WaterReport {
    private final SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy HH:mm:ss");
    private final WaterType waterType;
    private final WaterCondition waterCondition;

    public static final List<String> legalWaterTypes = Arrays.asList(
            WaterType.BOTTLED.getTypeString(),
            WaterType.WELL.getTypeString(),
            WaterType.STREAM.getTypeString(),
            WaterType.LAKE.getTypeString(),
            WaterType.SPRING.getTypeString(),
            WaterType.OTHER.getTypeString()
    );

    /**
     * Given the string associated with a userType, returns the UserTpe
     * @param strType the string associated with the userType
     * @return the userType associated with the passed in String
     */
    public static WaterType getTypeFromString(String strType) {
        for (WaterType t: WaterType.values()) {
            String type = t.getTypeString();
            if (type.equals(strType)) {
                return t;
            }
        }
        return WaterType.BOTTLED;
    }

    public static final List<String> legalWaterConditions = Arrays.asList(
            WaterCondition.WASTE.getConditionString(),
            WaterCondition.TREATABLE_CLEAR.getConditionString(),
            WaterCondition.TREATABLE_MUDDY.getConditionString(),
            WaterCondition.POTABLE.getConditionString()
    );

    /**
     * Given the string associated with a userType, returns the UserTpe
     * @param strCondition the string associated with the userType
     * @return the userType associated with the passed in String
     */
    public static WaterCondition getConditionFromString(String strCondition) {
        for (WaterCondition t: WaterCondition.values()) {
            String condition = t.getConditionString();
            if (condition.equals(strCondition)) {
                return t;
            }
        }
        return WaterCondition.WASTE;
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
    public WaterSourceReport(UserInterface sub, Date subDate, double lat, double lng, WaterType type,
                             WaterCondition condition, int number) {
        super(sub, subDate, lat, lng, number);
        waterType = type;
        waterCondition = condition;
    }

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
                + "\nWater Type: " + waterType.getTypeString() + ", Water Condition: "
                + waterCondition.getConditionString()
                + "\nDate submitted: " + sdf.format(getDateSubmitted());
    }
}
