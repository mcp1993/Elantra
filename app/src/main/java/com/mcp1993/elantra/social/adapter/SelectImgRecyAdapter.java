package com.mcp1993.elantra.social.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mcp1993.elantra.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/22 0022.
 */

public class SelectImgRecyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<String> paths = new ArrayList<>();
    private Context context;
    private LayoutInflater layoutInflater;

    public static final int TYPE_FOOTER = 1;  //说明是带有Footer的
    public static final int TYPE_NORMAL = 2;  //说明是不带有header和footer的
    private OnItemClickListener mOnItemClickListener;

    public SelectImgRecyAdapter(List<String> paths, Context context) {
        this.paths = paths;
        this.context = context;
        this.layoutInflater =LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount()-1){
            //最后一个,应该加载Footer
            return TYPE_FOOTER;
        }
        return TYPE_NORMAL;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        if( viewType == TYPE_FOOTER){
            View view1= layoutInflater.inflate(R.layout.item_social_select_add,parent,false);
            return new ItemViewFooterHolder(view1);
        }
        View view = layoutInflater.inflate(R.layout.item_social_select_img, parent, false);
        // 实例化viewholder
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (getItemViewType(position) == TYPE_NORMAL){
//            Picasso.with(context).load(paths.get(position)).error(R.mipmap.error)
//                    .placeholder(R.mipmap.ic_launcher_1).resize(getScreenWidth()/3,getScreenWidth()/3)
//                    .into( ((ItemViewHolder)holder).item_img_select);
            File file = new File(paths.get(position));
            Glide.with(context).load(file).override(getScreenWidth()/3,getScreenWidth()/3).error(R.mipmap.ic_launcher)
                    .centerCrop()
                    .into(((ItemViewHolder)holder).item_img_select);
        }else {
            //footer
           Picasso.with(context).load(R.mipmap.bracelet_add)
                   .resize(getScreenWidth()/3,getScreenWidth()/3)
                   .centerCrop()
                   .into( ((ItemViewFooterHolder)holder).item_img_add);
            ((ItemViewFooterHolder)holder).item_img_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mOnItemClickListener != null) {
                        mOnItemClickListener.onClick(holder,position);
                    }
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return paths == null? 1 :paths.size()+1;
    }

    class ItemViewHolder extends RecyclerView.ViewHolder{
        ImageView item_img_select;
        public ItemViewHolder(View itemView) {
            super(itemView);

            item_img_select = (ImageView) itemView.findViewById(R.id.item_img_select);
        }
    }

    class ItemViewFooterHolder extends RecyclerView.ViewHolder{
        ImageView item_img_add;
        public ItemViewFooterHolder(View itemView) {
            super(itemView);
            item_img_add = (ImageView) itemView.findViewById(R.id.item_img_add);
        }
    }

    protected int getScreenWidth() {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        return display.getWidth();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }
    public interface OnItemClickListener {
        void onClick(RecyclerView.ViewHolder VH, int position);
    }
}
