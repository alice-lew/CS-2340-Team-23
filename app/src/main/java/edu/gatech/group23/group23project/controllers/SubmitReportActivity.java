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
import edu.gatech.group23.group23project.model.WaterSourceReport;

/**
 * The screen where users may submit source reports
 */
public class SubmitReportActivity extends AppCompatActivity {
    private EditText longTextBox;
    private EditText latTextBox;
    private Spinner typeSpinner;
    private Spinner conditionSpinner;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_report);

        longTextBox = (EditText) findViewById(R.id.maxLongBox);
        latTextBox = (EditText) findViewById(R.id.maxLatBox);
        typeSpinner = (Spinner) findViewById(R.id.typeSpinner);
        conditionSpinner = (Spinner) findViewById(R.id.conditionSpinner);
        Button submitButton = (Button) findViewById(R.id.submitButton);

        Button cancelButton = (Button) findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = SubmitReportActivity.this;
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


        //  Set up the adapter to display the allowable water types in the spinner
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, WaterSourceReport.legalWaterTypes);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(typeAdapter);


        // Set up the adapter to display the allowable water conditions in the spinner
        ArrayAdapter<String> conditionAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, WaterSourceReport.legalWaterConditions);
        conditionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        conditionSpinner.setAdapter(conditionAdapter);
    }

    /**
     * Attempts to submit a new water source report if the information entered is valid
     */
    @SuppressWarnings("FeatureEnvy")
    private void attemptSubmit() {
        longTextBox.setError(null);
        latTextBox.setError(null);
        if (longTextBox.getText().length() < 1) {
            longTextBox.setError("You must enter a longitude.");
        } else if (latTextBox.getText().length() < 1) {
            latTextBox.setError("You must enter a latitude.");
        } else if (isNotNumeric(longTextBox.getText().toString())) {
            longTextBox.setError("You must enter a number.");
        } else if (isNotNumeric(latTextBox.getText().toString())) {
            latTextBox.setError("You must enter a number.");
        } else {
            Date date = new java.util.Date();
            Model modelInstance = Model.getInstance();
            modelInstance.submitWaterReport(modelInstance.getCurrentUser(), date,
                    Double.parseDouble(latTextBox.getText().toString()),
                    Double.parseDouble(longTextBox.getText().toString()),
                    WaterSourceReport.getTypeFromString((String)typeSpinner.getSelectedItem()),
                    WaterSourceReport.getConditionFromString((String)conditionSpinner
                            .getSelectedItem()));
            Context context = SubmitReportActivity.this;
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
        Context context = SubmitReportActivity.this;
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
