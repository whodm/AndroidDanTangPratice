package com.example.whodm.retrofitdemo.model.banners;

/**
 * Created by whodm on 2016/8/7.
 */
public class BannerData<T> {
    public T banners;

    public T getBanners() {
        return banners;
    }

    public void setBanners(T banners) {
        this.banners = banners;
    }
}
