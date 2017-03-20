package com.mcp1993.elantra.social.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.mcp1993.elantra.R;
import com.mcp1993.elantra.social.PhotoImgActivity;
import com.mcp1993.elantra.social.bean.MIXResult;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/20 0020.
 */

public class SocialHotImgAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<MIXResult> datas = new ArrayList<>();
    private Context context;
    private LayoutInflater layoutInflater;

    public SocialHotImgAdapter(List<MIXResult> datas, Context context) {
        this.datas = datas;
        this.context = context;
        layoutInflater=LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        View view = layoutInflater.inflate(R.layout.item_social_hot_img, parent, false);
        // 实例化viewholder
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        Picasso.with(context).load(datas.get(position).getPic()).error(R.mipmap.error)
                .placeholder(R.mipmap.ic_launcher_1).resize(getScreenWidth()/3,getScreenWidth()/3)
                .into( ((ItemViewHolder)holder).item_img);
        ((ItemViewHolder)holder).item_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PhotoImgActivity.class);
                intent.putExtra("social_hot_view",datas.get(position).getPic());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas == null? 0 :datas.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder{
        ImageView item_img;
        public ItemViewHolder(View itemView) {
            super(itemView);
            item_img = (ImageView) itemView.findViewById(R.id.item_img);
        }
    }

    protected int getScreenWidth() {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        return display.getWidth();
    }

}
