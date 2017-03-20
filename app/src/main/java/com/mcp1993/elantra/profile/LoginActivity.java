package com.mcp1993.elantra.profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.mcp1993.elantra.R;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareConfig;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/3/9 0009.
 */

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.iv_login_QQ)
    ImageView iv_login_QQ;
    @BindView(R.id.iv_login_WX)
    ImageView iv_login_WX;
    @BindView(R.id.iv_login_SINA)
    ImageView iv_login_SINA;

    private UMShareAPI mShareAPI;
    private int ResCodeQQ = 0x002;
    private int ResCodeSINA = 0x003;
    UMShareConfig config;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mShareAPI = UMShareAPI.get(this);

        config = new UMShareConfig();
        config.isNeedAuthOnGetUserInfo(true);
        UMShareAPI.get(LoginActivity.this).setShareConfig(config);

    }
    @OnClick({R.id.iv_login_QQ,R.id.iv_login_WX,R.id.iv_login_SINA})
    public void umLogin(View view){
        SHARE_MEDIA platform = null;
        switch (view.getId()){
            case R.id.iv_login_QQ:
                platform = SHARE_MEDIA.QQ;
                mShareAPI.getPlatformInfo(LoginActivity.this, platform, authListener);
                break;
            case R.id.iv_login_WX:
                platform = SHARE_MEDIA.WEIXIN;
                mShareAPI.getPlatformInfo(LoginActivity.this, platform, authListener);
                break;
            case R.id.iv_login_SINA:
                platform = SHARE_MEDIA.SINA;
                mShareAPI.getPlatformInfo(LoginActivity.this, platform, authListener);
                break;
        }
    }

    UMAuthListener authListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {
            Log.e("data=====>","开始登录");

        }

        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            Toast.makeText(LoginActivity.this, "成功了", Toast.LENGTH_LONG).show();
            Log.e("data=====>",data+"");
            Intent intent = new Intent();
            if (platform ==  SHARE_MEDIA.QQ){

                intent.putExtra("screen_name",data.get("screen_name"));
                intent.putExtra("vip",data.get("vip"));
                intent.putExtra("profile_image_url",data.get("profile_image_url"));
                setResult(ResCodeQQ,intent);

            }else if (platform ==  SHARE_MEDIA.WEIXIN){

            }else if (platform ==  SHARE_MEDIA.SINA){
                intent.putExtra("screen_name",data.get("screen_name"));
                intent.putExtra("iconurl",data.get("iconurl"));
                intent.putExtra("statuses_count",data.get("statuses_count"));
                setResult(ResCodeSINA,intent);
            }
            finish();
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText(LoginActivity.this, "失败：" + t.getMessage(), Toast.LENGTH_LONG).show();

        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(LoginActivity.this, "取消了", Toast.LENGTH_LONG).show();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}
