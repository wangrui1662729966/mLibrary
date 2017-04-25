package com.zenchn.librarydemo.base;

import android.os.Message;
import android.support.annotation.NonNull;

import com.zenchn.librarydemo.app.ApplicationKit;
import com.zenchn.library.base.DefaultFragmentActivity;
import com.zenchn.library.base.IApplicationKit;

/**
 * 作    者：wangr on 2017/4/7 0007 21:07
 * 描    述：
 * 修订记录：
 */
public abstract class BaseFragmentActivity extends DefaultFragmentActivity implements IView{

    @NonNull
    @Override
    protected IApplicationKit getDefaultApplicationKit() {
        return ApplicationKit.getInstance();
    }

    // 可能全屏或者没有ActionBar等
    @Override
    protected void initCustomBaseData() {

    }

    //如果需要接受消息(useUiHandler()=true)，则重写该方法接收消息
    @Override
    protected void handler(Message msg) {

    }

}
