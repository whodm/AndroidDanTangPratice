package com.example.whodm.retrofitdemo.model.search;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by whodm on 2016/8/7.
 */
public class SearchData {
    public List<Item> items = new ArrayList<Item>();
    public Paging paging;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Paging getPaging() {
        return paging;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }
}
