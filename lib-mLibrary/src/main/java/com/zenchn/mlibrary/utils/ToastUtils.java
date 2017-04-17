package com.zenchn.mlibrary.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.zenchn.mlibrary.R;


/**
 * 作者：wangr on 2017/1/4 10:26
 * 描述：Toast工具类
 */
public class ToastUtils {

    private ToastUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    private static Handler handler = new Handler(Looper.getMainLooper());
    private static Object synObj = new Object();
    private static Toast toast;
    private static Toast defaultToast;
    private static View customToastView;
    private static TextView toastTextView;

    /**
     * 初始化toast样式
     *
     * @param customToastView
     */
    public static void setCustomToastView(View customToastView) {
        ToastUtils.customToastView = customToastView;
        ToastUtils.toastTextView = (TextView) customToastView.findViewById(R.id.text);
        if (toastTextView == null)
            throw new IllegalStateException("make toast without text view ...");
    }

    /**
     * 系统默认的Toast发送消息，默认Toast.LENGTH_SHORT
     *
     * @param context
     * @param msg
     */
    public static void showDefaultMessage(Context context, String msg) {
        showDefaultToast(context.getApplicationContext(), msg, Toast.LENGTH_SHORT);
    }

    /**
     * 系统默认的Toast发送消息
     *
     * @param context
     * @param msg
     */
    public static void showDefaultMessageLong(Context context, String msg) {
        showDefaultToast(context.getApplicationContext(), msg, Toast.LENGTH_LONG);
    }

    /**
     * 系统默认的Toast发送消息
     *
     * @param context
     * @param msg
     * @param len
     */
    private static void showDefaultToast(final Context context, final String msg, final int len) {
        handler.post(new Runnable() {

            @Override
            public void run() {
                synchronized (synObj) {
                    if (defaultToast != null)
                        defaultToast.cancel();
                    defaultToast = Toast.makeText(context, msg, len);
                    defaultToast.show();
                }
            }
        });
    }

    /**
     * 发送自定义样式的toast
     *
     * @param context
     * @param msg
     */
    public static void showCustomMessage(Context context, String msg) {
        if (customToastView != null)
            showCustomToast(context.getApplicationContext(), msg, customToastView);
        else
            showDefaultMessage(context.getApplicationContext(), msg);
    }

    /**
     * Toast发送自定义样式
     *
     * @param context
     * @param msg
     * @param view
     */
    private static void showCustomToast(final Context context, final String msg, final View view) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                synchronized (synObj) {
                    if (toast != null)
                        toast.cancel();
                    toast = new Toast(context);
                    if (toastTextView == null)
                        toastTextView = (TextView) view.findViewById(R.id.text);
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    toast.setView(view);
                    toast.setDuration(Toast.LENGTH_LONG);
                    toastTextView.setText(msg);
                    toast.show();
                }
            }
        });
    }

    /**
     * 关闭当前Toast
     */
    public static void cancelCurrentToast() {
        if (toast != null) {
            toast.cancel();
            toast = null;
        }
        if (defaultToast != null) {
            defaultToast.cancel();
            defaultToast = null;
        }
    }
}
