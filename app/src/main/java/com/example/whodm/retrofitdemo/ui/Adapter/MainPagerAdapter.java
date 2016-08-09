package com.example.whodm.retrofitdemo.ui.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.whodm.retrofitdemo.ui.Fragment.ClassesFragment;
import com.example.whodm.retrofitdemo.ui.Fragment.IndexFragment;
import com.example.whodm.retrofitdemo.ui.Fragment.SingleFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by X on 2016/8/9.
 */
public class MainPagerAdapter extends FragmentPagerAdapter {
    private IndexFragment indexFragment;
    private SingleFragment singleFragment;
    private ClassesFragment classesFragment;

    private List<Fragment> fragmentList = new ArrayList<Fragment>();

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
        indexFragment = new IndexFragment();
        singleFragment = new SingleFragment();
        classesFragment = new ClassesFragment();
        fragmentList.add(indexFragment);
        fragmentList.add(singleFragment);
        fragmentList.add(classesFragment);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
