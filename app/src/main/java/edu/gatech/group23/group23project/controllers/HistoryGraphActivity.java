package edu.gatech.group23.group23project.controllers;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import edu.gatech.group23.group23project.R;
import edu.gatech.group23.group23project.model.GraphType;
import edu.gatech.group23.group23project.model.Model;
import edu.gatech.group23.group23project.model.WaterAdditionalReport;
import edu.gatech.group23.group23project.model.WaterPurityReport;

/**
 * The screen that displays the history chart of water reports
 */
public class HistoryGraphActivity extends AppCompatActivity {
    private final Calendar cal = Calendar.getInstance();
    private final Model modelInstance = Model.getInstance();
    private LineChart mChart;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_graph);


        mChart = (LineChart) findViewById(R.id.chart1);
        mChart.setViewPortOffsets(100, 10, 50, 10);
        mChart.setBackgroundColor(Color.rgb(125, 125, 125));

        // no description text
        mChart.getDescription().setEnabled(false);

        // enable touch gestures
        mChart.setTouchEnabled(true);

        // enable scaling and dragging
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);

        // if disabled, scaling can be done on x- and y-axis separately
        mChart.setPinchZoom(false);

        mChart.setDrawGridBackground(false);
        mChart.setMaxHighlightDistance(300);

        XAxis x = mChart.getXAxis();
        x.setEnabled(true);

        YAxis y = mChart.getAxisLeft();
        //y.setTypeface(mTfLight);
        y.setLabelCount(6, false);
        y.setTextColor(Color.WHITE);
        y.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        y.setDrawGridLines(true);
        y.setAxisLineColor(Color.WHITE);

        mChart.getAxisRight().setEnabled(false);

        // add data
        setData();

        mChart.getLegend().setEnabled(false);

        mChart.animateXY(2000, 2000);

        // do not forget to refresh the drawing
        mChart.invalidate();
    }

    /**
     * Sets the data for the graph to show based on the user's inputs
     */
    @SuppressWarnings({"OverlyLongMethod", "OverlyComplexMethod", "FeatureEnvy"})
    private void setData() {
        int year = modelInstance.getGraphYear();
        double minLat = modelInstance.getGraphMinLat();
        double minLng = modelInstance.getGraphMinLng();
        double maxLng = modelInstance.getGraphMaxLng();
        double maxLat = modelInstance.getGraphMaxLat();
        GraphType gType = modelInstance.getCurGraphType();
        Log.d("graph type", gType.toString());
        List<WaterPurityReport> pReps = modelInstance.getPurityReportList();
        Collections.sort(pReps);
        List<WaterAdditionalReport> aReps = modelInstance.getAdditionalReportList();
        Collections.sort(aReps);
        ArrayList<Entry> yValues = new ArrayList<>();
        int i = 0;
        float totalPPM = 0;
        int numReps = 0;

        if (gType == GraphType.VIRUS) {
            for (WaterPurityReport r: pReps) {
                //add option for either virus or contaminant ppm
                double lat = r.getLatitude();
                double lng = r.getLongitude();
                cal.setTime(r.getDateSubmitted());
                int month = cal.get(Calendar.MONTH);
                int repYear = cal.get(Calendar.YEAR);
                while (month > i) {
                    float avgPPM = 0;
                    if(numReps > 0) {
                        avgPPM = totalPPM / numReps;
                    }
                    yValues.add(new Entry(i, avgPPM));
                    i++;
                    totalPPM = 0;
                    numReps = 0;
                }
                if ((month == i) && (repYear == year) && (lat <= maxLat) && (lat >= minLat)
                        && (lng <= maxLng) && (lng >= minLng)) {
                    totalPPM += r.getVirusPPM();
                    numReps++;
                }
            }
            while (i < 12) {
                if (numReps > 0) {
                    yValues.add(new Entry(i, totalPPM / numReps));
                } else {
                    yValues.add(new Entry(i, 0));
                }
                i++;
                totalPPM = 0;
            }
        } else if (gType == GraphType.CONTAMINANT){
            for (WaterPurityReport r: pReps) {
                //add option for either virus or contaminant ppm
                cal.setTime(r.getDateSubmitted());
                int month = cal.get(Calendar.MONTH);
                int repYear = cal.get(Calendar.YEAR);
                while (month > i) {
                    float avgPPM = 0;
                    if(numReps > 0) {
                        avgPPM = totalPPM / numReps;
                    }
                    yValues.add(new Entry(i, avgPPM));
                    i++;
                    totalPPM = 0;
                    numReps = 0;
                }
                if ((month == i) && (repYear == year)) {
                    totalPPM += r.getContaminantPPM();
                    numReps++;
                }
            }
            while (i < 12) {
                if (numReps > 0) {
                    yValues.add(new Entry(i, totalPPM / numReps));
                } else {
                    yValues.add(new Entry(i, 0));
                }
                i++;
                totalPPM = 0;
            }
        } else if (gType == GraphType.PURPLENESS) {
            float numPurple = 0;
            for (WaterAdditionalReport r: aReps) {
                Log.d("graphing", "LOOKING AT SOME PURPLENESS REPORT");
                cal.setTime(r.getDateSubmitted());
                int month = cal.get(Calendar.MONTH);
                int repYear = cal.get(Calendar.YEAR);
                while (month > i) {
                    yValues.add(new Entry(i, numPurple));
                    i++;
                    numPurple = 0;
                }
                if ((month == i) && (repYear == year)) {
                    if (r.isPurple()) {
                        numPurple += 1;
                        Log.d("graphing", "found a purple report");
                    }
                    numReps++;
                }
            }
            while (i < 12) {
                yValues.add(new Entry(i, numPurple));
                i++;
                numPurple = 0;
            }
        }



        LineDataSet set1;

        if ((mChart.getData() != null) &&
                (mChart.getData().getDataSetCount() > 0)) {
            set1 = (LineDataSet)mChart.getData().getDataSetByIndex(0);
            set1.setValues(yValues);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        } else {
            // create a dataSet and give it a type
            set1 = new LineDataSet(yValues, "DataSet 1");

            set1.setMode(LineDataSet.Mode.LINEAR);
            //set1.setCubicIntensity(0.2f);
            //set1.setDrawFilled(true);
            set1.setDrawCircles(false);
            set1.setLineWidth(1.8f);
            set1.setCircleRadius(4f);
            set1.setCircleColor(Color.WHITE);
            set1.setHighLightColor(Color.rgb(244, 117, 117));
            set1.setColor(Color.WHITE);
            set1.setFillColor(Color.WHITE);
            set1.setFillAlpha(100);
            set1.setDrawHorizontalHighlightIndicator(false);
            set1.setFillFormatter(new IFillFormatter() {
                @Override
                public float getFillLinePosition(ILineDataSet dataSet,
                                                 LineDataProvider dataProvider) {
                    return -10;
                }
            });

            // create a data object with the dataSets
            LineData data = new LineData(set1);
            //data.setValueTypeface(mTfLight);
            data.setValueTextSize(15f);
            data.setValueTextColor(Color.BLACK);
            data.setDrawValues(true);

            // set data
            mChart.setData(data);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onBackPressed() {
        //makes the hardware back button return to the logged in screen
        Context context = HistoryGraphActivity.this;
        Intent intent = new Intent(context, GraphInfoActivity.class);
        finish();
        context.startActivity(intent);
    }
}