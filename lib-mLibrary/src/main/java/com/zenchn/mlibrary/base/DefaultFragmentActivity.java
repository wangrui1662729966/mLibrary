package com.zenchn.mlibrary.base;

import android.support.annotation.CallSuper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.zenchn.mlibrary.R;
import com.zenchn.mlibrary.utils.KeyboardUtils;

import java.lang.ref.WeakReference;
import java.util.Stack;

/**
 * 作    者：wangr on 2017/4/7 0007 21:07
 * 描    述：
 * 修订记录：
 */
public abstract class DefaultFragmentActivity extends DefaultAppCompatActivity implements UiCallback, IDefaultFragmentView {

    private Stack<WeakReference<Fragment>> fragmentStack = new Stack<>();

    @Override
    public final int getLayoutRes() {
        return R.layout.m_default_fragment_activity_layout;
    }

    @CallSuper
    @Override
    public void initWidget() {
        showDefaultFragment(getDefaultFragment());
    }

    @Override
    public void inflateFragment(int containerViewId, Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(containerViewId, fragment)
                .commit();
    }

    @Override
    public void returnToDefaultFragment(Fragment targetFragment) {
        Fragment defaultFragment = null;
        if (!fragmentStack.isEmpty())
            defaultFragment = fragmentStack.get(0).get();
        if (defaultFragment == null)
            defaultFragment = getDefaultFragment();
        showDefaultFragment(defaultFragment);
    }

    private void showDefaultFragment(Fragment defaultFragment) {
        fragmentStack.clear();
        fragmentStack.add(new WeakReference<>(defaultFragment));
        inflateFragment(R.id.fragment_container, defaultFragment);
    }

    protected abstract Fragment getDefaultFragment();

    @Override
    public void goToNextFragment(Fragment targetFragment) {
        Fragment sourceFragment = fragmentStack.peek().get();
        if (sourceFragment != null)
            switchFragment(sourceFragment, targetFragment);
    }

    @Override
    public boolean backToPreFragment() {
        if (KeyboardUtils.isSoftInputShow(this))
            KeyboardUtils.hideSoftInput(this);
        if (fragmentStack.size() > 1) {
            Fragment topFragment = fragmentStack.pop().get();
            Fragment targetFragment = fragmentStack.peek().get();
            getSupportFragmentManager().beginTransaction().remove(topFragment).show(targetFragment).commit();
            return true;
        }
        return false;
    }

    @Override
    public void switchFragment(Fragment sourceFragment, Fragment targetFragment) {
        if (fragmentStack.peek().get() != targetFragment) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            if (targetFragment.isAdded())
                transaction.hide(sourceFragment).show(targetFragment).commit(); // 隐藏当前的fragment，显示下一个
            else
                transaction.hide(sourceFragment).add(R.id.fragment_container, targetFragment).commit(); // 隐藏当前的fragment，add下一个到Activity中
            fragmentStack.add(new WeakReference<>(targetFragment));
        }
    }

    @Override
    public void onBackPressed() {
        if (backToPreFragment()) {
            //TODO 后退一级
        } else {
            //TODO 后退一级（到底了）
            finish();
        }
    }

}
