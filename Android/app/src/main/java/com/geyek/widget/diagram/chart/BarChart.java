package com.geyek.widget.diagram.chart;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;

import com.geyek.widget.diagram.GeyekChartView;
import com.geyek.widget.diagram.kernel.BaseChart;

/**
 * Created by LiHuan on 2016/12/19.
 */
public class BarChart extends BaseChart {
    private static final String TAG = "BarChart";

    private float mSpacing;
    private int[] mShaderColor;
    private float[] mShaderPosition;

    private FangXiang mFangXiang = FangXiang.VERTICAL;

    public BarChart(GeyekChartView geyekChartView) {
        super(geyekChartView);
    }

    public enum FangXiang {
        VERTICAL, HORIZONTAL;
    }

    public void setShaderColor(int... color) {
        mShaderColor = color;
    }

    public void setShaderPosition(float... position) {
        mShaderPosition = position;
    }

    public float getCellWidth() {
        float cellWidth;
        switch (mFangXiang) {
            case HORIZONTAL:
                cellWidth = mCharView.getDrawableWidth() / mMaxValue;
                break;
            case VERTICAL:
            default:
                cellWidth = (mCharView.getDrawableWidth() - mSpacing * (mMaxItem - 1)) / mMaxItem;
                break;
        }
        return cellWidth;
    }

    public float getCellHeight() {
        float cellHeight;
        switch (mFangXiang) {
            case HORIZONTAL:
                cellHeight = (mCharView.getDrawableHeight() - mSpacing * (mMaxItem - 1)) / mMaxItem;
                break;
            case VERTICAL:
            default:
                cellHeight = mCharView.getDrawableHeight() / mMaxValue;
                break;
        }
        return cellHeight;
    }

    @Override
    public void onDraw(Canvas canvas) {
        Paint mBarPaint = new Paint();

        if (mShaderColor != null && mShaderColor.length > 1) {
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
            mBarPaint.setColor((mShaderColor == null || mShaderColor.length < 1) ? Color.BLACK : mShaderColor[0]);
        }

        float cellWidth = getCellWidth();
        float cellHeight = getCellHeight();
        int size = mPointList.size();
        for (int i = 0; i < size; i++) {
            Float value = mPointList.get(i);
            switch (mFangXiang) {
                case HORIZONTAL:
                    canvas.drawRect(
                            0,
                            (mMaxItem - i - 1) * cellHeight + (mMaxItem - 1 - i) * mSpacing,
                            value * cellWidth,
                            (mMaxItem - i) * cellHeight + (mMaxItem - 1 - i) * mSpacing,
                            mBarPaint
                    );
                    break;
                case VERTICAL:
                default:
                    canvas.drawRect(
                            i * cellWidth + i * mSpacing,
                            mCharView.getDrawableHeight() - value * cellHeight,
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
