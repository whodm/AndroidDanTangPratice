package com.example.whodm.retrofitdemo.model.all;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by whodm on 2016/8/7.
 */
public class AllData {
    public List<Collection> collections = new ArrayList<Collection>();
    public Paging paging;

    public List<Collection> getCollections() {
        return collections;
    }

    public void setCollections(List<Collection> collections) {
        this.collections = collections;
    }

    public Paging getPaging() {
        return paging;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }
}
