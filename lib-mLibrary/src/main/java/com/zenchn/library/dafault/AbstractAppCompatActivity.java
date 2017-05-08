package com.zenchn.library.dafault;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.Window;

import com.zenchn.library.base.IActivityLifecycle;
import com.zenchn.library.base.IDefaultView;
import com.zenchn.library.base.IUiController;
import com.zenchn.library.utils.ToastUtils;

import butterknife.ButterKnife;

/**
 * 作    者：wangr on 2016/12/30 11:02
 * 描    述：activity的基类
 * 修订记录：
 */

public abstract class AbstractAppCompatActivity extends FragmentActivity implements IDefaultView {

    protected IActivityLifecycle mActivityLifecycle;//代理activity生命周期管理（使用单例对象）
    protected IUiController mUiController;//基本的ui控制器

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityLifecycle.onActivityCreated(this, savedInstanceState);// activity入栈
        initBaseData();
        if (getLayoutRes() > 0) {
            setContentView(getLayoutRes());
//            KnifeKit.bind(this);
            ButterKnife.bind(this);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mActivityLifecycle.onActivityStarted(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mActivityLifecycle.onActivityResumed(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mActivityLifecycle.onActivityPaused(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mActivityLifecycle.onActivityStopped(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mActivityLifecycle.onActivityDestroyed(this);//activity出栈
    }

    public AbstractAppCompatActivity() {
        this.mActivityLifecycle = getDefaultActivityLifecycle();
        this.mUiController = getDefaultUiController();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mActivityLifecycle.onActivitySaveInstanceState(this, outState);
    }

    protected void initBaseData() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
        initCustomBaseData();
    }

    @NonNull
    protected abstract IActivityLifecycle getDefaultActivityLifecycle();

    protected IUiController getDefaultUiController() {
        return new DefaultUiController(this);
    }

    protected void setUiController(IUiController mUiController) {
        this.mUiController = mUiController;
    }

    @LayoutRes
    public abstract int getLayoutRes();

    protected abstract void initCustomBaseData();

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        ToastUtils.cancelCurrentToast();
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        ToastUtils.cancelCurrentToast();
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void showProgress() {
        if (mUiController != null)
            mUiController.showProgress();
    }

    @Override
    public void updateProgress(int progress, String msg) {
        if (mUiController != null)
            mUiController.updateProgress(progress, msg);
    }

    @Override
    public void hideProgress() {
        if (mUiController != null)
            mUiController.hideProgress();
    }

    @Override
    public void showMessage(String msg) {
        if (mUiController != null)
            mUiController.showMessage(msg);
    }

    @Override
    public void showResMessage(@StringRes int resId) {
        if (mUiController != null)
            mUiController.showResMessage(resId);
    }

}
