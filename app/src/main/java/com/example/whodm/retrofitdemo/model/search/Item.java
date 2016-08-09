package com.example.whodm.retrofitdemo.model.search;

/**
 * Created by whodm on 2016/8/7.
 */
public class Item {
    public String cover_imageUrl;
    public String description;
    public Integer favorites_count;
    public Integer id;
    public Boolean liked;
    public Integer likes_count;
    public String name;
    public String price;

    public String getCover_imageUrl() {
        return cover_imageUrl;
    }

    public void setCover_imageUrl(String cover_imageUrl) {
        this.cover_imageUrl = cover_imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
