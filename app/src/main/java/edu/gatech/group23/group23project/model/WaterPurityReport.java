package edu.gatech.group23.group23project.model;

import android.icu.text.SimpleDateFormat;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Noah Blume on 2/28/2017.
 */

public class WaterPurityReport extends WaterReport {
    private User submitter;
    private Date dateSubmitted;
    private SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy HH:mm:ss");
    private String reporterName;
    private double latitude;
    private double longitude;
    private Model.WaterOverallCondition waterOverallCondition;
    private double virusPPM;
    private double contaminantPPM;
    private int reportNumber;

    public static List<String> legalOverallConditions = Arrays.asList(
            Model.WaterOverallCondition.SAFE.getOverallConditionString(),
            Model.WaterOverallCondition.TREATABLE.getOverallConditionString(),
            Model.WaterOverallCondition.UNSAFE.getOverallConditionString()
    );

    /**
     * Given the string associated with a userType, returns the UserTpe
     * @param strType the string associated with the userType
     * @return the userType associated with the passed in String
     */
    public static Model.WaterOverallCondition getOverallConditionFromString(String strType) {
        for (Model.WaterOverallCondition t: Model.WaterOverallCondition.values()) {
            if (t.getOverallConditionString().equals(strType)) {
                return t;
            }
        }
        return Model.WaterOverallCondition.SAFE;
    }

    public WaterPurityReport(User sub, Date subDate, double lat, double lng, Model.WaterOverallCondition oCondition, double vPPM, double cPPM, int number) {
        submitter = sub;
        dateSubmitted = subDate;
        reporterName = submitter.getName();
        latitude = lat;
        longitude = lng;
        waterOverallCondition = oCondition;
        virusPPM = vPPM;
        contaminantPPM = cPPM;
        reportNumber = number;
    }

    public User getSubmitter() { return submitter;}
    public Date getDateSubmitted() { return dateSubmitted;}
    public double getLatitude() { return latitude;}
    public double getLongitude() { return longitude;}
    public String getReporterName() { return reporterName;}
    public Model.WaterOverallCondition getWaterOverallCondition() { return waterOverallCondition;}
    public double getVirusPPM() { return virusPPM;}
    public double getContaminantPPM() { return  contaminantPPM;}
    public int getReportNumber() { return reportNumber;}

    @Override
    public boolean equals(Object o) {
        if (o == null) { return false;}
        if (this == o) { return true;}
        if (!(o instanceof WaterPurityReport)) {
            return false;
        }
        WaterPurityReport oRep = (WaterPurityReport) o;
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
                + "\nWater Condition: " + waterOverallCondition.getOverallConditionString()
                + "\nVirus PPM: " + virusPPM + ", Contaminant PPM: " + contaminantPPM;
    }

    public String getSnippet() {
        return "Water Condition: " + waterOverallCondition.getOverallConditionString()
                + "\nVirus PPM: " + virusPPM + ", Contaminant PPM: " + contaminantPPM;
    }
}
