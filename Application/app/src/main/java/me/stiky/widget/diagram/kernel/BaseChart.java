package me.stiky.widget.diagram.kernel;

import android.graphics.Canvas;

import me.stiky.widget.diagram.ChartView;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LiHuan on 2016-12-18.
 */
public abstract class BaseChart {
    protected ChartView mCharView;
    protected List<Float> mPointList = new ArrayList<>();
    protected int mMaxItem = 1; //当前界面显示的最大条目数,最小为1
    protected float mMaxValue = Float.MIN_VALUE;    //当前单个条目最大的值
    protected float mMinValue = Float.MAX_VALUE;
    protected boolean mIsAutoMaxValue;
    protected boolean mIsAutoMinValue;

    public BaseChart(ChartView chartView) {
        mCharView = chartView;
    }

    public abstract void onDraw(Canvas canvas);

    public void setMaxItem(int item) {
        if (item < 1) {
            item = 1;
        }
        mMaxItem = item;
    }

    public float getMaxItem() {
        return mMaxItem;
    }

    public void setAutoMaxValue(boolean autoMaxValue) {
        mIsAutoMaxValue = autoMaxValue;
        refreshMaxAndMinValue();
    }

    public void setAutoMinValue(boolean autoMinValue) {
        mIsAutoMinValue = autoMinValue;
        refreshMaxAndMinValue();
    }

    public boolean isAutoMaxValue() {
        return mIsAutoMaxValue;
    }

    public boolean isAutoMinValue() {
        return mIsAutoMinValue;
    }

    /**
     * @param maxValue
     */
    public void setMaxValue(float maxValue) {
        if (mMaxValue < maxValue) {
            mMaxValue = maxValue;
        }
    }

    /**
     * @return
     */
    public float getMaxValue() {
        return mMaxValue;
    }

    /**
     * @param minValue
     */
    public void setMinValue(float minValue) {
        if (mMinValue > minValue) {
            mMinValue = minValue;
        }
    }

    /**
     * @return
     */
    public float getMinValue() {
        return mMinValue;
    }

    /**
     * @return
     */
    public abstract float getCellHeight();

    /**
     * @return
     */
    public abstract float getCellWidth();

    /**
     * Create a Chart from Class.
     *
     * @param chartView
     * @param clazz
     * @return
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    public static BaseChart newInstance(ChartView chartView, Class<? extends BaseChart> clazz) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class[] paramTypes = {ChartView.class};
        Object[] params = {chartView};

        if (clazz == null) {
            return null;
        }
        Constructor con = clazz.getConstructor(paramTypes);
        if (con == null) {
            return null;
        }
        Object obj = con.newInstance(params);
        if (obj != null && obj instanceof BaseChart) {
            return (BaseChart) obj;
        } else {
            return null;
        }
    }

    //---------------------------------------Set Point Data Method Area. Start---------------------------------------

    /**
     * Calculate the maximum and minimum value.
     */
    protected void refreshMaxAndMinValue() {
        for (Float point : mPointList) {
            if (point == null) {
                continue;
            }

            if (mIsAutoMaxValue) {
                if (mMaxValue < point) {
                    mMaxValue = point;
                }
            }

            if (mIsAutoMinValue) {
                if (mMinValue > point) {
                    mMinValue = point;
                }
            }
        }
    }

    /**
     * @param point
     * @return
     */
    public void setPoint(Float point) {
        mPointList.add(point);
        refreshMaxAndMinValue();
    }

    /**
     * @param point
     * @param position
     */
    public void setPoint(Float point, int position) {
        if (position < 0) {
            position = mPointList.size();
        }
        mPointList.add(position, point);
        refreshMaxAndMinValue();
    }

    /**
     * @param pointList
     * @return
     */
    public void setPointList(List<Float> pointList) {
        if (pointList == null) {
            return;
        }
        mPointList.addAll(pointList);
        refreshMaxAndMinValue();
    }

    /**
     * @param pointList
     * @param position
     * @return
     */
    public void setPointList(List<Float> pointList, int position) {
        if (position < 0) {
            position = mPointList.size();
        }

        if (pointList == null) {
            return;
        }
        mPointList.addAll(position, pointList);
        refreshMaxAndMinValue();
    }

    /**
     * @param point
     * @param position
     */
    public void replace(Float point, int position) {
        int size = mPointList.size();
        if (position < 0) {
            position = size;
        }
        if (size > position) {
            mPointList.remove(position);
            mPointList.add(position, point);
        } else {
            mPointList.add(point);
        }
        refreshMaxAndMinValue();
    }

    /**
     * @param pointList
     * @param fromPosition
     */
    public void replace(List<Float> pointList, int fromPosition) {
        if (pointList == null) {
            return;
        }

        int size = mPointList.size();
        if (fromPosition < 0) {
            fromPosition = size;
        }

        for (Float point : pointList) {
            if (size > fromPosition) {
                mPointList.remove(fromPosition);
                mPointList.add(fromPosition++, point);
            } else {
                mPointList.add(point);
            }
        }

        refreshMaxAndMinValue();
    }

    /**
     * Clear all of the collection's data.
     */
    public void clear() {
        mPointList.clear();

        if (mIsAutoMaxValue) {
            mMaxValue = Float.MIN_VALUE;
        }

        if (mIsAutoMinValue) {
            mMinValue = Float.MAX_VALUE;
        }
    }

    //---------------------------------------Set Point Data Method Area. End---------------------------------------
}
