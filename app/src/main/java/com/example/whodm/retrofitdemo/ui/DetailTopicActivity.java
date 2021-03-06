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
import com.example.whodm.retrofitdemo.callback.TopicDataCallback;
import com.example.whodm.retrofitdemo.model.channel.ChannelData;
import com.example.whodm.retrofitdemo.model.channel.Item;
import com.example.whodm.retrofitdemo.model.topic.Post;
import com.example.whodm.retrofitdemo.model.topic.TopicData;
import com.example.whodm.retrofitdemo.service.HttpService;
import com.example.whodm.retrofitdemo.ui.Adapter.ItemRecyclerViewAdapter;
import com.example.whodm.retrofitdemo.ui.model.ItemCover;

import java.util.ArrayList;
import java.util.List;

public class DetailTopicActivity extends AppCompatActivity implements TopicDataCallback {
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
        String id = i.getStringExtra("ID");
        initID(id);
    }

    public void initID(final String i) {
        httpService.topicService(i, offset, this);
        itemRecyclerViewAdapter.setEndlessLoadListener(new ItemRecyclerViewAdapter.EndlessLoadListener() {
            @Override
            public void loadMore() {
                onUpdate(i);
            }
        });
        itemRecyclerViewAdapter.setOnItemClickListener(new ItemRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void ItemClickListener(View view, String url) {
                Intent intent = new Intent(DetailTopicActivity.this, WebViewActivity.class);
                intent.putExtra("URL", url);
                startActivity(intent);
            }
        });
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

    public void onUpdate(String i) {
        offset = offset + 20;
        httpService.topicService(i, offset, this);
    }

    @Override
    public void onTopicDataSuccess(TopicData topicData) {
        List<Post> posts = topicData.getPosts();
        List<ItemCover> itemCoverList = new ArrayList<>();
        for (int i = 0; i < posts.size(); i++) {
            ItemCover itemCover = new ItemCover();
            itemCover.setUrl(posts.get(i).cover_image_url);
            itemCover.setTitle(posts.get(i).title);
            itemCover.setLike(posts.get(i).likes_count.toString());
            itemCover.setContent_url(posts.get(i).getUrl());
            itemCoverList.add(itemCover);
        }
        itemRecyclerViewAdapter.addItem(itemCoverList);
    }

    @Override
    public void onTopicDataFail() {
        Toast.makeText(this, "连接失败", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onTopicDataNothing() {
        Toast.makeText(this, "没东西啦！", Toast.LENGTH_LONG).show();
    }
}
