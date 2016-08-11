package com.example.whodm.retrofitdemo.ui.Adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.whodm.retrofitdemo.R;
import com.example.whodm.retrofitdemo.ui.MyGridView;
import com.example.whodm.retrofitdemo.ui.model.Icon;
import com.example.whodm.retrofitdemo.ui.model.TopicIcon;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by X on 2016/8/11.
 */
public class ClassRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private Context context;

    private static final int TOPICICON = 1111;
    private static final int STYLE = 2222;
    private static final int PINLEI = 3333;

    private LinearLayoutManager horizontalManager;

    private List<Icon> icons = new ArrayList<>();

    private List<String> stringList = new ArrayList<>();

    private List<TopicIcon> topList = new ArrayList<>();

    private List<Icon> iconList = new ArrayList<>();


    public ClassRecyclerViewAdapter(Context context) {
        this.context = context;
        horizontalManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TOPICICON) {
            View view = LayoutInflater.from(context).inflate(R.layout.type_topic, parent, false);
            TopicHolder viewHolder = new TopicHolder(view);
            return viewHolder;
        } else if (viewType == STYLE) {
            View view = LayoutInflater.from(context).inflate(R.layout.type_first_group, parent, false);
            IconHolder viewHolder = new IconHolder(view);
            return viewHolder;
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.type_second_group, parent, false);
            PinLeiHolder viewHolder = new PinLeiHolder(view);
            return viewHolder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TOPICICON) {
            TopicHolder topicHolder = (TopicHolder) holder;
            topicHolder.recyclerView.setLayoutManager(horizontalManager);
            topicHolder.recyclerView.setAdapter(new ClassInnerRecyclerViewAdapter(context, topList));
        } else if (getItemViewType(position) == STYLE) {
            IconHolder iconHolder = (IconHolder) holder;
            iconHolder.gridView.setAdapter(new GridViewAdapter(context, iconList));
        } else if (getItemViewType(position) == PINLEI) {
            PinLeiHolder pinLeiHolder = (PinLeiHolder) holder;
            pinLeiHolder.gridView.setAdapter(new GridViewAdapter(context, icons));
        }
    }


    @Override
    public int getItemCount() {
        return 3;
    }


    public List<TopicIcon> getTopList() {
        return topList;
    }

    public void setTopList(List<TopicIcon> topList) {
        this.topList = topList;
    }

    public void setIconList(List<Icon> iconList) {
        this.iconList = iconList;
    }

    public List<Icon> getIcons() {
        return icons;
    }

    public List<String> getStringList() {
        return stringList;
    }

    public void setStringList(List<String> stringList) {
        this.stringList = stringList;
    }

    public void setIcons(List<Icon> icons) {
        this.icons = icons;
    }

    public List<Icon> getIconList() {
        return iconList;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TOPICICON;
        } else if (position == 1) {
            return STYLE;
        } else if (position == 2) {
            return PINLEI;
        }
        return position;
    }

    @Override
    public void onClick(View v) {

    }

    private class PinLeiHolder extends RecyclerView.ViewHolder {
        private MyGridView gridView;

        public PinLeiHolder(View itemView) {
            super(itemView);
            gridView = (MyGridView) itemView.findViewById(R.id.gridview_second);
        }
    }

    private class IconHolder extends RecyclerView.ViewHolder {
        private MyGridView gridView;

        public IconHolder(View itemView) {
            super(itemView);
            gridView = (MyGridView) itemView.findViewById(R.id.gridview_first);

        }
    }

    private class TopicHolder extends RecyclerView.ViewHolder {
        private RecyclerView recyclerView;

        public TopicHolder(View itemView) {
            super(itemView);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.recycler_type_topic);
        }
    }
}
