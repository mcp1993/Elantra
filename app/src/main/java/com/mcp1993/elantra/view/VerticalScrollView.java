package com.mcp1993.elantra.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/9 0009.
 */

public class VerticalScrollView  extends ScrollView {

    private Map<Integer,Integer> map=new HashMap<>(2);
    private int currentPage;

    public VerticalScrollView(Context context) {
        this(context, null);
    }

    public VerticalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height=0;
        if(map.size()>0 && map.containsKey(currentPage)){
            height=map.get(currentPage);
        }
        //得到ViewPager的MeasureSpec，使用固定值和MeasureSpec.EXACTLY，
        heightMeasureSpec=MeasureSpec.makeMeasureSpec(height,MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 在切换tab的时候，重置ViewPager的高度
     * @param current
     */
    public void resetHeight(int current){
        this.currentPage=current;
        MarginLayoutParams params= (MarginLayoutParams) getLayoutParams();
        if(map.size()>0 && map.containsKey(currentPage)){
            if(params==null){
                params=new MarginLayoutParams(LayoutParams.MATCH_PARENT,map.get(current));
            }else {
                params.height=map.get(current);
            }
            setLayoutParams(params);
        }
    }

    /**
     * 获取、存储每一个tab的高度，在需要的时候显示存储的高度
     * @param current  tab的position
     * @param height   当前tab的高度
     */
    public void addHeight(int current,int height){
        map.put(current,height);
    }

}
