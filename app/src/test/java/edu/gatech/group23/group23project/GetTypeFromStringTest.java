package edu.gatech.group23.group23project;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import edu.gatech.group23.group23project.model.Model;
import edu.gatech.group23.group23project.model.User;

/**
 * A unit test for the user's getTypeFromString method
 * Created by Asher Kenerly
 */
public class GetTypeFromStringTest {
    //private User user;
    //private Model model;
    private String basic;
    private String worker;
    private String manager;
    private String admin;
    private String badString;

    /**
     * Sets up values to be used in the unit test
     */
    @Before
    public void setUp() {
        //user = new User("")
        //model = Model.getInstance();

        basic = "Basic User";
        worker = "Worker";
        manager = "Manager";
        admin = "Administrator";
        badString = "This is a bad string";
    }

    /**
     * Checks that the method accurately returns the type from String
     * Also checks that a bad string returns a basic user type
     */
    @Test
    public void correctReturnTest() {

        assertEquals(Model.UserType.ADMIN, User.getTypeFromString(admin));
        assertEquals(Model.UserType.MANAGER, User.getTypeFromString(manager));
        assertEquals(Model.UserType.WORKER, User.getTypeFromString(worker));
        assertEquals(Model.UserType.BASIC, User.getTypeFromString(basic));
        assertEquals(Model.UserType.BASIC, User.getTypeFromString(badString));
    }
}
