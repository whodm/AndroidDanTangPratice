package com.example.whodm.retrofitdemo.ui.util;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by whodm on 2016/8/14.
 */
public class ConectionFailView extends LinearLayout {
    private TextView tv_None;

    public ConectionFailView(Context context) {
        super(context);
        init(context);
    }

    public ConectionFailView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void init(Context context) {
        tv_None = new TextView(context);
        tv_None.setText("连接失败");
        tv_None.setTextSize(18);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;

        tv_None.setLayoutParams(params);

        this.setOrientation(VERTICAL);

        this.addView(tv_None);

        this.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }
}
