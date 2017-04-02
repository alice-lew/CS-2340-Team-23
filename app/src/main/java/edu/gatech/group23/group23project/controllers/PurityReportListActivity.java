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

/**
 * The screen that lists all of the purity reports
 */
public class PurityReportListActivity extends AppCompatActivity {
    private final Model modelInstance = Model.getInstance(); //gets the singleton model instance

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purity_report_list);

        ListView reportsListView = (ListView) findViewById(R.id.reportsListView);
        ListAdapter adapter = new ArrayAdapter<>(this,R.layout.report_list_view,
                R.id.reportListTextView, modelInstance.getPurityReportList());
        reportsListView.setAdapter(adapter);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onBackPressed() {
        //makes the hardware back button return to the welcome activity
        Context context = PurityReportListActivity.this;
        Intent intent = new Intent(context, LoggedInActivity.class);
        finish();
        context.startActivity(intent);
    }
}
