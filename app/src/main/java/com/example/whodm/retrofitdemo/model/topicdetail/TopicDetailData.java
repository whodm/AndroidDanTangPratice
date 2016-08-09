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

    public Integer getComments_count() {
        return comments_count;
    }

    public void setComments_count(Integer comments_count) {
        this.comments_count = comments_count;
    }

    public String getContent_html() {
        return content_html;
    }

    public void setContent_html(String content_html) {
        this.content_html = content_html;
    }

    public String getContent_url() {
        return content_url;
    }

    public void setContent_url(String content_url) {
        this.content_url = content_url;
    }

    public String getCoverimage_url() {
        return coverimage_url;
    }

    public void setCoverimage_url(String coverimage_url) {
        this.coverimage_url = coverimage_url;
    }

    public Integer getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Integer created_at) {
        this.created_at = created_at;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Object> getLabel_ids() {
        return label_ids;
    }

    public void setLabel_ids(List<Object> label_ids) {
        this.label_ids = label_ids;
    }

    public Boolean getLiked() {
        return liked;
    }

    public void setLiked(Boolean liked) {
        this.liked = liked;
    }

    public Integer getLikes_count() {
        return likes_count;
    }

    public void setLikes_count(Integer likes_count) {
        this.likes_count = likes_count;
    }

    public Integer getPublished_at() {
        return published_at;
    }

    public void setPublished_at(Integer published_at) {
        this.published_at = published_at;
    }

    public String getShare_msg() {
        return share_msg;
    }

    public void setShare_msg(String share_msg) {
        this.share_msg = share_msg;
    }

    public Integer getShares_count() {
        return shares_count;
    }

    public void setShares_count(Integer shares_count) {
        this.shares_count = shares_count;
    }

    public String getShort_title() {
        return short_title;
    }

    public void setShort_title(String short_title) {
        this.short_title = short_title;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Integer updated_at) {
        this.updated_at = updated_at;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
