package edu.gatech.group23.group23project.model;

import android.icu.text.SimpleDateFormat;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Noah Blume on 2/28/2017.
 */

public class WaterPurityReport extends WaterReport implements Comparable<WaterPurityReport> {
    //private User submitter;
    //private Date dateSubmitted;
    private SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy HH:mm:ss");
    //private String reporterName;
    //private double latitude;
    //private double longitude;
    private Model.WaterOverallCondition waterOverallCondition;
    private double virusPPM;
    private double contaminantPPM;
    //private int reportNumber;
    private Calendar cal = Calendar.getInstance();

    public int compareTo(WaterPurityReport r) {
        cal.setTime(getDateSubmitted());
        int thisMonth = cal.get(Calendar.MONTH);
        cal.setTime(r.getDateSubmitted());
        int otherMonth = cal.get(Calendar.MONTH);
        return thisMonth - otherMonth;
    }
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
        super(sub, subDate, lat, lng, number);
        waterOverallCondition = oCondition;
        virusPPM = vPPM;
        contaminantPPM = cPPM;
    }

    public Model.WaterOverallCondition getWaterOverallCondition() { return waterOverallCondition;}
    public double getVirusPPM() { return virusPPM;}
    public double getContaminantPPM() { return  contaminantPPM;}

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) { return false;}
        if (this == o) { return true;}
        if (!(o instanceof WaterPurityReport)) {
            return false;
        }
        WaterPurityReport oRep = (WaterPurityReport) o;
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
                + "\nWater Condition: " + waterOverallCondition.getOverallConditionString()
                + "\nVirus PPM: " + virusPPM + ", Contaminant PPM: " + contaminantPPM
                + "\nDate submitted: " + sdf.format(getDateSubmitted());
    }

    /**
     * Gets the snippet about the report for another class
     * @return a string containing information to be displayed in the map snippet
     */
    public String getSnippet() {
        return "Water Condition: " + waterOverallCondition.getOverallConditionString()
                + "\nVirus PPM: " + virusPPM + ", Contaminant PPM: " + contaminantPPM;
    }
}
