package com.mcp1993.elantra.social.adapter;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mcp1993.elantra.R;
import com.mcp1993.elantra.social.bean.MIXResult;
import com.mcp1993.elantra.social.circularimage.CircularImage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/17 0017.
 */

public class SocialHotReAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<MIXResult> datas = new ArrayList<>();
    private Context context;
    private LayoutInflater layoutInflater;
    private OnItemCommentClickListener onItemCommentClickListener;

    public SocialHotReAdapter(List<MIXResult> datas, Context context) {
        this.datas = datas;
        this.context = context;
        layoutInflater=LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        View view = layoutInflater.inflate(R.layout.item_social_hot, parent, false);
        // 实例化viewholder
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        ((ItemViewHolder) holder).txt_author.setText("小悦");
        ((ItemViewHolder) holder).txt_title.setText(datas.get(position).getTitle());
        ((ItemViewHolder) holder).txt_content.setText(datas.get(position).getType_id());
        ((ItemViewHolder) holder).txt_adress.setText("惠州市");

        GridLayoutManager gridLayoutManager = new GridLayoutManager(context,3);
        //设置布局管理器
        ((ItemViewHolder) holder).recy_imgs.setLayoutManager(gridLayoutManager);
        ((ItemViewHolder) holder).recy_imgs.setItemAnimator(new DefaultItemAnimator());
        ((ItemViewHolder) holder).recy_imgs.setNestedScrollingEnabled(false);
//        ((ItemViewHolder) holder).recy_imgs.addItemDecoration(new RecyclerView.ItemDecoration() {
//            @Override
//            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//                if (parent.getChildPosition(view) !=-1){
//                    outRect.left  = 5;
//
//                }
//            }
//        });
//        ((ItemViewHolder) holder).recy_imgs.addItemDecoration(new DividerGridItemDecoration(context));
        GridLayoutItemDecoration itemDecoration = new GridLayoutItemDecoration(3);
        itemDecoration.setDivideParams(20, 20);
        ((ItemViewHolder) holder).recy_imgs.addItemDecoration(itemDecoration);
        ((ItemViewHolder) holder).recy_imgs.setAdapter(new SocialHotImgAdapter(datas,context));

        ((ItemViewHolder) holder).img_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ItemViewHolder) holder).img_like.setBackgroundResource(R.mipmap.icon_praise);
                ((ItemViewHolder) holder).txt_like.setText("1");
            }
        });
        ((ItemViewHolder) holder).lin_comments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemCommentClickListener.onClick(holder,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas == null? 0:datas.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        CircularImage cimg_hot;
        TextView txt_author;
        TextView txt_title;
        TextView txt_content;
        RecyclerView recy_imgs;
        TextView txt_adress;
        TextView txt_comments;
        TextView  txt_like;
        ImageView img_like;
        LinearLayout lin_comments;
        public ItemViewHolder(View itemView) {
            super(itemView);
            cimg_hot = (CircularImage) itemView.findViewById(R.id.cimg_hot);
            txt_author = (TextView) itemView.findViewById(R.id.txt_author);
            txt_title = (TextView) itemView.findViewById(R.id.txt_title);
            txt_content = (TextView) itemView.findViewById(R.id.txt_content);
            recy_imgs = (RecyclerView) itemView.findViewById(R.id.recy_imgs);
            txt_adress = (TextView) itemView.findViewById(R.id.txt_adress);
            txt_comments= (TextView) itemView.findViewById(R.id.txt_comments);
            txt_like= (TextView) itemView.findViewById(R.id.txt_like);
            img_like = (ImageView) itemView.findViewById(R.id.img_like);
            lin_comments= (LinearLayout) itemView.findViewById(R.id.lin_comments);
        }
    }



    public void setOnItemCommentClickListener(OnItemCommentClickListener onItemCommentClickListener){
        this.onItemCommentClickListener = onItemCommentClickListener;
    }
    public interface OnItemCommentClickListener{
        void onClick(RecyclerView.ViewHolder VH ,int position);
    }
}
