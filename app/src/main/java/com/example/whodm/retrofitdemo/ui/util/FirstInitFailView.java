package com.example.whodm.retrofitdemo.ui.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.text.BoringLayout;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by X on 2016/8/15.
 */
public class FirstInitFailView extends RelativeLayout {
    private TextView tv_None;
    private DisplayMetrics displaymetrics = new DisplayMetrics();
    int height, width;

    public FirstInitFailView(Context context) {
        super(context);
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        height = displaymetrics.heightPixels;
        init(context);
    }

    public FirstInitFailView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void init(Context context) {
        tv_None = new TextView(context);
        tv_None.setText("你确定你联网了？");
        tv_None.setTextSize(18);
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        params.addRule(RelativeLayout.CENTER_VERTICAL);

        tv_None.setLayoutParams(params);

        this.addView(tv_None);

        this.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

    }
}
