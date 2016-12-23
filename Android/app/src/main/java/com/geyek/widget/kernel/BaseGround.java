package com.geyek.widget.kernel;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.geyek.widget.GeyekChartView;

import java.util.List;

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
