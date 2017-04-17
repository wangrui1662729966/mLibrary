package com.zenchn.mlibrary.base;

import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;

/**
 * 作    者：wangr on 2016/12/30 11:02
 * 描    述：
 * 修订记录：
 */
public interface UiCallback {

    @LayoutRes
    int getLayoutRes();

    void initWidget();

    void showProgress();

    void updateProgress(int diff, String progress);

    void hideProgress();

    void showMessage(String message);

    void showResMessage(@StringRes int resId);

    void onResponseError(String msg);

    void onFailure();

}
