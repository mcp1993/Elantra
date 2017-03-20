package com.mcp1993.elantra.social;

import com.mcp1993.elantra.base.BasePresenter;
import com.mcp1993.elantra.challenge.bean.Challenge_horBean;
import com.mcp1993.elantra.explorer.banner.bean.FuLiBean;
import com.mcp1993.elantra.retrofit.ApiCallback;
import com.mcp1993.elantra.social.bean.MusicBean;

/**
 * Created by Administrator on 2017/2/15 0015.
 */

public class SocialPresenter extends BasePresenter<SocialView> {

    public SocialPresenter(SocialView view) {
        attachView(view);
    }

    public void getFuLiBean(){
        String url = "http://gank.io/api/data/福利/3/1";
        addSubscription(apiStores.getFuLiBean(url), new ApiCallback<FuLiBean>() {
            @Override
            public void onSuccess(FuLiBean model) {
                mvpView.showBanner(model);
            }

            @Override
            public void onFailure(String msg) {

            }

            @Override
            public void onFinish() {

            }
        });
    }

    public void getMySocial(){
        String url = "http://v.juhe.cn/toutiao/index?";
        String type = "keji";
        String key = "e3e6514ccfa98992c198c33683e35c5d";
        addSubscription(apiStores.getHorBean(url, type, key), new ApiCallback<Challenge_horBean>() {
            @Override
            public void onSuccess(Challenge_horBean model) {
                mvpView.showMySocialView(model);
            }

            @Override
            public void onFailure(String msg) {

            }

            @Override
            public void onFinish() {

            }
        });
    }

    public void getSocialView(){
        String url ="http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.8.1.0&channel=ppzs&operator\"%20=3&method=baidu.ting.plaza.index&cuid=89CF1E1A06826F9AB95A34DC0F6AAA14";
        addSubscription(apiStores.getMusicBean(url), new ApiCallback<MusicBean>() {
            @Override
            public void onSuccess(MusicBean model) {
                mvpView.showSocialView(model);
            }

            @Override
            public void onFailure(String msg) {

            }

            @Override
            public void onFinish() {

            }
        });
    }
}
