package edu.gatech.group23.group23project.model;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by Mbrune on 3/23/2017.
 */

public class SaveHelper {

    public static void saveModel(Model m, Context context) {
        try {
            FileOutputStream fos = context.openFileOutput("model_save", Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(m);
            os.close();
            fos.close();
            System.out.println("Save SUCCEEDED.");
        } catch(IOException e) {
            System.out.println("Failed to save.");
            System.out.println(e.getMessage());
        }
    }

    public static Model loadModel(Context context) {
        try {
            FileInputStream fis = context.openFileInput("model_save");
            ObjectInputStream is = new ObjectInputStream(fis);
            Model m = (Model) is.readObject();
            is.close();
            fis.close();
            System.out.println("Load SUCCEEDED.");
            return m;
        } catch (IOException e) {
            System.out.println("Failed to load.");
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Failed to load 2.");
            System.out.println(e.getMessage());
        }
        return null;
    }
}
