package com.zenchn.librarydemo.ui;

import android.app.Activity;
import android.widget.Button;
import android.widget.Toast;

import com.zenchn.library.anim.ActivityAnimator;
import com.zenchn.librarydemo.R;
import com.zenchn.librarydemo.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作    者：wangr on 2017/5/5 0005 21:10
 * 描    述：
 * 修订记录：
 */
public class SecondActivity extends BaseActivity {

    @BindView(R.id.bt_back)
    Button btBack;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_second;
    }

    @Override
    public void initWidget() {

    }

    @Override
    public void initData() {

    }


    @Override
    public void onBackPressed() {
        finish();
        try {
            ActivityAnimator anim = new ActivityAnimator();
            anim.getClass().getMethod(this.getIntent().getExtras().getString("backAnimation") + "Animation", Activity.class).invoke(anim, this);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "An error occured " + e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    @OnClick(R.id.bt_back)
    public void onClick() {
        onBackPressed();
    }
}
