package com.example.whodm.retrofitdemo.model.topic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by whodm on 2016/8/7.
 */
public class Post {
    public String content_url;
    public String cover_image_url;
    public Integer created_at;
    public Integer id;
    public List<Object> labelIds = new ArrayList<Object>();
    public Boolean liked;
    public Integer likes_count;
    public Integer published_at;
    public String share_msg;
    public String short_title;
    public Integer status;
    public String title;
    public Integer updated_at;
    public String url;

}
