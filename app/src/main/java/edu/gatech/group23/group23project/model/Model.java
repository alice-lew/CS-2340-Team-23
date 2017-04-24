package edu.gatech.group23.group23project.model;

import android.content.Context;
import android.icu.text.SimpleDateFormat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * The model singleton object for the application
 *
 * Created by Noah Blume on 2/19/2017.
 */
@SuppressWarnings({"CyclicClassDependency", "ClassWithTooManyDependents"})
public final class Model implements Serializable, ModelInterface {
    private final Collection<WaterReportInterface> genericRepList = new ArrayList<>();
    private final Collection<UserInterface> userSet = new HashSet<>();
    private int numRepCreated;
    private final List<WaterSourceReport> repList = new ArrayList<>();
    private final List<WaterPurityReport> pRepList = new ArrayList<>();
    private final List<WaterAdditionalReport> aRepList = new ArrayList<>();
    private static ModelInterface modelSingleton;    //the model singleton
    private int graphYear;
    private GraphType curGraphType;
    private double graphMinLat;
    private double graphMinLng;
    private double graphMaxLat;
    private double graphMaxLng;
    private final SaveHelperInterface saveHelper;
    private final List<LogObjectInterface> securityLogs = new ArrayList<>();
    private final SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy HH:mm:ss");

    private UserInterface currentUser;    //keeps track of the user who is currently signed in


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
    public static ModelInterface getInstance() {
        if (modelSingleton == null) {
            modelSingleton = new Model();
        }
        return modelSingleton;
    }

    /**
     * Updates the model instance when the application loads
     * @param m the new model instance
     */
    public static void setInstance(ModelInterface m) {
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
    public UserInterface registerUser(String name, String user, String pass, String email, String address,
                             String title, UserType type) {
        UserInterface newUser = new User(name, user, pass, email, address, title, type);
        userSet.add(newUser);
        return newUser;
    }

    /**
     * Gets the set of users for another class
     * @return the set of registered users
     */
    public Iterable<UserInterface> getUserSet() {
        return userSet;
    }

    /**
     * Gets the current user for another class
     * @return the current user
     */
    public UserInterface getCurrentUser() {
        return currentUser;
    }

    /**
     * Changes the current user
     * @param u the user to be set as the new current user
     */
    public void setCurrentUser(UserInterface u) {
        currentUser = u;
    }


    /**
     * Gets a list of all water reports for another class
     * @return return a list of all water reports
     */
    public Iterable<WaterReportInterface> getReportList(){ return genericRepList;}

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
     */
    public void submitWaterReport(UserInterface sub, Date subDate, double lat, double lng,
                                  WaterType type, WaterCondition condition) {
        numRepCreated++;
        WaterSourceReport newRep = new WaterSourceReport(sub, subDate, lat, lng, type, condition,
                numRepCreated);
        repList.add(newRep);
        genericRepList.add(newRep);
    }

    /**
     * Creates a new water source report based on the data passed in
     * @param sub the submitter of the report
     * @param subDate the date submitted
     * @param lat the latitude of the water reported
     * @param lng the longitude of the water reported
     */
    public void submitAdditionalWaterReport(UserInterface sub, Date subDate, double lat, double lng, int yesOrNo) {
        numRepCreated++;
        boolean isPurple = false;
        if (yesOrNo == 0) {
            isPurple = true;
        }
        WaterAdditionalReport newRep = new WaterAdditionalReport(sub, subDate, lat, lng, numRepCreated, isPurple);
        aRepList.add(newRep);
        genericRepList.add(newRep);
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
     */
    public void submitWaterPurityReport(UserInterface sub, Date subDate, double lat, double lng,
                                        WaterOverallCondition cond, double vPPM,
                                        double cPPM) {
        numRepCreated++;
        WaterPurityReport newRep = new WaterPurityReport(sub, subDate, lat, lng, cond, vPPM, cPPM,
                numRepCreated);
        pRepList.add(newRep);
        genericRepList.add(newRep);
    }

    /**
     * Checks if an existing user already has the username passed in
     * @param s the username being checked
     * @return whether or not the username is already taken
     */
    public boolean usernameTaken(String s) {
        UserInterface checkUser = new User("n", s, "p", "e", "a", "t", UserType.BASIC);
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
    public void setGraphInfo(int year, int typeOrdinal, double minLat, double maxLat, double minLng,
                             double maxLng) {
        graphYear = year;
        graphMinLat = minLat;
        graphMinLng = minLng;
        graphMaxLat = maxLat;
        graphMaxLng = maxLng;
        if (typeOrdinal == 0) {
            curGraphType = GraphType.VIRUS;
        } else if (typeOrdinal == 1){
            curGraphType = GraphType.CONTAMINANT;
        } else {
            curGraphType = GraphType.PURPLENESS;
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

    /**
     * Saves the model by calling saveHelper's saveModel method
     * @param m the model being saved
     * @param context the screen the user is currently on
     */
    public void saveModel(ModelInterface m, Context context) {
        saveHelper.saveModel(m, context);
    }

    /**
     * Loads a previously saved instance of the model by calling the saveHelper's loadModel method
     * @param context the screen the user is currently on
     * @return the loaded instance of the model
     */
    public ModelInterface loadModel(Context context) {
        return saveHelper.loadModel(context);
    }

    /**
     * Gets the current user's email for another class
     * @return the current user's email
     */
    public CharSequence getCurUserEmail() {
        return currentUser.getEmail();
    }

    /**
     * Gets the current user's title for another class
     * @return the current user's title
     */
    public CharSequence getCurUserTitle() {
        return currentUser.getTitle();
    }

    /**
     * Gets the current user's home address for another class
     * @return the current user's home address
     */
    public CharSequence getCurUserHome() {
        return currentUser.getHome();
    }

    /**
     * Gets the current user's type for another class
     * @return the current user's type
     */
    public UserType getCurUserType() {
        return currentUser.getUserType();
    }

    /**
     * Sets the current user's email address to the passed in string
     * @param e the new email address
     */
    public void setCurUserEmail(String e) {
        currentUser.setEmail(e);
    }

    /**
     * Sets the current user's title to the passed in string
     * @param t the new title address
     */
    public void setCurUserTitle(String t) {
        currentUser.setTitle(t);
    }

    /**
     * Sets the current user's home address to the passed in string
     * @param h the new home address
     */
    public void setCurUserHome(String h) {
        currentUser.setHome(h);
    }

    public List<WaterAdditionalReport> getAdditionalReportList() {
        return aRepList;
    }

    public List<LogObjectInterface> getSecurityLogs() { return securityLogs;}

    public void addSecurityLog(String uid, Date d, String action) {
        LogObjectInterface log = new LogObject(uid + " " + action + " on " + sdf.format(d));
        securityLogs.add(log);
    }
}
