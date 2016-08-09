package com.example.whodm.retrofitdemo.callback;

import com.example.whodm.retrofitdemo.model.bottomstyle.BottomStyleData;

import java.util.List;

/**
 * Created by X on 2016/8/9.
 */
public interface ClassesCallback {
    void onClassesSuccess(BottomStyleData bottomStyleData);

    void onClassesFail();
}
