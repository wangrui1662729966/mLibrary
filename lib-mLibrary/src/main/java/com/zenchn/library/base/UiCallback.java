package com.zenchn.library.base;

import android.support.annotation.StringRes;

/**
 * 作    者：wangr on 2016/12/30 11:02
 * 描    述：
 * 修订记录：
 */
public interface UiCallback {

    void showProgress();

    void hideProgress();

    void updateProgress(int progressDiff, String progressMsg);

    void showMessage(String msg);

    void showResMessage(@StringRes int resId);

}
