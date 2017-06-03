package com.zenchn.library.dafault;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.zenchn.library.R;
import com.zenchn.library.base.IDefaultFragmentView;


/**
 * 作    者：wangr on 2017/2/24 14:33
 * 描    述：与activity相互关联的fragment
 * 修订记录：
 */
public abstract class AbstractHandleFragment extends AbstractFragment implements IDefaultFragmentView {

    protected IDefaultFragmentView mDefaultFragmentView;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof IDefaultFragmentView) {
            mDefaultFragmentView = (IDefaultFragmentView) activity;
            mUiController = mDefaultFragmentView.getUiController();
        }
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
    public void inflateFragment(int containerViewId, Fragment fragment) {
        if (mDefaultFragmentView != null) {
            mDefaultFragmentView.inflateFragment(containerViewId, fragment);
        }
    }

    @Override
    public boolean backToPreFragment() {
        return mDefaultFragmentView != null && mDefaultFragmentView.backToPreFragment();
    }

    @Override
    public void goToNextFragment(@NonNull Fragment targetFragment) {
        if (mDefaultFragmentView != null)
            mDefaultFragmentView.goToNextFragment(targetFragment);
    }

    @Override
    public void switchFragment(@NonNull Fragment sourceFragment, @NonNull Fragment targetFragment) {
        if (mDefaultFragmentView != null)
            mDefaultFragmentView.switchFragment(sourceFragment, targetFragment);
    }

    @Override
    public void returnToDefaultFragment(@NonNull Fragment targetFragment) {
        if (mDefaultFragmentView != null)
            mDefaultFragmentView.returnToDefaultFragment(targetFragment);
    }
}
