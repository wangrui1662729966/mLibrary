package com.zenchn.library.base;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

/**
 * 作    者：wangr on 2017/4/7 0007 20:53
 * 描    述：
 * 修订记录：
 */
public interface IDefaultFragmentView extends IDefaultView {

    void inflateFragment(@IdRes int containerViewId, @NonNull Fragment fragment);//填充到指定容器

    void returnToDefaultFragment(@NonNull Fragment targetFragment);//返回主功能菜单页

    boolean backToPreFragment();//后退一级

    void goToNextFragment(@NonNull Fragment targetFragment);//前进一级

    void switchFragment(@NonNull Fragment sourceFragment, @NonNull Fragment targetFragment);

}