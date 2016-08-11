package com.example.whodm.retrofitdemo.callback;

import com.example.whodm.retrofitdemo.model.single.SingleData;

/**
 * Created by X on 2016/8/11.
 */
public interface SingleCallback {
    void onSingleSuccess(SingleData singleData);
    void onSingleNothing();
    void onSingleFail();
}
