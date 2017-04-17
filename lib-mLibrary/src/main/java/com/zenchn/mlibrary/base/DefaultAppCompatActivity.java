package com.zenchn.mlibrary.base;

import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.Window;

import com.zenchn.mlibrary.R;
import com.zenchn.mlibrary.event.BusFactory;
import com.zenchn.mlibrary.kit.KnifeKit;
import com.zenchn.mlibrary.utils.NetworkUtils;
import com.zenchn.mlibrary.utils.ToastUtils;
import com.zenchn.mlibrary.widget.CustomProgress;

/**
 * 作    者：wangr on 2016/12/30 11:02
 * 描    述：activity的基类
 * 修订记录：
 */

public abstract class  DefaultAppCompatActivity extends FragmentActivity implements UiCallback, IDefaultView {

    protected IApplicationKit mApplicationKit;//代理类
    protected CustomProgress mProgress;//加载提示框
    protected UiHandler mHandler;//主线程handler（可选）

    public DefaultAppCompatActivity() {
        this.mApplicationKit = getDefaultApplicationKit();// 设置异常处理
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBaseData();
        if (getLayoutRes() > 0) {
            setContentView(getLayoutRes());
            KnifeKit.bind(this);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (useEventBus())
            BusFactory.getBus().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (useEventBus())
            BusFactory.getBus().unregister(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mApplicationKit.removeActivity(this);//activity出栈
        if (mHandler != null)
            mHandler.removeCallbacksAndMessages(null);
    }

    protected void initBaseData() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
        mApplicationKit.addActivity(this);// activity入栈

        if (useUiHandler()) {
            mHandler = new UiHandler(Looper.getMainLooper());//设置主线程的handler
            mHandler.setHandler(new IHandler() {
                @Override
                public void handleMessage(Message msg) {
                    handler(msg);
                }
            });
        }
        initCustomBaseData();
    }

    protected abstract void handler(Message msg);

    @Override
    public boolean useEventBus() {
        return false;
    }

    @Override
    public boolean useUiHandler() {
        return false;
    }

    @NonNull
    protected abstract IApplicationKit getDefaultApplicationKit();

    @LayoutRes
    public abstract int getLayoutRes();

    protected abstract void initCustomBaseData();

    @Override
    public void onBackPressed() {
        ToastUtils.cancelCurrentToast();
        super.onBackPressed();
    }

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
        if (mProgress == null) {
            mProgress = new CustomProgress(this);
            mProgress.setCancelable(false);
            mProgress.setMsg(getApplicationContext().getResources().getString(R.string.mLibrary_loading));
        }
        mProgress.show();
    }

    @Override
    public void updateProgress(int diff, String progress) {
        if (mProgress != null)
            mProgress.updateMsg(diff, progress);
    }

    @Override
    public void hideProgress() {
        if (mProgress != null)
            mProgress.dismiss();
    }

    @Override
    public void onFailure() {
        showResMessage(NetworkUtils.isNetworkAvailable(this) ? R.string.mLibrary_error_local : R.string.mLibrary_error_network);
    }

    @Override
    public void onResponseError(String msg) {
        showMessage(msg);
    }

    @Override
    public void showMessage(String msg) {
        ToastUtils.showCustomMessage(this, msg);
    }

    @Override
    public void showResMessage(@StringRes int resId) {
        showMessage(getApplicationContext().getString(resId));
    }
}
