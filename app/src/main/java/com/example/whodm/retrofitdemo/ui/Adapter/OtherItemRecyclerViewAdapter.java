package com.example.whodm.retrofitdemo.ui.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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
 * Created by X on 2016/8/18.
 */
public class OtherItemRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    public static final int TYPE_FOOTER = 1;  //说明是带有Footer的
    public static final int TYPE_NORMAL = 2;  //说明是不带有header和footer的

    private View mFooter;
    private ItemViewHolder itemViewHolder;
    private List<ItemCover> itemCovers = new ArrayList<>();
    private View mFooterView;
    private Context context;
    private EndlessLoadListener endlessLoadListener;

    public OtherItemRecyclerViewAdapter(Context context, View mFooterView) {
        this.mFooterView = mFooterView;
        this.context = context;
        mFooter = LayoutInflater.from(context).inflate(R.layout.layout_footer, null);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mFooterView != null && viewType == TYPE_FOOTER) {
            FrameLayout frameLayout = new FrameLayout(parent.getContext());
            frameLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            return new FooterViewHolder(frameLayout);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
            ItemViewHolder viewHolder = new ItemViewHolder(view);
            view.setOnClickListener(this);
            return viewHolder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_FOOTER) {
            if (holder instanceof FooterViewHolder) {
                ((FooterViewHolder) holder).frameLayout.removeAllViews();
                if (mFooterView != null) {
                    ((FooterViewHolder) holder).frameLayout.addView(mFooterView);
                }
                if (endlessLoadListener != null && position - 1 >= itemCovers.size() - 1) {
                    endlessLoadListener.loadMore();
                }
            } else {
                itemViewHolder = (ItemViewHolder) holder;
                ItemCover itemCover = itemCovers.get(position - 1);
                Glide.with(context)
                        .load(itemCover.getUrl())
                        .placeholder(R.drawable.loading)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(itemViewHolder.iv_cover);
                itemViewHolder.tv_title.setText(itemCover.getTitle());
                itemViewHolder.tv_like.setText(itemCover.getLike());
                holder.itemView.setTag(itemCover.getContent_url());
            }
        }
    }

    public void setEndlessLoadListener(EndlessLoadListener endlessLoadListener) {
        this.endlessLoadListener = endlessLoadListener;
    }

    public interface EndlessLoadListener {
        void loadMore();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            return TYPE_FOOTER;
        } else {
            return TYPE_NORMAL;
        }
    }

    public void addItem(List<ItemCover> list) {
        itemCovers.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return itemCovers.size() + 1;
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_cover;
        private TextView tv_title;
        private TextView tv_like;

        public ItemViewHolder(View itemView) {
            super(itemView);
            iv_cover = (ImageView) itemView.findViewById(R.id.iv_cover);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_like = (TextView) itemView.findViewById(R.id.tv_like);
        }
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder {
        private FrameLayout frameLayout;

        public FooterViewHolder(View itemView) {
            super(itemView);
            frameLayout = (FrameLayout) itemView;
        }
    }


    @Override
    public void onClick(View view) {

    }
}
