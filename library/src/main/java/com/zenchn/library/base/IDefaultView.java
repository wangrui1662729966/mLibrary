package com.zenchn.library.base;

import android.support.annotation.LayoutRes;

public interface IDefaultView extends UiCallback {

    @LayoutRes
    int getLayoutRes();

    void initWidget();
}
