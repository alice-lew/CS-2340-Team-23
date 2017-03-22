package edu.gatech.group23.group23project.controllers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import edu.gatech.group23.group23project.R;
import edu.gatech.group23.group23project.model.Model;

/**
 * The screen users are brought to after logging in
 *
 * Created by Asher on 2/12/2017.
 */

public class LoggedInActivity extends AppCompatActivity {

    private Model modelInstance = Model.getInstance();  //gets the instance of the model

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);


        //button to cancel logging in and return to the welcome screen
        Button logoutButton = (Button) findViewById(R.id.logoutButton);
        Button profileEditButton = (Button) findViewById(R.id.profileEditButton);
        Button submitReportButton = (Button) findViewById(R.id.submitReportButton);
        Button viewReportListButton = (Button) findViewById(R.id.reportListButton);
        Button viewMapButton = (Button) findViewById(R.id.viewMapButton);
        Button submitPurityReportButton = (Button) findViewById(R.id.submitPurityReportButton);
        Button viewPurityReportListButton = (Button) findViewById(R.id.purityReportListButton);
        Button historyGraphButton = (Button) findViewById(R.id.historyGraphButton);

        if (modelInstance.getCurrentUser().getUserType() != Model.UserType.BASIC) {
            submitPurityReportButton.setVisibility(View.VISIBLE);
            viewPurityReportListButton.setVisibility(View.VISIBLE);
        } else {
            submitPurityReportButton.setVisibility(View.GONE);
            viewPurityReportListButton.setVisibility(View.GONE);
        }

        if (modelInstance.getCurrentUser().getUserType() == Model.UserType.MANAGER  ||
                modelInstance.getCurrentUser().getUserType() == Model.UserType.ADMIN) {
            historyGraphButton.setVisibility(View.VISIBLE);
        } else {
            historyGraphButton.setVisibility(View.GONE);
        }


        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modelInstance.setCurrentUser(null);
                Context context = view.getContext();
                Intent intent = new Intent(context, WelcomeActivity.class);
                context.startActivity(intent);
            }
        });

        profileEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, EditProfileActivity.class);
                context.startActivity(intent);
            }
        });

        submitReportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, SubmitReportActivity.class);
                context.startActivity(intent);
            }
        });

        viewReportListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, ReportListActivity.class);
                context.startActivity(intent);
            }
        });

        viewMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, WaterMapActivity.class);
                context.startActivity(intent);
            }
        });

        submitPurityReportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, SubmitPurityReportActivity.class);
                context.startActivity(intent);
            }
        });

        viewPurityReportListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, PurityReportListActivity.class);
                context.startActivity(intent);
            }
        });

        historyGraphButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, GraphInfoActivity.class);
                context.startActivity(intent);
            }
        });

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onBackPressed() {
        //changes the hardware back button functionality to return the user to the welcome page
        Context context = LoggedInActivity.this;
        Intent intent = new Intent(context, WelcomeActivity.class);
        context.startActivity(intent);
        return;
    }
}
