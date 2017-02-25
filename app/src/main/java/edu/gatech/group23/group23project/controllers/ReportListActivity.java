package edu.gatech.group23.group23project.controllers;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import edu.gatech.group23.group23project.R;
import edu.gatech.group23.group23project.model.Model;
import edu.gatech.group23.group23project.model.WaterSourceReport;

public class ReportListActivity extends AppCompatActivity {
    private ListView reportsListView;
    private Model modelInstance = Model.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_list);

        reportsListView = (ListView) findViewById(R.id.reportsListView);
        ArrayAdapter adapter = new ArrayAdapter<WaterSourceReport>(this,R.layout.report_list_view, R.id.reportListTextView, modelInstance.getReportList());
        reportsListView.setAdapter(adapter);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onBackPressed() {
        //makes the hardware back button return to the welcome activity
        Context context = ReportListActivity.this;
        Intent intent = new Intent(context, LoggedInActivity.class);
        context.startActivity(intent);
        return;
    }
}
