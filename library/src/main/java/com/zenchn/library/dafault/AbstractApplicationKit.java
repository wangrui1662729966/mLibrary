package com.zenchn.library.dafault;

import android.app.Application;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.view.View;

import com.zenchn.library.R;
import com.zenchn.library.base.IApplicationKit;
import com.zenchn.library.base.ICrashCallback;
import com.zenchn.library.wrapper.SuperToast;

/**
 * 作    者：wangr on 2017/2/20 22:51
 * 描    述：Application管理类
 * 修订记录：
 */
public abstract class AbstractApplicationKit implements IApplicationKit, ICrashCallback {

    protected Application application;

    @Override
    public void initKit(@NonNull Application application) {
        this.application = application;
        initSetting();//设置用户自定义设置
    }

    @Override
    public Application getApplication() {
        return application;
    }

    @CallSuper
    @Override
    public void initSetting() {
        initCrashHandler();
        initToastStyle();
    }

    /**
     * 初始化toast样式
     */
    protected void initToastStyle() {
        View toastView = View.inflate(application, R.layout.m_toast_ios_style_simple, null);
        SuperToast.setCustomToastView(toastView);
    }

    /**
     * 初始化crash异常处理
     */
    @CallSuper
    protected void initCrashHandler() {
        DefaultUncaughtHandler.getInstance().init(application, this);
    }

}
