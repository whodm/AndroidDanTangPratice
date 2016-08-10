package com.example.whodm.retrofitdemo.callback;

import com.example.whodm.retrofitdemo.model.all.AllData;

import java.util.List;

/**
 * Created by X on 2016/8/10.
 */
public interface AllTopicCallback {
    void onAllTopicSuccess(AllData data);

    void onAllTopicNothing();
    void onFail();

}
