package com.example.whodm.retrofitdemo.ui.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.whodm.retrofitdemo.R;

/**
 * Created by X on 2016/8/8.
 */
public class OtherFragment extends Fragment {
    private static final String ARG_POSITION = "position";
    private int position;
    private TextView tv;

    public static OtherFragment newInstance(int position){
        OtherFragment otherFragment = new OtherFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_POSITION,position);
        otherFragment.setArguments(bundle);
        return otherFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getArguments().getInt(ARG_POSITION);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_channel,container,false);
        tv = (TextView)view.findViewById(R.id.tv_other);
        tv.setText(position+"");
        return view;

    }
}
