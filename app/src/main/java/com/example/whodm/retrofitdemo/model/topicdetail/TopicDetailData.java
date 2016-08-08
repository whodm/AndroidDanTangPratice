package com.example.whodm.retrofitdemo.model.topicdetail;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by whodm on 2016/8/7.
 */
public class TopicDetailData {
    public Integer comments_count;
    public String content_html;
    public String content_url;
    public String coverimage_url;
    public Integer created_at;
    public Integer id;
    public List<Object> label_ids = new ArrayList<Object>();
    public Boolean liked;
    public Integer likes_count;
    public Integer published_at;
    public String share_msg;
    public Integer shares_count;
    public String short_title;
    public Integer status;
    public String title;
    public Integer updated_at;
    public String url;

}
