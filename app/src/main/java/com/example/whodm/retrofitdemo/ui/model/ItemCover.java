package com.example.whodm.retrofitdemo.ui.model;

import android.widget.ImageView;

/**
 * Created by X on 2016/8/9.
 */
public class ItemCover {
    private String url;
    private String title;
    private String like;
    private String content_url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public String getContent_url() {
        return content_url;
    }

    public void setContent_url(String content_url) {
        this.content_url = content_url;
    }
}
