package edu.gatech.group23.group23project.model;

import android.util.Log;

import java.io.Serializable;

/**
 * Created by Mbrune on 4/23/2017.
 */

public class LogObject implements Serializable {
    private String logMessage;

    public LogObject(String m) {
        logMessage = m;
    }

    @Override
    public String toString() {
        return logMessage;
    }
}
