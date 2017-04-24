package edu.gatech.group23.group23project.model;


import java.io.Serializable;
import java.util.Date;

/**
 * The superclass for the 2 types of water reports (is abstract)
 * Created by Noah Blume on 2/28/2017.
 */

public abstract class WaterReport implements Serializable, WaterReportInterface {
    private final Date dateSubmitted;
    private final String reporterName;
    private final double latitude;
    private final double longitude;
    private final int reportNumber;

    /**
     * Constructs a new water report
     * @param sub the user who submitted the report
     * @param date the date the report was submitted
     * @param lat the latitude of the water
     * @param lng the longitude of the water
     * @param repNum the number of the report
     */
    WaterReport(UserInterface sub, Date date, double lat, double lng, int repNum) {
        dateSubmitted = date;
        reporterName = sub.getName();
        latitude = lat;
        longitude = lng;
        reportNumber = repNum;
    }


    /**
     * Gets the report's date submitted for another class
     * @return report's date submitted
     */
    public Date getDateSubmitted() { return dateSubmitted;}

    /**
     * Gets the report's latitude for another class
     * @return report's latitude
     */
    public double getLatitude() { return latitude;}

    /**
     * Gets the report's longitude for another class
     * @return report's longitude
     */
    public double getLongitude() { return longitude;}

    /**
     * Gets the report's name for another class
     * @return report's name
     */
    public String getReporterName() { return reporterName;}

    /**
     * Gets the report's number for another class
     * @return report's number
     */
    public int getReportNumber() { return reportNumber;}

}
