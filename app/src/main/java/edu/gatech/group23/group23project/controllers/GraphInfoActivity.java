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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import edu.gatech.group23.group23project.R;
import edu.gatech.group23.group23project.model.Model;
import edu.gatech.group23.group23project.model.WaterPurityReport;

public class GraphInfoActivity extends AppCompatActivity {

    Button cancelButton;
    Button viewGraphButton;
    Spinner ppmSpinner;
    EditText yearTextBox;
    Model modelInstance = Model.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_info);

        cancelButton = (Button) findViewById(R.id.cancelButton);
        viewGraphButton = (Button) findViewById(R.id.viewGraphButton);
        ppmSpinner = (Spinner) findViewById(R.id.ppmSpinner);
        yearTextBox = (EditText) findViewById(R.id.yearTextInput);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        viewGraphButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptViewGraph();
            }
        });

        /**
         *  Set up the adapter to display the allowable water types in the spinner
         */
        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,
                Arrays.asList("Virus PPM", "Contaminant PPM"));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ppmSpinner.setAdapter(adapter);
    }

    private void attemptViewGraph() {
        yearTextBox.setError(null);

        if (yearTextBox.getText().length() < 1) {
            yearTextBox.setError("You must enter your email.");
        } else if (!isNumeric(yearTextBox.getText().toString())) {
            yearTextBox.setError("You must enter a number.");
        } else {
            modelInstance.setGraphInfo(Math.round(Math.abs(Float.parseFloat(yearTextBox.getText().toString()))),
                    ppmSpinner.getSelectedItemPosition());
            Context context = GraphInfoActivity.this;
            Intent intent = new Intent(context, HistoryGraphActivity.class);
            context.startActivity(intent);
        }
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
