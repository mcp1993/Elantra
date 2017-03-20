package com.mcp1993.elantra.home;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mcp1993.elantra.R;
import com.mcp1993.elantra.base.MFragment;
import com.mcp1993.elantra.home.sportitem.BottomFragment;
import com.mcp1993.elantra.home.sportitem.ViewPageFragment;
import com.mcp1993.elantra.home.verticalslide.VerticalSlide;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/2/8 0008.
 */

public class HomeFragment extends MFragment<HomePresenter> implements HomeView{
    public static HomeFragment homeFragment;
    @BindView(R.id.verticalSlide)
    VerticalSlide verticalSlide;

    private ViewPageFragment topFragment;
    private BottomFragment bottomFragment;

    @Override
    public HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    public static HomeFragment getInstance(){
        if (homeFragment!=null){
            return homeFragment;
        }
        return new HomeFragment();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        homeFragment=new HomeFragment();
        View view = inflater.inflate(R.layout.fragment_home, null);
        ButterKnife.bind(this,view);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        topFragment = ViewPageFragment.getInstance();
        transaction.replace(R.id.first, topFragment);

        verticalSlide.setOnShowNextPageListener(new VerticalSlide.OnShowNextPageListener() {
            @Override
            public void onShowNextPage() {
                bottomFragment = BottomFragment.getInstance();
            }
        });
        bottomFragment = BottomFragment.getInstance();
        transaction.replace(R.id.second, bottomFragment);

        transaction.commit();

        return view;
    }

}
