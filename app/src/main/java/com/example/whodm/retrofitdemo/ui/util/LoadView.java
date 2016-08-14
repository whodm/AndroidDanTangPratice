package com.example.whodm.retrofitdemo.ui.util;

import android.content.Context;
import android.support.annotation.VisibleForTesting;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by whodm on 2016/8/14.
 */
public class LoadView extends LinearLayout {
    @VisibleForTesting
    static final int CIRCLE_DIAMETER = 40;

    private int[] colors = {
            0xFFFF0000, 0xFFFF7F00, 0xFFFFFF00, 0xFF00FF00
            , 0xFF00FFFF, 0xFF0000FF, 0xFF8B00FF};
    private final int CIRCLE_BG_LIGHT = 0xFFFAFAFA;
    private CircleImageView mCircleImageView;
    private MaterialProgressDrawable mProgress;

    public LoadView(Context context) {
        super(context);
        init(context);
    }

    public LoadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void init(Context context) {
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);

        params.gravity = Gravity.CENTER;

        mCircleImageView = new CircleImageView(context, CIRCLE_BG_LIGHT, CIRCLE_DIAMETER / 2);

        mCircleImageView.setLayoutParams(params);

        this.setOrientation(VERTICAL);

        this.addView(mCircleImageView);

        this.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        mProgress = new MaterialProgressDrawable(context, mCircleImageView);

        mProgress.setBackgroundColor(CIRCLE_BG_LIGHT);
        //圈圈颜色,可以是多种颜色
        mProgress.setColorSchemeColors(colors);
        //设置圈圈的各种大小
        mCircleImageView.setImageDrawable(mProgress);
        //圈圈的旋转角度
        mProgress.setProgressRotation(0.5f);
        //圈圈周长，0f-1F
        mProgress.setStartEndTrim(0f, 0.8f);
        //箭头大小，0f-1F
        mProgress.setArrowScale(0.5f);
        //透明度，0-255
        mProgress.setAlpha((255));
    }

    public void startAnime() {
        mProgress.start();
    }
}
