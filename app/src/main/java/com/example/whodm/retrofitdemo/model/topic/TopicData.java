package com.example.whodm.retrofitdemo.model.topic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by whodm on 2016/8/7.
 */
public class TopicData {
    public String banner_image_url;
    public String cover_image_url;
    public Integer created_at;
    public Integer id;
    public Paging paging;
    public List<Post> posts = new ArrayList<Post>();
    public Integer posts_count;
    public Integer status;
    public String subtitle;
    public String title;
    public Integer updated_at;
}
