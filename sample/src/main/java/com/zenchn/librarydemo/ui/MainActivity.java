package com.zenchn.librarydemo.ui;

import com.orhanobut.logger.Logger;
import com.orhanobut.logger.Settings;
import com.zenchn.library.log.EasyLogger;
import com.zenchn.librarydemo.R;
import com.zenchn.librarydemo.base.BaseActivity;

import java.util.ArrayList;

/**
 * 作    者：wangr on 2017/4/25 15:43
 * 描    述：
 * 修订记录：
 */

public class MainActivity extends BaseActivity {

    @Override
    public int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    public void initWidget() {

    }

    @Override
    public void initData() {
        Logger.init("wangr");

        Settings settings = new Settings();

        ArrayList<String> strings = new ArrayList<>();
        for (String s : "HELLO".split("")) {
            strings.add(s);
        }
//        Logger.i("test", strings);
//        Logger.i("test1", null);

        EasyLogger.d(strings);
        Logger.d(strings);

//        Logger.e("hello");
//        Logger.w("hello");
//        Logger.v("hello");
//        Logger.wtf("hello");
    }

}
