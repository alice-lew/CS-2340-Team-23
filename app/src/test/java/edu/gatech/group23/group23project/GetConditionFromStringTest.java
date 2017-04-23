package edu.gatech.group23.group23project;

import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertEquals;

import dalvik.annotation.TestTargetClass;
import edu.gatech.group23.group23project.model.Model;
import edu.gatech.group23.group23project.model.WaterCondition;
import edu.gatech.group23.group23project.model.WaterSourceReport;

/**
 * Created by Soham on 4/5/2017.
 */

public class GetConditionFromStringTest {
    private WaterSourceReport sourceReport;
    private String strCondition;

    /**
     * Checks that the Waste condition of the Water Source works
     */
    @Test
    public void testIfWaste() {
        strCondition = "Waste";
        WaterCondition testCondition = sourceReport.getConditionFromString(strCondition);
        assertEquals(WaterCondition.WASTE, testCondition);
    }
    /**
     * Checks that the Treatable Clear condition of the Water Source works
     */
    @Test
    public void testIfTC() {
        strCondition = "Treatable-Clear";
        WaterCondition testCondition = sourceReport.getConditionFromString(strCondition);
        assertEquals(WaterCondition.TREATABLE_CLEAR, testCondition);
    }
    /**
     * Checks that the Treatable Muddy condition of the Water Source works
     */
    @Test
    public void testIfTM() {
        strCondition = "Treatable-Muddy";
        WaterCondition testCondition = sourceReport.getConditionFromString(strCondition);
        assertEquals(WaterCondition.TREATABLE_MUDDY, testCondition);
    }
    /**
     * Checks that the Potable condition of the Water Source works
     */
    @Test
    public void testIfPotable() {
        strCondition = "Potable";
        WaterCondition testCondition = sourceReport.getConditionFromString(strCondition);
        assertEquals(WaterCondition.POTABLE, testCondition);
    }
}
