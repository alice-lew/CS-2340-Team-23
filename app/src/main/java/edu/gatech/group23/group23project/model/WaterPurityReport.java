package edu.gatech.group23.group23project.model;

import android.icu.text.SimpleDateFormat;
import android.support.annotation.NonNull;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * The water purity report class, created by workers, manager, and admins
 * Created by Noah Blume on 2/27/2017.
 */

public class WaterPurityReport extends WaterReport implements Comparable<WaterPurityReport> {
    //private User submitter;
    //private Date dateSubmitted;
    private final SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy HH:mm:ss");
    //private String reporterName;
    //private double latitude;
    //private double longitude;
    private final WaterOverallCondition waterOverallCondition;
    private final double virusPPM;
    private final double contaminantPPM;
    //private int reportNumber;
    private final Calendar cal = Calendar.getInstance();


    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(@NonNull WaterPurityReport r) {
        cal.setTime(this.getDateSubmitted());
        int thisMonth = cal.get(Calendar.MONTH);
        cal.setTime(r.getDateSubmitted());
        int otherMonth = cal.get(Calendar.MONTH);
        return thisMonth - otherMonth;
    }
    public static final List<String> legalOverallConditions = Arrays.asList(
            WaterOverallCondition.SAFE.getOverallConditionString(),
            WaterOverallCondition.TREATABLE.getOverallConditionString(),
            WaterOverallCondition.UNSAFE.getOverallConditionString()
    );

    /**
     * Given the string associated with a userType, returns the UserTpe
     * @param strCond the string associated with the userType
     * @return the userType associated with the passed in String
     */
    public static WaterOverallCondition getOverallConditionFromString(String strCond) {
        for (WaterOverallCondition t: WaterOverallCondition.values()) {
            String conditionString = t.getOverallConditionString();
            if (conditionString.equals(strCond)) {
                return t;
            }
        }
        return WaterOverallCondition.SAFE;
    }

    /**
     * Constructs a new water purity report
     * @param sub the user who submitted the report
     * @param subDate the date the report was submitted
     * @param lat the latitude of the water
     * @param lng the longitude of the water
     * @param oCondition the overall condition of the water
     * @param vPPM the virus pmm of the water
     * @param cPPM the contaminant ppm of the water
     * @param number the number of the report
     */
    public WaterPurityReport(UserInterface sub, Date subDate, double lat, double lng,
                             WaterOverallCondition oCondition, double vPPM, double cPPM,
                             int number) {
        super(sub, subDate, lat, lng, number);
        waterOverallCondition = oCondition;
        virusPPM = vPPM;
        contaminantPPM = cPPM;
    }


    /**
     * Gets the virus ppm of the water for another class
     * @return the virus ppm
     */
    public double getVirusPPM() { return virusPPM;}

    /**
     * gets the contaminant ppm for another class
     * @return the contaminant ppm
     */
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
                + "\nWater Condition: " + waterOverallCondition.getOverallConditionString()
                + "\nVirus PPM: " + virusPPM + ", Contaminant PPM: " + contaminantPPM
                + "\nDate submitted: " + sdf.format(getDateSubmitted());
    }
}
