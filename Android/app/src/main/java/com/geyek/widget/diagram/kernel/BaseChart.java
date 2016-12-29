package com.geyek.widget.diagram.kernel;

import android.graphics.Canvas;

import com.geyek.widget.diagram.GeyekChartView;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LiHuan on 2016-12-18.
 */
public abstract class BaseChart {
    protected GeyekChartView mCharView;
    protected List<Float> mPointList = new ArrayList<>();
    protected int mMaxItem = 1; //当前界面显示的最大条目数,最小为1
    protected float mMaxValue;    //当前单个条目最大的值
    protected float mMinValue;
    protected boolean mIsAutoMaxValue;
    protected boolean mIsAutoMinValue;

    public BaseChart(GeyekChartView chartView) {
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
        refreshMaxValue();
    }

    public void setAutoMinValue(boolean autoMinValue) {
        mIsAutoMinValue = autoMinValue;

    }

    protected void refreshMaxValue() {
        if (!mIsAutoMaxValue) {
            return;
        }
        float maxValue = 0;
        for (Float point : mPointList) {
            if (point == null) {
                continue;
            }
            if (maxValue < point) {
                maxValue = point;
            }
        }
        if (mMaxValue < maxValue) {
            mMaxValue = maxValue;
        }
    }

    protected void refreshMaxValue(float point) {
        if (!mIsAutoMaxValue) {
            return;
        }
        if (mMaxValue < point) {
            mMaxValue = point;
        }
    }

    protected void refreshMinValue() {
        if (!mIsAutoMaxValue) {
            return;
        }
        float minValue = 0;
        for (Float point : mPointList) {
            if (point == null) {
                continue;
            }
            if (minValue > point) {
                minValue = point;
            }
        }
        if (mMinValue > minValue) {
            mMinValue = minValue;
        }
    }

    protected void refreshMinValute(float point) {
        if (!mIsAutoMinValue) {
            return;
        }
        if (mMinValue > point) {
            mMinValue = point;
        }
    }

    public boolean isAutoMaxValue() {
        return mIsAutoMaxValue;
    }

    public boolean isAutoMinValue() {
        return mIsAutoMinValue;
    }

    public boolean setPointList(List<Float> pointList) {
        if (pointList == null) {
            return false;
        }
        boolean isSuccess = mPointList.addAll(pointList);
        refreshMaxValue();
        refreshMinValue();
        return isSuccess;
    }

    public boolean setPoint(float point) {
        boolean isSuccess = mPointList.add(point);
        refreshMaxValue(point);
        refreshMinValute(point);
        return isSuccess;
    }

    public void setPoint(float point, int position) {
        if (mPointList.size() > position) {
            mPointList.remove(position);
            mPointList.add(position, point);
        } else {
            mPointList.add(point);
        }
        refreshMaxValue(point);
        refreshMinValute(point);
    }

    public void clear() {
        mPointList.clear();
    }

    public void setMaxValue(float maxValue) {
        if (mMaxValue < maxValue) {
            mMaxValue = maxValue;
        }
        if (mMinValue > mMaxValue) {
            float temp = mMinValue;
            mMinValue = mMaxValue;
            mMaxValue = temp;
        }
    }

    public float getMaxValue() {
        return mMaxValue;
    }

    public void setMinValue(float minValue) {
        if (mMinValue > minValue) {
            mMinValue = minValue;
        }
        if (mMinValue > mMaxValue) {
            float temp = mMinValue;
            mMinValue = mMaxValue;
            mMaxValue = temp;
        }
    }

    public float getMinValue() {
        return mMinValue;
    }

    public abstract float getCellHeight();

    public abstract float getCellWidth();

    /**
     * The class whose name is Builder, it create some chart which have the same property.
     */
    public static class Builder {
        private GeyekChartView mGeyekChartView;
        private List<BaseChart> mChartList = new ArrayList<>();
        protected int mMaxItem = 1; //当前界面显示的最大条目数,最小为1
        protected float mMaxValue;    //当前单个条目最大的值
        protected float mMinValue;
        protected boolean mIsAutoMaxValue;
        protected boolean mIsAutoMinValue;

        public Builder(GeyekChartView geyekChartView) {
            mGeyekChartView = geyekChartView;
        }

        public BaseChart build(Class<? extends BaseChart> clazz) {
            BaseChart baseChart = null;
            try {
                baseChart = newInstance(mGeyekChartView, clazz);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } finally {
                if (baseChart != null) {
                    initial(baseChart);
                    mChartList.add(baseChart);
                }
                return baseChart;
            }
        }

        private void initial(BaseChart chart) {
            chart.setMaxItem(mMaxItem);
            chart.setMaxValue(mMaxValue);
            chart.setMinValue(mMinValue);
            chart.setAutoMaxValue(mIsAutoMaxValue);
            chart.setAutoMinValue(mIsAutoMinValue);
        }

        private void refresh() {
            for (BaseChart chart : mChartList) {
                initial(chart);
            }
        }

        public void setMaxItem(int item) {
            for (BaseChart chart : mChartList) {
                chart.setMaxItem(item);
            }
        }

        public float getMaxItem() {
            return mMaxItem;
        }

        public void setAutoMaxValue(boolean autoMaxValue) {
            for (BaseChart chart : mChartList) {
                chart.setAutoMaxValue(autoMaxValue);
            }
        }

        public void setAutoMinValue(boolean autoMinValue) {
            for (BaseChart chart : mChartList) {
                chart.setAutoMinValue(autoMinValue);
            }
        }

        public boolean isAutoMaxValue() {
            return mIsAutoMaxValue;
        }

        public boolean isAutoMinValue() {
            return mIsAutoMinValue;
        }

        public void setPointList(List<Float> pointList) {
            for (BaseChart chart : mChartList) {
                chart.setPointList(pointList);
            }
        }

        public void setPoint(float point) {
            for (BaseChart chart : mChartList) {
                chart.setPoint(point);
            }
        }

        public void setPoint(float point, int position) {
            for (BaseChart chart : mChartList) {
                chart.setPoint(point, position);
            }
        }

        public void clear() {
            for (BaseChart chart : mChartList) {
                chart.clear();
            }
        }

        public void clearChartList() {
            mChartList.clear();
        }

        public void setMaxValue(float maxValue) {
            for (BaseChart chart : mChartList) {
                chart.setMaxValue(maxValue);
            }
        }

        public float getMaxValue() {
            return mMaxValue;
        }

        public void setMinValue(float minValue) {
            for (BaseChart chart : mChartList) {
                chart.setMinValue(minValue);
            }
        }

        public float getMinValue() {
            return mMinValue;
        }
    }

    public static BaseChart newInstance(GeyekChartView chartView, Class<? extends BaseChart> clazz) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class[] paramTypes = {GeyekChartView.class};
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
}
