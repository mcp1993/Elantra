package com.mcp1993.elantra;

import android.app.Application;

import com.mcp1993.elantra.dao.DaoMaster;
import com.mcp1993.elantra.dao.DaoSession;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import org.greenrobot.greendao.database.Database;

/**
 * Created by Administrator on 2017/2/16 0016.
 */

public class APP extends Application{
    public static DaoSession mDaoSession;
    @Override
    public void onCreate() {
        super.onCreate();
        //开启debug模式，方便定位错误，具体错误检查方式可以查看http://dev.umeng.com/social/android/quick-integration的报错必看，正式发布，请关闭该模式
        Config.DEBUG = true;
        UMShareAPI.get(this);
        initGreenDao();//初始化greendao数据库

    }

    {

        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
        PlatformConfig.setQQZone("1105402592", "ztYTt2lyhzSqi50C");
        PlatformConfig.setSinaWeibo("290199473", "58ce8cc340458108573c3d046d4f26a2", "http://sns.whalecloud.com");
    }

    private void initGreenDao() {
        //DevOpenHelper每次数据库升级会清空数据，一般用于开发
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this,"easyMvp_db",null);
        Database db = helper.getWritableDb();
        mDaoSession = new DaoMaster(db).newSession();
    }

    public static DaoSession getDaoSession(){
        return mDaoSession;
    }
}
