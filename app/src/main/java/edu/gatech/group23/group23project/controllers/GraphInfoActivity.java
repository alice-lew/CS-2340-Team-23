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

import java.util.Arrays;

import edu.gatech.group23.group23project.R;
import edu.gatech.group23.group23project.model.Model;

/**
 * The screen that asks the user what data to display on the graph
 */
public class GraphInfoActivity extends AppCompatActivity {

    private EditText minLongTextBox;
    private EditText minLatTextBox;
    private EditText maxLongTextBox;
    private EditText maxLatTextBox;
    private Spinner ppmSpinner;
    private EditText yearTextBox;
    private final Model modelInstance = Model.getInstance();

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_info);

        minLongTextBox = (EditText) findViewById(R.id.minLongBox);
        minLatTextBox = (EditText) findViewById(R.id.minLatBox);
        maxLongTextBox = (EditText) findViewById(R.id.maxLongBox);
        maxLatTextBox = (EditText) findViewById(R.id.maxLatBox);
        Button cancelButton = (Button) findViewById(R.id.cancelButton);
        Button viewGraphButton = (Button) findViewById(R.id.viewGraphButton);
        ppmSpinner = (Spinner) findViewById(R.id.ppmSpinner);
        yearTextBox = (EditText) findViewById(R.id.yearTextInput);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = GraphInfoActivity.this;
                Intent intent = new Intent(context, LoggedInActivity.class);
                context.startActivity(intent);
            }
        });

        viewGraphButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptViewGraph();
            }
        });

        //Set up the adapter to display the allowable water types in the spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,
                Arrays.asList("Virus PPM", "Contaminant PPM"));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ppmSpinner.setAdapter(adapter);
    }

    /**
     * Checks to see if the user entered valid input and then shows the graph of the data
     */
    private void attemptViewGraph() {
        minLongTextBox.setError(null);
        minLatTextBox.setError(null);
        maxLongTextBox.setError(null);
        maxLatTextBox.setError(null);
        yearTextBox.setError(null);

        if (minLongTextBox.getText().length() < 1) {
            minLongTextBox.setError("You must enter a longitude.");
        } else if (minLatTextBox.getText().length() < 1) {
            minLatTextBox.setError("You must enter a latitude.");
        } else if (isNotNumeric(minLongTextBox.getText().toString())) {
            minLongTextBox.setError("You must enter a number.");
        } else if (isNotNumeric(minLatTextBox.getText().toString())) {
            minLatTextBox.setError("You must enter a number.");
        } else if (maxLongTextBox.getText().length() < 1) {
            maxLongTextBox.setError("You must enter a longitude.");
        } else if (maxLatTextBox.getText().length() < 1) {
            maxLatTextBox.setError("You must enter a latitude.");
        } else if (isNotNumeric(maxLongTextBox.getText().toString())) {
            maxLongTextBox.setError("You must enter a number.");
        } else if (isNotNumeric(maxLatTextBox.getText().toString())) {
            maxLatTextBox.setError("You must enter a number.");
        } else if (yearTextBox.getText().length() < 1) {
            yearTextBox.setError("You must enter a year.");
        } else if (isNotNumeric(yearTextBox.getText().toString())) {
            yearTextBox.setError("You must enter a number.");
        } else {
            setGraphInfo();
        }
    }

    /**
     * Has the model set up the info entered for the graph
     */
    private void setGraphInfo() {
        modelInstance.setGraphInfo
                (Math.round(Math.abs(Float.parseFloat(yearTextBox.getText().toString()))),
                        ppmSpinner.getSelectedItemPosition(),
                        Double.parseDouble(minLatTextBox.getText().toString()),
                        Double.parseDouble(maxLatTextBox.getText().toString()),
                        Double.parseDouble(minLongTextBox.getText().toString()),
                        Double.parseDouble(maxLongTextBox.getText().toString()));
        Context context = GraphInfoActivity.this;
        Intent intent = new Intent(context, HistoryGraphActivity.class);
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void onBackPressed() {
        //makes the hardware back button return to the logged in screen
        Context context = GraphInfoActivity.this;
        Intent intent = new Intent(context, LoggedInActivity.class);
        finish();
        context.startActivity(intent);
    }
}
