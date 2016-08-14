package com.example.whodm.retrofitdemo.service;

import android.util.Log;

import com.example.whodm.retrofitdemo.callback.AllTopicCallback;
import com.example.whodm.retrofitdemo.callback.BannerCallback;
import com.example.whodm.retrofitdemo.callback.ChannelCallback;
import com.example.whodm.retrofitdemo.callback.ClassesCallback;
import com.example.whodm.retrofitdemo.callback.IndexCallback;
import com.example.whodm.retrofitdemo.callback.SearchDataCallback;
import com.example.whodm.retrofitdemo.callback.SingleCallback;
import com.example.whodm.retrofitdemo.callback.TopicDataCallback;
import com.example.whodm.retrofitdemo.model.all.AllData;
import com.example.whodm.retrofitdemo.model.banners.BannerData;
import com.example.whodm.retrofitdemo.model.banners.Banners;
import com.example.whodm.retrofitdemo.model.BaseModel;
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
import com.example.whodm.retrofitdemo.request.API;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by whodm on 2016/8/7.
 */
public class HttpService {
    private static Retrofit retrofit;
    private API api;
    public HttpService() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://api.dantangapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(API.class);
    }
    //专题合集 -> 查看全部
    public void allTopicService(int i, final AllTopicCallback callback) {
        Call<BaseModel<AllData>> call = api.defaultAll(i);

        call.enqueue(new Callback<BaseModel<AllData>>() {
            @Override
            public void onResponse(Response<BaseModel<AllData>> response, Retrofit retrofit) {
                if (response.body().data == null || response.body().data.collections.size() == 0) {
                    Log.d("onRespone allData","Null");
                    callback.onAllTopicNothing();
                } else {
                    Log.d("onRespone allData",response.body().data.collections.toString());
                    callback.onAllTopicSuccess(response.body().data);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                callback.onFail();
            }
        });
    }
    //分类界面 风格品类 点击按钮
    public void channelService(String id, int i, final ChannelCallback callback) {
        final Call<BaseModel<ChannelData>> call = api.defaultChannelData(id, i);
        call.enqueue(new Callback<BaseModel<ChannelData>>() {
            @Override
            public void onResponse(Response<BaseModel<ChannelData>> response, Retrofit retrofit) {
                if (response.body().data == null || response.body().data.items.size() == 0) {
                    callback.onChannelNothing();
                } else {
                    callback.onChannelSuccess(response.body().data);
                }
            }
            @Override
            public void onFailure(Throwable t) {
                callback.onChannelFail();
            }
        });
    }
    //底部 风格，品类
    public void bottomStyleService(final ClassesCallback callback) {
        Call<BaseModel<BottomStyleData>> call = api.defaultChannel_Group();

        call.enqueue(new Callback<BaseModel<BottomStyleData>>() {
            @Override
            public void onResponse(Response<BaseModel<BottomStyleData>> response, Retrofit retrofit) {
                if (response.body().data == null || response.body().data.channel_groups.size() == 0) {
                    callback.onClassesNothing();
                } else {
                    callback.onClassesSuccess(response.body().data);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                callback.onClassesFail();
            }
        });
    }
    //专题列表数据 -> 专题详情
    public void topDetailService(){
        Call<BaseModel<TopicDetailData>> call = api.defaultTopicDetail();

        call.enqueue(new Callback<BaseModel<TopicDetailData>>() {
            @Override
            public void onResponse(Response<BaseModel<TopicDetailData>> response, Retrofit retrofit) {
                if (response.body().data == null) {
                    Log.d("onRespone topDetail","Null");

                } else {
                    Log.d("onRespone topDetail",response.body().data.toString());
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
    //专题合集 -> 专题列表数据
    //通过获取id得到专题列表
    public void topicService(String id, int offset, final TopicDataCallback callback) {
        Call<BaseModel<TopicData>> call = api.defaultTopic(id, offset);

        call.enqueue(new Callback<BaseModel<TopicData>>() {
            @Override
            public void onResponse(Response<BaseModel<TopicData>> response, Retrofit retrofit) {
                if (response.body().data == null || response.body().data.posts.size() == 0) {
                    callback.onTopicDataNothing();

                } else {
                    Log.d("onRespone topic",response.body().data.posts.toString());
                    callback.onTopicDataSuccess(response.body().data);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                callback.onTopicDataFail();
            }
        });
    }
    //单品接口
    public void singleService(int offset, final SingleCallback callback) {
        Call<BaseModel<SingleData>> call = api.defaultSingleItem(offset);

        call.enqueue(new Callback<BaseModel<SingleData>>() {
            @Override
            public void onResponse(Response<BaseModel<SingleData>> response, Retrofit retrofit) {
                if (response.body().data == null || response.body().data.items.size() == 0) {
                    Log.d("onRespone single","Null");
                    callback.onSingleNothing();
                } else {
                    Log.d("onRespone single",response.body().data.items.toString());
                    callback.onSingleSuccess(response.body().data);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                callback.onSingleFail();
            }
        });
    }
    //根据搜索条件进行搜索
    public void searchService(String s, final SearchDataCallback callback) {
        Call<BaseModel<SearchData>> call = api.defaultKeyWord(s);

        call.enqueue(new Callback<BaseModel<SearchData>>() {
            @Override
            public void onResponse(Response<BaseModel<SearchData>> response, Retrofit retrofit) {
                if (response.body().data == null || response.body().data.items.size() == 0) {
                    Log.d("onRespone search","Null");
                    callback.onSeachNoting();
                } else {
                    Log.d("onRespone search",response.body().data.items.toString());
                    callback.onSeachSuccess(response.body().data);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                callback.onSeachFail();
            }
        });
    }
    //大家都在搜
    public void hotWordService(){
        Call<BaseModel<HotWordData>> call = api.defaultHotWord();

        call.enqueue(new Callback<BaseModel<HotWordData>>() {
            @Override
            public void onResponse(Response<BaseModel<HotWordData>> response, Retrofit retrofit) {
                if (response.body().data == null || response.body().data.hot_words.size() == 0) {
                    Log.d("onRespone hotword","Null"+response.body().message);

                } else {
                    Log.d("onRespone hotword",response.body().data.hot_words.toString());
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
    //首页顶部数据
    public void headerService(){
        Call<BaseModel<HeaderData>> call = api.defaultHeader();

        call.enqueue(new Callback<BaseModel<HeaderData>>() {
            @Override
            public void onResponse(Response<BaseModel<HeaderData>> response, Retrofit retrofit) {
                if (response.body().data == null || response.body().data.candidates.size() == 0) {
                    Log.d("onRespone header","Null");

                } else {
                    Log.d("onRespone header",response.body().data.candidates.toString());
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
    //首页数据 GET
    public void indexService(int id, int offset, final IndexCallback callback) {
        final Call<BaseModel<IndexData<ArrayList<Item>>>> call = api.defaultIndex(id, offset);

        call.enqueue(new Callback<BaseModel<IndexData<ArrayList<Item>>>>() {
            @Override
            public void onResponse(Response<BaseModel<IndexData<ArrayList<Item>>>> response, Retrofit retrofit) {
                if (response.body().data.items == null || response.body().data.items.size() == 0) {
                    Log.d("onRespone Nothing", response.body().data.items + "");
                    callback.onIndexNothing();
                } else {
                    Log.d("onRespone index",response.body().data.items.get(1).content_url);
                    callback.onIndexSuccess(response.body().data.items);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                callback.onIndexFail();
            }
        });
    }
    //头部旋转视图数据
    public void bannerService(final BannerCallback callback) {
        Call<BaseModel<BannerData<ArrayList<Banners>>>> call = api.defaultBanner();
        call.enqueue(new Callback<BaseModel<BannerData<ArrayList<Banners>>>>() {
            @Override
            public void onResponse(Response<BaseModel<BannerData<ArrayList<Banners>>>> response, Retrofit retrofit) {
                if (response.body().data.banners == null || response.body().data.banners.size() == 0) {
                    Log.d("onRespone banner", "nothing in here");
                    callback.onBannerNothing();
                } else {
                    Log.d("onRespone banner", response.body().data.banners.get(0).getImage_url());
                    callback.onBannerSuccess(response.body().data.banners);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                callback.onBannerFail();
            }
        });
    }

}
