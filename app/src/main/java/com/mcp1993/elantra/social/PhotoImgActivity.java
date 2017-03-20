package com.mcp1993.elantra.social;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;

import com.githang.statusbar.StatusBarCompat;
import com.mcp1993.elantra.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by Administrator on 2017/2/20 0020.
 */

public class PhotoImgActivity extends AppCompatActivity {
    @BindView(R.id.photoView)
    ImageView photoView;
    PhotoViewAttacher mAttacher;
    private String imgurl;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photoimg);
        ButterKnife.bind(this);
        StatusBarCompat.setStatusBarColor(this, R.color.bottom_text_color_normal, false);
        Intent intent =getIntent();
        imgurl = intent.getStringExtra("social_hot_view");
        Picasso.with(PhotoImgActivity.this).load(imgurl).error(R.mipmap.error)
               .resize(getScreenWidth(),getScreenHight())
                .centerCrop()
                .into(photoView);
        mAttacher = new PhotoViewAttacher(photoView);

    }
    protected int getScreenWidth() {
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        return display.getWidth();
    }

    protected int getScreenHight(){
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        return display.getHeight();
    }
}
