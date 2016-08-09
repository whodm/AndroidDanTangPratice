package com.example.whodm.retrofitdemo.model.single;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by whodm on 2016/8/7.
 */
public class Data_ {
    public Object brand_id;
    public Integer brand_order;
    public Object category_id;
    public String cover_image_url;
    public Integer created_at;
    public String description;
    public Integer editor_id;
    public Integer favorites_count;
    public Integer id;
    public List<String> image_urls = new ArrayList<String>();
    public Boolean is_favorite;
    public String name;
    public List<Object> post_ids = new ArrayList<Object>();
    public String price;
    public String purchase_id;
    public Integer purchase_status;
    public Integer purchase_type;
    public String purchase_url;
    public Object subcategory_id;
    public Integer updated_at;
    public String url;

    public Object getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(Object brand_id) {
        this.brand_id = brand_id;
    }

    public Integer getBrand_order() {
        return brand_order;
    }

    public void setBrand_order(Integer brand_order) {
        this.brand_order = brand_order;
    }

    public Object getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Object category_id) {
        this.category_id = category_id;
    }

    public String getCover_image_url() {
        return cover_image_url;
    }

    public void setCover_image_url(String cover_image_url) {
        this.cover_image_url = cover_image_url;
    }

    public Integer getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Integer created_at) {
        this.created_at = created_at;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getEditor_id() {
        return editor_id;
    }

    public void setEditor_id(Integer editor_id) {
        this.editor_id = editor_id;
    }

    public Integer getFavorites_count() {
        return favorites_count;
    }

    public void setFavorites_count(Integer favorites_count) {
        this.favorites_count = favorites_count;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<String> getImage_urls() {
        return image_urls;
    }

    public void setImage_urls(List<String> image_urls) {
        this.image_urls = image_urls;
    }

    public Boolean getIs_favorite() {
        return is_favorite;
    }

    public void setIs_favorite(Boolean is_favorite) {
        this.is_favorite = is_favorite;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Object> getPost_ids() {
        return post_ids;
    }

    public void setPost_ids(List<Object> post_ids) {
        this.post_ids = post_ids;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPurchase_id() {
        return purchase_id;
    }

    public void setPurchase_id(String purchase_id) {
        this.purchase_id = purchase_id;
    }

    public Integer getPurchase_status() {
        return purchase_status;
    }

    public void setPurchase_status(Integer purchase_status) {
        this.purchase_status = purchase_status;
    }

    public Integer getPurchase_type() {
        return purchase_type;
    }

    public void setPurchase_type(Integer purchase_type) {
        this.purchase_type = purchase_type;
    }

    public String getPurchase_url() {
        return purchase_url;
    }

    public void setPurchase_url(String purchase_url) {
        this.purchase_url = purchase_url;
    }

    public Object getSubcategory_id() {
        return subcategory_id;
    }

    public void setSubcategory_id(Object subcategory_id) {
        this.subcategory_id = subcategory_id;
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
