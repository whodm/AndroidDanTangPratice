package com.example.whodm.retrofitdemo.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.whodm.retrofitdemo.R;
import com.example.whodm.retrofitdemo.callback.ChannelCallback;
import com.example.whodm.retrofitdemo.model.channel.ChannelData;
import com.example.whodm.retrofitdemo.model.channel.Item;
import com.example.whodm.retrofitdemo.service.HttpService;
import com.example.whodm.retrofitdemo.ui.Adapter.ItemRecyclerViewAdapter;
import com.example.whodm.retrofitdemo.ui.model.ItemCover;

import java.util.ArrayList;
import java.util.List;

public class DetailIconActivity extends AppCompatActivity implements ChannelCallback {
    private RecyclerView recyclerView;
    private ItemRecyclerViewAdapter itemRecyclerViewAdapter;
    private final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
    private int offset = 0;
    private TextView tv_detail;
    private TextView tv_back;
    private final static HttpService httpService = new HttpService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        findById();
        Intent i = getIntent();
        String id = i.getStringExtra("ICON");
        initIcon(id);
    }

    public void findById() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_detail);
        tv_detail = (TextView) findViewById(R.id.tv_topic_detail);
        tv_back = (TextView) findViewById(R.id.tv_back_detail);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        itemRecyclerViewAdapter = new ItemRecyclerViewAdapter(this);
        recyclerView.setAdapter(itemRecyclerViewAdapter);
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void initIcon(final String icon) {
        httpService.channelService(icon, offset, this);
        itemRecyclerViewAdapter.setEndlessLoadListener(new ItemRecyclerViewAdapter.EndlessLoadListener() {
            @Override
            public void loadMore() {
                onIconUpdate(icon);
            }
        });
        itemRecyclerViewAdapter.setOnItemClickListener(new ItemRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void ItemClickListener(View view, String url) {
                Intent intent = new Intent(DetailIconActivity.this, WebViewActivity.class);
                intent.putExtra("URL", url);
                startActivity(intent);
            }
        });
    }

    public void onIconUpdate(String i) {
        offset = offset + 20;
        httpService.channelService(i, offset, this);
    }

    @Override
    public void onChannelSuccess(ChannelData channelData) {
        List<Item> items = channelData.getItems();
        List<ItemCover> itemCoverList = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            ItemCover itemCover = new ItemCover();
            itemCover.setUrl(items.get(i).cover_image_url);
            itemCover.setTitle(items.get(i).title);
            itemCover.setLike(items.get(i).likes_count.toString());
            itemCover.setContent_url(items.get(i).getUrl());
            itemCoverList.add(itemCover);
        }
        itemRecyclerViewAdapter.addItem(itemCoverList);
    }

    @Override
    public void onChannelFail() {
        Toast.makeText(this, "连接失败", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onChannelNothing() {
        Toast.makeText(this, "没东西啦！", Toast.LENGTH_LONG).show();
    }
}
