package org.devlang.widget.diagram.ground;

import android.graphics.Canvas;
import android.graphics.Paint;

import org.devlang.widget.diagram.ChartView;
import org.devlang.widget.diagram.kernel.BaseGround;

/**
 * Created by LiHuan on 2016/12/19.
 */
public class GridGround extends BaseGround {
    private static final String TAG = "GridGround";

    private int mXNum;
    private int mYNum;
    private int mSubXNum;
    private int mSubYNum;

    private Paint mLinePaint = new Paint();
    private Paint mSubLinePaint = new Paint();

    public GridGround(ChartView chartView) {
        super(chartView);
    }

    @Override
    public void onDraw(Canvas canvas) {
        //绘制水平线
        float height = mCharView.getDrawableHeight();
        float cellHeight = height / mYNum;
        float cellSubHeight = 0;
        if (mSubYNum > 0) {
            cellSubHeight = cellHeight / mSubYNum;
        } else {
            cellSubHeight = cellHeight;
        }
        for (int i = 0; i <= mYNum; i++) {
            canvas.drawLine(
                    0,
                    i * cellHeight,
                    mCharView.getDrawableWidth(),
                    i * cellHeight,
                    mLinePaint
            );

            if (mSubYNum > 0) {
                for (int j = 1; j < mSubYNum; j++) {
                    canvas.drawLine(
                            0,
                            i * cellHeight + j * cellSubHeight,
                            mCharView.getDrawableWidth(),
                            i * cellHeight + j * cellSubHeight,
                            mSubLinePaint
                    );
                }
            }
        }

        //绘制垂直线
        float width = mCharView.getDrawableWidth();
        float cellWidth = width / mXNum;
        float cellSubWidth = 0;
        if (mSubXNum > 0) {
            cellSubWidth = cellWidth / mSubXNum;
        } else {
            cellSubWidth = cellWidth;
        }

        for (int i = 0; i <= mXNum; i++) {
            canvas.drawLine(
                    i * cellWidth,
                    0,
                    i * cellWidth,
                    mCharView.getDrawableHeight(),
                    mLinePaint
            );

            if (mSubXNum > 0) {
                for (int j = 1; j < mSubXNum; j++) {
                    canvas.drawLine(
                            i * cellWidth + j * cellSubWidth,
                            0,
                            i * cellWidth + j * cellSubWidth,
                            mCharView.getDrawableHeight(),
                            mSubLinePaint
                    );
                }
            }
        }
    }

    public void setLineWidth(int width) {
        if (width > 0) {
            mLinePaint.setStrokeWidth(width);
        } else {
            mLinePaint.setStrokeWidth(0);
        }
    }

    public void setSubLineWidth(int width) {
        if (width > 0) {
            mSubLinePaint.setStrokeWidth(width);
        } else {
            mSubLinePaint.setStrokeWidth(0);
        }
    }

    public void setLineColor(int color) {
        mLinePaint.setColor(color);
    }

    public void setSubLineColor(int color) {
        mSubLinePaint.setColor(color);
    }

    public void setXNum(int XNum) {
        mXNum = XNum;
    }

    public void setSubXNum(int subXNum) {
        mSubXNum = subXNum;
    }

    public void setYNum(int YNum) {
        mYNum = YNum;
    }

    public void setSubYNum(int subYNum) {
        mSubYNum = subYNum;
    }
}
