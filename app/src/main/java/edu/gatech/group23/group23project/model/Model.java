package edu.gatech.group23.group23project.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Exchanger;

/**
 * The model singleton object for the application
 *
 * Created by Noah Blume on 2/19/2017.
 */
public class Model {
    public List<WaterReport> genericRepList = new ArrayList<>();
    private Set<WaterSourceReport> repSet = new HashSet<>();
    private Set<WaterPurityReport> pRepSet = new HashSet<>();
    private Set<User> userSet = new HashSet<>();
    private int numRepCreated;
    private List<WaterSourceReport> repList = new ArrayList<>();
    private List<WaterPurityReport> pRepList = new ArrayList<>();
    private static Model modelSingleton;    //the model singleton
    private DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    private int graphYear;
    private GraphType curGraphType;

    private User currentUser;    //keeps track of the user who is currently signed in

    /**
     * An enum of all of the possible graph types
     */
    public enum GraphType {
        VIRUS("Virus PPM"), CONTAMINANT("Contaminant PPM");
        private String typeString;
        GraphType(String s) { typeString = s;}
        public String getTypeString() { return typeString;}
    }

    /**
     * An enum of all of the possible user types
     */
    public enum UserType {
        BASIC("Basic User"), WORKER("Worker"), MANAGER("Manager"), ADMIN("Administrator");

        private String typeString;
        UserType(String s) {
            typeString = s;
        }

        public String getTypeString() {
            return typeString;
        }
    }

    /**
     * An enum of all of the possible water types
     */
    public enum WaterType {
        BOTTLED("Bottled"), WELL("Well"), STREAM("Stream"), LAKE("Lake"), SPRING("Spring"), OTHER("Other");

        private String typeString;
        WaterType(String s) {
            typeString = s;
        }

        public String getTypeString() {
            return typeString;
        }
    }

    /**
     * An enum of all of the possible water conditions
     */
    public enum WaterCondition {
        WASTE("Waste"), TREATABLECLEAR("Treatable-Clear"), TREATABLEMUDDY("Treatable-Muddy"), POTABLE("Potable");

        private String typeString;
        WaterCondition(String s) {
            typeString = s;
        }

        public String getConditionString() {
            return typeString;
        }
    }

    /**
     * An enum of all of the possible overall water conditions
     */
    public enum WaterOverallCondition {
        SAFE("Safe"), TREATABLE("Treatable"), UNSAFE("Unsafe");

        private String typeString;
        WaterOverallCondition(String s) {
            typeString = s;
        }

        public String getOverallConditionString() {
            return typeString;
        }
    }

    /**
     * constructor for the model, sets up necessary parts of the model
     */
    private Model() {
        try {
            User defaultAdmin = new User("admin", "admin", "admin", "Admin@admin.com", "admin drive", "admin title", UserType.ADMIN);
            userSet.add(defaultAdmin);


            String inputString = "10-10-2012";
            Date date = dateFormat.parse(inputString);
            submitWaterPurityReport(defaultAdmin, date, 1, 1, WaterOverallCondition.SAFE, 12, 12);

            inputString = "10-10-2012";
            date = dateFormat.parse(inputString);
            submitWaterPurityReport(defaultAdmin, date, 1, 1, WaterOverallCondition.SAFE, 200, 200);

            inputString = "10-10-2012";
            date = dateFormat.parse(inputString);
            submitWaterPurityReport(defaultAdmin, date, 1, 1, WaterOverallCondition.SAFE, 20, 20);

            inputString = "09-09-2012";
            date = dateFormat.parse(inputString);
            submitWaterPurityReport(defaultAdmin, date, 1, 1, WaterOverallCondition.SAFE, 25, 25);

            inputString = "08-08-2012";
            date = dateFormat.parse(inputString);
            submitWaterPurityReport(defaultAdmin, date, 1, 1, WaterOverallCondition.SAFE, 117, 117);

            inputString = "07-07-2012";
            date = dateFormat.parse(inputString);
            submitWaterPurityReport(defaultAdmin, date, 1, 1, WaterOverallCondition.SAFE, 117, 117);

            inputString = "06-06-2012";
            date = dateFormat.parse(inputString);
            submitWaterPurityReport(defaultAdmin, date, 1, 1, WaterOverallCondition.SAFE, 117, 117);

            inputString = "05-05-2012";
            date = dateFormat.parse(inputString);
            submitWaterPurityReport(defaultAdmin, date, 1, 1, WaterOverallCondition.SAFE, 117, 117);

          //  inputString = "04-04-2012";
            //date = dateFormat.parse(inputString);
            //submitWaterPurityReport(defaultAdmin, date, 1, 1, WaterOverallCondition.SAFE, 117, 117);

            inputString = "03-03-2012";
            date = dateFormat.parse(inputString);
            submitWaterPurityReport(defaultAdmin, date, 1, 1, WaterOverallCondition.SAFE, 117, 117);

            inputString = "02-02-2012";
            date = dateFormat.parse(inputString);
            submitWaterPurityReport(defaultAdmin, date, 1, 1, WaterOverallCondition.SAFE, 75, 51);

            inputString = "02-02-2012";
            date = dateFormat.parse(inputString);
            submitWaterPurityReport(defaultAdmin, date, 1, 1, WaterOverallCondition.SAFE, 25, 50);

            inputString = "01-01-2012";
            date = dateFormat.parse(inputString);
            submitWaterPurityReport(defaultAdmin, date, 1, 1, WaterOverallCondition.SAFE, 25, 50);


        } catch(Exception e) {
            System.out.println("Something went wrong");
        }
    }

    /**
     * Gets the instance of the model for another class.
     * @return an instance of the model
     */
    public static Model getInstance() {
        if (modelSingleton == null) {
            modelSingleton = new Model();
        }
        return modelSingleton;
    }

    /**
     * Registers a user and adds them to the user list
     * @param name the name of the user
     * @param user the username of the user
     * @param pass the user's password
     * @param email the user's email address
     * @param address the user's home address
     * @param title the user's title
     * @param type the user's type (basic, manager, worker, or administrator)
     * @return the new user that was created and added to the user list
     */
    public User registerUser(String name, String user, String pass, String email, String address, String title, UserType type) {
        User newUser = new User(name, user, pass, email, address, title, type);
        userSet.add(newUser);
        return newUser;
    }

    /**
     * Gets the set of users for another class
     * @return the set of registered users
     */
    public Set<User> getUserSet() {
        return userSet;
    }

    /**
     * Gets the current user for another class
     * @return the current user
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * Changes the current user
     * @param u the user to be set as the new current user
     */
    public void setCurrentUser(User u) {
        currentUser = u;
    }


    /**
     * Gets the user associated with a certain username and password combo
     * @param creds the credentials of the user being looked for
     * @return the user asociated with the credentials passed in
     */
    public User getUserWithCredentials(String creds) {
        for (User u: userSet) {
            if (u.getCredentials().equals(creds)) {
                return u;
            }
        }
        //the user was not found, returns null - maybe look into a better solution
        return null;
    }

    /**
     * Gets a list of all water reports for another class
     * @return return a list of all water reports
     */
    public List<WaterReport> getReportList(){ return genericRepList;}

    /**
     * Gets a list of all water source reports for another class
     * @return return a list of all water source reports
     */
    public List<WaterSourceReport> getSourceReportList(){ return repList;}

    /**
     * Gets a list of all water purity reports for another class
     * @return return a list of all water purity reports
     */
    public List<WaterPurityReport> getPurityReportList(){ return pRepList;}

    /**
     * Creates a new water source report based on the data passed in
     * @param sub the submitter of the report
     * @param subDate the date submitted
     * @param lat the latitude of the water reported
     * @param lng the longitude of the water reported
     * @param type the type of water reported
     * @param condition the condition of the water reported
     * @return the new water source report
     */
    public WaterSourceReport submitWaterReport(User sub, Date subDate, double lat, double lng, Model.WaterType type, Model.WaterCondition condition) {
        numRepCreated++;
        WaterSourceReport newRep = new WaterSourceReport(sub, subDate, lat, lng, type, condition, numRepCreated);
        repSet.add(newRep);
        repList.add(newRep);
        genericRepList.add(newRep);
        return newRep;
    }

    /**
     * Creates a new water purity report based on the data passed in
     * @param sub the submitter of the report
     * @param subDate the date submitted
     * @param lat the latitude of the water reported
     * @param lng the longitude of the water reported
     * @param cond the condition of the water reported
     * @param vPPM the virusPPM of the water
     * @param cPPM the contaminantPPM of the water
     * @return the new water purity report
     */
    public WaterPurityReport submitWaterPurityReport(User sub, Date subDate, double lat, double lng, Model.WaterOverallCondition cond, double vPPM, double cPPM) {
        numRepCreated++;
        WaterPurityReport newRep = new WaterPurityReport(sub, subDate, lat, lng, cond, vPPM, cPPM, numRepCreated);
        pRepSet.add(newRep);
        pRepList.add(newRep);
        genericRepList.add(newRep);
        return newRep;
    }

    /**
     * Checks if an existing user already has the username passed in
     * @param s the username being checked
     * @return whether or not the username is already taken
     */
    public boolean usernameTaken(String s) {
        User checkUser = new User("n", s, "p", "e", "a", "t", UserType.BASIC);
        return userSet.contains(checkUser);
    }



    public void setGraphInfo(int year, int typeOrdinal) {
        graphYear = year;
        if (typeOrdinal == 0) {
            curGraphType = GraphType.VIRUS;
        } else {
            curGraphType = GraphType.CONTAMINANT;
        }
    }

    public int getGraphYear() {return graphYear;}
    public GraphType getCurGraphType() {return curGraphType;}

}
