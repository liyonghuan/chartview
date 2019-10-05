package org.devlang.widget.diagram.chart;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

import org.devlang.widget.diagram.ChartView;

/**
 * Created by LiHuan on 2016/12/23.
 */
public class CurveChart extends LineChart {
    private static final String TAG = "CurveChart";
    private Path mPath = new Path();

    public CurveChart(ChartView chartView) {
        super(chartView);
    }

    @Override
    public void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStrokeWidth(getLineWidth());
        paint.setColor(getLineColor());
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);

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
            float endX = offset + i * cellWidth;
            float endY = drawableHeight - (currentPoint - mMinValue) * cellHeight;

            //设置Path的起点
            mPath.moveTo(startX, startY);

            float tempx = (startX + endX) / 2;

            //设置贝塞尔曲线的控制点坐标和终点坐标
            mPath.cubicTo(tempx, startY, tempx, endY, endX, endY);

            //画出贝塞尔曲线
            canvas.drawPath(mPath, paint);

            mPath.reset();

            prePoint = currentPoint;
        }
    }
}
