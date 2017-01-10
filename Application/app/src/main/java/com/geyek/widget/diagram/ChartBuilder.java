package com.geyek.widget.diagram;

import com.geyek.widget.diagram.kernel.BaseChart;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import static com.geyek.widget.diagram.kernel.BaseChart.newInstance;

/**
 * Created by LiHuan on 2016/12/23.
 * <p>
 * The class whose name is ChartBuilder, it create some chart which have the same property.
 */
public class ChartBuilder {
    private static final String TAG = "ChartBuilder";

    private GeyekChartView mGeyekChartView;
    private List<BaseChart> mChartList = new ArrayList<>();
    protected int mMaxItem = 1; //当前界面显示的最大条目数,最小为1
    protected float mMaxValue;    //当前单个条目最大的值
    protected float mMinValue;
    protected boolean mIsAutoMaxValue;
    protected boolean mIsAutoMinValue;

    public ChartBuilder(GeyekChartView geyekChartView) {
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
