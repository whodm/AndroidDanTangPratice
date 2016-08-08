package com.example.whodm.retrofitdemo.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.whodm.retrofitdemo.R;

public class SearchActivity extends AppCompatActivity {
    private TextView tv_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        findById();
        init();
    }
    public void init(){
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public void findById(){
        tv_back = (TextView)findViewById(R.id.tv_back);
    }
}
