package edu.gatech.group23.group23project;


import org.junit.Before;
import org.junit.Test;

import dalvik.annotation.TestTargetClass;
import edu.gatech.group23.group23project.model.Model;
import edu.gatech.group23.group23project.model.WaterPurityReport;

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
        Model.WaterOverallCondition val = waterPurityReport.getOverallConditionFromString(condition);
        assertEquals(Model.WaterOverallCondition.SAFE, val);
    }
    // tests to see if treatable water works
    @Test
    public void treatableWaterTest() {
        condition = "Treatable";
        Model.WaterOverallCondition val = waterPurityReport.getOverallConditionFromString(condition);
        assertEquals(Model.WaterOverallCondition.TREATABLE, val);
    }
    // tests to see if unsafe water works
    @Test
    public void unsafeWaterTest() {
        condition = "Unsafe";
        Model.WaterOverallCondition val = waterPurityReport.getOverallConditionFromString(condition);
        assertEquals(Model.WaterOverallCondition.UNSAFE, val);
    }


}
