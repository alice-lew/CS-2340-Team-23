package edu.gatech.group23.group23project.controllers;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Date;

import edu.gatech.group23.group23project.R;
import edu.gatech.group23.group23project.model.Model;
import edu.gatech.group23.group23project.model.WaterPurityReport;

/**
 * The screen where one may submit purity reports
 */
public class SubmitPurityReportActivity extends AppCompatActivity {
    private EditText longTextBox;
    private EditText latTextBox;
    private EditText virusTextBox;
    private EditText contaminantTextBox;
    private Spinner conditionSpinner;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_purity_report);

        longTextBox = (EditText) findViewById(R.id.maxLongBox);
        latTextBox = (EditText) findViewById(R.id.maxLatBox);
        virusTextBox = (EditText) findViewById(R.id.virusBox);
        contaminantTextBox = (EditText) findViewById(R.id.contaminantBox);
        conditionSpinner = (Spinner) findViewById(R.id.conditionSpinner);
        Button submitButton = (Button) findViewById(R.id.submitButton);

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

    /**
     * Attempts to submit a new water source report if the information entered is valid
     */
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
            Model modelInstance = Model.getInstance();
            modelInstance.submitWaterPurityReport(modelInstance.getCurrentUser(), date,
                    Double.parseDouble(latTextBox.getText().toString()),
                    Double.parseDouble(longTextBox.getText().toString()),
                    WaterPurityReport.getOverallConditionFromString((String)conditionSpinner
                            .getSelectedItem()),
                    Double.parseDouble(virusTextBox.getText().toString()),
                    Double.parseDouble(contaminantTextBox.getText().toString()));
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

    /**
     * checks if the string entered is a valid number
     * @param s a string being checked whether or not it's a number
     * @return whether or not the string is a number
     */
    private boolean isNotNumeric(String s) {
        return !s.matches("[-+]?\\d*\\.?\\d+");
    }
}
