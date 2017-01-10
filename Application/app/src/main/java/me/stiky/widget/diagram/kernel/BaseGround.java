package me.stiky.widget.diagram.kernel;

import android.graphics.Canvas;

import me.stiky.widget.diagram.ChartView;

/**
 * Created by LiHuan on 2016-12-18.
 */
public abstract class BaseGround {
    protected ChartView mCharView;

    public BaseGround(ChartView chartView) {
        mCharView = chartView;
    }

    public abstract void onDraw(Canvas canvas);
}
