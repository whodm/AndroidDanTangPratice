package com.example.whodm.retrofitdemo.ui.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.whodm.retrofitdemo.R;
import com.example.whodm.retrofitdemo.callback.SingleCallback;
import com.example.whodm.retrofitdemo.model.single.SingleData;

import java.util.List;

/**
 * Created by X on 2016/8/8.
 */
public class SingleFragment extends Fragment implements SingleCallback {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_single,container,false);
        return view;
    }

    @Override
    public void onSingleSuccess(SingleData singleData) {

    }

    @Override
    public void onSingleFail() {

    }
}
