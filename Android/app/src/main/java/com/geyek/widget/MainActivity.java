package com.geyek.widget;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.geyek.widget.diagram.GeyekChartView;
import com.geyek.widget.diagram.chart.BarChart;
import com.geyek.widget.diagram.ground.GridGround;

public class MainActivity extends AppCompatActivity {

    private GeyekChartView mCharView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCharView = (GeyekChartView) findViewById(R.id.chartview);
        mCharView.setPadding(40, 40, 40, 40);

        GridGround gridGround = new GridGround(mCharView);
        gridGround.setLineColor(Color.parseColor("#0C0C0C"));
        gridGround.setSubLineColor(Color.parseColor("#151515"));
        gridGround.setLineWidth(2);
        gridGround.setSubLineWidth(0);
        gridGround.setXNum(1);
        gridGround.setSubXNum(0);
        gridGround.setYNum(3);
        gridGround.setSubYNum(5);
        mCharView.setBackground(gridGround);

        BarChart barChart = new BarChart(mCharView);
        barChart.setMaxXValue(10);
        barChart.setMaxYValue(60);
        barChart.setSpacing(10);
        barChart.setShaderColor(/*Color.parseColor("#00ECFF"), Color.BLACK, Color.RED, Color.YELLOW, Color.GRAY, */Color.parseColor("#0048FF"));
        barChart.setData(60);
        barChart.setData(33);
        barChart.setData(21);
        barChart.setData(5);
        barChart.setData(34);
        barChart.setData(15);
        barChart.setData(23);
        barChart.setFangXiang(BarChart.FangXiang.VERTICAL);
        mCharView.setChart(barChart);
    }
}
