package com.mcp1993.elantra.challenge.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mcp1993.elantra.R;
import com.mcp1993.elantra.challenge.bean.Challenge_horData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/10 0010.
 */

public class HorRecyclerAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private OnItemClickListener mOnItemClickListener;
    private List<Challenge_horData> datas = new ArrayList<>();
    private LayoutInflater mInflater;
    private Context context;

    public HorRecyclerAdapter(List<Challenge_horData> datas, Context context) {
        this.datas = datas;
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        View view = mInflater.inflate(R.layout.item_challemge_holist, parent, false);
        // 实例化viewholder
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

//        Picasso.with(context).load(datas.get(position).getThumbnail_pic_s())
//                .resize(200, 80)
//                .centerCrop()
//                .into(  ((ItemViewHolder) holder).img_challenge);
        Glide.with(context).load(datas.get(position).getThumbnail_pic_s())
                .override(200,80)
                .centerCrop()
                .into(((ItemViewHolder) holder).img_challenge);
//            viewHolder.challenge_state.setText(datas.get(position).getTitle());
        ((ItemViewHolder) holder).challenge_time.setText(datas.get(position).getDate());
        ((ItemViewHolder) holder).join.setText(datas.get(position).getTitle());

        ((ItemViewHolder) holder).lin_hor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnItemClickListener != null) {
                    mOnItemClickListener.onClick(holder,position);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    public  class ItemViewHolder extends RecyclerView.ViewHolder {

        LinearLayout lin_hor;
        ImageView img_challenge;
        TextView challenge_state;
        TextView challenge_time;
        TextView join;

        public ItemViewHolder(View itemView) {
            super(itemView);
            lin_hor = (LinearLayout) itemView.findViewById(R.id.lin_hor);
            img_challenge = (ImageView) itemView.findViewById(R.id.img_challenge);
            challenge_state = (TextView) itemView.findViewById(R.id.challenge_state);
            challenge_time = (TextView) itemView.findViewById(R.id.challenge_time);
            join = (TextView) itemView.findViewById(R.id.join);

        }
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }
    public interface OnItemClickListener {
        void onClick(RecyclerView.ViewHolder VH ,int position);
    }
}
