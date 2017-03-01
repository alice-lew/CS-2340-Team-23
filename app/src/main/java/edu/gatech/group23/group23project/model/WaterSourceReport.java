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
    private User submitter;
    private Date dateSubmitted;
    private SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy HH:mm:ss");
    private String reporterName;
    private double latitude;
    private double longitude;
    private Model.WaterType waterType;
    private Model.WaterCondition waterCondition;
    private int reportNumber;

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
        submitter = sub;
        dateSubmitted = subDate;
        reporterName = submitter.getName();
        latitude = lat;
        longitude = lng;
        waterType = type;
        waterCondition = condition;
        reportNumber = number;
    }

    public User getSubmitter() { return submitter;}
    public Date getDateSubmitted() { return dateSubmitted;}
    public double getLatitude() { return latitude;}
    public double getLongitude() { return longitude;}
    public String getReporterName() { return reporterName;}
    public Model.WaterType getWaterType() { return waterType;}
    public Model.WaterCondition getWaterCondition() { return waterCondition;}
    public int getReportNumber() { return reportNumber;}

    @Override
    public boolean equals(Object o) {
        if (o == null) { return false;}
        if (this == o) { return true;}
        if (!(o instanceof WaterSourceReport)) {
            return false;
        }
        WaterSourceReport oRep = (WaterSourceReport) o;
        if (oRep.getReportNumber() == this.reportNumber) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return reportNumber;
    }

    @Override
    public String toString() {
        return "Report number: " + reportNumber + ", Submitted by: " + reporterName
                + "\nLocated at latitude: " + latitude + ", longitude: " + longitude
                + "\nWater Type: " + waterType.getTypeString() + ", Water Condition: " + waterCondition.getConditionString();
    }

    public String getSnippet() {
        return "Water Type: " + waterType.getTypeString() + ", Water Condition: " + waterCondition.getConditionString();
    }
}
