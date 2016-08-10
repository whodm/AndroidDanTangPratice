package com.example.whodm.retrofitdemo.callback;

import com.example.whodm.retrofitdemo.model.single.SingleData;

import java.util.List;

/**
 * Created by X on 2016/8/9.
 */
public interface SingleCallback {
    void onSingleSuccess(SingleData singleData);

    void onSingleNothing();
    void onSingleFail();
}
