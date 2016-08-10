package com.example.whodm.retrofitdemo.ui.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.whodm.retrofitdemo.callback.BannerCallback;
import com.example.whodm.retrofitdemo.R;
import com.example.whodm.retrofitdemo.callback.IndexCallback;
import com.example.whodm.retrofitdemo.ui.DetailActivity;
import com.example.whodm.retrofitdemo.ui.WebViewActivity;
import com.example.whodm.retrofitdemo.ui.model.ItemCover;
import com.example.whodm.retrofitdemo.model.banners.Banners;
import com.example.whodm.retrofitdemo.model.index.Item;
import com.example.whodm.retrofitdemo.service.HttpService;
import com.example.whodm.retrofitdemo.ui.Adapter.ItemRecyclerViewAdapter;
import com.example.whodm.retrofitdemo.ui.LoopView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by X on 2016/8/8.
 */
public class CollectionFragment extends Fragment implements BannerCallback, IndexCallback {
    private static String TAG = "CollectionFragment";
    private LoopView loopView;
    private List<String> imagesUrl = new ArrayList<>();
    private static int COLLECTION = 4;
    private final static HttpService httpservice = new HttpService();
    private RecyclerView recyclerView;
    private ItemRecyclerViewAdapter itemRecyclerViewAdapter;
    private List<ItemCover> itemCoverList = new ArrayList<>();
    private final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
    private static int offset = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_collection,container,false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        itemRecyclerViewAdapter = new ItemRecyclerViewAdapter(getActivity());
        init();
        itemRecyclerViewAdapter.setEndlessLoadListener(new ItemRecyclerViewAdapter.EndlessLoadListener() {
            @Override
            public void loadMore() {

            }
        });
        itemRecyclerViewAdapter.setOnItemClickListener(new ItemRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void ItemClickListener(View view, int postion) {
                String url = itemCoverList.get(postion).getContent_url();
                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                intent.putExtra("URL", url);
                startActivity(intent);
            }
        });
        return view;
    }

    public void init() {
        httpservice.bannerService(this);
        httpservice.indexService(COLLECTION, 0, this);
    }


    @Override
    public void onBannerSuccess(List<Banners> bannerList) {
        for (int i = 0; i < bannerList.size(); i++) {
            imagesUrl.add(bannerList.get(i).getImage_url());
        }
        loopView = new LoopView(getContext(), imagesUrl);
        itemRecyclerViewAdapter.addHeader(loopView);
    }

    @Override
    public void onBannerFail() {
        Toast.makeText(getContext(), "滚滚滚连接失败", Toast.LENGTH_LONG).show();
    }

    public void onUpdate() {
        httpservice.indexService(COLLECTION, 20, this);
    }

    @Override
    public void onIndexSuccess(List<Item> list) {
        for (int i = 0; i < list.size(); i++) {
            ItemCover itemCover = new ItemCover();
            itemCover.setUrl(list.get(i).cover_image_url);
            Log.d(TAG, itemCover.getUrl());
            itemCover.setTitle(list.get(i).title);
            itemCover.setLike(list.get(i).likes_count.toString());
            itemCover.setContent_url(list.get(i).content_url);
            itemCoverList.add(itemCover);
        }
        itemRecyclerViewAdapter.addItem(itemCoverList);
        //itemRecyclerViewAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(itemRecyclerViewAdapter);
    }

    @Override
    public void onIndexFail() {
        Toast.makeText(getContext(), "Index连接失败", Toast.LENGTH_LONG).show();
    }
}
