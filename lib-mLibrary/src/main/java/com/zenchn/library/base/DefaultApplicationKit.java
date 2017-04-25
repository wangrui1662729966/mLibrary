package com.zenchn.library.base;

import android.app.Activity;
import android.app.Application;
import android.os.Process;
import android.support.annotation.NonNull;
import android.view.View;

import com.zenchn.library.MLibraryConf;
import com.zenchn.library.R;
import com.zenchn.library.log.LogUtils;
import com.zenchn.library.utils.CrashUtils;
import com.zenchn.library.utils.ToastUtils;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.Stack;

/**
 * 作    者：wangr on 2017/2/20 22:51
 * 描    述：Application管理类
 * 修订记录：
 */
public abstract class DefaultApplicationKit implements IApplicationKit {

    protected Application application;

    @Override
    public void initKit(@NonNull Application application) {
        this.application = application;
        initCustomSetting();//设置用户自定义设置
        setUncaughtException();//设置默认异常处理
    }

    @Override
    public Application getApplication() {
        return application;
    }

    @Override
    public void initCustomSetting() {
        setDefaultToastStyle(application);//初始化默认Toast样式（可重写该方法）
    }

    /**
     * 初始化toast样式
     *
     * @param app
     */
    private void setDefaultToastStyle(Application app) {
        View toastView = View.inflate(app, R.layout.m_toast_ios_style_simple, null);
        ToastUtils.setCustomToastView(toastView);
    }

    /**
     * 设置异常处理器
     */
    public void setUncaughtException() {
        Thread.currentThread().setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, Throwable ex) {
                LogUtils.printErrorLog(ex.getMessage());
                CrashUtils.printException(MLibraryConf.appFolder + File.separator + MLibraryConf.FILE_DIR_LOGS, ex, application);
                exitApp();
            }
        });
    }

    /**
     * Activity任务栈(由弱引用降低内存泄漏的隐患)
     */
    protected Stack<WeakReference<Activity>> activityStack = new Stack<>();

    @Override
    public void addActivity(Activity activity) {
        LogUtils.printCustomLog(MLibraryConf.LogTags.ACTIVITY_TAG, activity.getClass().getSimpleName() + "--开启了");
        activityStack.add(new WeakReference<>(activity));
    }

    @Override
    public void removeActivity(Activity activity) {
        for (int i = 0; i < activityStack.size(); i++) {
            if (activityStack.get(i).get() == activity) {
                LogUtils.printCustomLog(MLibraryConf.LogTags.ACTIVITY_TAG, activity.getClass().getSimpleName() + "--关闭了");
                activityStack.remove(i);
                break;
            }
        }
    }

    @Override
    public Activity getTopActivity() {
        if (activityStack.size() > 0) {
            return activityStack.peek().get();
        }
        return null;
    }

    @Override
    public void finishAllActivity() {
        for (int i = activityStack.size() - 1; i >= 0; i--) {
            Activity activity = activityStack.get(i).get();
            if (activity != null) {
                activity.finish();
            }
        }
    }

    /**
     * 退出程序
     */
    @Override
    public void exitApp() {
        finishAllActivity();
        Process.killProcess(Process.myPid());
    }

}
