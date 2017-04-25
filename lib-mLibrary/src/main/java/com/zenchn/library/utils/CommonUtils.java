package com.zenchn.library.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.zenchn.library.base.UiHandler;

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 通用工具类
 */
public class CommonUtils {

    private static long lastClickTime;
    private static ExecutorService executorService = Executors.newCachedThreadPool();
    private static UiHandler handler = new UiHandler(Looper.getMainLooper());

    public static UiHandler getUIHandler() {
        if (handler != null) {
            handler = new UiHandler(Looper.getMainLooper());
        }
        return handler;
    }

    /**
     * 防止控件被重复点击相隔800ms
     *
     * @return
     */
    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 800) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    /**
     * 在子线程中执行
     *
     * @param task
     */
    public static void runInThread(Runnable task) {
        executorService.execute(task);
    }

    /**
     * 在主线程中执行
     *
     * @param task
     */
    public static void runOnUIThread(Runnable task) {
        handler.post(task);
    }

    /**
     * 启动一个Activity
     *
     * @param ctx
     * @param clazz
     */
    @SuppressWarnings("rawtypes")
    public static void startActivity(Object ctx, Class clazz) {
        if (ctx instanceof Context) {
            Context mContext = (Context) ctx;
            mContext.startActivity(new Intent(mContext, clazz));
        }
    }

    /**
     * ViewGroup设置不可点击
     *
     * @param view
     * @param enabled
     */
    public static void setViewGroupEnabled(View view, boolean enabled) {
        if (null == view) {
            return;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            LinkedList<ViewGroup> queue = new LinkedList<>();
            queue.add(viewGroup);
            // 遍历viewGroup
            while (!queue.isEmpty()) {
                ViewGroup current = queue.removeFirst();
                current.setEnabled(enabled);
                for (int i = 0; i < current.getChildCount(); i++) {
                    if (current.getChildAt(i) instanceof ViewGroup) {
                        queue.addLast((ViewGroup) current.getChildAt(i));
                    } else {
                        current.getChildAt(i).setEnabled(enabled);
                    }
                }
            }
        } else {
            view.setEnabled(enabled);
        }
    }

    /**
     * 隐藏软键盘
     *
     * @param mContext
     */
    public static void hideSoftInputFrom(Context mContext) {
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            View focusView = ((Activity) mContext).getCurrentFocus();
            if (focusView != null)
                imm.hideSoftInputFromWindow(focusView.getWindowToken(), 0);
        }
    }

    /**
     * 显示软键盘
     *
     * @param mContext
     */
    public static void showSoftInputFrom(Context mContext) {
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            View focusView = ((Activity) mContext).getCurrentFocus();
            if (focusView != null)
                imm.hideSoftInputFromWindow(focusView.getWindowToken(), 0);
        }
    }

}
