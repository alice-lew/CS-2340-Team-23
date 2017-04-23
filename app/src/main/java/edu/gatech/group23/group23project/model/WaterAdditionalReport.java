package edu.gatech.group23.group23project.model;

import android.icu.text.SimpleDateFormat;
import android.support.annotation.NonNull;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * This is the class for water source reports created by any type of user
 * Created by Noah Blume on 2/24/2017.
 */

public class WaterAdditionalReport extends WaterReport implements Comparable<WaterAdditionalReport> {
    private final SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy HH:mm:ss");
    private final String isTheWaterPurple;
    private final Calendar cal = Calendar.getInstance();

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(@NonNull WaterAdditionalReport r) {
        cal.setTime(this.getDateSubmitted());
        int thisMonth = cal.get(Calendar.MONTH);
        cal.setTime(r.getDateSubmitted());
        int otherMonth = cal.get(Calendar.MONTH);
        return thisMonth - otherMonth;
    }

    /**
     * Constructs a new water source report
     * @param sub the user who submitted the report
     * @param subDate the date the report was submitted
     * @param lat the latitude of the water
     * @param lng the longitude of the water
     * @param number the number of the report
     */
    public WaterAdditionalReport(User sub, Date subDate, double lat, double lng, int number, boolean isPurple) {
        super(sub, subDate, lat, lng, number);
        if (isPurple) {
            isTheWaterPurple = "Yes";
        } else {
            isTheWaterPurple = "No";
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) { return false;}
        if (this == o) { return true;}
        if (!(o instanceof WaterAdditionalReport)) {
            return false;
        }
        WaterAdditionalReport oRep = (WaterAdditionalReport) o;
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
                + "\nDate submitted: " + sdf.format(getDateSubmitted())
                + "\nIs the water purple?: " + isTheWaterPurple;
    }

    public boolean isPurple() {
        if ("Yes".equals(isTheWaterPurple)) {
            return true;
        }
        return false;
    }
}
