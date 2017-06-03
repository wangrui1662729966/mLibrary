package com.zenchn.library.dafault;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zenchn.library.base.IDefaultView;
import com.zenchn.library.base.IUiController;
import com.zenchn.library.kit.KnifeKit;

/**
 * 作    者：wangr on 2017/2/24 14:33
 * 描    述：
 * 修订记录：
 */
public abstract class AbstractFragment extends Fragment implements IDefaultView {

    protected IUiController mUiController;//基本的ui控制器
    protected View rootView;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(getLayoutRes(), null);
            getChildView(inflater, rootView);
            KnifeKit.bind(this, rootView);
            initWidget();
        }
        return rootView;
    }

    public AbstractFragment() {
        mUiController = getDefaultUiController();
    }

    protected IUiController getDefaultUiController() {
        return new DefaultUiController(getActivity());
    }

    protected void setUiController(IUiController mUiController) {
        this.mUiController = mUiController;
    }

    protected void getChildView(LayoutInflater inflater, View rootView) {

    }

    @Override
    public void showProgress() {
        if (mUiController != null)
            mUiController.showProgress();
    }

    @Override
    public void updateProgress(int diff, String progress) {
        if (mUiController != null)
            mUiController.updateProgress(diff, progress);
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
