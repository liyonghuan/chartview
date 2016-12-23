package com.geyek.widget.diagram;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import com.geyek.widget.diagram.kernel.BaseChart;
import com.geyek.widget.diagram.kernel.BaseGround;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LiHuan on 2016-12-18.
 */
public class GeyekChartView extends View {

    private BaseGround mBackground;
    private BaseGround mForeground;
    private List<BaseChart> mChartList = new ArrayList<>();

    private int mHeight;
    private int mWidth;

    private float mPaddingLeft;
    private float mPaddingTop;
    private float mPaddingRight;
    private float mPaddingBottom;

    public void setPaddingLeft(float paddingLeft) {
        if (paddingLeft < 0) {
            paddingLeft = 0;
        }
        mPaddingLeft = paddingLeft;
    }

    public void setPaddingRight(float paddingRight) {
        if (paddingRight < 0) {
            paddingRight = 0;
        }
        mPaddingRight = paddingRight;
    }

    public void setPaddingTop(float paddingTop) {
        if (paddingTop < 0) {
            paddingTop = 0;
        }
        mPaddingTop = paddingTop;
    }

    public void setPaddingBottom(float paddingBottom) {
        if (paddingBottom < 0) {
            paddingBottom = 0;
        }
        mPaddingBottom = paddingBottom;
    }

    public void setPadding(float paddingLeft, float paddingTop, float paddingRight, float paddingBottom) {
        setPaddingLeft(paddingLeft);
        setPaddingTop(paddingTop);
        setPaddingRight(paddingRight);
        setPaddingBottom(paddingBottom);
    }

    @Override
    public void setPadding(int left, int top, int right, int bottom) {
        setPaddingLeft(left);
        setPaddingTop(top);
        setPaddingRight(right);
        setPaddingBottom(bottom);
        super.setPadding(left, top, right, bottom);
    }

    private int mLeftTagWidth;
    private int mTopTagHeight;
    private int mRightTagWidth;
    private int mBottomTagHeight;

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
        canvas.save();
        canvas.clipRect(getStartX(), getStartY(), getEndX(), getEndY());
        canvas.translate(getStartX(), getStartY());

        if (mBackground != null) {
            mBackground.onDraw(canvas);
        }

        if (mChartList != null) {
            for (BaseChart chart : mChartList) {
                chart.onDraw(canvas);
            }
        }

        if (mForeground != null) {
            mForeground.onDraw(canvas);
        }

        canvas.restore();
    }

    public GeyekChartView setBackground(BaseGround background) {
        if (background == null) {
            return this;
        }
        mBackground = background;
        return this;
    }

    public GeyekChartView setForefground(BaseGround forefground) {
        if (forefground == null) {
            return this;
        }
        mForeground = forefground;
        return this;
    }

    public GeyekChartView setChart(BaseChart chart) {
        if (chart == null) {
            return this;
        }
        mChartList.add(chart);
        return this;
    }

    public float getDrawableHeight() {
        return getEndY() - getStartY();
    }

    public float getDrawableWidth() {
        return getEndX() - getStartX();
    }

    public float getStartX() {
        return mPaddingLeft + mLeftTagWidth;
    }

    public float getStartY() {
        return mPaddingTop + mTopTagHeight;
    }

    public float getEndX() {
        return mWidth - mPaddingRight - mRightTagWidth;
    }

    public float getEndY() {
        return mHeight - mPaddingBottom - mBottomTagHeight;
    }
}
