package com.mcp1993.elantra.home.sportitem;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mcp1993.elantra.R;
import com.mcp1993.elantra.base.MFragment;
import com.mcp1993.elantra.home.HomePresenter;
import com.mcp1993.elantra.home.HomeView;

/**
 * Created by Administrator on 2017/3/9 0009.
 */

public class CycleFragment extends MFragment<HomePresenter> implements HomeView {
//    public static CycleFragment stepFragment;
    public static final String CYCLEPAGE ="CyclePage";
    @Override
    public HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    public static CycleFragment getInstance(int page){
        Bundle args = new Bundle();
        args.putInt(CYCLEPAGE, page);
        CycleFragment stepFragment = new CycleFragment();
        stepFragment.setArguments(args);
        return stepFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        stepFragment = new CycleFragment();
        View view = inflater.inflate(R.layout.fragment_cycle,null);
        return view;

    }
}
