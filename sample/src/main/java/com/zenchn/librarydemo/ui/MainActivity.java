package com.zenchn.librarydemo.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.zenchn.library.kit.AndroidKits;
import com.zenchn.librarydemo.R;
import com.zenchn.librarydemo.base.BaseActivity;
import com.zenchn.librarydemo.widget.LiveCameraView;

import butterknife.BindView;

/**
 * 作    者：wangr on 2017/4/25 15:43
 * 描    述：
 * 修订记录：
 */

public class MainActivity extends BaseActivity {

    @BindView(R.id.lcv)
    LiveCameraView lcv;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    public void initWidget() {

    }

    @Override
    public void initData() {

    }

    public void openCamera(View view) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // 没有权限，申请权限。
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 0);

        } else {

            // 有权限了，去放肆吧。
            Camera camera = AndroidKits.Camera.prepareCamera();
            lcv.setCamera(camera).startPreviewDisplay();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void openLight(View view) {
        AndroidKits.Camera.lightOn();
    }

    public void closeLight(View view) {
        AndroidKits.Camera.lightOff();
    }

    public void closeCamera(View view) {
        lcv.stopPreviewDisplay();
        AndroidKits.Camera.releaseCamera();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
