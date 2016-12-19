package com.geyek.widget;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private GeyekChartView mCharView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCharView = (GeyekChartView) findViewById(R.id.chartview);
        DemoBackground demoBackground = new DemoBackground();
        demoBackground.setLineColor(Color.parseColor("#595959"));
        demoBackground.setSubLineColor(Color.parseColor("#1F1F1F"));
        demoBackground.setLineWidth(5);
        demoBackground.setSubLineWidth(2);
        demoBackground.setXNum(3);
        demoBackground.setSubXNum(5);
        demoBackground.setYNum(3);
        demoBackground.setSubYNum(10);
        mCharView.setForefground(demoBackground);

        DemoBarChart demoBarChart = new DemoBarChart();
        demoBarChart.setMaxXValue(15);
        demoBarChart.setMaxYValue(30);
        demoBarChart.setShaderColor(Color.RED, Color.YELLOW);
        demoBarChart.setData(10);
        demoBarChart.setData(20);
        demoBarChart.setData(30);
        demoBarChart.setData(15);
        demoBarChart.setData(3);
        demoBarChart.setData(24);
        demoBarChart.setData(13);
        demoBarChart.setData(22);
        demoBarChart.setData(7);
        demoBarChart.setData(15);
        demoBarChart.setData(28);
        demoBarChart.setData(14);
        demoBarChart.setData(5, 2);
        mCharView.setChart(demoBarChart);
    }
}
