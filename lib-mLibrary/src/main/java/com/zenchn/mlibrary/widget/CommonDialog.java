package com.zenchn.mlibrary.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zenchn.mlibrary.R;

/**
 * 作    者：wangr on 2017/2/25 9:19
 * 描    述： 
 * 修订记录：
 */
public class CommonDialog extends Dialog {

    public CommonDialog(Context context) {
        super(context);
    }

    public CommonDialog(Context context, int theme) {
        super(context, theme);
    }

    public static class Builder {

        private Context context;
        private String title;
        private String message;
        private String positiveButtonText;
        private String negativeButtonText;
        private View contentView;
        private OnClickListener positiveButtonClickListener;
        private OnClickListener negativeButtonClickListener;
        private boolean cancelable;
        private boolean canceledOnTouchOutside;
        private boolean hideTitle;
        private boolean hidePositiveButton;
        private boolean hideNegativeButton;
        private float buttonTextSize;
        private int titleColor;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setMessage(int message) {
            this.message = (String) context.getText(message);
            return this;
        }

        public Builder setTitle(int title) {
            this.title = (String) context.getText(title);
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setContentView(View v) {
            this.contentView = v;
            return this;
        }

        public Builder setButtonTextSize(float buttonTextSize) {
            this.buttonTextSize = buttonTextSize;
            return this;
        }

        public Builder setCancelable(boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }

        public Builder setCanceledOnTouchOutside(boolean canceledOnTouchOutside) {
            this.canceledOnTouchOutside = canceledOnTouchOutside;
            return this;
        }

        public Builder isHidePositiveButton(boolean hidePositiveButton) {
            this.hidePositiveButton = hidePositiveButton;
            return this;
        }

        public Builder isHideNegativeButton(boolean hideNegativeButton) {
            this.hideNegativeButton = hideNegativeButton;
            return this;
        }

        public Builder isHideTitle(boolean hideTitle) {
            this.hideTitle = hideTitle;
            return this;
        }

        public Builder setPositiveButton(int positiveButtonText, OnClickListener listener) {
            this.positiveButtonText = (String) context.getText(positiveButtonText);
            this.positiveButtonClickListener = listener;
            return this;
        }

        public Builder setPositiveButton(String positiveButtonText, OnClickListener listener) {
            this.positiveButtonText = positiveButtonText;
            this.positiveButtonClickListener = listener;
            return this;
        }

        public Builder setNegativeButton(int negativeButtonText, OnClickListener listener) {
            this.negativeButtonText = (String) context.getText(negativeButtonText);
            this.negativeButtonClickListener = listener;
            return this;
        }

        public Builder setNegativeButton(String negativeButtonText, OnClickListener listener) {
            this.negativeButtonText = negativeButtonText;
            this.negativeButtonClickListener = listener;
            return this;
        }

        public Builder setPositiveButtonText(String positiveButtonText) {
            this.positiveButtonText = positiveButtonText;
            return this;
        }

        public Builder setNegativeButtonText(String negativeButtonText) {
            this.negativeButtonText = negativeButtonText;
            return this;
        }

        public CommonDialog build() {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            final CommonDialog dialog = new CommonDialog(context, R.style.AppTheme_Dialog);

            View layout = inflater.inflate(R.layout.m_dialog_common_layout, null);

            //是否隐藏标题
            if (hideTitle || title == null || TextUtils.isEmpty(title)) {
                layout.findViewById(R.id.title).setVisibility(View.GONE);
            } else {
                TextView tvTitle = (TextView) layout.findViewById(R.id.title);
                tvTitle.setText(title);
            }

            //是否隐藏积极按钮
            if (hidePositiveButton) {
                layout.findViewById(R.id.positiveButton).setVisibility(View.GONE);
            } else {
                if (positiveButtonClickListener != null) {
                    if (positiveButtonText != null) {
                        TextView tv = (TextView) layout.findViewById(R.id.tv_positiveButton);
                        tv.setText(positiveButtonText);
                        if (buttonTextSize != 0)
                            tv.setTextSize(buttonTextSize);
                        layout.findViewById(R.id.positiveButton).setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                                positiveButtonClickListener.onClick(dialog, DialogInterface.BUTTON_POSITIVE);
                            }
                        });
                    }
                }
            }

            //是否隐藏消极按钮
            if (hideNegativeButton) {
                layout.findViewById(R.id.negativeButton).setVisibility(View.GONE);
                layout.findViewById(R.id.separate).setVisibility(View.GONE);
            } else {
                if (negativeButtonText != null) {
                    TextView tv = (TextView) layout.findViewById(R.id.tv_negativeButton);
                    tv.setText(negativeButtonText);
                    if (buttonTextSize != 0)
                        tv.setTextSize(buttonTextSize);
                    layout.findViewById(R.id.negativeButton).setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            negativeButtonClickListener.onClick(dialog, DialogInterface.BUTTON_NEGATIVE);
                        }
                    });
                }
            }

            if (message != null) {
                ((TextView) layout.findViewById(R.id.message)).setText(message);
            }

            if (contentView != null) {
                ((LinearLayout) layout.findViewById(R.id.content)).removeAllViews();
                ((LinearLayout) layout.findViewById(R.id.content)).addView(contentView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            }

            dialog.addContentView(layout, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

            dialog.setCancelable(cancelable);

            dialog.setCanceledOnTouchOutside(canceledOnTouchOutside);

            dialog.setCancelable(cancelable);

            return dialog;
        }
    }

}

