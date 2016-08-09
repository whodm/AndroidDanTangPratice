package com.example.whodm.retrofitdemo.ui;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;
import com.example.whodm.retrofitdemo.R;
import com.example.whodm.retrofitdemo.service.HttpService;
import com.example.whodm.retrofitdemo.ui.Adapter.IndexPagerAdapter;
import com.example.whodm.retrofitdemo.ui.Adapter.MainPagerAdapter;
import com.example.whodm.retrofitdemo.ui.Fragment.ClassesFragment;
import com.example.whodm.retrofitdemo.ui.Fragment.IndexFragment;
import com.example.whodm.retrofitdemo.ui.Fragment.SingleFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {
    private LinearLayout ll_Index;
    private LinearLayout ll_Single;
    private LinearLayout ll_Class;
    //viewPage
    private ViewPager viewPager;
    private MainPagerAdapter mainPagerAdapter;

    private TextView tv_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findIdBy();
        init();
    }
    public void init(){
        tv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SearchActivity.class);
                startActivity(intent);
            }
        });
        mainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mainPagerAdapter);
        viewPager.setCurrentItem(0);
        viewPager.setOffscreenPageLimit(2);
        ll_Index.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(0);
            }
        });
        ll_Single.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(1);
            }
        });
        ll_Class.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(2);
            }
        });

    }
    public void findIdBy(){
        tv_search = (TextView)findViewById(R.id.tv_search);
        ll_Index = (LinearLayout)findViewById(R.id.id_tab_index);
        ll_Single = (LinearLayout) findViewById(R.id.id_tab_single);
        ll_Class = (LinearLayout) findViewById(R.id.id_tab_class);
        viewPager = (ViewPager) findViewById(R.id.id_page_vp);
    }
}
