package com.zenchn.mlibrary.base;

import android.app.Activity;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zenchn.mlibrary.event.BusFactory;
import com.zenchn.mlibrary.kit.KnifeKit;

/**
 * 作    者：wangr on 2017/2/24 14:33
 * 描    述：
 * 修订记录：
 */
public abstract class DefaultFragment extends Fragment implements UiCallback, IDefaultView {

    protected UiCallback uiCallback;
    protected View rootView;
    protected UiHandler mHandler;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.uiCallback = (UiCallback) activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initBaseData();
        if (rootView == null) {
            rootView = inflater.inflate(getLayoutRes(), null);
            getChildView(inflater, rootView);
            KnifeKit.bind(this, rootView);
            initWidget();
        }
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (useEventBus())
            BusFactory.getBus().register(this);//设置eventBus
    }

    @Override
    public void onStop() {
        super.onStop();
        if (useEventBus())
            BusFactory.getBus().unregister(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        uiCallback = null;
    }

    private void initBaseData() {
        if (useUiHandler()) {
            mHandler = new UiHandler(Looper.getMainLooper());//设置主线程的handler
            mHandler.setHandler(new IHandler() {
                @Override
                public void handleMessage(Message msg) {
                    handler(msg);
                }
            });
        }
    }

    protected abstract IApplicationKit getIApplicationKit();

    protected abstract void handler(Message msg);

    @Override
    public boolean useUiHandler() {
        return false;
    }

    @Override
    public boolean useEventBus() {
        return false;
    }

    protected void getChildView(LayoutInflater inflater, View rootView) {

    }

    @Override
    public void showProgress() {
        uiCallback.showProgress();
    }

    @Override
    public void updateProgress(int diff, String progress) {
        uiCallback.updateProgress(diff, progress);
    }

    @Override
    public void hideProgress() {
        uiCallback.hideProgress();
    }

    @Override
    public void onResponseError(String msg) {
        uiCallback.onResponseError(msg);
    }

    @Override
    public void onFailure() {
        uiCallback.onFailure();
    }

    @Override
    public void showMessage(String msg) {
        uiCallback.showMessage(msg);
    }

    @Override
    public void showResMessage(int resId) {
        uiCallback.showResMessage(resId);
    }

}
