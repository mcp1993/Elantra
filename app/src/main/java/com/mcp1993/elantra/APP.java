package com.mcp1993.elantra;

import android.app.Application;

import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

/**
 * Created by Administrator on 2017/2/16 0016.
 */

public class APP extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        //开启debug模式，方便定位错误，具体错误检查方式可以查看http://dev.umeng.com/social/android/quick-integration的报错必看，正式发布，请关闭该模式
        Config.DEBUG = true;
        UMShareAPI.get(this);


    }

    {

        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
        PlatformConfig.setQQZone("1105402592", "ztYTt2lyhzSqi50C");
        PlatformConfig.setSinaWeibo("290199473", "58ce8cc340458108573c3d046d4f26a2", "http://sns.whalecloud.com");
    }
}
