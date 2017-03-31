package com.mcp1993.elantra.challenge;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mcp1993.elantra.APP;
import com.mcp1993.elantra.R;
import com.mcp1993.elantra.base.MFragment;
import com.mcp1993.elantra.challenge.adapter.HorRecyclerAdapter;
import com.mcp1993.elantra.challenge.adapter.SportsAdapter;
import com.mcp1993.elantra.challenge.adapter.WXnewRecyclerAdapter;
import com.mcp1993.elantra.challenge.bean.Challenge_horBean;
import com.mcp1993.elantra.challenge.bean.Challenge_horData;
import com.mcp1993.elantra.challenge.bean.TopViewBean;
import com.mcp1993.elantra.challenge.bean.WXNewBean;
import com.mcp1993.elantra.challenge.bean.WXNewList;
import com.mcp1993.elantra.dao.Challenge_horDataDao;
import com.mcp1993.elantra.utils.NetworkUtil;
import com.mcp1993.elantra.view.AutoLinearLayoutManager;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.schedulers.Schedulers;


/**
 * Created by Administrator on 2017/2/8 0008.
 */

public class ChallengeFragment extends MFragment<ChallengePresenter> implements ChallengeView ,
        HorRecyclerAdapter.OnItemClickListener{
    public static ChallengeFragment challengeFragment;

    @BindView(R.id.iv_topWX)
    ImageView iv_topWX;
    @BindView(R.id.recy_clerview)
    RecyclerView recy_clerview;
    @BindView(R.id.challenge_state)
    TextView challenge_state;
    @BindView(R.id.tv_topWX)
    TextView tv_topWX;
    @BindView(R.id.tv_topWXjoin)
    TextView tv_topWXjoin;
    @BindView(R.id.lin_top)
    LinearLayout lin_top;
    @BindView(R.id.recy_marathon)
    RecyclerView recy_marathon;
    @BindView(R.id.recy_tiyu)
    RecyclerView recy_tiyu;
    @BindView(R.id.swipeRL)
    SwipeRefreshLayout swipeRL;

    private List<Challenge_horData> datas = new ArrayList<>();
    private List<Challenge_horData> mData = new ArrayList<>();
    private List<WXNewList> newLists = new ArrayList<>();
//    private RecyclerView recy_clerview;
    private HorRecyclerAdapter mAdapter;
    private WXnewRecyclerAdapter newAdapter;
    private SportsAdapter sportsAdapter;
    private Intent intent;
    private TopViewBean topViewBean;
    private long count;//用来记录从数据库取出指定项的数量
    private List<Object> mItems;
    @Override
    public ChallengePresenter createPresenter() {
        return new ChallengePresenter(this);
    }

    public static ChallengeFragment getInstance(){
        if (challengeFragment!=null){
            return challengeFragment;
        }
        return new ChallengeFragment();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        challengeFragment=new ChallengeFragment();
        View view = inflater.inflate(R.layout.fragment_challenge, null);
        ButterKnife.bind(this,view);
        initRecycler();
        initSwipeRefreshLayout();

        mPresenter.getHorBean();
        mPresenter.getTopBean( "西游降魔篇");
        mPresenter.getWXNEW();
        mPresenter.getSportBean();
        //读缓存
        if (null != APP.getDaoSession().getChallenge_horDataDao().loadAll()
                && 0 <APP.getDaoSession().getChallenge_horDataDao().loadAll().size()
                && !NetworkUtil.isAvailable(getActivity())){
            datas.clear();//清空集合，为了保险起见
            datas.addAll(APP.getDaoSession().getChallenge_horDataDao().loadAll());

            mAdapter = new HorRecyclerAdapter(datas, getActivity());
            recy_clerview.setAdapter(mAdapter);
            recy_clerview.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener(){
                @Override
                public void onTouchEvent(RecyclerView rv, MotionEvent e) {
                    super.onTouchEvent(rv, e);
                }
            });
            mAdapter.setOnItemClickListener(this);
        }else {

        }

        return view;
    }
    private void initSwipeRefreshLayout(){
        swipeRL.setColorSchemeColors(getResources().getColor(R.color.bottom_text_color_pressed));
        swipeRL.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
            @Override
            public void onRefresh() {
                new Handler().post(new Runnable() {
            @Override
            public void run() {

                refreshData();
                swipeRL.setRefreshing(false);
            }
        });
            }
        });
    }
    private void refreshData(){
        mPresenter.getHorBean();
        mPresenter.getTopBean("海贼王");
        mPresenter.getWXNEW();
        mPresenter.getSportBean();
    }
    private void initRecycler(){
        recy_clerview.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL ,false));
        //确保尺寸是一个常数，避免计算每个item的size
        recy_clerview.setHasFixedSize(true);
        //设置显示动画
        recy_clerview.setItemAnimator(new DefaultItemAnimator());
        recy_clerview.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                if (parent.getChildPosition(view) !=-1){
                    outRect.top  = 20;
                    outRect.right = 20;
                }
            }
        });
        recy_marathon.setLayoutManager(new AutoLinearLayoutManager(getActivity(), AutoLinearLayoutManager.HORIZONTAL ,false));
        //确保尺寸是一个常数，避免计算每个item的size
        recy_marathon.setHasFixedSize(true);
        //设置显示动画
        recy_marathon.setItemAnimator(new DefaultItemAnimator());
        recy_marathon.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                if (parent.getChildPosition(view) !=-1){
                    outRect.top  = 20;
                    outRect.right = 20;
                }
            }
        });

        recy_tiyu.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL ,false));
        //确保尺寸是一个常数，避免计算每个item的size
        recy_tiyu.setHasFixedSize(true);
        //设置显示动画
        recy_tiyu.setItemAnimator(new DefaultItemAnimator());
        recy_tiyu.setNestedScrollingEnabled(false);
        recy_tiyu.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                if (parent.getChildPosition(view) !=-1){
                    outRect.bottom  = 20;
                }
            }
        });
    }
    @Override
    public void showHorView(Challenge_horBean bean) {
        for (int i = 0;i<bean.getResult().getData().size();i++){
            datas.add(bean.getResult().getData().get(i));
        }
        mAdapter = new HorRecyclerAdapter(datas, getActivity());
        recy_clerview.setAdapter(mAdapter);
        recy_clerview.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener(){
            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
                super.onTouchEvent(rv, e);
            }
        });
        mAdapter.setOnItemClickListener(this);

        //将数据插入数据库
        Observable.from(datas).subscribeOn(Schedulers.io()).subscribe(challenge_horData -> {
            //避免插入重复数据的逻辑
            count = APP.getDaoSession().getChallenge_horDataDao().queryBuilder().where(Challenge_horDataDao.Properties.Uniquekey.eq(challenge_horData.getUniquekey())).count();
            if (count == 0) APP.getDaoSession().getChallenge_horDataDao().insertOrReplaceInTx(challenge_horData);
        });

    }

    @Override
    public void showTopView(TopViewBean bean) {
        topViewBean =bean;
        Picasso.with(getActivity()).load(bean.getResult().getCover()).into(iv_topWX);
        tv_topWX.setText(bean.getResult().getYear());
        tv_topWXjoin.setText(bean.getResult().getDesc());
    }

    @Override
    public void showWXNewView(WXNewBean bean) {
        for (int i = 0;i<5;i++){
            newLists.add(bean.getResult().getList().get(i));
        }
        newAdapter = new WXnewRecyclerAdapter(newLists,getActivity());
        recy_marathon.setAdapter(newAdapter);
        recy_marathon.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener());
        newAdapter.setOnItemClickListener(new WXnewRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onClick(RecyclerView.ViewHolder VH, int position) {
                Toast.makeText(getActivity(),"position:" + position,Toast.LENGTH_SHORT).show();
                intent = new Intent(getActivity(),Challenge_horWebActivity.class);
                intent.putExtra("url",newLists.get(position).getUrl());
                Log.e("url",newLists.get(position).getUrl());
                getActivity().startActivity(intent);
            }
        });
    }

    @Override
    public void showSport(Challenge_horBean bean) {
        for (int i = 0;i<bean.getResult().getData().size();i++){
            mData.add(bean.getResult().getData().get(i));
        }
        Log.e("mData====>",mData.size()+"");
        sportsAdapter = new SportsAdapter(mData, getActivity());
        recy_tiyu.setAdapter(sportsAdapter);
        recy_tiyu.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener(){
            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
                super.onTouchEvent(rv, e);
            }
        });
        sportsAdapter.setOnItemClickListener(new SportsAdapter.OnItemClickListener() {
            @Override
            public void onClick(RecyclerView.ViewHolder VH, int position) {
                Toast.makeText(getActivity(),"postion: "+(position-1),Toast.LENGTH_SHORT).show();
            }
        });
        //为RecyclerView添加HeaderView和FooterView
        setHeaderView(recy_tiyu);
        setFooterView(recy_tiyu);

    }
    private void setHeaderView(RecyclerView view){
        View header = LayoutInflater.from(getActivity()).inflate(R.layout.item_challenge_tiyu_header, view, false);
        sportsAdapter.setHeaderView(header);
    }

    private void setFooterView(RecyclerView view){
        View footer = LayoutInflater.from(getActivity()).inflate(R.layout.item_challenge_tiyu_footer, view, false);
        sportsAdapter.setFooterView(footer);
    }

    @Override
    public void onClick(RecyclerView.ViewHolder VH, int position) {
        Toast.makeText(getActivity(),"position:" + position,Toast.LENGTH_SHORT).show();
        intent = new Intent(getActivity(),Challenge_horWebActivity.class);
        intent.putExtra("url",datas.get(position).getUrl());
        Log.e("url",datas.get(position).getUrl());
        getActivity().startActivity(intent);
    }


    @OnClick(R.id.lin_top)
    public void intoWeb(){
        intent = new Intent(getActivity(),Challenge_horWebActivity.class) ;
        intent.putExtra("url",topViewBean.getResult().getPlaylinks().getQq());
        Log.e("url",topViewBean.getResult().getPlaylinks().getQq());
        getActivity().startActivity(intent);
    }


}
