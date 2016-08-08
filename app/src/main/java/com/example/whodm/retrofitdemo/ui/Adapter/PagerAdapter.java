package com.example.whodm.retrofitdemo.ui.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.whodm.retrofitdemo.ui.Fragment.CollectionFragment;
import com.example.whodm.retrofitdemo.ui.Fragment.OtherFragment;

/**
 * Created by X on 2016/8/8.
 */
public class PagerAdapter extends FragmentPagerAdapter{
    private final String[] Titles = {"精选","美食","家居","数码","美食","杂货"};
    private CollectionFragment collectionFragment;
    public PagerAdapter(FragmentManager fm) {
        super(fm);
        collectionFragment = new CollectionFragment();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0){
            return collectionFragment;
        }else {
            return OtherFragment.newInstance(position);
        }
    }

    @Override
    public int getCount() {
        return Titles.length;
    }
}
