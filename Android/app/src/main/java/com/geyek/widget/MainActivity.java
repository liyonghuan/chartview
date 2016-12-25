package com.geyek.widget;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.geyek.widget.diagram.GeyekChartView;
import com.geyek.widget.diagram.chart.BarChart;

public class MainActivity extends AppCompatActivity {

    private GeyekChartView mCharView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCharView = (GeyekChartView) findViewById(R.id.chartview);
        mCharView.setPadding(40, 40, 40, 40);

        /*LineChart lineChart = new LineChart(mCharView);
        lineChart.setOffset(40);
        lineChart.setPoint(10);
        lineChart.setMaxItem(10);
        lineChart.setPoint(40);
        lineChart.setPoint(30);
        lineChart.setPoint(70);
        lineChart.setLineWidth(5);
        lineChart.setLineColor(Color.RED);
        lineChart.setAutoMaxValue(true);
        lineChart.setAutoMinValue(true);
        lineChart.setPoint(-10);
        lineChart.setPoint(0);
        lineChart.setPoint(10);
        lineChart.setPoint(40);
        mCharView.setChart(lineChart);

        GridGround gridGround = new GridGround(mCharView);
        gridGround.setLineColor(Color.parseColor("#0C0C0C"));
        gridGround.setSubLineColor(Color.parseColor("#151515"));
        gridGround.setLineWidth(2);
        gridGround.setSubLineWidth(0);
        gridGround.setXNum(1);
        gridGround.setSubXNum(0);
        gridGround.setYNum(3);
        gridGround.setSubYNum(5);
        mCharView.setBackground(gridGround);*/

        BarChart barChart = new BarChart(mCharView);
        barChart.setMaxValue(60);
        barChart.setMaxItem(10);
        barChart.setSpacing(10);
        barChart.setShaderColor(/*Color.parseColor("#00ECFF"), Color.BLACK, Color.RED, Color.YELLOW, Color.GRAY, */Color.parseColor("#0048FF"));
        barChart.setPoint(60);
        barChart.setPoint(33);
        barChart.setPoint(21);
        barChart.setPoint(5);
        barChart.setPoint(34);
        barChart.setPoint(15);
        barChart.setPoint(23);
        barChart.setFangXiang(BarChart.FangXiang.VERTICAL);
        mCharView.setChart(barChart);
    }
}
