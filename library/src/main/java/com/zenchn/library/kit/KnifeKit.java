package com.zenchn.library.kit;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作    者：wangr on 2017/5/5 0005 22:03
 * 描    述：
 * 修订记录：
 */
public class KnifeKit {

    /**
     * 绑定
     *
     * @param target
     * @return
     */
    public static Unbinder bind(Object target) {
        if (target instanceof Activity) {
            return ButterKnife.bind((Activity) target);
        } else if (target instanceof Dialog) {
            return ButterKnife.bind((Dialog) target);
        } else if (target instanceof View) {
            return ButterKnife.bind((View) target);
        }
        return Unbinder.EMPTY;
    }

    /**
     * 绑定
     *
     * @param target
     * @param source
     * @return
     */
    public static Unbinder bind(Object target, Object source) {
        if (source instanceof Activity) {
            return ButterKnife.bind(target, (Activity) source);
        } else if (source instanceof Dialog) {
            return ButterKnife.bind(target, (Dialog) source);
        } else if (source instanceof View) {
            return ButterKnife.bind(target, (View) source);
        }
        return Unbinder.EMPTY;
    }

    /**
     * 解绑
     *
     * @param unbinder
     */
    public static void unBind(Unbinder unbinder) {
        if (unbinder != Unbinder.EMPTY) {
            unbinder.unbind();
        }
    }
}
