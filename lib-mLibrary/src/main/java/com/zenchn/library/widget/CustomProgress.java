package com.zenchn.library.widget;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.DialogInterface.OnShowListener;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zenchn.library.R;

public class CustomProgress extends ProgressDialog implements OnShowListener, OnDismissListener {

    Context context;
    ImageView imageview;
    TextView textView;
    String msg;

    public CustomProgress(Context context) {
        this(context, R.style.AppTheme_Dialog_NoTitleBar);
    }

    public CustomProgress(Context context, int theme) {
        super(context, theme);
        this.context = context;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = View.inflate(context, R.layout.m_custom_progress, null);
        setContentView(view);
        imageview = (ImageView) view.findViewById(R.id.iv_loading);
        textView = (TextView) view.findViewById(R.id.tv_msg);
        setOnShowListener(this);
        updateMsg(0, msg);
    }

    public void onShow(DialogInterface dialog) {
        AnimationDrawable animationDrawable = (AnimationDrawable) imageview.getBackground();
        animationDrawable.start();
    }

    public void onDismiss(DialogInterface dialog) {
        AnimationDrawable animationDrawable = (AnimationDrawable) imageview.getBackground();
        animationDrawable.stop();
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void updateMsg(int diff, String msg) {
        textView.setText(msg);
        textView.setVisibility(TextUtils.isEmpty(msg) ? View.GONE : View.VISIBLE);
        incrementProgressBy(diff);
    }
}