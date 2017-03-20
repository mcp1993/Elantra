package com.mcp1993.elantra.social.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mcp1993.elantra.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/27 0027.
 */

public class SelectTagRecyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<String> datas = new ArrayList<>();
    private Context context;
    private LayoutInflater layoutInflater;
    private OnItemClickListener onItemtClickListener;
    private OnItemLastClickListener onItemLastClickListener;

    public SelectTagRecyAdapter(List<String> datas, Context context) {
        this.datas = datas;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_tag,parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        ((ItemViewHolder) holder).tv_tag.setText(datas.get(position));
        ((ItemViewHolder) holder).tv_tag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onItemtClickListener != null && position != datas.size()-1) {
                    onItemtClickListener.onClick(holder,position);
                }else if (onItemLastClickListener != null && position == datas.size()-1){
                    onItemLastClickListener.onClick();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas == null? 0:datas.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        TextView tv_tag;
        public ItemViewHolder(View itemView) {
            super(itemView);
            tv_tag = (TextView) itemView.findViewById(R.id.tv_tag);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener,
                                       OnItemLastClickListener onItemLastClickListener) {
        this.onItemtClickListener = onItemClickListener;
        this.onItemLastClickListener = onItemLastClickListener;
    }
    public interface OnItemClickListener{
        void onClick(RecyclerView.ViewHolder VH ,int position);
    }

    public interface OnItemLastClickListener{
        void onClick();
    }
}
