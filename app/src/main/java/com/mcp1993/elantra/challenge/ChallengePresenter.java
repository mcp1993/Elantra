package com.mcp1993.elantra.challenge;

import com.mcp1993.elantra.base.BasePresenter;
import com.mcp1993.elantra.challenge.bean.Challenge_horBean;
import com.mcp1993.elantra.challenge.bean.TopViewBean;
import com.mcp1993.elantra.challenge.bean.WXNewBean;
import com.mcp1993.elantra.retrofit.ApiCallback;

/**
 * Created by Administrator on 2017/2/8 0008.
 */

public class ChallengePresenter extends BasePresenter<ChallengeView> {
    public ChallengePresenter(ChallengeView view){
        attachView(view);
    }

    public void getHorBean(){
        String url = "http://v.juhe.cn/toutiao/index?";
        String type = "top";
        String key = "e3e6514ccfa98992c198c33683e35c5d";
        addSubscription(apiStores.getHorBean(url,type,key), new ApiCallback<Challenge_horBean>() {

            @Override
            public void onSuccess(Challenge_horBean model) {
                mvpView.showHorView(model);
            }

            @Override
            public void onFailure(String msg) {

            }

            @Override
            public void onFinish() {

            }
        });
    }

    public void getTopBean(String q){

        String url = "http://op.juhe.cn/onebox/movie/video?";
        String key = "e7b0e23a310f7ae5c102611fd0e2359b";

        addSubscription(apiStores.getTopViewBean(url,key, q), new ApiCallback<TopViewBean>() {

            @Override
            public void onSuccess(TopViewBean model) {
                mvpView.showTopView(model);
            }

            @Override
            public void onFailure(String msg) {

            }

            @Override
            public void onFinish() {

            }
        });
    }

    public void getWXNEW(){
        String key = "0b9a33ed76fe36cc490ba0cfda66be25";
        addSubscription(apiStores.getWXNewBean(key), new ApiCallback<WXNewBean>() {
            @Override
            public void onSuccess(WXNewBean model) {
                mvpView.showWXNewView(model);
            }

            @Override
            public void onFailure(String msg) {

            }

            @Override
            public void onFinish() {

            }
        });
    }

    public void getSportBean(){
        String url = "http://v.juhe.cn/toutiao/index?";
        String type = "tiyu";
        String key = "e3e6514ccfa98992c198c33683e35c5d";
        addSubscription(apiStores.getHorBean(url,type,key), new ApiCallback<Challenge_horBean>() {

            @Override
            public void onSuccess(Challenge_horBean model) {
                mvpView.showSport(model);
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

