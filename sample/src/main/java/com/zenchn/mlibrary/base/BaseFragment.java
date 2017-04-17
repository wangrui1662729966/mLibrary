package com.zenchn.mlibrary.base;

import android.os.Message;

import com.zenchn.mlibrary.app.ApplicationKit;

/**
 * 作    者：wangr on 2017/4/17 14:52
 * 描    述：
 * 修订记录：
 */
public abstract class BaseFragment extends DefaultFragment{

    @Override
    protected IApplicationKit getIApplicationKit() {
        return ApplicationKit.getInstance();
    }

    @Override
    protected void handler(Message msg) {

    }

}
