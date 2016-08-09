package com.example.whodm.retrofitdemo.ui.Fragment;

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
import android.widget.TextView;
import android.widget.Toast;

import com.example.whodm.retrofitdemo.R;
import com.example.whodm.retrofitdemo.callback.IndexCallback;
import com.example.whodm.retrofitdemo.model.ItemCover;
import com.example.whodm.retrofitdemo.model.index.Item;
import com.example.whodm.retrofitdemo.service.HttpService;
import com.example.whodm.retrofitdemo.ui.Adapter.ExRecycleViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by X on 2016/8/8.
 */
public class OtherFragment extends Fragment implements IndexCallback {
    private static String TAG = "otherFragment";
    private static final String ARG_POSITION = "position";
    private RecyclerView recyclerView;
    private ExRecycleViewAdapter exRecycleViewAdapter;
    private final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
    private int position;
    private HttpService httpService = new HttpService();
    private List<ItemCover> itemCoverList = new ArrayList<>();
    private static int Foods = 14;
    private static int Housing = 16;
    private static int Digtal = 17;
    private static int Beauty = 13;
    private static int Groceries = 22;


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
        //exRecycleViewAdapter = new ExRecycleViewAdapter(getActivity());
        init();
        return view;
    }

    public void init() {
        switch (position) {
            case 1:
                httpService.indexService(Foods, 0, this);
                break;
            case 2:
                httpService.indexService(Housing, 0, this);
                break;
            case 3:
                httpService.indexService(Digtal, 0, this);
                break;
            case 4:
                httpService.indexService(Beauty, 0, this);
                break;
            case 5:
                httpService.indexService(Groceries, 0, this);
                break;
            default:
                httpService.indexService(Foods, 0, this);
                break;
        }
    }

    @Override
    public void onIndexSuccess(List<Item> list) {
        for (int i = 0; i < list.size(); i++) {
            ItemCover itemCover = new ItemCover();
            itemCover.setUrl(list.get(i).cover_image_url);
            Log.d(TAG, itemCover.getUrl());
            itemCover.setTitle(list.get(i).title);
            itemCover.setLike(list.get(i).likes_count.toString());
            itemCoverList.add(itemCover);
        }
        //exRecycleViewAdapter.addItem(itemCoverList);
        exRecycleViewAdapter = new ExRecycleViewAdapter(itemCoverList, getContext());
        exRecycleViewAdapter.setEndlessLoadListener(new ExRecycleViewAdapter.EndlessLoadListener() {
            @Override
            public void loadMore() {
                //httpservice.indexService(COLLECTION,offset,);
            }
        });
        recyclerView.setAdapter(exRecycleViewAdapter);
    }

    @Override
    public void onIndexFail() {
        Toast.makeText(getContext(), "Index连接失败", Toast.LENGTH_LONG).show();
    }
}
