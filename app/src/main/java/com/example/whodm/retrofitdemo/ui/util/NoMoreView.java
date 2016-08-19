package com.example.whodm.retrofitdemo.ui.util;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by whodm on 2016/8/14.
 */
public class NoMoreView extends RelativeLayout {
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
        tv_None.setText("已经没有更多了啊");
        tv_None.setTextSize(18);
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        params.addRule(RelativeLayout.CENTER_VERTICAL);

        tv_None.setLayoutParams(params);

        this.addView(tv_None);

        this.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }
}
