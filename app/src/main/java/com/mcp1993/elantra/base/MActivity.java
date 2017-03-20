package com.mcp1993.elantra.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by Administrator on 2017/2/8 0008.
 */

public abstract class MActivity<P extends BasePresenter> extends BaseActivity {
    public P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
    }
    public abstract P createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter!=null){
            mPresenter.detachView();
        }
    }
}
