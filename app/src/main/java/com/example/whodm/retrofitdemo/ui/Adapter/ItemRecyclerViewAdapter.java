package com.example.whodm.retrofitdemo.ui.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.whodm.retrofitdemo.R;
import com.example.whodm.retrofitdemo.ui.model.ItemCover;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by whodm on 2016/7/9.
 */
public class ItemRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //our items
    List<ItemCover> items = new ArrayList<>();
    //headers
    List<View> headers = new ArrayList<>();
    //footers
    List<View> footers = new ArrayList<>();

    private ItemViewHolder itemViewHolder;

    private Context context;

    public static final int TYPE_HEADER = 111;
    public static final int TYPE_FOOTER = 222;
    public static final int TYPE_ITEM = 333;

    private EndlessLoadListener endlessLoadListener;

    private OnItemClickListener onItemClickListener;

    public ItemRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
            return new ItemViewHolder(view);
        } else if (viewType == TYPE_HEADER) {
            FrameLayout frameLayout = new FrameLayout(parent.getContext());
            frameLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            return new HeaderViewHolder(frameLayout);
        } else {
            FrameLayout frameLayout = new FrameLayout(parent.getContext());
            frameLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            return new FooterViewHolder(frameLayout);
        }
    }


    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        Log.d("Position", position + "");
        if (position < headers.size()) {
            HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
//            View view = headers.get(position);
            View view = headers.get(position);
            if (headerViewHolder.frameLayout.getChildCount() == 0) {
                headerViewHolder.frameLayout.addView(view);
            } else {
                headerViewHolder.frameLayout.removeAllViews();
                headerViewHolder.frameLayout.addView(view);
            }

        } else if (position >= headers.size() + items.size()) {
            FooterViewHolder footerViewHolder = (FooterViewHolder) holder;
            View view = footers.get(position - headers.size() - items.size());
            footerViewHolder.frameLayout.removeAllViews();
            footerViewHolder.frameLayout.addView(view);
        } else {
            itemViewHolder = (ItemViewHolder) holder;
            ItemCover itemCover = items.get(position - headers.size());
            Glide.with(context)
                    .load(itemCover.getUrl())
                    .placeholder(R.drawable.loading)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(itemViewHolder.iv_cover);
            itemViewHolder.tv_title.setText(itemCover.getTitle());
            itemViewHolder.tv_like.setText(itemCover.getLike());
            itemViewHolder.url = itemCover.getContent_url();
            if (onItemClickListener != null) {
                itemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemClickListener.ItemClickListener(itemViewHolder.itemView, itemViewHolder.url);
                    }
                });
            }
            if (endlessLoadListener != null && position >= headers.size() + items.size() - 1) {
                endlessLoadListener.loadMore();
            }
        }
    }

    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder) {
        super.onViewRecycled(holder);
    }

    public void setEndlessLoadListener(EndlessLoadListener endlessLoadListener) {
        this.endlessLoadListener = endlessLoadListener;
    }

    public interface EndlessLoadListener {
        void loadMore();
    }

    public interface OnItemClickListener {
        void ItemClickListener(View view, String url);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemCount() {
        return headers.size() + footers.size() + items.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position < headers.size()) {
            return TYPE_HEADER;
        } else if (position >= items.size() + headers.size()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    public void addItem(List<ItemCover> list) {
        items.addAll(list);
        notifyItemInserted(headers.size() + items.size() - 1);
    }

    public void addHeader(View header) {
        if (!headers.contains(header)) {
            headers.add(header);
            //animate
            notifyItemInserted(headers.size() - 1);
            Log.d("addHeader", header + "");
        }
    }


    public static class HeaderViewHolder extends RecyclerView.ViewHolder {
        private FrameLayout frameLayout;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            this.frameLayout = (FrameLayout) itemView;
        }
    }

    public static class FooterViewHolder extends RecyclerView.ViewHolder {
        private FrameLayout frameLayout;

        public FooterViewHolder(View itemView) {
            super(itemView);
            this.frameLayout = (FrameLayout) itemView;
        }
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_cover;
        private TextView tv_title;
        private TextView tv_like;
        private String url;

        public ItemViewHolder(View itemView) {
            super(itemView);
            iv_cover = (ImageView) itemView.findViewById(R.id.iv_cover);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_like = (TextView) itemView.findViewById(R.id.tv_like);
        }
    }
}
