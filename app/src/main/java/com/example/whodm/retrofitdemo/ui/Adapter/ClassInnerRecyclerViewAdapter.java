package com.example.whodm.retrofitdemo.ui.Adapter;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.whodm.retrofitdemo.R;
import com.example.whodm.retrofitdemo.ui.model.TopicIcon;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by X on 2016/8/11.
 */
public class ClassInnerRecyclerViewAdapter extends RecyclerView.Adapter<ClassInnerRecyclerViewAdapter.ItemViewHolder> {

    private List<TopicIcon> items = new ArrayList<>();
    private Context mContext;

    public ClassInnerRecyclerViewAdapter(Context context, List<TopicIcon> items) {
        this.items = items;
        this.mContext = context;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_cover;

        public ItemViewHolder(View itemView) {
            super(itemView);
            iv_cover = (ImageView) itemView.findViewById(R.id.iv_top);
        }
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.top_icon, parent, false);
        ItemViewHolder viewHolder = new ItemViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        ItemViewHolder itemViewHolder = holder;
        TopicIcon topicIcon = items.get(position);
        Glide.with(mContext)
                .load(topicIcon.getUrl())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(itemViewHolder.iv_cover);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}
