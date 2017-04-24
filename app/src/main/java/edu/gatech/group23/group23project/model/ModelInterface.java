package edu.gatech.group23.group23project.model;

import android.content.Context;

import java.util.Date;
import java.util.List;

/**
 * Created by Mbrune on 4/24/2017.
 */

public interface ModelInterface {

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
                                      String title, UserType type);

    /**
     * Gets the set of users for another class
     * @return the set of registered users
     */
    public Iterable<UserInterface> getUserSet();

    /**
     * Gets the current user for another class
     * @return the current user
     */
    public UserInterface getCurrentUser();

    /**
     * Changes the current user
     * @param u the user to be set as the new current user
     */
    public void setCurrentUser(UserInterface u);


    /**
     * Gets a list of all water reports for another class
     * @return return a list of all water reports
     */
    public Iterable<WaterReportInterface> getReportList();

    /**
     * Gets a list of all water source reports for another class
     * @return return a list of all water source reports
     */
    public List<WaterSourceReport> getSourceReportList();

    /**
     * Gets a list of all water purity reports for another class
     * @return return a list of all water purity reports
     */
    public List<WaterPurityReport> getPurityReportList();

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
                                  WaterType type, WaterCondition condition);

    /**
     * Creates a new water source report based on the data passed in
     * @param sub the submitter of the report
     * @param subDate the date submitted
     * @param lat the latitude of the water reported
     * @param lng the longitude of the water reported
     */
    public void submitAdditionalWaterReport(UserInterface sub, Date subDate, double lat, double lng, int yesOrNo);

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
                                        double cPPM);

    /**
     * Checks if an existing user already has the username passed in
     * @param s the username being checked
     * @return whether or not the username is already taken
     */
    public boolean usernameTaken(String s);


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
                             double maxLng);

    /**
     * Gets the year for the graph to display data for
     * @return return the graphYear
     */
    public int getGraphYear();

    /**
     * Gets the type of data (virus of containment ppm) for the graph to display data for
     * @return returns whether to display virus ppm or containment ppm in the graph
     */
    public GraphType getCurGraphType();

    /**
     * Gets the minimum latitude of reports for the graph to show
     * @return return minimum latitude
     */
    public double getGraphMinLat();

    /**
     * Gets the minimum longitude of reports for the graph to show
     * @return return minimum longitude
     */
    public double getGraphMinLng();

    /**
     * Gets the max latitude of reports for the graph to show
     * @return return maximum latitude
     */
    public double getGraphMaxLat();

    /**
     * Gets the maximum longitude of reports for the graph to show
     * @return return maximum longitude
     */
    public double getGraphMaxLng();

    /**
     * Saves the model by calling saveHelper's saveModel method
     * @param m the model being saved
     * @param context the screen the user is currently on
     */
    public void saveModel(ModelInterface m, Context context);

    /**
     * Loads a previously saved instance of the model by calling the saveHelper's loadModel method
     * @param context the screen the user is currently on
     * @return the loaded instance of the model
     */
    public ModelInterface loadModel(Context context);

    /**
     * Gets the current user's email for another class
     * @return the current user's email
     */
    public CharSequence getCurUserEmail();

    /**
     * Gets the current user's title for another class
     * @return the current user's title
     */
    public CharSequence getCurUserTitle();

    /**
     * Gets the current user's home address for another class
     * @return the current user's home address
     */
    public CharSequence getCurUserHome();

    /**
     * Gets the current user's type for another class
     * @return the current user's type
     */
    public UserType getCurUserType();

    /**
     * Sets the current user's email address to the passed in string
     * @param e the new email address
     */
    public void setCurUserEmail(String e);

    /**
     * Sets the current user's title to the passed in string
     * @param t the new title address
     */
    public void setCurUserTitle(String t);

    /**
     * Sets the current user's home address to the passed in string
     * @param h the new home address
     */
    public void setCurUserHome(String h);;

    public List<WaterAdditionalReport> getAdditionalReportList();

    public List<LogObjectInterface> getSecurityLogs();

    public void addSecurityLog(String uid, Date d, String action);
}
