package com.example.whodm.retrofitdemo.ui.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.whodm.retrofitdemo.R;
import com.example.whodm.retrofitdemo.ui.model.Icon;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by X on 2016/8/11.
 */
public class GridViewAdapter extends BaseAdapter {
    private List<Icon> iconList = new ArrayList<>();
    private Context mContext;

    public GridViewAdapter(Context context, List<Icon> iconList) {
        this.iconList = iconList;
        mContext = context;
    }

    @Override
    public int getCount() {
        return iconList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.icon_layout, parent, false);
        } else {
            view = convertView;
        }
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_icon);
        TextView textView = (TextView) view.findViewById(R.id.tv_icon);
        Glide.with(mContext)
                .load(iconList.get(position).getUrl())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
        textView.setText(iconList.get(position).getTitle());
        return view;
    }
}
