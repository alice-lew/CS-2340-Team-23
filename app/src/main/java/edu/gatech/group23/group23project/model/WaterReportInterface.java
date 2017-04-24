package edu.gatech.group23.group23project.model;

import java.util.Date;

/**
 * Created by Mbrune on 4/24/2017.
 */

public interface WaterReportInterface {
    /**
     * Gets the report's date submitted for another class
     * @return report's date submitted
     */
    public Date getDateSubmitted();

    /**
     * Gets the report's latitude for another class
     * @return report's latitude
     */
    public double getLatitude();

    /**
     * Gets the report's longitude for another class
     * @return report's longitude
     */
    public double getLongitude();

    /**
     * Gets the report's name for another class
     * @return report's name
     */
    String getReporterName();

    /**
     * Gets the report's number for another class
     * @return report's number
     */
    public int getReportNumber();
}
