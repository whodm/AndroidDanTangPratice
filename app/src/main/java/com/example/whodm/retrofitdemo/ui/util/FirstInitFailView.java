package com.example.whodm.retrofitdemo.ui.util;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Display;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by X on 2016/8/15.
 */
public class FirstInitFailView extends LinearLayout {
    private TextView tv_None;
    private int height;

    public FirstInitFailView(Context context) {
        super(context);
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
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT);
        params.gravity = Gravity.CENTER;

        tv_None.setLayoutParams(params);

        this.setOrientation(VERTICAL);

        this.addView(tv_None);

        this.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }
}
