package com.mcp1993.elantra.base;

import com.mcp1993.elantra.retrofit.ApiStores;
import com.mcp1993.elantra.retrofit.AppClient;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 2017/2/8 0008.
 */

public class BasePresenter<V>{
    public V mvpView;
    public ApiStores apiStores;
    private CompositeSubscription mCompositeSubscription;

    public void attachView(V mvpView){
        this.mvpView=mvpView;
        apiStores= AppClient.retrofit().create(ApiStores.class);
    }

    public void detachView(){
        this.mvpView=null;
        onUnsubscribe();
    }

    //RXjava取消注册，以避免内存泄露
    public void onUnsubscribe() {
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }

    public void addSubscription(Observable observable, Subscriber subscriber) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber));
    }
}
