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
public class ItemRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    //our items
    List<ItemCover> items = new ArrayList<>();
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

    public ItemRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public View getHeader() {
        return header;
    }

    public void setHeader(View header) {
        this.header = null;
        this.header = header;
        notifyItemInserted(0);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (header != null && viewType == TYPE_HEADER) {
            return new HeaderViewHolder(header);
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

        } else if (getItemViewType(position) == TYPE_ITEM) {
            itemViewHolder = (ItemViewHolder) holder;
            int realPosition;
            if (header != null) {
                realPosition = position - 1;
            } else {
                realPosition = position;
            }
            ItemCover itemCover = items.get(realPosition);
            Log.d("Item", realPosition + "");
            Glide.with(context)
                    .load(itemCover.getUrl())
                    .placeholder(R.drawable.loading)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(itemViewHolder.iv_cover);
            itemViewHolder.tv_title.setText(itemCover.getTitle());
            itemViewHolder.tv_like.setText(itemCover.getLike());
            holder.itemView.setTag(itemCover.getContent_url());
            if (endlessLoadListener != null && realPosition >= items.size() - 1) {
                endlessLoadListener.loadMore();
            }
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
        if (header == null) {
            return items.size();
        } else {
            return items.size() + 1;
        }
    }

    public View getFooter() {
        return footer;
    }

    public void setFooter(View footer) {
        this.footer = footer;
    }

    @Override
    public int getItemViewType(int position) {
//        if (header == null && footer == null ) {
//            return TYPE_ITEM;
//        }
//        if (position == 0 ) {
//            return TYPE_HEADER;
//        }
//        return TYPE_ITEM;
        return TYPE_ITEM;
    }

    public List<ItemCover> getItems() {
        return items;
    }

    public void setItems(List<ItemCover> items) {
        this.items = items;
    }

    public void clearItems() {
        int size = getItemCount();
        this.items.clear();
        this.header = null;
        notifyDataSetChanged();
//        if (header == null){
//            notifyItemRangeRemoved(1,size);
//        }
//        notifyItemRangeRemoved(0,size);
    }

    public void addItem(List<ItemCover> list) {
//        if (header != null) {
//            items.addAll(list);
////            notifyItemRangeInserted(size,list.size());
//        }
        items.addAll(list);
//        notifyItemRangeInserted(size,list.size() - 1);
        notifyDataSetChanged();
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

        public ItemViewHolder(View itemView) {
            super(itemView);
            iv_cover = (ImageView) itemView.findViewById(R.id.iv_cover);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_like = (TextView) itemView.findViewById(R.id.tv_like);
        }
    }
}
