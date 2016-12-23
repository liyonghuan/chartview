package com.geyek.widget.diagram.kernel;

import android.graphics.Canvas;

import com.geyek.widget.diagram.GeyekChartView;

/**
 * Created by LiHuan on 2016-12-18.
 */
public abstract class BaseGround {
    protected GeyekChartView mCharView;

    public BaseGround(GeyekChartView chartView) {
        mCharView = chartView;
    }

    public abstract void onDraw(Canvas canvas);
}
