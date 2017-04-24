package edu.gatech.group23.group23project.controllers;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import java.util.Date;
import java.util.Random;

import edu.gatech.group23.group23project.R;
import edu.gatech.group23.group23project.model.Model;
import edu.gatech.group23.group23project.model.ModelInterface;
import edu.gatech.group23.group23project.model.WaterPurityReport;

/**
 * The screen where one may submit purity reports
 */
public class SubmitPurityReportActivity extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private EditText longTextBox;
    private EditText latTextBox;
    private EditText virusTextBox;
    private EditText contaminantTextBox;
    private Spinner conditionSpinner;
    GoogleApiClient mGoogleApiClient = null;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_purity_report);

        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }


        longTextBox = (EditText) findViewById(R.id.maxLongBox2);
        latTextBox = (EditText) findViewById(R.id.maxLatBox2);
        virusTextBox = (EditText) findViewById(R.id.virusBox);
        contaminantTextBox = (EditText) findViewById(R.id.contaminantBox);
        conditionSpinner = (Spinner) findViewById(R.id.conditionSpinner);
        Button submitButton = (Button) findViewById(R.id.submitButton2);

        Button cancelButton = (Button) findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = SubmitPurityReportActivity.this;
                Intent intent = new Intent(context, LoggedInActivity.class);
                finish();
                context.startActivity(intent);
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptSubmit();
            }
        });


        //Set up the adapter to display the allowable water types in the spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,
                WaterPurityReport.legalOverallConditions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        conditionSpinner.setAdapter(adapter);
    }

    @Override
    public void onConnected(Bundle connectionHint) {
        Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null) {
            double lat = mLastLocation.getLatitude();
            double lng = mLastLocation.getLongitude();
        } else {
            tryAgain();
        }
    }
    @Override
    public void onConnectionFailed(ConnectionResult cr) {
        int i = 27;
    }

    @Override
    public void onConnectionSuspended(int i) {
        int z = 0;
    }

    /**
     * Attempts to submit a new water source report if the information entered is valid
     */
    @SuppressWarnings("FeatureEnvy")
    private void attemptSubmit() {
        longTextBox.setError(null);
        latTextBox.setError(null);
        virusTextBox.setError(null);
        contaminantTextBox.setError(null);

        if (longTextBox.getText().length() < 1) {
            longTextBox.setError("You must enter a longitude.");
        } else if (latTextBox.getText().length() < 1) {
            latTextBox.setError("You must enter a latitude.");
        } else if (virusTextBox.getText().length() < 1) {
            virusTextBox.setError("You must enter a virus PPM");
        } else if (contaminantTextBox.getText().length() < 1) {
            contaminantTextBox.setError("You must enter a contaminant PPM");
        } else if (isNotNumeric(longTextBox.getText().toString())) {
            longTextBox.setError("You must enter a number.");
        } else if (isNotNumeric(latTextBox.getText().toString())) {
            latTextBox.setError("You must enter a number.");
        } else if (isNotNumeric(virusTextBox.getText().toString())) {
            virusTextBox.setError("You must enter a number.");
        } else if (isNotNumeric(contaminantTextBox.getText().toString())) {
            contaminantTextBox.setError("You must enter a number.");
        } else {
            Date date = new java.util.Date();
            ModelInterface modelInstance = Model.getInstance();
            modelInstance.submitWaterPurityReport(modelInstance.getCurrentUser(), date,
                    Double.parseDouble(latTextBox.getText().toString()),
                    Double.parseDouble(longTextBox.getText().toString()),
                    WaterPurityReport.getOverallConditionFromString((String)conditionSpinner
                            .getSelectedItem()),
                    Double.parseDouble(virusTextBox.getText().toString()),
                    Double.parseDouble(contaminantTextBox.getText().toString()));
            modelInstance.saveModel(modelInstance, this);
            Context context = SubmitPurityReportActivity.this;
            Intent intent = new Intent(context, LoggedInActivity.class);
            finish();
            context.startActivity(intent);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onBackPressed() {
        //makes the hardware back button return to the welcome activity
        Context context = SubmitPurityReportActivity.this;
        Intent intent = new Intent(context, LoggedInActivity.class);
        finish();
        context.startActivity(intent);
    }

    private void tryAgain() {
        Random rand = new Random();
        double j = rand.nextInt(1000) / 100000.0;
        double k = rand.nextInt(1000) / 100000.0;
        double lat = 33.777553 + k;
        double lng = -84.395993 + j;
        longTextBox.setText(String.format("%.5f", lng));
        latTextBox.setText(String.format("%.5f", lat));
    }

    /**
     * checks if the string entered is a valid number
     * @param s a string being checked whether or not it's a number
     * @return whether or not the string is a number
     */
    private boolean isNotNumeric(String s) {
        return !s.matches("[-+]?\\d*\\.?\\d+");
    }
}
