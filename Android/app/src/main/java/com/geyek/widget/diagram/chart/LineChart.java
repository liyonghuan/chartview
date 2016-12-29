package com.geyek.widget.diagram.chart;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.geyek.widget.diagram.GeyekChartView;
import com.geyek.widget.diagram.kernel.BaseChart;

/**
 * Created by LiHuan on 2016/12/23.
 */
public class LineChart extends BaseChart {
    private static final String TAG = "LineChart";

    private float mOffset;  //设置偏移量
    private float mCurrentPosition; //标记当前的坐标
    private int mLineColor;
    private float mLineWidth;

    public LineChart(GeyekChartView chartView) {
        super(chartView);
    }

    @Override
    public void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStrokeWidth(mLineWidth);
        paint.setColor(mLineColor);

        int size = mPointList.size();
        Float prePoint = null;
        float drawableHeight = mCharView.getDrawableHeight();
        float cellHeight = getCellHeight();
        float cellWidth = getCellWidth();
        float offset = getOffset();
        for (int i = 0; i < size; i++) {
            Float currentPoint = mPointList.get(i);
            if (prePoint == null) {
                prePoint = currentPoint;
                continue;
            }

            if (currentPoint == null) {
                continue;
            }

            float startX = offset + (i - 1) * cellWidth;
            float startY = drawableHeight - (prePoint - mMinValue) * cellHeight;
            float endX = mOffset + i * cellWidth;
            float endY = drawableHeight - (currentPoint - mMinValue) * cellHeight;

            canvas.drawLine(startX, startY, endX, endY, paint);

            prePoint = currentPoint;
        }
    }

    /**
     * 获取单位值所占用的高度
     * @return
     */
    public float getCellHeight() {
        float v = mMaxValue - mMinValue;
        if (v != 0) {
            return mCharView.getDrawableHeight() / v;
        } else {
            float v1 = mMaxValue - 0;
            if (v1 != 0) {
                return mCharView.getDrawableHeight() / v1;
            } else {
                return 0;
            }
        }
    }

    /**
     * 获取单位值所占用的宽度
     * @return
     */
    public float getCellWidth() {
        return mCharView.getDrawableWidth() / mMaxItem;
    }

    public void setOffset(float offset) {
        mOffset = offset;
    }

    public float getOffset() {
        return mOffset;
    }

    public void setLineColor(int lineColor) {
        mLineColor = lineColor;
    }

    public int getLineColor() {
        return mLineColor;
    }

    public void setLineWidth(float lineWidth) {
        if (lineWidth < 0) {
            lineWidth = 1;
        }
        mLineWidth = lineWidth;
    }

    public float getLineWidth() {
        return mLineWidth;
    }
}
