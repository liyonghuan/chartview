package com.geyek.widget.diagram.chart;

import android.graphics.Canvas;

import com.geyek.widget.diagram.GeyekChartView;
import com.geyek.widget.diagram.kernel.BaseChart;

import java.util.HashMap;

/**
 * Created by LiHuan on 2016/12/23.
 */
public class LineChart extends BaseChart {
    private static final String TAG = "LineChart";
    private HashMap<Float, Float> mPointList = new HashMap<>();

    public LineChart(GeyekChartView chartView) {
        super(chartView);
    }

    @Override
    public void onDraw(Canvas canvas) {

    }
}
