package com.mcp1993.elantra.home.sportitem;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.mcp1993.elantra.R;
import com.mcp1993.elantra.base.MFragment;
import com.mcp1993.elantra.home.charview.MyAxisValueFormatter;
import com.mcp1993.elantra.home.charview.WeekAxisValueFormatter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/2/8 0008.
 */

public class BottomFragment extends MFragment<BottomPresenter> implements BottomView {

    public static BottomFragment bottomFragment;
    BarDataSet set1;
    @BindView(R.id.chart_bar)
    BarChart mChart;
    @BindView(R.id.iv_advertisement)
    ImageView iv_advertisement;


    protected Typeface mTfLight;
    ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
    private int[] countDatas = new int[]{6660,10000,7000,0,1000,2000,0};
    @Override
    public BottomPresenter createPresenter() {
        return new BottomPresenter(this);
    }

    public static BottomFragment getInstance(){
        if (bottomFragment!=null){
            return bottomFragment;
        }
        return new BottomFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bottomFragment=new BottomFragment();
        View view = inflater.inflate(R.layout.fragment_bottom, null);
        ButterKnife.bind(this,view);
        mTfLight = Typeface.createFromAsset(getActivity().getAssets(), "OpenSans-Light.ttf");
        LinearLayout.LayoutParams  layoutParams=
                new LinearLayout.LayoutParams(getScreenWidth(),getScreenWidth()*12/35);
        iv_advertisement.setLayoutParams(layoutParams);
        initChar();
        getAD();

        return view;
    }
    private void getAD(){
        Picasso.with(getActivity()).load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489494846118&di=31df433e8390937d763d889139090a20&imgtype=0&src=http%3A%2F%2Fi5.download.fd.pchome.net%2Ft_960x600%2Fg1%2FM00%2F0A%2F06%2FoYYBAFPV5mOIfKPXACDuRaF_0rsAABycgCgul4AIO5d365.jpg")
                .resize(getScreenWidth(),getScreenWidth()*12/35)
                .error(R.mipmap.advertisement)
                .centerCrop()
                .into(iv_advertisement);
    }
    private void initChar() {
        mChart.setDrawBarShadow(false);
        mChart.setDrawValueAboveBar(true);
        mChart.setMaxVisibleValueCount(100000);
        mChart.getDescription().setEnabled(false);

        // scaling can now only be done on x- and y-axis separately
        mChart.setPinchZoom(false);
        mChart.setDrawGridBackground(false);
        //去除y轴网格
        mChart.getAxisLeft().setEnabled(false);
        mChart.getAxisRight().setEnabled(false);
        mChart.setTouchEnabled(false); // 设置是否可以触摸
        //设置水平线隐藏
        mChart.getXAxis().setDrawAxisLine(false);

        IAxisValueFormatter xAxisFormatter = new WeekAxisValueFormatter(mChart);
        XAxis xAxis = mChart.getXAxis();
        //控制横向坐标位置
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTypeface(mTfLight);
        xAxis.setTextColor(getResources().getColor(R.color.white));

        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f); // only intervals of 1 day
        xAxis.setLabelCount(7);
        //设置横向坐标字体大小
//        xAxis.setTextSize(12);
        xAxis.setValueFormatter(xAxisFormatter);

        IAxisValueFormatter custom = new MyAxisValueFormatter();
        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setTypeface(mTfLight);
        leftAxis.setLabelCount(8, false);
        leftAxis.setValueFormatter(custom);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setTypeface(mTfLight);
        rightAxis.setLabelCount(8, false);
        rightAxis.setValueFormatter(custom);
        rightAxis.setSpaceTop(15f);
        rightAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        Legend l = mChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setForm(Legend.LegendForm.SQUARE);
        l.setFormSize(9f);
        l.setTextSize(11f);
        l.setXEntrySpace(4f);
        //设置比例图标的显示隐藏，左下角
        l.setEnabled(false);
        setData(6, 6000);



    }


    private void setData(int count, float range) {

        float start = 1f;

        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();

        for (int i = (int) start; i < start + count + 1; i++) {

            yVals1.add(new BarEntry(i, countDatas[i-1]));
        }



        if (mChart.getData() != null &&
                mChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) mChart.getData().getDataSetByIndex(0);
            set1.setValues(yVals1);

            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(yVals1, "");
          //设置柱状图顶部不显示数值
            set1.setDrawValues(true);




            //圆柱体的颜色
            set1.setColors(getResources().getColor(R.color.charcolor));

            dataSets.add(set1);
            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);//圆柱体上面的文字的大小
            //圆柱体上面的文字的颜色
            data.setValueTextColor(getResources().getColor(R.color.white));
            data.setValueTypeface(mTfLight);
            //设置圆柱的宽度
            data.setBarWidth(0.2f);
            mChart.setData(data);
        }
    }

    protected int getScreenWidth() {
        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        return display.getWidth();
    }
}
