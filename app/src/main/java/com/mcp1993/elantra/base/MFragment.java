package com.mcp1993.elantra.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by Administrator on 2017/2/8 0008.
 */

public abstract class MFragment<P extends BasePresenter> extends BaseFragment {
    public P mPresenter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
    }

    public abstract P createPresenter();


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter!=null){
            mPresenter.detachView();
        }
    }

}
