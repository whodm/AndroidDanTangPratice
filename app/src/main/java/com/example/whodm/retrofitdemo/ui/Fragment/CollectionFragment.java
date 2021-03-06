package com.example.whodm.retrofitdemo.ui.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.whodm.retrofitdemo.callback.BannerCallback;
import com.example.whodm.retrofitdemo.R;
import com.example.whodm.retrofitdemo.callback.IndexCallback;
import com.example.whodm.retrofitdemo.ui.WebViewActivity;
import com.example.whodm.retrofitdemo.ui.model.Banner;
import com.example.whodm.retrofitdemo.ui.model.ItemCover;
import com.example.whodm.retrofitdemo.model.banners.Banners;
import com.example.whodm.retrofitdemo.model.index.Item;
import com.example.whodm.retrofitdemo.service.HttpService;
import com.example.whodm.retrofitdemo.ui.Adapter.ItemRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by X on 2016/8/8.
 */
public class CollectionFragment extends Fragment implements BannerCallback, IndexCallback {
    private static String TAG = "CollectionFragment";
    private static int COLLECTION = 4;
    private final static HttpService httpservice = new HttpService();
    private RecyclerView recyclerView;
    private FrameLayout frameLayout;
    private View firstLoadFail;
    private ItemRecyclerViewAdapter itemRecyclerViewAdapter;
    private final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
    private int offset = 0;
    private SwipeRefreshLayout swipeRefreshLayout;
    private boolean firstInit = true;
    public static final int VIEW_NOMOREVIEW = 999;
    public static final int VIEW_LOAD = 888;
    public static final int VIEW_FIRST = 777;
    public static final int VIEW_FAIL = 666;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_collection,container,false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh_collection);
        frameLayout = (FrameLayout)view.findViewById(R.id.collection_container);
        firstLoadFail = inflater.inflate(R.layout.frame_firstloadfail,container,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        itemRecyclerViewAdapter = new ItemRecyclerViewAdapter(getActivity());
        recyclerView.setAdapter(itemRecyclerViewAdapter);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                offset = 0;
                itemRecyclerViewAdapter.clearItems();
                frameLayout.removeView(firstLoadFail);
                firstInit = true;
                init();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        init();
        return view;
    }

    public void init() {
        httpservice.bannerService(this);
        httpservice.indexService(COLLECTION, offset, this);
    }

    @Override
    public void onBannerSuccess(List<Banners> bannerList) {
        List<Banner> imagesUrl = new ArrayList<>();
        for (int i = 0; i < bannerList.size(); i++) {
            Banner banner = new Banner();
            banner.setId(bannerList.get(i).getTarget_id());
            banner.setUrl(bannerList.get(i).getImage_url());
            imagesUrl.add(banner);
        }
        itemRecyclerViewAdapter.addLoopViewImages(imagesUrl);
    }

    @Override
    public void onBannerFail() {

    }

    public void onUpdate() {
        offset = offset + 20;
        httpservice.bannerService(this);
        httpservice.indexService(COLLECTION, offset, this);
    }

    @Override
    public void onIndexSuccess(final List<Item> list) {
        itemRecyclerViewAdapter.setEndlessLoadListener(new ItemRecyclerViewAdapter.EndlessLoadListener() {
            @Override
            public void loadMore() {
                onUpdate();

            }
        });
        itemRecyclerViewAdapter.setOnItemClickListener(new ItemRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void ItemClickListener(View view, String url) {
                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                intent.putExtra("URL", url);
                startActivity(intent);
            }
        });
        List<ItemCover> itemCoverList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            ItemCover itemCover = new ItemCover();
            itemCover.setUrl(list.get(i).cover_image_url);
            itemCover.setTitle(list.get(i).title);
            itemCover.setLike(list.get(i).likes_count.toString());
            itemCover.setContent_url(list.get(i).content_url);
            itemCoverList.add(itemCover);
        }
        itemRecyclerViewAdapter.addItem(itemCoverList);
        itemRecyclerViewAdapter.setFooter(VIEW_LOAD);
        firstInit = false;
    }

    @Override
    public void onIndexFail() {
        if (firstInit) {
            frameLayout.addView(firstLoadFail);
            //itemRecyclerViewAdapter.setHeader(VIEW_FIRST);
        } else {
            itemRecyclerViewAdapter.setFooter(VIEW_FAIL);
        }
    }

    @Override
    public void onBannerNothing() {

    }

    @Override
    public void onIndexNothing() {
        itemRecyclerViewAdapter.setFooter(VIEW_NOMOREVIEW);
        itemRecyclerViewAdapter.setEndlessLoadListener(null);
    }
}
