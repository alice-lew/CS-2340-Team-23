package edu.gatech.group23.group23project.model;

import android.content.Context;

/**
 * Created by Mbrune on 4/24/2017.
 */

public interface SaveHelperInterface {
    /**
     * Saves a serialized copy of the model
     * @param m the model object
     * @param context the context for the screen the user is currently at
     */
    public void saveModel(ModelInterface m, Context context);

    /**
     * loads the previously saved model if one exists
     * @param context context the context for the screen the user is currently at
     * @return the model object that was loaded
     */
    public ModelInterface loadModel(Context context);
}
