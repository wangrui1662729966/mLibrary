package com.zenchn.librarydemo.base;

import android.os.Message;

import com.zenchn.librarydemo.app.ApplicationKit;
import com.zenchn.library.base.DefaultHandleFragment;
import com.zenchn.library.base.IApplicationKit;

/**
 * 作    者：wangr on 2017/3/18 0018 14:03
 * 描    述：
 * 修订记录：
 */
public abstract class BaseHandleFragment extends DefaultHandleFragment {

    @Override
    protected IApplicationKit getIApplicationKit() {
        return ApplicationKit.getInstance();
    }

    @Override
    protected void handler(Message msg) {

    }


}