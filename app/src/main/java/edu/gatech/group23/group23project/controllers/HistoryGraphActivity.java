package edu.gatech.group23.group23project.controllers;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import edu.gatech.group23.group23project.R;
import edu.gatech.group23.group23project.model.Model;
import edu.gatech.group23.group23project.model.WaterPurityReport;

public class HistoryGraphActivity extends AppCompatActivity {
    private Calendar cal = Calendar.getInstance();
    private Model modelInstance = Model.getInstance();
    private LineChart mChart;

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

        // dont forget to refresh the drawing
        mChart.invalidate();
    }

    private void setData() {
        int year = modelInstance.getGraphYear();
        Model.GraphType gType = modelInstance.getCurGraphType();
        List<WaterPurityReport> pReps = modelInstance.getPurityReportList();
        Collections.sort(pReps);
        ArrayList<Entry> yVals = new ArrayList<>();
        int i = 0;
        float totalPPM = 0;
        int numReps = 0;


        if (gType == Model.GraphType.VIRUS) {
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
                    yVals.add(new Entry(i, avgPPM));
                    i++;
                    totalPPM = 0;
                    numReps = 0;
                }
                if (month == i && repYear == year) {
                    totalPPM += r.getVirusPPM();
                    numReps++;
                }
            }
        } else {
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
                    yVals.add(new Entry(i, avgPPM));
                    i++;
                    totalPPM = 0;
                    numReps = 0;
                }
                if (month == i && repYear == year) {
                    totalPPM += r.getContaminantPPM();
                    numReps++;
                }
            }
        }

        while (i < 12) {
            if (numReps > 0) {
                yVals.add(new Entry(i, totalPPM / numReps));
            } else {
                yVals.add(new Entry(i, 0));
            }
            i++;
            totalPPM = 0;
        }

        LineDataSet set1;

        if (mChart.getData() != null &&
                mChart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet)mChart.getData().getDataSetByIndex(0);
            set1.setValues(yVals);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(yVals, "DataSet 1");

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
                public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
                    return -10;
                }
            });

            // create a data object with the datasets
            LineData data = new LineData(set1);
            //data.setValueTypeface(mTfLight);
            data.setValueTextSize(15f);
            data.setValueTextColor(Color.BLACK);
            data.setDrawValues(true);

            // set data
            mChart.setData(data);
        }
    }

}