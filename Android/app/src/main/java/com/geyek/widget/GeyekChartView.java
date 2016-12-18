package com.geyek.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LiHuan on 2016-12-18.
 */
public class GeyekChartView extends View {
    private BaseCoordinatorView mCoordinatorView;
    private BaseChartView mChartView;
    private int mHeight;
    private int mWidth;

    private int mPaddingLeft;
    private int mPaddingTop;
    private int mPaddingRight;
    private int mPaddingBottom;

    private int mLeftTagWidth;
    private int mTopTagHeight;
    private int mRightTagWidth;
    private int mBottomTagHeight;

    private int mXNum;
    private int mYNum;
    private int mSubXNum;
    private int mSubYNum;
    private int mLineWidth;
    private int mSubLineWidth;
    private int mLineColor;
    private int mSubLineColor;

    private int mFromColor;
    private int mToColor;

    private List<Integer> mData = new ArrayList<>();

    public GeyekChartView(Context context) {
        this(context, null);
    }

    public GeyekChartView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GeyekChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /*public GeyekChartView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }*/

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        mHeight = getMeasuredHeight();
        mWidth = getMeasuredWidth();
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint mLinePaint = new Paint();
        mLinePaint.setColor(mLineColor);
        mLinePaint.setStrokeWidth(mLineWidth);
        Paint mSubLinePaint = new Paint();
        mSubLinePaint.setStrokeWidth(mSubLineWidth);
        mSubLinePaint.setColor(mSubLineColor);

        //绘制水平线
        float height = mHeight - mPaddingTop - mPaddingBottom - mTopTagHeight - mBottomTagHeight;
        float cellHeight = height / mYNum;
        float cellSubHeight = 0;
        if (mSubYNum > 0) {
            cellSubHeight = cellHeight / mSubYNum;
        } else {
            cellSubHeight = cellHeight;
        }

        //绘制垂直线
        float width = mWidth - mPaddingLeft - mPaddingRight - mLeftTagWidth - mRightTagWidth;
        float cellWidth = width / mXNum;
        float cellSubWidth = 0;
        if (mSubXNum > 0) {
            cellSubWidth = cellWidth / mSubXNum;
        } else {
            cellSubWidth = cellWidth;
        }

        Paint p = new Paint();
        LinearGradient lg = new LinearGradient(0, 0, 0, mHeight, mFromColor, mToColor, Shader.TileMode.REPEAT);
        p.setShader(lg);

        for (int i = 0; i < mData.size(); i++) {
            Integer integer = mData.get(i);
            canvas.drawRect(
                    i * cellSubWidth
                    , mHeight - integer * cellSubHeight,
                    (i + 1) * cellSubWidth,
                    mHeight,
                    p
            );
        }

        for (int i = 0; i <= mYNum; i++) {
            canvas.drawLine(
                    0,
                    i * cellHeight,
                    mWidth,
                    i * cellHeight,
                    mLinePaint
            );

            if (mSubYNum > 0) {
                for (int j = 1; j < mSubYNum; j++) {
                    canvas.drawLine(
                            0,
                            i * cellHeight + j * cellSubHeight,
                            mWidth,
                            i * cellHeight + j * cellSubHeight,
                            mSubLinePaint
                    );
                }
            }
        }

        for (int i = 0; i <= mXNum; i++) {
            canvas.drawLine(
                    i * cellWidth,
                    0,
                    i * cellWidth,
                    mHeight, mLinePaint);

            if (mSubXNum > 0) {
                for (int j = 1; j < mSubXNum; j++) {
                    canvas.drawLine(
                            i * cellWidth + j * cellSubWidth,
                            0,
                            i * cellWidth + j * cellSubWidth,
                            mHeight,
                            mSubLinePaint
                    );
                }
            }
        }
    }

    public GeyekChartView setCoordinatorView(BaseCoordinatorView coordinatorView) {
        mCoordinatorView = coordinatorView;
        return this;
    }

    public GeyekChartView setChartView(BaseChartView chartView) {
        mChartView = chartView;
        return this;
    }

    public void setXNum(int xNum) {
        if (xNum > 0) {
            mXNum = xNum;
        } else {
            mXNum = 0;
        }
    }

    public void setYNum(int yNum) {
        if (yNum > 0) {
            mYNum = yNum;
        } else {
            mYNum = 0;
        }
    }

    public void setSubXNum(int subXNum) {
        if (subXNum > 0) {
            mSubXNum = subXNum;
        } else {
            mSubXNum = 0;
        }
    }

    public void setSubYNum(int subYNum) {
        if (subYNum > 0) {
            mSubYNum = subYNum;
        } else {
            mSubYNum = 0;
        }
    }

    public void setLineWidth(int width) {
        if (width > 0) {
            mLineWidth = width;
        } else {
            mLineWidth = 0;
        }
    }

    public void setSubLineWidth(int width) {
        if (width > 0) {
            mSubLineWidth = width;
        } else {
            mSubLineWidth = 0;
        }
    }

    public void setLineColor(int color) {
        mLineColor = color;
    }

    public void setSubLineColor(int color) {
        mSubLineColor = color;
    }

    public void setData(List list) {
        if (list != null) {
            mData.clear();
            mData.addAll(list);
        }
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

    public void setFromColor(int color) {
        mFromColor = color;
    }

    public void setToColor(int color) {
        mToColor = color;
    }
}
