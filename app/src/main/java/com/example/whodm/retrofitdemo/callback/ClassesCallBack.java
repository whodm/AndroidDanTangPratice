package com.example.whodm.retrofitdemo.callback;

import com.example.whodm.retrofitdemo.model.bottomstyle.BottomStyleData;

/**
 * Created by X on 2016/8/11.
 */
public interface ClassesCallback {
    void onClassesSuccess(BottomStyleData bottomStyleData);
    void onClassesNothing();
    void onClassesFail();
}
