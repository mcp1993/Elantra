package com.mcp1993.elantra.explorer;

import com.mcp1993.elantra.base.BasePresenter;
import com.mcp1993.elantra.challenge.bean.Challenge_horBean;
import com.mcp1993.elantra.explorer.banner.bean.FuLiBean;
import com.mcp1993.elantra.retrofit.ApiCallback;

/**
 * Created by Administrator on 2017/2/14 0014.
 */

public class ExplorerPresenter extends BasePresenter<ExplorerView> {
    public ExplorerPresenter(ExplorerView view) {
        attachView(view);
    }

    public void getBannerBean(String type){
        String url = "http://v.juhe.cn/toutiao/index?";
        String key = "e3e6514ccfa98992c198c33683e35c5d";
       addSubscription(apiStores.getHorBean(url, type, key), new ApiCallback<Challenge_horBean>() {
           @Override
           public void onSuccess(Challenge_horBean model) {
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

    public void getFuLiBean(){
        String url = "http://gank.io/api/data/福利/14/1";
        addSubscription(apiStores.getFuLiBean(url), new ApiCallback<FuLiBean>() {
            @Override
            public void onSuccess(FuLiBean model) {
                mvpView.showItemView(model);
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
