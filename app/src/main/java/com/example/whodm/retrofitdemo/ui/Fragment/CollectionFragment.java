package com.example.whodm.retrofitdemo.ui.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
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
import com.example.whodm.retrofitdemo.ui.model.Banner;
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
    private static int COLLECTION = 4;
    private final static HttpService httpservice = new HttpService();
    private RecyclerView recyclerView;
    private ItemRecyclerViewAdapter itemRecyclerViewAdapter;
    private final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
    private int offset = 0;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_collection,container,false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh_collection);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        itemRecyclerViewAdapter = new ItemRecyclerViewAdapter(getActivity());
        recyclerView.setAdapter(itemRecyclerViewAdapter);
//        loopView.setOnItemClickListener(new LoopView.OnItemClickListener() {
//            @Override
//            public void ItemClickListener(int id) {
//                Intent intent = new Intent(getActivity(),DetailActivity.class);
//                intent.putExtra("ID",id);
//                Log.d(TAG,"id = "+id+"");
//                startActivity(intent);
//            }
//        });
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
        LoopView loopView = new LoopView(getContext());
        loopView.setImageUrl(imagesUrl);
        itemRecyclerViewAdapter.addHeader(loopView);
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                itemRecyclerViewAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onBannerFail() {
        Toast.makeText(getContext(), "滚滚滚连接失败", Toast.LENGTH_LONG).show();
    }

    public void onUpdate() {
        offset = offset + 20;
        httpservice.indexService(COLLECTION, offset, this);
    }

    @Override
    public void onIndexSuccess(List<Item> list) {
        List<ItemCover> itemCoverList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            ItemCover itemCover = new ItemCover();
            itemCover.setUrl(list.get(i).cover_image_url);
            itemCover.setTitle(list.get(i).title);
            itemCover.setLike(list.get(i).likes_count.toString());
            Log.d(TAG, "url = " + list.get(i).content_url);
            itemCover.setContent_url(list.get(i).content_url);
            itemCoverList.add(itemCover);
        }
        itemRecyclerViewAdapter.addItem(itemCoverList);
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                itemRecyclerViewAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onIndexFail() {
        Toast.makeText(getContext(), "Index连接失败", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBannerNothing() {
        Toast.makeText(getContext(), "没有更多了", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onIndexNothing() {
        Toast.makeText(getContext(), "没有更多了", Toast.LENGTH_LONG).show();
    }
}
