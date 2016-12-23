package com.geyek.widget.chart;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;

import com.geyek.widget.GeyekChartView;
import com.geyek.widget.kernel.BaseChart;

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
    private float mSpacing;
    private int[] mShaderColor;
    private float[] mShaderPosition;
    private int mColor = Color.BLACK;

    private FangXiang mFangXiang = FangXiang.VERTICAL;

    public DemoBarChart(GeyekChartView geyekChartView) {
        super(geyekChartView);
    }

    public enum FangXiang {
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
        float cellWidth;
        switch (mFangXiang) {
            case HORIZONTAL:
                cellWidth = mCharView.getDrawableWidth() / mMaxXValue;
                break;
            case VERTICAL:
            default:
                cellWidth = (mCharView.getDrawableWidth() - mSpacing * (mMaxXValue - 1)) / mMaxXValue;
                break;
        }
        return cellWidth;
    }

    public float getCellHeight() {
        float cellHeight;
        switch (mFangXiang) {
            case HORIZONTAL:
                cellHeight = (mCharView.getDrawableHeight() - mSpacing * (mMaxYValue - 1)) / mMaxYValue;
                break;
            case VERTICAL:
            default:
                cellHeight = mCharView.getDrawableHeight() / mMaxYValue;
                break;
        }
        return cellHeight;
    }

    public void setMaxXValue(float maxXValue) {
        if (maxXValue > 0) {
            mMaxXValue = maxXValue;
        } else {
            mMaxXValue = 0;
        }
    }

    public float getMaxXValue() {
        return mMaxXValue;
    }

    public void setMaxYValue(float maxYValue) {
        if (maxYValue > 0) {
            mMaxYValue = maxYValue;
        } else {
            mMaxYValue = 0;
        }
    }

    public float getMaxYValue() {
        return mMaxYValue;
    }

    @Override
    public void onDraw(Canvas canvas) {
        Paint mBarPaint = new Paint();

        if (mShaderColor != null) {
            LinearGradient lg;
            switch (mFangXiang) {
                case HORIZONTAL:
                    lg = new LinearGradient(0, 0, mCharView.getDrawableWidth(), 0, mShaderColor, mShaderPosition, Shader.TileMode.REPEAT);
                    break;
                case VERTICAL:
                default:
                    lg = new LinearGradient(0, 0, 0, mCharView.getDrawableHeight(), mShaderColor, mShaderPosition, Shader.TileMode.REPEAT);
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
                            (mMaxYValue - i - 1) * cellHeight + (mMaxYValue - 1 - i) * mSpacing,
                            integer * cellWidth,
                            (mMaxYValue - i) * cellHeight + (mMaxYValue - 1 - i) * mSpacing,
                            mBarPaint
                    );
                    break;
                case VERTICAL:
                default:
                    canvas.drawRect(
                            i * cellWidth + i * mSpacing,
                            mCharView.getDrawableHeight() - integer * cellHeight,
                            (i + 1) * cellWidth + i * mSpacing,
                            mCharView.getDrawableHeight(),
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

    public void setSpacing(float spacing) {
        if (spacing < 0) {
            spacing = 0;
        }
        mSpacing = spacing;
    }
}
