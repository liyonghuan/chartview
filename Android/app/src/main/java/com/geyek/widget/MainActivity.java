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
        demoBackground.setXNum(2);
        demoBackground.setSubXNum(10);
        demoBackground.setYNum(1);
        demoBackground.setSubYNum(0);
        mCharView.setForefground(demoBackground);

        DemoBarChart demoBarChart = new DemoBarChart();
        demoBarChart.setMaxXValue(60);
        demoBarChart.setMaxYValue(1);
        demoBarChart.setShaderColor(Color.RED, Color.YELLOW);
        demoBarChart.setData(54);
        demoBarChart.setFangXiang(DemoBarChart.FangXiang.HORIZONTAL);
        mCharView.setChart(demoBarChart);
    }
}
