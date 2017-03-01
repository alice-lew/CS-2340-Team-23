package edu.gatech.group23.group23project.controllers;

import android.content.Context;
import android.content.Intent;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import edu.gatech.group23.group23project.R;
import edu.gatech.group23.group23project.model.Model;
import edu.gatech.group23.group23project.model.WaterPurityReport;
import edu.gatech.group23.group23project.model.WaterSourceReport;

public class SubmitPurityReportActivity extends AppCompatActivity {
    private EditText longTextBox;
    private EditText latTextBox;
    private EditText virusTextBox;
    private EditText contaminantTextBox;
    private Spinner conditionSpinner;
    private Button cancelButton;
    private Button submitButton;
    private Model modelInstance = Model.getInstance();
    private DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
    private java.util.Date date;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_purity_report);

        longTextBox = (EditText) findViewById(R.id.longBox);
        latTextBox = (EditText) findViewById(R.id.latBox);
        virusTextBox = (EditText) findViewById(R.id.virusBox);
        contaminantTextBox = (EditText) findViewById(R.id.contaminantBox);
        conditionSpinner = (Spinner) findViewById(R.id.conditionSpinner);
        cancelButton = (Button) findViewById(R.id.cancelButton);
        submitButton = (Button) findViewById(R.id.submitButton);

        Button cancelButton = (Button) findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button registerButton = (Button) findViewById(R.id.submitButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptSubmit();
            }
        });

        /**
         *  Set up the adapter to display the allowable water types in the spinner
         */
        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, WaterPurityReport.legalOverallConditions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        conditionSpinner.setAdapter(adapter);
    }

    private void attemptSubmit() {
        longTextBox.setError(null);
        latTextBox.setError(null);
        virusTextBox.setError(null);
        contaminantTextBox.setError(null);

        if (longTextBox.getText().length() < 1) {
            longTextBox.setError("You must enter a longitude.");
        } else if (latTextBox.getText().length() < 1) {
            latTextBox.setError("You must enter a longitude.");
        } else if (virusTextBox.getText().length() < 1) {
            virusTextBox.setError("You must enter a virus PPM");
        } else if (contaminantTextBox.getText().length() < 1) {
            contaminantTextBox.setError("You must enter a contaminant PPM");
        } else if (!isNumeric(longTextBox.getText().toString())) {
            longTextBox.setError("You must enter a number.");
        } else if (!isNumeric(latTextBox.getText().toString())) {
            latTextBox.setError("You must enter a number.");
        } else if (!isNumeric(virusTextBox.getText().toString())) {
            virusTextBox.setError("You must enter a number.");
        } else if (!isNumeric(contaminantTextBox.getText().toString())) {
            contaminantTextBox.setError("You must enter a number.");
        } else {
            date = new java.util.Date();
            modelInstance.submitWaterPurityReport(modelInstance.getCurrentUser(), date, Double.parseDouble(latTextBox.getText().toString()),
                    Double.parseDouble(longTextBox.getText().toString()), WaterPurityReport.getOverallConditionFromString((String)conditionSpinner.getSelectedItem()),
                    Double.parseDouble(virusTextBox.getText().toString()), Double.parseDouble(contaminantTextBox.getText().toString()));
            Context context = SubmitPurityReportActivity.this;
            Intent intent = new Intent(context, LoggedInActivity.class);
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
        context.startActivity(intent);
        return;
    }

    /**
     * checks if the string entered is a valid number
     * @param s a string being checked whether or not it's a number
     * @return whether or not the string is a number
     */
    private boolean isNumeric(String s) {
        return s.matches("[-+]?\\d*\\.?\\d+");
    }
}
