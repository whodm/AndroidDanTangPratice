package com.example.whodm.retrofitdemo.ui.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.whodm.retrofitdemo.callback.BannerCallback;
import com.example.whodm.retrofitdemo.R;
import com.example.whodm.retrofitdemo.callback.IndexCallback;
import com.example.whodm.retrofitdemo.model.ItemCover;
import com.example.whodm.retrofitdemo.model.banners.Banners;
import com.example.whodm.retrofitdemo.model.index.Item;
import com.example.whodm.retrofitdemo.service.HttpService;
import com.example.whodm.retrofitdemo.ui.Adapter.ExRecycleViewAdapter;
import com.example.whodm.retrofitdemo.ui.LoopView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by X on 2016/8/8.
 */
public class CollectionFragment extends Fragment implements BannerCallback, IndexCallback {
    private static String TAG = "CollectionFragment";
    private LoopView loopView;
    //    private FrameLayout frameLayout;
    private List<String> imagesUrl = new ArrayList<>();
    private static int COLLECTION = 4;
    private final static HttpService httpservice = new HttpService();
    private RecyclerView recyclerView;
    private ExRecycleViewAdapter exRecycleViewAdapter;
    private ItemCover itemCover = new ItemCover();
    private List<ItemCover> itemCoverList = new ArrayList<>();
    private List<View> header = new ArrayList<>();
    final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_collection,container,false);
//        frameLayout = (FrameLayout) view.findViewById(R.id.frameContainer);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        init();
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
        header.add(loopView);
        //frameLayout.addView(loopView);
    }

    @Override
    public void onBannerFail() {
        Toast.makeText(getContext(), "滚滚滚连接失败", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onIndexSuccess(List<Item> list) {
        for (int i = 0; i < list.size(); i++) {
            itemCover.setUrl(list.get(i).cover_image_url);
            Log.d(TAG, itemCover.getUrl());
            itemCover.setTitle(list.get(i).title);
            itemCover.setLike(list.get(i).likes_count.toString());
            itemCoverList.add(itemCover);
        }
        exRecycleViewAdapter = new ExRecycleViewAdapter(itemCoverList, header, getActivity());
        recyclerView.setAdapter(exRecycleViewAdapter);
    }

    @Override
    public void onIndexFail() {
        Toast.makeText(getContext(), "Index连接失败", Toast.LENGTH_LONG).show();
    }
}
