package com.mcp1993.elantra.social;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mcp1993.elantra.R;
import com.mcp1993.elantra.base.MFragment;
import com.mcp1993.elantra.challenge.bean.Challenge_horBean;
import com.mcp1993.elantra.challenge.bean.Challenge_horData;
import com.mcp1993.elantra.explorer.banner.bean.FuLiBean;
import com.mcp1993.elantra.social.adapter.SocialHotReAdapter;
import com.mcp1993.elantra.social.adapter.SocialRecyAdapter;
import com.mcp1993.elantra.social.bean.MIXResult;
import com.mcp1993.elantra.social.bean.MusicBean;
import com.mcp1993.elantra.social.ordinarybanner.RollHeaderView;
import com.mcp1993.elantra.utils.Constants;
import com.mcp1993.elantra.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/2/8 0008.
 */

public class SocialFragment extends MFragment<SocialPresenter> implements SocialView {
    public static SocialFragment socialFragment;

    @BindView(R.id.rollHeaderView)
    RollHeaderView rollHeaderView;
    @BindView(R.id.rc_social)
    RecyclerView rc_social;
    @BindView(R.id.re_social_hot)
    RecyclerView re_social_hot;
    private List<String> bannerUrls = new ArrayList<>();
    private List<Challenge_horData> mySocialDatas = new ArrayList<>();
    private List<MIXResult> mixResults = new ArrayList<>();
    private SocialRecyAdapter mySocialAdapter ;
    private SocialHotReAdapter socialHotReAdapter;
    private int mySocialSize;

    @Override
    public SocialPresenter createPresenter() {
        return new SocialPresenter(this);
    }

    public static SocialFragment getInstance(){
        if (socialFragment!=null){
            return socialFragment;
        }
        return new SocialFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        socialFragment=new SocialFragment();
        View view = inflater.inflate(R.layout.fragment_social, null);
        ButterKnife.bind(this,view);

        initRecyclerView();

        mPresenter.getFuLiBean();
        mPresenter.getMySocial();
        mPresenter.getSocialView();

        return view;
    }

    @OnClick(R.id.iv_release)
    public void goRelease(){
        Intent intent = new Intent(getActivity(),ReleaseActivity.class);
        startActivity(intent);
    }

    private void initRecyclerView(){
        //实例化
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),4);

        //设置布局管理器
        rc_social.setLayoutManager(gridLayoutManager);
        rc_social.setItemAnimator(new DefaultItemAnimator());
        rc_social.setNestedScrollingEnabled(false);
        rc_social.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                if (parent.getChildPosition(view) !=-1){
                    outRect.bottom  = 20;
                }
            }
        });

        LinearLayoutManager hotLinearLayoutManager =
                new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        //确保尺寸是一个常数，避免计算每个item的size
        re_social_hot.setLayoutManager(hotLinearLayoutManager);
        re_social_hot.setHasFixedSize(true);
        //设置显示动画
        re_social_hot.setItemAnimator(new DefaultItemAnimator());
        re_social_hot.setNestedScrollingEnabled(false);
        re_social_hot.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                if (parent.getChildPosition(view) !=-1){
                    outRect.bottom  = 20;
                }
            }
        });
    }


    @Override
    public void showBanner(FuLiBean bean) {
        for (int i = 0;i<bean.getResults().size();i++){
            bannerUrls.add(bean.getResults().get(i).getUrl());
        }
        rollHeaderView.setImgUrlData(bannerUrls);
        rollHeaderView.setOnHeaderViewClickListener(new RollHeaderView.HeaderViewClickListener() {
            @Override
            public void HeaderViewClick(int position) {
                Toast.makeText(getActivity(),"position:"+position,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void showMySocialView(Challenge_horBean bean) {
        initMySocial();
        mySocialDatas.clear();
        for (int i =0;i<mySocialSize;i++){
            mySocialDatas.add(bean.getResult().getData().get(i));
        }
        mySocialAdapter = new SocialRecyAdapter(mySocialDatas,getActivity());
        rc_social.setAdapter(mySocialAdapter);
        mySocialAdapter.setOnItemClickListener( new SocialRecyAdapter.OnItemClickListener() {
            @Override
            public void onClick(RecyclerView.ViewHolder VH, int position) {
                Toast.makeText(getActivity(),"position:"+position,Toast.LENGTH_SHORT).show();
            }
        },new SocialRecyAdapter.OnItemLastClickListener() {
            @Override
            public void onClick(RecyclerView.ViewHolder VH, int position) {

                Toast.makeText(getActivity(),"添加成功",Toast.LENGTH_SHORT).show();
                SPUtils.put(getActivity(), Constants.FILE_SOCIAL,Constants.MYSCIALSIZE
                        ,mySocialSize+1);
                mPresenter.getMySocial();
            }
        });
    }

    @Override
    public void showSocialView(MusicBean bean) {
       for (int i= 0 ;i<bean.getResult().getMix_1().getResult().size();i++){
           mixResults.add(bean.getResult().getMix_1().getResult().get(i));
       }
        socialHotReAdapter = new SocialHotReAdapter(mixResults,getActivity());
        re_social_hot.setAdapter(socialHotReAdapter);
        socialHotReAdapter.setOnItemCommentClickListener(new SocialHotReAdapter.OnItemCommentClickListener() {
            @Override
            public void onClick(RecyclerView.ViewHolder VH, int position) {
                Intent intent = new Intent(getActivity(),ReplyActivity.class);
                getActivity().startActivity(intent);
            }
        });

    }

    private void initMySocial(){
        //获取圈子个数
        boolean isContains = SPUtils.contains(getActivity(),
                Constants.FILE_SOCIAL,Constants.MYSCIALSIZE);
        if (isContains){
            mySocialSize =(int) SPUtils.get(getActivity(), Constants.FILE_SOCIAL,Constants.MYSCIALSIZE,0);
        }else {
            SPUtils.put(getActivity(), Constants.FILE_SOCIAL,Constants.MYSCIALSIZE
                    ,Constants.MY_SCIALSIZE);
            mySocialSize =(int) SPUtils.get(getActivity(), Constants.FILE_SOCIAL,Constants.MYSCIALSIZE,0);
        }
    }
}
