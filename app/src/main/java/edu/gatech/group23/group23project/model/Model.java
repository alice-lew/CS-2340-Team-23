package edu.gatech.group23.group23project.model;

import android.content.Context;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The model singleton object for the application
 *
 * Created by Noah Blume on 2/19/2017.
 */
public class Model implements Serializable {
    private Collection<WaterReport> genericRepList = new ArrayList<>();
    private Collection<WaterSourceReport> repSet = new HashSet<>();
    private Collection<WaterPurityReport> pRepSet = new HashSet<>();
    private Collection<User> userSet = new HashSet<>();
    private int numRepCreated;
    private List<WaterSourceReport> repList = new ArrayList<>();
    private List<WaterPurityReport> pRepList = new ArrayList<>();
    private static Model modelSingleton;    //the model singleton
    private int graphYear;
    private GraphType curGraphType;
    private double graphMinLat;
    private double graphMinLng;
    private double graphMaxLat;
    private double graphMaxLng;
    private SaveHelper saveHelper;

    private User currentUser;    //keeps track of the user who is currently signed in

    /**
     * An enum of all of the possible graph types
     */
    public enum GraphType {
        VIRUS("Virus PPM"), CONTAMINANT("Contaminant PPM");
        private String typeString;
        GraphType(String s) { typeString = s;}

        /**
         * gets the type of graph as a string for another class
         * @return the graph type as a string
         */
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

        /**
         * gets the type of user as a string for another class
         * @return the user type as a string
         */
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

        /**
         * gets the type of water as a string for another class
         * @return the water type as a string
         */
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

        /**
         * gets the water condition as a string for another class
         * @return the water condition as a string
         */
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

        /**
         * gets the overall water condition as a string for another class
         * @return the overall water condition as a string
         */
        public String getOverallConditionString() {
            return typeString;
        }
    }

    /**
     * constructor for the model, sets up necessary parts of the model
     */
    private Model() {
        saveHelper = new SaveHelper();
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
     * Updates the model instance when the application loads
     * @param m the new model instance
     */
    public static void setInstance(Model m) {
        modelSingleton = m;
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
    public Iterable<User> getUserSet() {
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
     * @param credentialsStr the credentials of the user being looked for
     * @return the user associated with the credentials passed in
     */
    public User getUserWithCredentials(String credentialsStr) {
        for (User u: userSet) {
            String credentials = u.getCredentials();
            if (credentials.equals(credentialsStr)) {
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
    public Iterable<WaterReport> getReportList(){ return genericRepList;}

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


    /**
     * Sets the values for the graph
     * @param year the year of the data in the graph
     * @param typeOrdinal the int corresponding to virus or contaminant ppm
     * @param minLat the lower end of latitudes included in the graph
     * @param maxLat the upper end of the latitudes included in the graph
     * @param minLng the lower end of the longitudes included in the graph
     * @param maxLng the upper end of the longitudes included in the graph
     */
    public void setGraphInfo(int year, int typeOrdinal, double minLat, double maxLat, double minLng, double maxLng) {
        graphYear = year;
        graphMinLat = minLat;
        graphMinLng = minLng;
        graphMaxLat = maxLat;
        graphMaxLng = maxLng;
        if (typeOrdinal == 0) {
            curGraphType = GraphType.VIRUS;
        } else {
            curGraphType = GraphType.CONTAMINANT;
        }
    }

    /**
     * Gets the year for the graph to display data for
     * @return return the graphYear
     */
    public int getGraphYear() {return graphYear;}

    /**
     * Gets the type of data (virus of containment ppm) for the graph to display data for
     * @return returns whether to display virus ppm or containment ppm in the graph
     */
    public GraphType getCurGraphType() {return curGraphType;}

    /**
     * Gets the minimum latitude of reports for the graph to show
     * @return return minimum latitude
     */
    public double getGraphMinLat() {return graphMinLat;}

    /**
     * Gets the minimum longitude of reports for the graph to show
     * @return return minimum longitude
     */
    public double getGraphMinLng() {return graphMinLng;}

    /**
     * Gets the max latitude of reports for the graph to show
     * @return return maximum latitude
     */
    public double getGraphMaxLat() {return graphMaxLat;}

    /**
     * Gets the maximum longitude of reports for the graph to show
     * @return return maximum longitude
     */
    public double getGraphMaxLng() {return graphMaxLng;}

    public void saveModel(Model m, Context context) {
        saveHelper.saveModel(m, context);
    }

    public Model loadModel(Context context) {
        return saveHelper.loadModel(context);
    }
}
