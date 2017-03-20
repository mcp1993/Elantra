package com.mcp1993.elantra.social.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mcp1993.elantra.R;
import com.mcp1993.elantra.challenge.bean.Challenge_horData;
import com.mcp1993.elantra.social.circularimage.CircularImage;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/15 0015.
 */

public class SocialRecyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Challenge_horData> datas = new ArrayList<>();
    private Context context;
    private LayoutInflater layoutInflater;
    private OnItemClickListener mOnItemClickListener;
    private OnItemLastClickListener mOnItemLastClickListener;

    public SocialRecyAdapter(List<Challenge_horData> datas, Context context) {
        this.datas = datas;
        this.context = context;
       this.layoutInflater = LayoutInflater.from(context);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        View view = layoutInflater.inflate(R.layout.item_social_social, parent, false);
        // 实例化viewholder
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (position != datas.size()){
            Picasso.with(context).load(datas.get(position).getThumbnail_pic_s())
                    .placeholder(R.mipmap.grade_center_person_2x)
                    .error(R.mipmap.ic_launcher_1)
                    .into(((ItemViewHolder) holder).item_cirImg);
            ((ItemViewHolder) holder).textView.setText(datas.get(position).getAuthor_name());
        }else {
            ((ItemViewHolder) holder).item_cirImg.setBackgroundResource(R.mipmap.add_2x);
            ((ItemViewHolder) holder).textView.setText("加入圈子");
        }

        ((ItemViewHolder) holder).re_item_social.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnItemClickListener != null && position != datas.size()) {
                    mOnItemClickListener.onClick(holder,position);
                }else if (mOnItemClickListener != null && position == datas.size()){
                    mOnItemLastClickListener.onClick(holder,position);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return datas.size()+1;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout re_item_social;
        CircularImage item_cirImg;
        TextView textView;
        public ItemViewHolder(View itemView) {
            super(itemView);
            re_item_social = (RelativeLayout) itemView.findViewById(R.id.re_item_social);
            item_cirImg = (CircularImage) itemView.findViewById(R.id.item_cirImg);
            textView = (TextView) itemView.findViewById(R.id.tv_1);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener,
                                       OnItemLastClickListener onItemLastClickListener) {
        mOnItemClickListener = onItemClickListener;
        mOnItemLastClickListener = onItemLastClickListener;
    }
    public interface OnItemClickListener {
        void onClick(RecyclerView.ViewHolder VH ,int position);
    }
    public interface OnItemLastClickListener {
        void onClick(RecyclerView.ViewHolder VH ,int position);
    }
}
