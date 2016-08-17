package com.example.whodm.retrofitdemo.ui.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.whodm.retrofitdemo.R;
import com.example.whodm.retrofitdemo.ui.Fragment.IndexFragment;
import com.example.whodm.retrofitdemo.ui.LoopView;
import com.example.whodm.retrofitdemo.ui.model.ItemCover;
import com.example.whodm.retrofitdemo.ui.util.ConectionFailView;
import com.example.whodm.retrofitdemo.ui.util.FirstInitFailView;
import com.example.whodm.retrofitdemo.ui.util.LoadView;
import com.example.whodm.retrofitdemo.ui.util.NoMoreView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by whodm on 2016/7/9.
 */
public class ItemRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    //our items
    private List<ItemCover> items = new ArrayList<>();
    //headers
    private View header;

    private LoopView loopView;
    private NoMoreView noMoreView;
    private LoadView loadView;
    private FirstInitFailView firstInitFailView;
    private ConectionFailView conectionFailView;

    private ItemViewHolder itemViewHolder;

    private View footer;

    private Context context;

    public static final int VIEW_LOOPVIEW = 000;
    public static final int VIEW_NOMOREVIEW = 999;
    public static final int VIEW_LOAD = 888;
    public static final int VIEW_FIRST = 777;
    public static final int TYPE_HEADER = 111;
    public static final int TYPE_FOOTER = 222;
    public static final int TYPE_ITEM = 333;

    private EndlessLoadListener endlessLoadListener;

    private OnItemClickListener onItemClickListener;

    public ItemRecyclerViewAdapter(Context context) {
        this.context = context;
        noMoreView = new NoMoreView(context);
        loadView = new LoadView(context);
        firstInitFailView = new FirstInitFailView(context);
        conectionFailView = new ConectionFailView(context);
    }

    public View getHeader() {
        return header;
    }

    public void setLoopView(LoopView loopView) {
        this.loopView = loopView;
    }

    public void setHeader(int i) {
        if (i == VIEW_LOOPVIEW) {
            header = loopView;
        } else {
            header = null;
        }
        notifyItemChanged(0);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            FrameLayout frameLayout = new FrameLayout(parent.getContext());
            frameLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            return new HeaderViewHolder(frameLayout);
        } else if (viewType == TYPE_FOOTER) {
            FrameLayout frameLayout = new FrameLayout(parent.getContext());
            frameLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            return new FooterViewHolder(frameLayout);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
            ItemViewHolder viewHolder = new ItemViewHolder(view);
            view.setOnClickListener(this);
            return viewHolder;
        }
    }


    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_HEADER) {
            if (holder instanceof HeaderViewHolder) {
                ((HeaderViewHolder) holder).frameLayout.removeAllViews();
                if (header != null) {
                    ((HeaderViewHolder) holder).frameLayout.addView(header);
                }
            }
        } else if (getItemViewType(position) == TYPE_FOOTER) {
            if (holder instanceof FooterViewHolder) {
                ((FooterViewHolder) holder).frameLayout.removeAllViews();
                if (footer != null) {
                    ((FooterViewHolder) holder).frameLayout.addView(footer);
                }
            }
            if (endlessLoadListener != null && position - 1 >= items.size() - 1) {
                endlessLoadListener.loadMore();
            }
        } else if (getItemViewType(position) == TYPE_ITEM) {
            itemViewHolder = (ItemViewHolder) holder;
            ItemCover itemCover = items.get(position - 1);
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

    @Override
    public void onClick(View v) {
        onItemClickListener.ItemClickListener(v, (String) v.getTag());
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
        return items.size() + 2;
//        if (header == null && footer == null) {
//            return items.size();
//        } else if (header == null || footer == null) {
//            return items.size() + 1;
//        } else {
//            return items.size() + 2;
//        }
    }

    public View getFooter() {
        return footer;
    }

    public void setFooter(int i) {
        if (i == VIEW_LOAD) {
            loadView.startAnime();
            footer = loadView;
        }
        if (i == VIEW_NOMOREVIEW) {
            footer = noMoreView;
        }
        if (i == VIEW_FIRST) {
            footer = firstInitFailView;
        }
        notifyDataSetChanged();
//        if (this.footer != null) {
//            this.footer = footer;
//            Log.d("wocaoChanged", getItemCount() + "");
//            notifyItemChanged(getItemCount() - 1);
//        } else {
//            this.footer = footer;
//            Log.d("wocaoInsert", getItemCount() + "");
//            notifyItemChanged(getItemCount());
//        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        }
        if (position == getItemCount() - 1) {
            return TYPE_FOOTER;
        }
        return TYPE_ITEM;
//        if (header == null && footer == null) {
//            return TYPE_ITEM;
//        }
//        if (position == 0 && header != null) {
//            return TYPE_HEADER;
//        }
//        if (position == getItemCount() - 1 && footer != null) {
//            return TYPE_FOOTER;
//        }
//        return TYPE_ITEM;
    }

    public List<ItemCover> getItems() {
        return items;
    }

    public void setItems(List<ItemCover> items) {
        this.items = items;
    }

    public void clearItems() {
        this.items.clear();
        notifyDataSetChanged();
    }

    public void addItem(List<ItemCover> list) {
        int size = getItemCount();
        items.addAll(list);
        notifyItemRangeChanged(size - 1, list.size());
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder {
        private FrameLayout frameLayout;
        public FooterViewHolder(View itemView) {
            super(itemView);
            frameLayout = (FrameLayout) itemView;
        }
    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder {
        private FrameLayout frameLayout;
        public HeaderViewHolder(View itemView) {
            super(itemView);
            frameLayout = (FrameLayout) itemView;
        }
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
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
}
