package com.example.whodm.retrofitdemo.ui.Fragment;

import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.whodm.retrofitdemo.R;
import com.example.whodm.retrofitdemo.callback.AllTopicCallback;
import com.example.whodm.retrofitdemo.callback.ClassesCallback;
import com.example.whodm.retrofitdemo.model.all.AllData;
import com.example.whodm.retrofitdemo.model.bottomstyle.BottomStyleData;
import com.example.whodm.retrofitdemo.model.bottomstyle.Channel;
import com.example.whodm.retrofitdemo.model.single.SingleData;
import com.example.whodm.retrofitdemo.service.HttpService;
import com.example.whodm.retrofitdemo.ui.Adapter.ClassInnerRecyclerViewAdapter;
import com.example.whodm.retrofitdemo.ui.Adapter.ClassRecyclerViewAdapter;
import com.example.whodm.retrofitdemo.ui.Adapter.GridViewAdapter;
import com.example.whodm.retrofitdemo.ui.Adapter.IconRecyclerViewAdapter;
import com.example.whodm.retrofitdemo.ui.Adapter.SingleRecyclerViewAdapter;
import com.example.whodm.retrofitdemo.ui.Adapter.TopicRecyclerViewAdapter;
import com.example.whodm.retrofitdemo.ui.model.Icon;
import com.example.whodm.retrofitdemo.ui.model.SingleCover;
import com.example.whodm.retrofitdemo.ui.model.TopicIcon;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by X on 2016/8/8.
 */
public class ClassesFragment extends Fragment implements ClassesCallback, AllTopicCallback {
    private static String TAG = "ClassesFragment";
    private RecyclerView recyclerView;
    //    private RecyclerView topicRecyclerView, styleRecyclerView, pinleiRecyclerView;
//    private GridView gridView;
//    private IconRecyclerViewAdapter styleRecyclerViewAdapter;
//    private IconRecyclerViewAdapter pinleiRecyclerViewAdapter;
//    //private TopicRecyclerViewAdapter topicRecyclerViewAdapter;
    private ClassRecyclerViewAdapter classRecyclerViewAdapter;
    private final static HttpService httpService = new HttpService();
    private List<TopicIcon> topicIcons = new ArrayList<>();
    private List<Icon> iconList_one = new ArrayList<>();
    private List<Icon> iconList_second = new ArrayList<>();
    private final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
    private final LinearLayoutManager horizontalManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_class_v1, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_class);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        classRecyclerViewAdapter = new ClassRecyclerViewAdapter(getActivity());
        recyclerView.setAdapter(classRecyclerViewAdapter);
//        topicRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_topic);
//        styleRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_style);
////        pinleiRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_pinlei);
//        //container_one = (FrameLayout) view.findViewById(R.id.container_one);
//        gridView = (GridView) view.findViewById(R.id.gridview);
//        gridView.setOnTouchListener(new View.OnTouchListener() {
//
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (event.getAction() == MotionEvent.ACTION_MOVE) {
//                    return true;
//                }
//                return false;
//            }
//        });
//        initRecycleView();
        Log.d(TAG, "RUN");
        init();
        return view;
    }

    public void init() {
        httpService.allTopicService(0, this);
        httpService.bottomStyleService(this);
    }

    @Override
    public void onAllTopicSuccess(AllData data) {
        AllData allData = data;
        for (int i = 0; i < allData.collections.size(); i++) {
            TopicIcon topicIcon = new TopicIcon();
            topicIcon.setUrl(allData.collections.get(i).getBanner_image_url());
            topicIcon.setContent_url(allData.collections.get(i).getId().toString());
            topicIcons.add(topicIcon);
        }
        classRecyclerViewAdapter.setTopList(topicIcons);
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                classRecyclerViewAdapter.notifyDataSetChanged();
            }
        });
//        topicRecyclerViewAdapter.addItem(topicIcons);
//        topicRecyclerViewAdapter.setEndlessLoadListener(new TopicRecyclerViewAdapter.EndlessLoadListener() {
//            @Override
//            public void loadMore() {
//
//            }
//        });
//        topicRecyclerView.setAdapter(topicRecyclerViewAdapter);
    }

    @Override
    public void onFail() {
        Toast.makeText(getContext(), "连接失败", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAllTopicNothing() {
        Toast.makeText(getContext(), "没有更多了", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClassesNothing() {
        Toast.makeText(getContext(), "没有更多了", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClassesSuccess(BottomStyleData bottomStyleData) {
        BottomStyleData data = bottomStyleData;
        for (int i = 0; i < data.getChannel_groups().size(); i++) {
            List<Channel> channelList = data.getChannel_groups().get(i).getChannels();
            for (int j = 0; j < channelList.size(); j++) {
                List<Channel> c = channelList;
                if (c.get(j).getGroup_id() == 1) {
                    //one floor
                    Icon icon = new Icon();
                    icon.setUrl(channelList.get(j).getIcon_url());
                    icon.setTitle(c.get(j).getName());
                    icon.setContent_id(c.get(j).getId());
                    Log.d(TAG, "Name" + c.get(j).getName());
                    iconList_one.add(icon);

                } else {
                    Icon icon = new Icon();
                    icon.setUrl(channelList.get(j).getIcon_url());
                    icon.setTitle(c.get(j).getName());
                    icon.setContent_id(c.get(j).getId());
                    Log.d(TAG, "Name" + c.get(j).getName());
                    iconList_second.add(icon);
                }
            }
        }
        classRecyclerViewAdapter.setIconList(iconList_one);
        classRecyclerViewAdapter.setIcons(iconList_second);
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                classRecyclerViewAdapter.notifyDataSetChanged();
            }
        });
//        gridView.setAdapter(new GridViewAdapter(getActivity(), iconList_second));
//        styleRecyclerViewAdapter.addItem(iconList_one);
//        styleRecyclerView.setAdapter(styleRecyclerViewAdapter);
    }

    @Override
    public void onClassesFail() {
        Toast.makeText(getContext(), "连接失败", Toast.LENGTH_LONG).show();
    }

    public void initRecycleView() {
//        topicRecyclerView.setLayoutManager(horizontalManager);
//        styleRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL));
//        topicRecyclerView.setItemAnimator(new DefaultItemAnimator());
//        topicRecyclerView.setHasFixedSize(true);
//        styleRecyclerView.setItemAnimator(new DefaultItemAnimator());
//        styleRecyclerView.setHasFixedSize(true);
//        styleRecyclerViewAdapter = new IconRecyclerViewAdapter(getActivity());
////        topicRecyclerViewAdapter = new TopicRecyclerViewAdapter(getActivity());
    }
}
