package com.zenchn.mlibrary.utils;

import android.hardware.Camera;
import android.util.Log;

/**
 * Created by wangr on 2016/10/31 0031.
 */
public class CameraUtils {

    private static Camera camera;

    /**
     * 打开闪光灯
     */
    public static void openCamera() {
        try {
            Log.i("tag", "~~~~~~~~~~~打开闪光灯~~~~~~~~~~~");
            camera = Camera.open();
            camera.startPreview();
            Camera.Parameters parameters = camera.getParameters();
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            camera.setParameters(parameters);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭闪光灯
     */
    public static void closeCamera() {
        try {
            Log.i("tag", "~~~~~~~~~~~关闭闪光灯~~~~~~~~~~~");
            Camera.Parameters parameters = camera.getParameters();
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            camera.setParameters(parameters);
            camera.release();
            camera = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
