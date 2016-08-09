package com.example.whodm.retrofitdemo.model.index;

/**
 * Created by whodm on 2016/8/7.
 */
public class IndexData<T> {
    public T items;
    public Paging paging;

    public T getItems() {
        return items;
    }

    public void setItems(T items) {
        this.items = items;
    }

    public Paging getPaging() {
        return paging;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }
}
