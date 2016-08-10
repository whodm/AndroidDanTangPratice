package com.example.whodm.retrofitdemo.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.example.whodm.retrofitdemo.R;
import com.example.whodm.retrofitdemo.ui.Adapter.ItemRecyclerViewAdapter;

public class DetailActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ItemRecyclerViewAdapter itemRecyclerViewAdapter;
    private String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent i = getIntent();
        id = i.getStringExtra("ID");

        findById();
        init();
    }

    public void init() {

    }

    public void findById() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_detail);
    }
}
