package com.zenchn.mlibrary.base;

import android.os.Bundle;
import android.os.Message;

import com.zenchn.mlibrary.app.ApplicationKit;


/**
 * 作    者：wangr on 2017/3/18 0018 13:52
 * 描    述：
 * 修订记录：
 */
public abstract class BaseActivity extends DefaultAppCompatActivity implements IView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContentView(savedInstanceState);
        initWidget();
    }

    @Override
    protected IApplicationKit getDefaultApplicationKit() {
        return ApplicationKit.getInstance();
    }

    //界面布局的初始化操作
    protected void initContentView(Bundle savedInstanceState) {

    }

    // 可能全屏或者没有ActionBar等
    @Override
    protected void initCustomBaseData() {

    }

    //如果需要接受消息(useUiHandler()=true)，则重写该方法接收消息
    protected void handler(Message msg) {
    }

    @Override
    public void onStart() {
        super.onStart();
        initData();
    }

}
