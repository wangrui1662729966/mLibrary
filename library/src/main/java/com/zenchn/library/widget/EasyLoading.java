package com.zenchn.library.widget;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.DialogInterface.OnShowListener;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zenchn.library.R;

/**
 * 作    者：wangr on 2017/5/26 13:20
 * 描    述：
 * 修订记录：
 */
public class EasyLoading extends ProgressDialog implements OnShowListener, OnDismissListener {

    private Context context;
    private ImageView ivLoading;
    private TextView tvMsg;
    private String msg;

    public EasyLoading(Context context) {
        this(context, R.style.AppTheme_Dialog_NoTitleBar);
    }

    public EasyLoading(Context context, int theme) {
        super(context, theme);
        this.context = context;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(context).inflate(R.layout.m_loading_progress, null);
        setContentView(view);
        ivLoading = (ImageView) view.findViewById(R.id.iv_loading);
        tvMsg = (TextView) view.findViewById(R.id.tv_msg);
        setOnShowListener(this);
        updateMsg(0, msg);
    }

    public void onShow(DialogInterface dialog) {
        AnimationDrawable animationDrawable = (AnimationDrawable) ivLoading.getBackground();
        animationDrawable.start();
    }

    public void onDismiss(DialogInterface dialog) {
        AnimationDrawable animationDrawable = (AnimationDrawable) ivLoading.getBackground();
        animationDrawable.stop();
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void updateMsg(int diff, String msg) {
        tvMsg.setText(msg);
        tvMsg.setVisibility(TextUtils.isEmpty(msg) ? View.GONE : View.VISIBLE);
        incrementProgressBy(diff);
    }
}