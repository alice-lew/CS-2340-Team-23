package edu.gatech.group23.group23project.controllers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import edu.gatech.group23.group23project.R;
import edu.gatech.group23.group23project.model.Model;
import edu.gatech.group23.group23project.model.SaveHelper;
import edu.gatech.group23.group23project.model.User;

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
        Button saveButton = (Button) findViewById(R.id.saveButton);
        Button loadButton = (Button) findViewById(R.id.loadButton);

        Model.UserType curUserType = modelInstance.getCurrentUser().getUserType();
        if (curUserType != Model.UserType.BASIC) {
            submitPurityReportButton.setVisibility(View.VISIBLE);
            viewPurityReportListButton.setVisibility(View.VISIBLE);
        } else {
            submitPurityReportButton.setVisibility(View.GONE);
            viewPurityReportListButton.setVisibility(View.GONE);
        }

        if ((curUserType == Model.UserType.MANAGER)  ||
                (curUserType == Model.UserType.ADMIN)) {
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
                finish();
                context.startActivity(intent);
            }
        });

        profileEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, EditProfileActivity.class);
                finish();
                context.startActivity(intent);
            }
        });

        submitReportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, SubmitReportActivity.class);
                finish();
                context.startActivity(intent);
            }
        });

        viewReportListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, ReportListActivity.class);
                finish();
                context.startActivity(intent);
            }
        });

        viewMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, WaterMapActivity.class);
                finish();
                context.startActivity(intent);
            }
        });

        submitPurityReportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, SubmitPurityReportActivity.class);
                finish();
                context.startActivity(intent);
            }
        });

        viewPurityReportListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, PurityReportListActivity.class);
                finish();
                context.startActivity(intent);
            }
        });

        historyGraphButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, GraphInfoActivity.class);
                finish();
                context.startActivity(intent);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modelInstance.saveModel(modelInstance,view.getContext());
            }
        });

        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Model m = modelInstance.loadModel(view.getContext());
                User curUser = null;
                modelInstance.getCurrentUser();
                if (m != null) {
                    m.setCurrentUser(curUser);
                    Model.setInstance(m);
                    modelInstance = m;
                } else {
                    Log.d("Loading", "failed to load");
                }
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
        finish();
        context.startActivity(intent);
    }
}
