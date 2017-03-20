package com.mcp1993.elantra.home.sportitem;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mcp1993.elantra.R;
import com.mcp1993.elantra.base.MFragment;
import com.mcp1993.elantra.home.HomePresenter;
import com.mcp1993.elantra.home.HomeView;
import com.wanli.com.circleprogressview.CircleProgressView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/9 0009.
 */

public class StepFragment extends MFragment<HomePresenter> implements HomeView {
      public static final String STEPPAGE ="StepPage";

    @BindView(R.id.circle)
    CircleProgressView circle;
    @BindView(R.id.tv_progress)
    TextView tv_progress;
    @Override
    public HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    public static StepFragment getInstance(int page){
        Bundle args = new Bundle();
        args.putInt(STEPPAGE, page);
        StepFragment stepFragment = new StepFragment();
        stepFragment.setArguments(args);
        return stepFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_step,null);
        ButterKnife.bind(this,view);
        circle.setDurationsecondscurrent(1f);   //设置当前进度
        circle.setDurationSecondsMax(10f);      //设置最大进度
        circle.setTotalSeconds(2000);      //设置一共需要耗时多少毫秒。不设置默认从1到10为每隔一秒加1

//        circle.start(new CircleProgressView.OnProgressListener() {
//            @Override
//            public void onProgressListener(float progress) {
//                tv_progress.setText(String.valueOf(progress)); //(int)
//            }
//
//            @Override
//            public void onProgressEnd(float progress) {
//                tv_progress.setText(String.valueOf(progress));
//            }
//        });
        return view;

    }
}
