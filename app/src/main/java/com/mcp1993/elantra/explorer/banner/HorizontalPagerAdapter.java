package com.mcp1993.elantra.explorer.banner;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mcp1993.elantra.R;
import com.mcp1993.elantra.challenge.bean.Challenge_horData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by GIGAMOLE on 7/27/16.
 */
public class HorizontalPagerAdapter extends PagerAdapter {

    private List<Challenge_horData> datas = new ArrayList<>();
    private Context mContext;
    private LayoutInflater mLayoutInflater;


    public HorizontalPagerAdapter(List<Challenge_horData> datas, Context mContext) {
        this.datas = datas;
        this.mContext = mContext;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public int getItemPosition(final Object object) {
        return POSITION_NONE;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        final View view;

        view = mLayoutInflater.inflate(R.layout.item, container, false);
        ImageView img_item = (ImageView) view.findViewById(R.id.img_item);
        TextView txt_item = (TextView) view.findViewById(R.id.txt_item);
        Picasso.with(mContext).load(datas.get(position)
                    .getThumbnail_pic_s()).into(img_item);
        txt_item .setText(datas.get(position).getTitle());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext,"position:"+position,Toast.LENGTH_SHORT).show();
            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public boolean isViewFromObject(final View view, final Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(final ViewGroup container, final int position, final Object object) {
        container.removeView((View) object);
    }
}
