package com.geyek.widget;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.geyek.widget.diagram.GeyekChartView;
import com.geyek.widget.diagram.chart.CurveChart;

public class MainActivity extends AppCompatActivity {

    private GeyekChartView mCharView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCharView = (GeyekChartView) findViewById(R.id.chartview);
        /*mCharView.setBackgroundColor(Color.DKGRAY);*/

        CurveChart curveChart = new CurveChart(mCharView);
        curveChart.setMaxItem(10);
        curveChart.setLineColor(Color.RED);
        curveChart.setLineWidth(5);
        curveChart.setMaxValue(50);
        curveChart.setMinValue(0);
        curveChart.setPoint(0F);
        curveChart.setPoint(10F);
        curveChart.setPoint(50F);
        curveChart.setPoint(30F);
        curveChart.setPoint(40F);
        mCharView.setChart(curveChart);
    }
}
