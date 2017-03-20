package com.mcp1993.elantra.profile.adapter;

import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mcp1993.elantra.R;

/**
 * Created by Administrator on 2017/3/8 0008.
 */

public class MineAdapter extends BaseAdapter {
    private Context mContext;

    public String[] img_text = { "我的消息","微信排行榜","运动商城","我的成就","健身计划",
            "智能硬件","健康周报","钱包","我的好友"};
    public int[] imgs = {R.mipmap.mine_tab_ic_msg ,R.mipmap.mine_tab_ic_wechat,
            R.mipmap.mine_tab_ic_mall ,R.mipmap.mine_tab_ic_honor,
            R.mipmap.mine_tab_ic_luckymoney,
            R.mipmap.mine_tab_ic_hardware ,
            R.mipmap.mine_tab_ic_report,
            R.mipmap.mine_tab_ic_wallet ,
            R.mipmap.mine_tab_ic_friend};

    public MineAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return img_text.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (null == convertView){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_profile_mine, null);
            viewHolder.re_item_mine = (RelativeLayout) convertView.findViewById(R.id.re_item_mine);
            viewHolder.iv_item_mine = (ImageView) convertView.findViewById(R.id.iv_item_mine);
            viewHolder.tv_item_mine = (TextView) convertView.findViewById(R.id.tv_item_mine);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(getScreenWidth()/3,getScreenWidth()/4);
        viewHolder.re_item_mine.setLayoutParams(layoutParams);
        viewHolder.iv_item_mine.setImageResource(imgs[position]);
        viewHolder.tv_item_mine.setText(img_text[position]);
        return convertView;
    }

    class ViewHolder {
        public RelativeLayout re_item_mine;
        public ImageView iv_item_mine;
        public TextView tv_item_mine;

    }
    protected int getScreenWidth() {
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        return display.getWidth();
    }
}
