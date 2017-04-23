package edu.gatech.group23.group23project.controllers;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import edu.gatech.group23.group23project.R;
import edu.gatech.group23.group23project.model.Model;

public class SecurityLogActivity extends AppCompatActivity {

    private final Model modelInstance = Model.getInstance(); //gets the singleton model instance

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_log);

        ListView securityLogView = (ListView) findViewById(R.id.securityLogView);
        ListAdapter adapter = new ArrayAdapter<>(this,R.layout.security_log_view,
                R.id.securityLogTextView, modelInstance.getSecurityLogs());
        securityLogView.setAdapter(adapter);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onBackPressed() {
        //makes the hardware back button return to the welcome activity
        Context context = SecurityLogActivity.this;
        Intent intent = new Intent(context, LoggedInActivity.class);
        finish();
        context.startActivity(intent);
    }
}
