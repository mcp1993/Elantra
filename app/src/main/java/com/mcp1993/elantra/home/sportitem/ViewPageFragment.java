package com.mcp1993.elantra.home.sportitem;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mcp1993.elantra.R;
import com.mcp1993.elantra.base.MFragment;
import com.mcp1993.elantra.home.MapActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/2/8 0008.
 */

public class ViewPageFragment extends MFragment<ViewPagePresenter> implements ViewPageView {

    public static ViewPageFragment viewPageFragment;

    @BindView(R.id.txt_map)
    TextView txt_map;
    @BindView(R.id.tab_sport)
    TabLayout tab_sport;
    @BindView(R.id.vp_sport)
    ViewPager vp_sport;

    @Override
    public ViewPagePresenter createPresenter() {
        return new ViewPagePresenter(this);
    }

    public static ViewPageFragment getInstance(){
        if (viewPageFragment!=null){
            return viewPageFragment;
        }
        return new ViewPageFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewPageFragment=new ViewPageFragment();
        View view = inflater.inflate(R.layout.fragment_viewpage, null);
        ButterKnife.bind(this,view);
        initViewPage();
        return view;
    }

       private void initViewPage(){
        vp_sport.setAdapter(new SportPageAdapter(getFragmentManager()));
        //实现TabLayout 与 ViewPager 联动
        tab_sport.setupWithViewPager(vp_sport);
        //默认选中某项
        tab_sport.getTabAt(0).select();

    }

    @OnClick(R.id.txt_map)
    public void goMap(){
        Intent intent = new Intent(getActivity(),MapActivity.class);
        startActivity(intent);
    }

}
