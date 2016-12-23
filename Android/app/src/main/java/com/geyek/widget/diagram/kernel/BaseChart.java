package com.geyek.widget.diagram.kernel;

import android.graphics.Canvas;

import com.geyek.widget.diagram.GeyekChartView;

/**
 * Created by LiHuan on 2016-12-18.
 */
public abstract class BaseChart {
    protected GeyekChartView mCharView;

    public BaseChart(GeyekChartView chartView) {
        mCharView = chartView;
    }

    public abstract void onDraw(Canvas canvas);
}
