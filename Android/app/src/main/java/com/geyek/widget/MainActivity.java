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
        mCharView.setPadding(40, 40, 40, 40);

        DemoBackground demoBackground = new DemoBackground();
        demoBackground.setLineColor(Color.parseColor("#0C0C0C"));
        demoBackground.setSubLineColor(Color.parseColor("#151515"));
        demoBackground.setLineWidth(20);
        demoBackground.setSubLineWidth(10);
        demoBackground.setXNum(2);
        demoBackground.setSubXNum(10);
        demoBackground.setYNum(1);
        demoBackground.setSubYNum(0);
        //mCharView.setForefground(demoBackground);

        DemoBarChart demoBarChart = new DemoBarChart();
        demoBarChart.setMaxXValue(60);
        demoBarChart.setMaxYValue(4);
        demoBarChart.setSpacing(40);
        demoBarChart.setShaderColor(Color.parseColor("#00ECFF"), Color.parseColor("#0048FF"));
        demoBarChart.setData(60);
        demoBarChart.setData(33);
        demoBarChart.setData(21);
        demoBarChart.setData(45);
        demoBarChart.setFangXiang(DemoBarChart.FangXiang.HORIZONTAL);
        mCharView.setChart(demoBarChart);
    }
}
