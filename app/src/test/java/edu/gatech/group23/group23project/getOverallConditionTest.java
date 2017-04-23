package edu.gatech.group23.group23project;


import org.junit.Before;
import org.junit.Test;

import dalvik.annotation.TestTargetClass;
import edu.gatech.group23.group23project.model.Model;
import edu.gatech.group23.group23project.model.WaterPurityReport;
import edu.gatech.group23.group23project.model.WaterOverallCondition;

import static org.junit.Assert.assertEquals;
/**
 * Created by gautum on 4/5/17.
 */

public class getOverallConditionTest {
    private String condition;
    private WaterPurityReport waterPurityReport;


    //tests to see if safe water works
    @Test
    public void safeWaterTest() {
        condition = "Safe";
        WaterOverallCondition val = waterPurityReport.getOverallConditionFromString(condition);
        assertEquals(WaterOverallCondition.SAFE, val);
    }
    // tests to see if treatable water works
    @Test
    public void treatableWaterTest() {
        condition = "Treatable";
        WaterOverallCondition val = waterPurityReport.getOverallConditionFromString(condition);
        assertEquals(WaterOverallCondition.TREATABLE, val);
    }
    // tests to see if unsafe water works
    @Test
    public void unsafeWaterTest() {
        condition = "Unsafe";
        WaterOverallCondition val = waterPurityReport.getOverallConditionFromString(condition);
        assertEquals(WaterOverallCondition.UNSAFE, val);
    }


}
