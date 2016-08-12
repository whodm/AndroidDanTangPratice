package com.example.whodm.retrofitdemo.callback;


import com.example.whodm.retrofitdemo.model.search.Item;
import com.example.whodm.retrofitdemo.model.search.SearchData;

/**
 * Created by X on 2016/8/12.
 */
public interface SearchDataCallback {
    void onSeachSuccess(SearchData searchData);

    void onSeachFail();

    void onSeachNoting();
}
