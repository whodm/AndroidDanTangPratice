package com.example.whodm.retrofitdemo.ui.Fragment;

import android.content.Intent;
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
import android.widget.Toast;

import com.example.whodm.retrofitdemo.R;
import com.example.whodm.retrofitdemo.callback.IndexCallback;
import com.example.whodm.retrofitdemo.ui.WebViewActivity;
import com.example.whodm.retrofitdemo.ui.model.ItemCover;
import com.example.whodm.retrofitdemo.model.index.Item;
import com.example.whodm.retrofitdemo.service.HttpService;
import com.example.whodm.retrofitdemo.ui.Adapter.ItemRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by X on 2016/8/8.
 */
public class OtherFragment extends Fragment implements IndexCallback {
    private static String TAG = "otherFragment";
    private static final String ARG_POSITION = "position";
    private RecyclerView recyclerView;
    private ItemRecyclerViewAdapter itemRecyclerViewAdapter;
    private final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
    private int position;
    private final static HttpService httpService = new HttpService();
    private static int Foods = 14;
    private static int Housing = 16;
    private static int Digtal = 17;
    private static int Beauty = 13;
    private static int Groceries = 22;
    private int offset = 0;


    public static OtherFragment newInstance(int position){
        OtherFragment otherFragment = new OtherFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_POSITION,position);
        otherFragment.setArguments(bundle);
        return otherFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getArguments().getInt(ARG_POSITION);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_channel,container,false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_channel);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        itemRecyclerViewAdapter = new ItemRecyclerViewAdapter(getActivity());
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
        recyclerView.setAdapter(itemRecyclerViewAdapter);
        init();
        return view;
    }

    public void init() {
        switch (position) {
            case 1:
                httpService.indexService(Foods, offset, this);
                break;
            case 2:
                httpService.indexService(Housing, offset, this);
                break;
            case 3:
                httpService.indexService(Digtal, offset, this);
                break;
            case 4:
                httpService.indexService(Beauty, offset, this);
                break;
            case 5:
                httpService.indexService(Groceries, offset, this);
                break;
            default:
                httpService.indexService(Foods, offset, this);
                break;
        }
    }

    public void onUpdate() {
        offset = offset + 20;
        switch (position) {
            case 1:
                httpService.indexService(Foods, offset, this);
                break;
            case 2:
                httpService.indexService(Housing, offset, this);
                break;
            case 3:
                httpService.indexService(Digtal, offset, this);
                break;
            case 4:
                httpService.indexService(Beauty, offset, this);
                break;
            case 5:
                httpService.indexService(Groceries, offset, this);
                break;
            default:
                httpService.indexService(Foods, offset, this);
                break;
        }
    }

    @Override
    public void onIndexSuccess(List<Item> list) {
        List<ItemCover> itemCoverList = new ArrayList<>();
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
    public void onIndexNothing() {
        Toast.makeText(getContext(), "没有更多了", Toast.LENGTH_LONG).show();
    }
}
