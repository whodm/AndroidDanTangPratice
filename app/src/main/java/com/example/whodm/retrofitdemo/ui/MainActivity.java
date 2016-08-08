package com.example.whodm.retrofitdemo.ui;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;
import com.example.whodm.retrofitdemo.R;
import com.example.whodm.retrofitdemo.service.HttpService;
import com.example.whodm.retrofitdemo.ui.Adapter.PagerAdapter;

public class MainActivity extends FragmentActivity {
    private HttpService httpService;
    private LinearLayout ll_Index;
    private LinearLayout ll_Single;
    private LinearLayout ll_Class;
    private PagerAdapter pagerAdapter;
    private ViewPager viewPager;
    private PagerSlidingTabStrip pagerSlidingTabStrip;
    private TextView tv_search;
    //当前页面
    private int currentIndex;
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
        pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources()
                .getDisplayMetrics());
        viewPager.setPageMargin(pageMargin);
        pagerSlidingTabStrip.setViewPager(viewPager);

    }
    public void findIdBy(){
        pagerSlidingTabStrip = (PagerSlidingTabStrip)findViewById(R.id.tabs);
        viewPager = (ViewPager)findViewById(R.id.vp);
        tv_search = (TextView)findViewById(R.id.tv_search);
        ll_Index = (LinearLayout)findViewById(R.id.id_tab_index);
    }
}
