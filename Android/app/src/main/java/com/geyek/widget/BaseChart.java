package com.geyek.widget;

import android.graphics.Canvas;

/**
 * Created by LiHuan on 2016-12-18.
 */
public abstract class BaseChart {
    protected float mStartX;
    protected float mStartY;
    protected float mEndX;
    protected float mEndY;

    public abstract void onDraw(Canvas canvas);

    public void setStarX(float starX) {
        mStartX = starX;
    }

    public void setStartY(float startY) {
        mStartY = startY;
    }

    public void setEndX(float endX) {
        mEndX = endX;
    }

    public void setEndY(float endY) {
        mEndY = endY;
    }
}
