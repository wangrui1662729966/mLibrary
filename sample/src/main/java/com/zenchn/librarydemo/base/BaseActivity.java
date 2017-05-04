package com.zenchn.librarydemo.base;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.zenchn.library.base.IActivityLifecycle;
import com.zenchn.library.dafault.DefaultActivityLifecycle;
import com.zenchn.library.dafault.AbstractAppCompatActivity;


/**
 * 作    者：wangr on 2017/3/18 0018 13:52
 * 描    述：
 * 修订记录：
 */
public abstract class BaseActivity extends AbstractAppCompatActivity implements IView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContentView(savedInstanceState);
        initWidget();
    }

    @NonNull
    @Override
    protected IActivityLifecycle getDefaultActivityLifecycle() {
        return DefaultActivityLifecycle.getInstance();
    }

    //界面布局的初始化操作
    protected void initContentView(Bundle savedInstanceState) {

    }

    // 可能全屏或者没有ActionBar等
    @Override
    protected void initCustomBaseData() {

    }

    @Override
    public void onStart() {
        super.onStart();
        initData();
    }


}
