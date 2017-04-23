package edu.gatech.group23.group23project.controllers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Date;

import edu.gatech.group23.group23project.R;
import edu.gatech.group23.group23project.model.Model;
import edu.gatech.group23.group23project.model.User;
import edu.gatech.group23.group23project.model.UserType;

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
        Button submitAddRep = (Button) findViewById(R.id.additionalReportButton);
        Button additionalRepListButton = (Button) findViewById(R.id.additionalreplist);
        Button securityLogButton = (Button) findViewById(R.id.securityLogButton);

        UserType curUserType = modelInstance.getCurUserType();
        if (curUserType != UserType.BASIC) {
            submitPurityReportButton.setVisibility(View.VISIBLE);
            viewPurityReportListButton.setVisibility(View.VISIBLE);
        } else {
            submitPurityReportButton.setVisibility(View.GONE);
            viewPurityReportListButton.setVisibility(View.GONE);
        }

        if ((curUserType == UserType.MANAGER)  ||
                (curUserType == UserType.ADMIN)) {
            historyGraphButton.setVisibility(View.VISIBLE);
        } else {
            historyGraphButton.setVisibility(View.GONE);
        }

        if (curUserType == UserType.ADMIN) {
            securityLogButton.setVisibility(View.VISIBLE);
        } else {
            securityLogButton.setVisibility(View.GONE);
        }


        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modelInstance.addSecurityLog(modelInstance.getCurrentUser().getUsername(), new Date(), "logged out");
                modelInstance.setCurrentUser(null);
                modelInstance.saveModel(modelInstance, LoggedInActivity.this);
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

        submitAddRep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, SubmitAdditionalReportActivity.class);
                context.startActivity(intent);
            }
        });

        additionalRepListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, AdditionalReportListActivity.class);
                context.startActivity(intent);
            }
        });

        securityLogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, SecurityLogActivity.class);
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
        modelInstance.addSecurityLog(modelInstance.getCurrentUser().getUsername(), new Date(), "logged out");
        modelInstance.setCurrentUser(null);
        modelInstance.saveModel(modelInstance, this);
        Context context = LoggedInActivity.this;
        Intent intent = new Intent(context, WelcomeActivity.class);
        context.startActivity(intent);
    }
}
