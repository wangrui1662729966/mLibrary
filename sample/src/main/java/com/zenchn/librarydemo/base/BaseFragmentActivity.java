package com.zenchn.librarydemo.base;

import com.zenchn.library.dafault.DefaultFragmentActivity;

/**
 * 作    者：wangr on 2017/4/7 0007 21:07
 * 描    述：
 * 修订记录：
 */
public abstract class BaseFragmentActivity extends DefaultFragmentActivity implements IView{

    // 可能全屏或者没有ActionBar等
    @Override
    protected void initCustomBaseData() {

    }

}
