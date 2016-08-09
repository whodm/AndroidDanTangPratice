package com.example.whodm.retrofitdemo.model.index;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by whodm on 2016/8/7.
 */
public class Item {
    public String content_url;
    public String cover_image_url;
    public Integer created_at;
    public Object editor_id;
    public Integer id;
    public List<Object> labels = new ArrayList<Object>();
    public Boolean liked;
    public Integer likes_count;
    public Integer published_at;
    public String share_msg;
    public String shortTitle;
    public Integer status;
    public String template;
    public String title;
    public String type;
    public Integer updated_at;
    public String url;

    public String getContent_url() {
        return content_url;
    }

    public void setContent_url(String content_url) {
        this.content_url = content_url;
    }
}
