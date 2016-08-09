package com.example.whodm.retrofitdemo.ui.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;
import com.example.whodm.retrofitdemo.R;
import com.example.whodm.retrofitdemo.ui.Adapter.IndexPagerAdapter;

/**
 * Created by X on 2016/8/9.
 */
public class IndexFragment extends Fragment {
    private IndexPagerAdapter indexPagerAdapter;
    private ViewPager viewPager;
    private PagerSlidingTabStrip pagerSlidingTabStrip;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_index, container, false);
        viewPager = (ViewPager) view.findViewById(R.id.vp);
        pagerSlidingTabStrip = (PagerSlidingTabStrip) view.findViewById(R.id.index_tabs);
        indexPagerAdapter = new IndexPagerAdapter(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(indexPagerAdapter);
        final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources()
                .getDisplayMetrics());
        viewPager.setPageMargin(pageMargin);
        viewPager.setOffscreenPageLimit(5);
        pagerSlidingTabStrip.setViewPager(viewPager);
        return view;
    }
}
