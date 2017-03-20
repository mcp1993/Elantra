package com.mcp1993.elantra.social;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mcp1993.elantra.R;
import com.mcp1993.elantra.social.imgselector.ImageLoader;

/**
 * Created by Administrator on 2017/2/22 0022.
 */

public class PicassoLoader implements ImageLoader {
    private static final long serialVersionUID = 1L;

    @Override
    public void displayImage(Context context, String path, ImageView imageView) {
//        Picasso.with(context)
//                .load(path)
//                .placeholder(R.mipmap.global_img_default)
//
//                .into(imageView);

        Glide.with(context)
                .load(path)
                .placeholder(R.mipmap.global_img_default)
                .centerCrop()
                .into(imageView);
    }
}
