package com.mcp1993.elantra.explorer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager;
import com.mcp1993.elantra.R;
import com.mcp1993.elantra.base.MFragment;
import com.mcp1993.elantra.challenge.bean.Challenge_horBean;
import com.mcp1993.elantra.challenge.bean.Challenge_horData;
import com.mcp1993.elantra.explorer.banner.HorizontalPagerAdapter;
import com.mcp1993.elantra.explorer.banner.bean.FuLiBean;
import com.mcp1993.elantra.explorer.banner.bean.FuLiResult;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/2/8 0008.
 */

public class ExplorerFragment extends MFragment<ExplorerPresenter> implements ExplorerView{
    public static ExplorerFragment explorerFragment;

     HorizontalInfiniteCycleViewPager horizontalInfiniteCycleViewPager;
    @BindView(R.id.iv_1_left)
    ImageView iv_1_left;
    @BindView(R.id.iv_2_left)
    ImageView iv_2_left;
    @BindView(R.id.iv_3_left)
    ImageView iv_3_left;
    @BindView(R.id.iv_4_left)
    ImageView iv_4_left;
    @BindView(R.id.iv_5_left)
    ImageView iv_5_left;
    @BindView(R.id.iv_6_left)
    ImageView iv_6_left;
    @BindView(R.id.iv_7_left)
    ImageView iv_7_left;
    @BindView(R.id.iv_8_left)
    ImageView iv_8_left;
    @BindView(R.id.iv_9_left)
    ImageView iv_9_left;
    @BindView(R.id.iv_10_left)
    ImageView iv_10_left;
    @BindView(R.id.iv_11_left)
    ImageView iv_11_left;
    @BindView(R.id.iv_12_left)
    ImageView iv_12_left;
    @BindView(R.id.iv_13_left)
    ImageView iv_13_left;
    @BindView(R.id.iv_14_left)
    ImageView iv_14_left;
    @BindView(R.id.txt_1_introduce)
    TextView txt_1_introduce;
    @BindView(R.id.txt_2_introduce)
    TextView txt_2_introduce;
    @BindView(R.id.txt_3_introduce)
    TextView txt_3_introduce;
    @BindView(R.id.txt_4_introduce)
    TextView txt_4_introduce;
    @BindView(R.id.txt_5_introduce)
    TextView txt_5_introduce;
    @BindView(R.id.txt_6_introduce)
    TextView txt_6_introduce;
    @BindView(R.id.txt_7_introduce)
    TextView txt_7_introduce;
    @BindView(R.id.txt_8_introduce)
    TextView txt_8_introduce;
    @BindView(R.id.txt_9_introduce)
    TextView txt_9_introduce;
    @BindView(R.id.txt_10_introduce)
    TextView txt_10_introduce;
    @BindView(R.id.txt_11_introduce)
    TextView txt_11_introduce;
    @BindView(R.id.txt_12_introduce)
    TextView txt_12_introduce;
    @BindView(R.id.txt_13_introduce)
    TextView txt_13_introduce;
    @BindView(R.id.txt_14_introduce)
    TextView txt_14_introduce;


    private List<Challenge_horData> datas = new ArrayList<>();
    private List<FuLiResult> mdata = new ArrayList<>();


    @Override
    public ExplorerPresenter createPresenter() {
        return new ExplorerPresenter(this);
    }

    public static ExplorerFragment getInstance(){
        if (explorerFragment!=null){
            return explorerFragment;
        }
        return new ExplorerFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        explorerFragment=new ExplorerFragment();
        View view = inflater.inflate(R.layout.fragment_explorer, null);
        ButterKnife.bind(this,view);
       horizontalInfiniteCycleViewPager = (HorizontalInfiniteCycleViewPager) view.findViewById(R.id.hicvp);
//        LinearLayout.LayoutParams layoutParams= new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT);
//        layoutParams.setMargins(0,0,0,0);
//        horizontalInfiniteCycleViewPager.setLayoutParams(layoutParams);

//        horizontalInfiniteCycleViewPager.startAutoScroll(true);
//        horizontalInfiniteCycleViewPager.setScrollDuration(3000);
        mPresenter.getBannerBean("shishang");
        mPresenter.getFuLiBean();
        return view;
    }

    @Override
    public void showBanner(Challenge_horBean bean) {
        for (int i=0;i < 5;i++){
            datas.add(bean.getResult().getData().get(i));
        }
        Log.e("datas_ex", datas + "");
        horizontalInfiniteCycleViewPager.setAdapter(new HorizontalPagerAdapter(datas,getActivity()));
        horizontalInfiniteCycleViewPager.startAutoScroll(true);
        horizontalInfiniteCycleViewPager.setScrollDuration(3000);

    }

    @Override
    public void showItemView(FuLiBean bean) {
        Picasso.with(getActivity()).load(bean.getResults().get(0).getUrl()).into(iv_1_left);
        Picasso.with(getActivity()).load(bean.getResults().get(1).getUrl()).into(iv_2_left);
        Picasso.with(getActivity()).load(bean.getResults().get(2).getUrl()).into(iv_3_left);
        Picasso.with(getActivity()).load(bean.getResults().get(3).getUrl()).into(iv_4_left);
        Picasso.with(getActivity()).load(bean.getResults().get(4).getUrl()).into(iv_5_left);
        Picasso.with(getActivity()).load(bean.getResults().get(5).getUrl()).into(iv_6_left);
        Picasso.with(getActivity()).load(bean.getResults().get(6).getUrl()).into(iv_7_left);
        Picasso.with(getActivity()).load(bean.getResults().get(7).getUrl()).into(iv_8_left);
        Picasso.with(getActivity()).load(bean.getResults().get(8).getUrl()).into(iv_9_left);
        Picasso.with(getActivity()).load(bean.getResults().get(9).getUrl()).into(iv_10_left);
        Picasso.with(getActivity()).load(bean.getResults().get(10).getUrl()).into(iv_11_left);
        Picasso.with(getActivity()).load(bean.getResults().get(11).getUrl()).into(iv_12_left);
        Picasso.with(getActivity()).load(bean.getResults().get(12).getUrl()).into(iv_13_left);
        Picasso.with(getActivity()).load(bean.getResults().get(13).getUrl()).into(iv_14_left);
        txt_1_introduce.setText(bean.getResults().get(0).getCreatedAt());
        txt_2_introduce.setText(bean.getResults().get(1).getCreatedAt());
        txt_3_introduce.setText(bean.getResults().get(2).getCreatedAt());
        txt_4_introduce.setText(bean.getResults().get(3).getCreatedAt());
        txt_5_introduce.setText(bean.getResults().get(4).getCreatedAt());
        txt_6_introduce.setText(bean.getResults().get(5).getCreatedAt());
        txt_7_introduce.setText(bean.getResults().get(6).getCreatedAt());
        txt_8_introduce.setText(bean.getResults().get(7).getCreatedAt());
        txt_9_introduce.setText(bean.getResults().get(8).getCreatedAt());
        txt_10_introduce.setText(bean.getResults().get(9).getCreatedAt());
        txt_11_introduce.setText(bean.getResults().get(10).getCreatedAt());
        txt_12_introduce.setText(bean.getResults().get(11).getCreatedAt());
        txt_13_introduce.setText(bean.getResults().get(12).getCreatedAt());
        txt_14_introduce.setText(bean.getResults().get(13).getCreatedAt());



    }
}
