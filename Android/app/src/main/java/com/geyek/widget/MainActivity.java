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
        mCharView.setXNum(3);
        mCharView.setSubXNum(5);
        mCharView.setYNum(3);
        mCharView.setSubYNum(10);
        mCharView.setLineColor(Color.parseColor("#595959"));
        mCharView.setLineWidth(5);
        mCharView.setSubLineColor(Color.parseColor("#1F1F1F"));
        mCharView.setSubLineWidth(2);
        mCharView.setFromColor(Color.RED);
        mCharView.setToColor(Color.YELLOW);
        mCharView.setData(10);
        mCharView.setData(20);
        mCharView.setData(30);
        mCharView.setData(15);
        mCharView.setData(3);
        mCharView.setData(24);
        mCharView.setData(13);
        mCharView.setData(22);
        mCharView.setData(7);
        mCharView.setData(15);
        mCharView.setData(30);
        mCharView.setData(14);
        mCharView.setData(5, 2);
    }
}
