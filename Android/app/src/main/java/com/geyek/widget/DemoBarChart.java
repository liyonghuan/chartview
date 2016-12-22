package com.geyek.widget;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LiHuan on 2016/12/19.
 */
public class DemoBarChart extends BaseChart {
    private static final String TAG = "DemoBarChart";

    private List<Integer> mData = new ArrayList<>();
    private float mMaxXValue;
    private float mMaxYValue;
    private int[] mShaderColor;
    private float[] mShaderPosition;
    private int mColor = Color.BLACK;

    private FangXiang mFangXiang = FangXiang.VERTICAL;

    enum FangXiang {
        VERTICAL, HORIZONTAL;
    }

    public void setData(int value, int position) {
        if (mData.size() > position) {
            mData.remove(position);
            mData.add(position, value);
        } else {
            mData.add(value);
        }
    }

    public void setData(int value) {
        mData.add(value);
    }

    public void setShaderColor(int... color) {
        mShaderColor = color;
    }

    public void setShaderPosition(float... position) {
        mShaderPosition = position;
    }

    public void setColor(int color) {
        mColor = color;
        mShaderColor = null;
    }

    public float getCellWidth() {
        return (mEndX - mStartX) / mMaxXValue;
    }

    public float getCellHeight() {
        return (mEndY - mStartY) / mMaxYValue;
    }

    public void setMaxXValue(float maxXValue) {
        if (maxXValue > 0) {
            mMaxXValue = maxXValue;
        } else {
            mMaxXValue = 0;
        }
    }

    public void setMaxYValue(float maxYValue) {
        if (maxYValue > 0) {
            mMaxYValue = maxYValue;
        } else {
            mMaxYValue = 0;
        }
    }

    @Override
    public void onDraw(Canvas canvas) {
        Paint mBarPaint = new Paint();

        if (mShaderColor != null) {
            LinearGradient lg;
            switch (mFangXiang) {
                case HORIZONTAL:
                    lg = new LinearGradient(mStartX, 0, mEndX, 0, mShaderColor, mShaderPosition, Shader.TileMode.REPEAT);
                    break;
                case VERTICAL:
                default:
                    lg = new LinearGradient(0, mStartY, 0, mEndY, mShaderColor, mShaderPosition, Shader.TileMode.REPEAT);
                    break;
            }
            mBarPaint.setShader(lg);
        } else {
            mBarPaint.setColor(mColor);
        }

        float cellWidth = getCellWidth();
        float cellHeight = getCellHeight();
        int size = mData.size();
        for (int i = 0; i < size; i++) {
            Integer integer = mData.get(i);
            switch (mFangXiang) {
                case HORIZONTAL:
                    canvas.drawRect(
                            0,
                            (size - i - 1) * cellHeight,
                            integer * cellWidth,
                            (size - i) * cellHeight,
                            mBarPaint
                    );
                    break;
                case VERTICAL:
                default:
                    canvas.drawRect(
                            i * cellWidth,
                            mEndY - integer * cellHeight,
                            (i + 1) * cellWidth,
                            mEndY,
                            mBarPaint
                    );
                    break;
            }
        }
    }

    public void setFangXiang(FangXiang fangXiang) {
        if (fangXiang != null) {
            mFangXiang = fangXiang;
        }
    }
}
