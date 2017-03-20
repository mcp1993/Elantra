package com.mcp1993.elantra.challenge.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mcp1993.elantra.R;
import com.mcp1993.elantra.challenge.bean.Challenge_horData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/13 0013.
 */

public class SportsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private OnItemClickListener mOnItemClickListener;
    private List<Challenge_horData> datas = new ArrayList<>();
    private Context context;
    private LayoutInflater mInflater;

    public static final int TYPE_HEADER = 0;  //说明是带有Header的
    public static final int TYPE_FOOTER = 1;  //说明是带有Footer的
    public static final int TYPE_NORMAL = 2;  //说明是不带有header和footer的
    //HeaderView, FooterView
    private View mHeaderView;
    private View mFooterView;

    public SportsAdapter(List<Challenge_horData> datas, Context context) {
        this.datas = datas;
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
    }
    //HeaderView和FooterView的get和set函数
    public View getHeaderView() {
        return mHeaderView;
    }
    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);
    }
    public View getFooterView() {
        return mFooterView;
    }
    public void setFooterView(View footerView) {
        mFooterView = footerView;
        notifyItemInserted(getItemCount()-1);
    }

    /** 重写这个方法，很重要，是加入Header和Footer的关键，我们通过判断item的类型，从而绑定不同的view    * */
    @Override
    public int getItemViewType(int position) {
        if (mHeaderView == null && mFooterView == null){
            return TYPE_NORMAL;
        }
        if (position == 0){
            //第一个item应该加载Header
            return TYPE_HEADER;
        }
        if (position == getItemCount()-1){
            //最后一个,应该加载Footer
            return TYPE_FOOTER;
        }
        return TYPE_NORMAL;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mHeaderView != null && viewType == TYPE_HEADER) {
            return new ListHolder(mHeaderView);
        }
        if(mFooterView != null && viewType == TYPE_FOOTER){
            return new ListHolder(mFooterView);
        }
        // 实例化展示的view
        View layout = mInflater.inflate(R.layout.item_challenge_tiyu, parent, false);
        // 实例化viewholder
        return new ListHolder(layout);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if(getItemViewType(position) == TYPE_NORMAL){
            if(holder instanceof ListHolder) {
                //这里加载数据的时候要注意，是从position-1开始，因为position==0已经被header占用了
                Picasso.with(context).load(datas.get(position-1).getThumbnail_pic_s())
                        .into( ((ListHolder) holder).iv_sport);
                ((ListHolder) holder).tv_time.setText(datas.get(position-1).getDate());
                ((ListHolder) holder).tv_join.setText(datas.get(position-1).getTitle());
                ((ListHolder) holder).lin_item_tiyu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(mOnItemClickListener != null) {
                            mOnItemClickListener.onClick(holder,position);
                        }
                    }
                });
            }
            return;
        }else if(getItemViewType(position) == TYPE_HEADER){
            return;
        }else{
            return;
        }

    }
    //在这里面加载ListView中的每个item的布局
    class ListHolder extends RecyclerView.ViewHolder{
        LinearLayout lin_item_tiyu;
        ImageView iv_sport;
        TextView tv_time;
        TextView tv_join;
        public ListHolder(View itemView) {
            super(itemView);
            //如果是headerview或者是footerview,直接返回
            if (itemView == mHeaderView){
                return;
            }
            if (itemView == mFooterView){
                return;
            }
            lin_item_tiyu = (LinearLayout) itemView.findViewById(R.id.lin_item_tiyu);
            iv_sport = (ImageView) itemView.findViewById(R.id.iv_sport);
            tv_time = (TextView) itemView.findViewById(R.id.tv_time);
            tv_join = (TextView) itemView.findViewById(R.id.tv_join);
        }
    }
    @Override
    public int getItemCount() {
        if(mHeaderView == null && mFooterView == null){
            return datas.size();
        }else if(mHeaderView == null && mFooterView != null){
            return datas.size() + 1;
        }else if (mHeaderView != null && mFooterView == null){
            return datas.size() + 1;
        }else {
            return datas.size() + 2;
        }
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        LinearLayout lin_item_tiyu;
        ImageView iv_sport;
        TextView tv_time;
        TextView tv_join;
        public ItemViewHolder(View itemView) {
            super(itemView);
            lin_item_tiyu = (LinearLayout) itemView.findViewById(R.id.lin_item_tiyu);
            iv_sport = (ImageView) itemView.findViewById(R.id.iv_sport);
            tv_time = (TextView) itemView.findViewById(R.id.tv_time);
            tv_join = (TextView) itemView.findViewById(R.id.tv_join);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }
    public interface OnItemClickListener {
        void onClick(RecyclerView.ViewHolder VH ,int position);
    }
}
