package com.example.whodm.retrofitdemo.ui.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.whodm.retrofitdemo.R;
import com.example.whodm.retrofitdemo.callback.SingleCallback;
import com.example.whodm.retrofitdemo.ui.WebViewActivity;
import com.example.whodm.retrofitdemo.ui.model.SingleCover;
import com.example.whodm.retrofitdemo.model.single.SingleData;
import com.example.whodm.retrofitdemo.service.HttpService;
import com.example.whodm.retrofitdemo.ui.Adapter.SingleRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by X on 2016/8/8.
 */
public class SingleFragment extends Fragment implements SingleCallback {
    private final static HttpService httpService = new HttpService();
    private RecyclerView recyclerView;
    private SingleRecyclerViewAdapter singleRecyclerViewAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_single, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_single);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        singleRecyclerViewAdapter = new SingleRecyclerViewAdapter(getActivity());
        singleRecyclerViewAdapter.setEndlessLoadListener(new SingleRecyclerViewAdapter.EndlessLoadListener() {
            @Override
            public void loadMore() {

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
        recyclerView.setAdapter(singleRecyclerViewAdapter);
        init();
        return view;
    }

    public void init() {
        httpService.singleService(0, this);
    }

    @Override
    public void onSingleSuccess(SingleData singleData) {
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
    }

    @Override
    public void onSingleFail() {
        Toast.makeText(getContext(), "Index连接失败", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSingleNothing() {
        Toast.makeText(getContext(), "没有更多了", Toast.LENGTH_LONG).show();
    }
}
