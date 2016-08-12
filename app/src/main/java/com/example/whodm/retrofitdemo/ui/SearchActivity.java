package com.example.whodm.retrofitdemo.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.whodm.retrofitdemo.R;
import com.example.whodm.retrofitdemo.callback.SearchDataCallback;
import com.example.whodm.retrofitdemo.callback.SingleCallback;
import com.example.whodm.retrofitdemo.model.search.SearchData;
import com.example.whodm.retrofitdemo.model.single.SingleData;
import com.example.whodm.retrofitdemo.service.HttpService;
import com.example.whodm.retrofitdemo.ui.Adapter.SingleRecyclerViewAdapter;
import com.example.whodm.retrofitdemo.ui.model.SingleCover;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements SearchDataCallback {
    private final static HttpService httpService = new HttpService();
    private TextView tv_back;
    private TextView tv_search;
    private EditText et_search;
    private RecyclerView recyclerView;
    private SingleRecyclerViewAdapter singleRecyclerViewAdapter;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private int offset = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        findById();
        init();
    }
    public void init(){
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        singleRecyclerViewAdapter = new SingleRecyclerViewAdapter(this);
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_search.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "请输入搜索内容", Toast.LENGTH_LONG).show();
                } else {

                }
            }
        });
    }
    public void findById(){
        tv_back = (TextView)findViewById(R.id.tv_back);
        et_search = (EditText) findViewById(R.id.et_search);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_search);

    }

    public void initService(String content) {
        //httpService.singleService(offset, this);
    }

    @Override
    public void onSeachSuccess(SearchData searchData) {
        List<SingleCover> itemCoverList = new ArrayList<>();
        SearchData search = searchData;
        for (int i = 0; i < search.items.size(); i++) {
            SingleCover singleCover = new SingleCover();
            singleCover.setUrl(search.items.get(i).cover_imageUrl);
            singleCover.setTitle(search.items.get(i).getName());
            singleCover.setLike(search.items.get(i).getLikes_count().toString());
            singleCover.setPrice(search.items.get(i).getPrice());
            //singleCover.setContent_url(search.items.get(i).get);
            itemCoverList.add(singleCover);
        }
        singleRecyclerViewAdapter.addItem(itemCoverList);
    }

    @Override
    public void onSeachFail() {
        Toast.makeText(this, "Index连接失败", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSeachNoting() {
        Toast.makeText(this, "没有更多了", Toast.LENGTH_LONG).show();
    }

    //    @Override
//    public void onSingleSuccess(SingleData singleData) {
//        List<SingleCover> itemCoverList = new ArrayList<>();
//        SingleData single = singleData;
//        for (int i = 0; i < single.items.size(); i++) {
//            SingleCover singleCover = new SingleCover();
//            singleCover.setUrl(single.items.get(i).getData().cover_image_url);
//            singleCover.setTitle(single.items.get(i).getData().getName());
//            singleCover.setLike(single.items.get(i).getData().getFavorites_count().toString());
//            singleCover.setPrice(single.items.get(i).getData().getPrice());
//            singleCover.setContent_url(single.items.get(i).getData().getUrl());
//            itemCoverList.add(singleCover);
//        }
//        singleRecyclerViewAdapter.addItem(itemCoverList);
//    }
//
//    @Override
//    public void onSingleNothing() {
//        Toast.makeText(this, "没有更多了", Toast.LENGTH_LONG).show();
//    }
//
//    @Override
//    public void onSingleFail() {
//        Toast.makeText(this, "Index连接失败", Toast.LENGTH_LONG).show();
//    }
}
