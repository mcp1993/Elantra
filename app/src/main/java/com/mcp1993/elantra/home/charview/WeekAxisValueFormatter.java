package com.mcp1993.elantra.home.charview;

import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

/**
 * Created by Administrator on 2017/3/14 0014.
 */

public class WeekAxisValueFormatter  implements IAxisValueFormatter {

    protected String[] weeks =new String[]{"一","二","三","四","五","六","日"};
    private BarLineChartBase<?> chart;

    public WeekAxisValueFormatter(BarLineChartBase<?> chart) {
        this.chart = chart;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {

        return weeks[(int)value-1];
    }
}
