package com.mcp1993.elantra.retrofit;

import com.mcp1993.elantra.challenge.bean.Challenge_horBean;
import com.mcp1993.elantra.challenge.bean.TopViewBean;
import com.mcp1993.elantra.challenge.bean.WXNewBean;
import com.mcp1993.elantra.explorer.banner.bean.FuLiBean;
import com.mcp1993.elantra.social.bean.MusicBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by Administrator on 2017/2/8 0008.
 */

public interface ApiStores {
    // BaseApi
    String  API_SERVER_URL="http://v.juhe.cn/";


    @GET
    Observable<Challenge_horBean> getHorBean(@Url String url, @Query("type")String type, @Query("key")String key);

    @GET
    Observable<TopViewBean> getTopViewBean(@Url String url,@Query("key")String key, @Query("q")String q);

    @GET("weixin/query?")
    Observable<WXNewBean> getWXNewBean(@Query("key")String key);

    @GET
    Observable<FuLiBean> getFuLiBean(@Url String url);

    @GET
    Observable<MusicBean> getMusicBean(@Url String url);
}
