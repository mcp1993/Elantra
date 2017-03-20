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

public class RunFragment extends MFragment<HomePresenter> implements HomeView {
//    public static RunFragment stepFragment;
      public static final String RUNPAGE ="RunPage";
    @Override
    public HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    public static RunFragment getInstance(int page){
        Bundle args = new Bundle();
        args.putInt(RUNPAGE, page);
        RunFragment runFragment = new RunFragment();
        runFragment.setArguments(args);
        return runFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        stepFragment = new RunFragment();
        View view = inflater.inflate(R.layout.fragment_run,null);
        return view;

    }
}
