package com.example.whodm.retrofitdemo.callback;

import com.example.whodm.retrofitdemo.model.banners.Banners;

import java.util.List;

/**
 * Created by X on 2016/8/9.
 */
public interface BannerCallback {
    void onBannerSuccess(List<Banners> bannerList);

    void onBannerFail();
}
