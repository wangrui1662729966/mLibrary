package com.zenchn.library.base;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.zenchn.library.R;


/**
 * 作    者：wangr on 2017/2/24 14:33
 * 描    述：与activity相互关联的fragment
 * 修订记录：
 */
public abstract class DefaultHandleFragment extends DefaultFragment implements UiCallback, IDefaultFragmentView {

    protected IDefaultFragmentView mDefaultFragmentView;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof IDefaultFragmentView)
            this.mDefaultFragmentView = (IDefaultFragmentView) activity;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.m_default_handle_fragment;
    }

    @Override
    public void getChildView(LayoutInflater inflater, View rootView) {
        FrameLayout childContainer = (FrameLayout) rootView.findViewById(R.id.fragment_base_child_container);
        inflater.inflate(getContentLayoutRes(), childContainer);
    }

    @LayoutRes
    protected abstract int getContentLayoutRes();

    @Override
    public void onDetach() {
        super.onDetach();
        mDefaultFragmentView = null;
    }

    @Override
    public void showProgress() {
        mDefaultFragmentView.showProgress();
    }

    @Override
    public void updateProgress(int diff, String progress) {
        mDefaultFragmentView.updateProgress(diff, progress);
    }

    @Override
    public void hideProgress() {
        mDefaultFragmentView.hideProgress();
    }

    @Override
    public void onResponseError(String msg) {
        mDefaultFragmentView.onResponseError(msg);
    }

    @Override
    public void onFailure() {
        mDefaultFragmentView.onFailure();
    }

    @Override
    public void showMessage(String msg) {
        mDefaultFragmentView.showMessage(msg);
    }

    @Override
    public void showResMessage(int resId) {
        mDefaultFragmentView.showResMessage(resId);
    }

    @Override
    public void inflateFragment(int containerViewId, Fragment fragment) {
        mDefaultFragmentView.inflateFragment(containerViewId, fragment);
    }

    @Override
    public boolean backToPreFragment() {
        return mDefaultFragmentView.backToPreFragment();
    }

    @Override
    public void goToNextFragment(@NonNull Fragment targetFragment) {
        mDefaultFragmentView.goToNextFragment(targetFragment);
    }

    @Override
    public void switchFragment(@NonNull Fragment sourceFragment, @NonNull Fragment targetFragment) {
        mDefaultFragmentView.switchFragment(sourceFragment, targetFragment);
    }

    @Override
    public void returnToDefaultFragment(@NonNull Fragment targetFragment) {
        mDefaultFragmentView.returnToDefaultFragment(targetFragment);
    }
}
