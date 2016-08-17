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
import com.example.whodm.retrofitdemo.ui.model.ItemCover;
import com.example.whodm.retrofitdemo.ui.model.SingleCover;
import com.example.whodm.retrofitdemo.ui.util.LoadView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by whodm on 2016/7/9.
 */
public class SingleRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    //our items
    List<SingleCover> items = new ArrayList<>();
    //headers
    private View header;

    private ItemViewHolder itemViewHolder;

    private View footer;

    private Context context;

    public static final int TYPE_HEADER = 111;
    public static final int TYPE_FOOTER = 222;
    public static final int TYPE_ITEM = 333;

    private EndlessLoadListener endlessLoadListener;

    private OnItemClickListener onItemClickListener;

    public SingleRecyclerViewAdapter(Context context) {
        this.context = context;
    }

//    public View getHeader() {
//        return header;
//    }
//
//    public void setHeader(View header) {
//        this.header = null;
//        this.header = header;
//        notifyItemInserted(0);
//    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (header != null && viewType == TYPE_HEADER) {
            return new HeaderViewHolder(header);
        } else if (footer != null && viewType == TYPE_FOOTER) {
            return new FooterViewHolder(footer);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_layout, parent, false);
            ItemViewHolder viewHolder = new ItemViewHolder(view);
            view.setOnClickListener(this);
            return viewHolder;
        }
    }


    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        int realPosition;
        if (header != null) {
            realPosition = position - 1;
        } else {
            realPosition = position;
        }
        if (getItemViewType(position) == TYPE_HEADER) {

        } else if (getItemViewType(position) == TYPE_FOOTER) {
            if (endlessLoadListener != null && realPosition >= items.size() - 1) {
                endlessLoadListener.loadMore();
            }
        } else if (getItemViewType(position) == TYPE_ITEM) {
            itemViewHolder = (ItemViewHolder) holder;
            SingleCover singleCover = items.get(realPosition);
            itemViewHolder.tv_title.setText(singleCover.getTitle());
            itemViewHolder.tv_like.setText(singleCover.getLike());
            itemViewHolder.tv_price.setText(singleCover.getPrice());
            holder.itemView.setTag(singleCover.getContent_url());
            Glide.with(context)
                    .load(singleCover.getUrl())
                    .placeholder(R.drawable.loading_single)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(itemViewHolder.iv_cover);
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
        if (header == null && footer == null) {
            return items.size();
        } else if (header == null) {
            return items.size() + 1;
        } else if (footer == null) {
            return items.size() + 1;
        } else {
            return items.size() + 2;
        }
    }

    public View getFooter() {
        return footer;
    }

    public void setFooter(View footer) {
        if (footer != null) {
            this.footer = footer;
            notifyItemChanged(getItemCount() - 1);
        }
        this.footer = footer;
        notifyItemInserted(getItemCount());
    }

    @Override
    public int getItemViewType(int position) {
        if (header == null && footer == null) {
            return TYPE_ITEM;
        }
        if (position == 0 && header != null) {
            return TYPE_HEADER;
        }
        if (position == getItemCount() - 1 && footer != null) {
            return TYPE_FOOTER;
        }
        return TYPE_ITEM;
    }

    public List<SingleCover> getItems() {
        return items;
    }

    public void setItems(List<SingleCover> items) {
        this.items = items;
    }

    public void clearItems() {
        this.footer = null;
        this.items.clear();
        notifyDataSetChanged();
    }

    public void addItem(List<SingleCover> list) {
        int size = getItemCount();
        items.addAll(list);
        Log.d("Add item", "size = " + size + "");
        notifyItemRangeChanged(size, list.size());
        Log.d("Add item", "size = " + footer + "");
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder {

        public FooterViewHolder(View itemView) {
            super(itemView);
            itemView = footer;

        }
    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder {

        public HeaderViewHolder(View itemView) {
            super(itemView);
            itemView = header;
        }
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_cover;
        private TextView tv_title;
        private TextView tv_like;
        private TextView tv_price;

        public ItemViewHolder(View itemView) {
            super(itemView);
            iv_cover = (ImageView) itemView.findViewById(R.id.iv_single_cover);
            tv_title = (TextView) itemView.findViewById(R.id.tv_single_title);
            tv_like = (TextView) itemView.findViewById(R.id.tv_single_like);
            tv_price = (TextView) itemView.findViewById(R.id.tv_single_price);
        }
    }
}
