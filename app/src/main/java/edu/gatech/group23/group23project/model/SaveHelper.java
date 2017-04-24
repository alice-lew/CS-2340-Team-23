package edu.gatech.group23.group23project.model;

import android.content.Context;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * This class handles the saving for the model
 * Created by Noah Blume on 3/23/2017.
 */

@SuppressWarnings("CyclicClassDependency")
class SaveHelper implements Serializable, SaveHelperInterface {

    /**
     * Saves a serialized copy of the model
     * @param m the model object
     * @param context the context for the screen the user is currently at
     */
    public void saveModel(ModelInterface m, Context context) {
        try {
            FileOutputStream fos = context.openFileOutput("model_file", Context.MODE_PRIVATE);
            ObjectOutput os = new ObjectOutputStream(fos);
            os.writeObject(m);
            os.close();
            fos.close();
            Toast.makeText(context, "Successfully saved.",
                    Toast.LENGTH_SHORT).show();
        } catch(IOException e) {
            Toast.makeText(context, "Failed to save.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * loads the previously saved model if one exists
     * @param context context the context for the screen the user is currently at
     * @return the model object that was loaded
     */
    public ModelInterface loadModel(Context context) {
        try {
            FileInputStream fis = context.openFileInput("model_file");
            ObjectInputStream is = new ObjectInputStream(fis);
            Model m = (Model) is.readObject();
            is.close();
            fis.close();
            Toast.makeText(context, "Successfully loaded.",
                    Toast.LENGTH_SHORT).show();
            return m;
        } catch (IOException | ClassNotFoundException e) {
            Toast.makeText(context, "Failed to load.",
                    Toast.LENGTH_SHORT).show();
        }
        return null;
    }
}
