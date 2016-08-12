package com.example.whodm.retrofitdemo.request;

import com.example.whodm.retrofitdemo.model.all.AllData;
import com.example.whodm.retrofitdemo.model.banners.Banners;
import com.example.whodm.retrofitdemo.model.BaseModel;
import com.example.whodm.retrofitdemo.model.banners.BannerData;
import com.example.whodm.retrofitdemo.model.bottomstyle.BottomStyleData;
import com.example.whodm.retrofitdemo.model.channel.ChannelData;
import com.example.whodm.retrofitdemo.model.headerindex.HeaderData;
import com.example.whodm.retrofitdemo.model.hotword.HotWordData;
import com.example.whodm.retrofitdemo.model.index.IndexData;
import com.example.whodm.retrofitdemo.model.index.Item;
import com.example.whodm.retrofitdemo.model.search.SearchData;
import com.example.whodm.retrofitdemo.model.single.SingleData;
import com.example.whodm.retrofitdemo.model.topic.TopicData;
import com.example.whodm.retrofitdemo.model.topicdetail.TopicDetailData;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by whodm on 2016/8/7.
 */
public interface API {
    //头部旋转视图数据
    @GET("v1/banners?channel=iOS")
    Call<BaseModel<BannerData<ArrayList<Banners>>>> defaultBanner();

    //首页数据 GET
    @GET("v1/channels/{id}/items?gender=1&generation=1&limit=20")
    Call<BaseModel<IndexData<ArrayList<Item>>>> defaultIndex(
            @Path("id") int id,
            @Query("offset") int pageOffset
    );

    //首页顶部数据
    @GET("v2/channels/preset?gender=1&generation=1")
    Call<BaseModel<HeaderData>> defaultHeader();

    //大家都在搜
    @GET("v1/search/hot_words")
    Call<BaseModel<HotWordData>> defaultHotWord();

    //根据搜索条件进行搜索
    @GET("v1/search/item?&limit=20&offset=0&sort=")
    Call<BaseModel<SearchData>> defaultKeyWord(
            @Query("keyword") String Word
    );

    //单品接口
    @GET("v2/items?gender=1&generation=1&limit=20")
    Call<BaseModel<SingleData>> defaultSingleItem(
            @Query("offset") int pageOffset
    );

    //专题合集 -> 查看全部
    @GET("v1/collections?limit=20")
    Call<BaseModel<AllData>> defaultAll(
            @Query("offset") int pageOffset
    );

    //专题合集 -> 专题列表数据
    @GET("v1/collections/{id}/posts?gender=1&generation=1&limit=20")
    Call<BaseModel<TopicData>> defaultTopic(
            @Path("id") String id,
            @Query("offset") int pageOffset
    );

    //专题列表数据 -> 专题详情
    @GET("v1/posts/258")
    Call<BaseModel<TopicDetailData>> defaultTopicDetail();

    //底部 风格，品类
    @GET("v1/channel_groups/all?")
    Call<BaseModel<BottomStyleData>> defaultChannel_Group();

    //分类界面 风格品类 点击按钮
    @GET("v1/channels/{id}/items?limit=20")
    Call<BaseModel<ChannelData>> defaultChannelData(
            @Path("id") String id,
            @Query("offset") int pageOffset
    );

}
