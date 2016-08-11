package com.example.whodm.retrofitdemo.ui.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
public class ClassRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private Context context;

    private static final int TEXT = 1111;

    private static final int TOPICICON = 2222;

    private static final int ICON = 3333;

    private List<String> textList = new ArrayList<>();

    private List<View> topList = new ArrayList<>();

    private List<Icon> iconList = new ArrayList<>();

    private OnItemClickListener onItemClickListener;

    private int counter = 0;

    public interface OnItemClickListener {
        void ItemClickListener(View view, String url);
    }

    public ClassRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TEXT) {
            View view = LayoutInflater.from(context).inflate(R.layout.type_text, parent, false);
            TextHolder textHolder = new TextHolder(view);
            return textHolder;
        } else if (viewType == TOPICICON) {
            View view = LayoutInflater.from(context).inflate(R.layout.type_topic, parent, false);
            TopicHolder topicHolder = new TopicHolder(view);
            return topicHolder;
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.type_icon, parent, false);
            IconHolder iconHolder = new IconHolder(view);
            return iconHolder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == 0 || position == 2 || position == 4) {
            TextHolder textHolder = (TextHolder) holder;
            switch (position) {
                case 0:
                    textHolder.tv_type_text.setText(textList.get(0));
                    break;
                case 2:
                    textHolder.tv_type_text.setText(textList.get(1));
                    break;
                case 4:
                    textHolder.tv_type_text.setText(textList.get(2));
                    break;
            }
        } else if (position == 1) {
            TopicHolder topicHolder = (TopicHolder) holder;

        } else {
            IconHolder iconHolder = (IconHolder) holder;
            for (int i = counter; i < counter + 4; i++) {
                intoImageView(iconHolder, i);
            }
            counter = counter + 4;
        }
    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemCount() {
        return textList.size() + topList.size() + iconList.size();
    }

    public void intoImageView(IconHolder iconHolder, int i) {
        switch (i) {
            case 0:
                Glide.with(context)
                        .load(iconList.get(i).getUrl())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(iconHolder.iv_1);
                break;
            case 1:
                Glide.with(context)
                        .load(iconList.get(i).getUrl())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(iconHolder.iv_2);
                break;
            case 2:
                Glide.with(context)
                        .load(iconList.get(i).getUrl())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(iconHolder.iv_3);
                break;
            case 3:
                Glide.with(context)
                        .load(iconList.get(i).getUrl())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(iconHolder.iv_4);
                break;
        }
    }

    public void setTextList(List<String> textList) {
        this.textList = textList;
    }

    public void setTopList(List<View> topList) {
        this.topList = topList;
    }

    public void setIconList(List<Icon> iconList) {
        this.iconList = iconList;
    }

    public List<String> getTextList() {
        return textList;
    }

    public List<View> getTopList() {
        return topList;
    }

    public List<Icon> getIconList() {
        return iconList;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 || position == 2 || position == 4) {
            return TEXT;
        } else if (position == 1) {
            return TOPICICON;
        } else {
            return ICON;
        }
    }

    @Override
    public void onClick(View v) {

    }

    private class TextHolder extends RecyclerView.ViewHolder {
        private TextView tv_type_text;

        public TextHolder(View itemView) {
            super(itemView);
            tv_type_text = (TextView) itemView.findViewById(R.id.tv_type_text);
        }
    }

    private class IconHolder extends RecyclerView.ViewHolder {
        private ImageView iv_1, iv_2, iv_3, iv_4;

        public IconHolder(View itemView) {
            super(itemView);
            iv_1 = (ImageView) itemView.findViewById(R.id.type_iv_1);
            iv_2 = (ImageView) itemView.findViewById(R.id.type_iv_2);
            iv_3 = (ImageView) itemView.findViewById(R.id.type_iv_3);
            iv_4 = (ImageView) itemView.findViewById(R.id.type_iv_4);

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
