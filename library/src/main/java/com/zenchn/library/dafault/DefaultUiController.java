package com.zenchn.library.dafault;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import com.zenchn.library.R;
import com.zenchn.library.base.IUiController;
import com.zenchn.library.widget.EasyLoading;
import com.zenchn.library.wrapper.SuperToast;

/**
 * 作    者：wangr on 2017/4/27 14:56
 * 描    述：
 * 修订记录：
 */
public class DefaultUiController implements IUiController {

    protected Context mContext;
    protected EasyLoading mProgress;//加载提示框

    public DefaultUiController(@NonNull Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void showProgress() {
        if (mProgress == null) {
            mProgress = new EasyLoading(mContext);
            mProgress.setCancelable(false);
            mProgress.setMsg(mContext.getResources().getString(R.string.mLibrary_loading));
        }
        mProgress.show();
    }

    @Override
    public void hideProgress() {
        if (mProgress != null)
            mProgress.dismiss();
    }

    @Override
    public void updateProgress(int progressDiff, String progressMsg) {
        if (mProgress != null)
            mProgress.updateMsg(progressDiff, progressMsg);
    }

    @Override
    public void showMessage(String message) {
        SuperToast.showCustomMessage(mContext, message);
    }

    @Override
    public void showResMessage(@StringRes int resId) {
        SuperToast.showCustomMessage(mContext, mContext.getString(resId));
    }
}
