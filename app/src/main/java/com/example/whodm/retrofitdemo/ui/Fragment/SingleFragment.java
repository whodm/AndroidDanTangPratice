package com.example.whodm.retrofitdemo.ui.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.whodm.retrofitdemo.R;
import com.example.whodm.retrofitdemo.callback.SingleCallback;
import com.example.whodm.retrofitdemo.ui.WebViewActivity;
import com.example.whodm.retrofitdemo.ui.model.SingleCover;
import com.example.whodm.retrofitdemo.model.single.SingleData;
import com.example.whodm.retrofitdemo.service.HttpService;
import com.example.whodm.retrofitdemo.ui.Adapter.SingleRecyclerViewAdapter;
import com.example.whodm.retrofitdemo.ui.util.ConectionFailView;
import com.example.whodm.retrofitdemo.ui.util.LoadView;
import com.example.whodm.retrofitdemo.ui.util.NoMoreView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by X on 2016/8/8.
 */
public class SingleFragment extends Fragment implements SingleCallback {
    private final static HttpService httpService = new HttpService();
    private RecyclerView recyclerView;
    private SingleRecyclerViewAdapter singleRecyclerViewAdapter;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private int offset = 0;
    private SwipeRefreshLayout swipeRefreshLayout;
    private LoadView loadView;
    private NoMoreView noMoreView;
    private ConectionFailView conectionFailView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_single, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_single);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh_single);
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
//        staggeredGridLayoutManager.setGapStrategy(0);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        loadView = new LoadView(getActivity());
        noMoreView = new NoMoreView(getActivity());
        conectionFailView = new ConectionFailView(getActivity());
        singleRecyclerViewAdapter = new SingleRecyclerViewAdapter(getActivity());
        recyclerView.setAdapter(singleRecyclerViewAdapter);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                offset = 0;
                singleRecyclerViewAdapter.clearItems();
                init();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        init();
        return view;
    }

    public void onUpdate() {
        offset = offset + 20;
        httpService.singleService(offset, this);
    }

    public void init() {
        httpService.singleService(offset, this);
    }

    @Override
    public void onSingleSuccess(SingleData singleData) {
        singleRecyclerViewAdapter.setEndlessLoadListener(new SingleRecyclerViewAdapter.EndlessLoadListener() {
            @Override
            public void loadMore() {
                onUpdate();
            }
        });
        singleRecyclerViewAdapter.setOnItemClickListener(new SingleRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void ItemClickListener(View view, String url) {
                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                intent.putExtra("URL", url);
                startActivity(intent);
            }
        });
        List<SingleCover> itemCoverList = new ArrayList<>();
        SingleData single = singleData;
        for (int i = 0; i < single.items.size(); i++) {
            SingleCover singleCover = new SingleCover();
            singleCover.setUrl(single.items.get(i).getData().cover_image_url);
            singleCover.setTitle(single.items.get(i).getData().getName());
            singleCover.setLike(single.items.get(i).getData().getFavorites_count().toString());
            singleCover.setPrice(single.items.get(i).getData().getPrice());
            singleCover.setContent_url(single.items.get(i).getData().getUrl());
            itemCoverList.add(singleCover);
        }
        singleRecyclerViewAdapter.addItem(itemCoverList);
        singleRecyclerViewAdapter.setFooter(loadView);

    }

    @Override
    public void onSingleFail() {
        //Toast.makeText(getContext(), "Index连接失败", Toast.LENGTH_LONG).show();
        singleRecyclerViewAdapter.setFooter(conectionFailView);
        singleRecyclerViewAdapter.setEndlessLoadListener(null);
    }

    @Override
    public void onSingleNothing() {
        //Toast.makeText(getContext(), "没有更多了", Toast.LENGTH_LONG).show();
        singleRecyclerViewAdapter.setFooter(noMoreView);
        singleRecyclerViewAdapter.setEndlessLoadListener(null);
    }
}
