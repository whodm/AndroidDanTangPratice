package com.example.whodm.retrofitdemo.model.bottomstyle;

/**
 * Created by whodm on 2016/8/7.
 */
public class Channel {
    public Integer group_id;
    public String icon_url;
    public Integer id;
    public Integer items_count;
    public String name;
    public Integer order;
    public Integer status;

    public Integer getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Integer group_id) {
        this.group_id = group_id;
    }

    public String getIcon_url() {
        return icon_url;
    }

    public void setIcon_url(String icon_url) {
        this.icon_url = icon_url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getItems_count() {
        return items_count;
    }

    public void setItems_count(Integer items_count) {
        this.items_count = items_count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
