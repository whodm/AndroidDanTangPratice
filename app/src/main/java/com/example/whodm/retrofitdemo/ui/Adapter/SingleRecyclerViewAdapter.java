package com.example.whodm.retrofitdemo.ui.Adapter;

/**
 * Created by X on 2016/8/10.
 */

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

import java.util.ArrayList;
import java.util.List;

import com.example.whodm.retrofitdemo.ui.model.ItemCover;
import com.example.whodm.retrofitdemo.ui.model.SingleCover;
import com.example.whodm.retrofitdemo.ui.util.ScreenUtil;


/**
 * Created by whodm on 2016/7/9.
 */
public class SingleRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    //our items
    private List<SingleCover> items = new ArrayList<>();
    //headers
    private View header;

    private static ItemViewHolder itemViewHolder;

    private Context context;

    public static final int TYPE_HEADER = 111;
    public static final int TYPE_ITEM = 333;

    private EndlessLoadListener endlessLoadListener;

    private OnItemClickListener onItemClickListener;


    public SingleRecyclerViewAdapter(Context context) {
        this.context = context;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (header != null && viewType == TYPE_HEADER) {
            return new HeaderViewHolder(header);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_layout, parent, false);
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
            SingleCover singleCover = items.get(realPosition);
            itemViewHolder.tv_title.setText(singleCover.getTitle());
            itemViewHolder.tv_like.setText(singleCover.getLike());
            itemViewHolder.tv_price.setText(singleCover.getPrice());
            holder.itemView.setTag(singleCover.getContent_url());
            Glide.with(context)
                    .load(singleCover.getUrl())
                    .placeholder(R.drawable.loading_single)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(itemViewHolder.iv_cover);
            if (endlessLoadListener != null && realPosition >= items.size() - 1) {
                endlessLoadListener.loadMore();
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
    public void onClick(View v) {
        onItemClickListener.ItemClickListener(v, (String) v.getTag());
    }

    public interface OnItemClickListener {
        void ItemClickListener(View view, String url);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
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
    public int getItemCount() {
        if (header == null) {
            return items.size();
        } else {
            return items.size() + 1;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (header == null) {

            return TYPE_ITEM;
        }
        if (position == 0) {

            return TYPE_HEADER;
        }
        return TYPE_ITEM;
    }

    public void addItem(List<SingleCover> list) {
        if (header != null) {
            items.addAll(list);
            notifyItemInserted(items.size());
        }
        items.addAll(list);
        Log.d("SingleRecycle", items.get(0).getTitle() + "");
        notifyItemInserted(items.size() - 1);
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
