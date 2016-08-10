package com.example.whodm.retrofitdemo.callback;

import com.example.whodm.retrofitdemo.model.banners.Banners;
import com.example.whodm.retrofitdemo.model.index.Item;

import java.util.List;

/**
 * Created by X on 2016/8/9.
 */
public interface IndexCallback {
    void onIndexSuccess(List<Item> list);

    void onIndexNothing();
    void onIndexFail();
}
