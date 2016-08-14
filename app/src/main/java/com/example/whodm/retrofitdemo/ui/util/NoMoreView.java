package com.example.whodm.retrofitdemo.ui.util;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by whodm on 2016/8/14.
 */
public class NoMoreView extends LinearLayout {
    private TextView tv_None;

    public NoMoreView(Context context) {
        super(context);
        init(context);
    }

    public NoMoreView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void init(Context context) {
        tv_None = new TextView(context);
        tv_None.setText("已经没有东西啦!");
        tv_None.setTextSize(18);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;

        this.setOrientation(HORIZONTAL);

        this.addView(tv_None);

        this.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }
}
