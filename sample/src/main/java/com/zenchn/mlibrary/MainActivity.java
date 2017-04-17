package com.zenchn.mlibrary;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.zenchn.mlibrary.base.DefaultFragmentActivity;
import com.zenchn.mlibrary.base.IApplicationKit;

public class MainActivity extends DefaultFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void handler(Message msg) {

    }

    @NonNull
    @Override
    protected IApplicationKit getDefaultApplicationKit() {
        return null;
    }

    @Override
    protected void initCustomBaseData() {

    }

    @Override
    public Fragment getDefaultFragment() {
        return null;
    }

    @Override
    public void initWidget() {

    }
}
