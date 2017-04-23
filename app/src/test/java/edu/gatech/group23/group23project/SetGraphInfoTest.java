package edu.gatech.group23.group23project;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import edu.gatech.group23.group23project.model.GraphType;
import edu.gatech.group23.group23project.model.Model;

/**
 * A unit test for the model's getInstance method
 * Created by Noah Blume on 4/2/2017.
 */
public class SetGraphInfoTest {
    private Model model;
    private int year;
    private int typeOrdinal;
    private double minLat;
    private double minLng;
    private double maxLat;
    private double maxLng;
    private final double epsilon = 0.01;

    /**
     * Sets up values to be used in the unit test
     */
    @Before
    public void setUp() {
        model = Model.getInstance();
        year = 1000;
        minLat = 1;
        maxLat = 10;
        minLng = 2;
        maxLng = 100;
    }

    /**
     * Checks that the graph type can properly be set to virus
     */
    @Test
    public void virusPPMTest() {
        typeOrdinal = 0;
        model.setGraphInfo(year, typeOrdinal, minLat, maxLat, minLng, maxLng);
        assertEquals(GraphType.VIRUS, model.getCurGraphType());
        assertEquals(1000, model.getGraphYear());
        assertEquals(10, model.getGraphMaxLat(), epsilon);
        assertEquals(1, model.getGraphMinLat(), epsilon);
        assertEquals(100, model.getGraphMaxLng(), epsilon);
        assertEquals(2, model.getGraphMinLng(), epsilon);
    }

    /**
     * Checks that the graph type can properly be set to contaminant
     */
    @Test
    public void contaminantPPMTest() {
        typeOrdinal = 1;
        model.setGraphInfo(year, typeOrdinal, minLat, maxLat, minLng, maxLng);
        assertEquals(GraphType.CONTAMINANT, model.getCurGraphType());
        assertEquals(1000, model.getGraphYear());
        assertEquals(10, model.getGraphMaxLat(), epsilon);
        assertEquals(1, model.getGraphMinLat(), epsilon);
        assertEquals(100, model.getGraphMaxLng(), epsilon);
        assertEquals(2, model.getGraphMinLng(), epsilon);
    }
}
