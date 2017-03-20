package com.mcp1993.elantra.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by yangsp on 2016/11/14.
 */
public class DoubleScrollViewPager extends ViewPager {
    public DoubleScrollViewPager(Context context) {
        super(context);
    }

    public DoubleScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int height = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
            int h = child.getMeasuredHeight();
            if (h > height)
                height = h;
        }

        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
//        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);


        super.onMeasure(widthMeasureSpec, heightMeasureSpec);



    }


}
