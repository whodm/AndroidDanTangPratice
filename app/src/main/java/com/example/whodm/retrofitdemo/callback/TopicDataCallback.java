package com.example.whodm.retrofitdemo.callback;

import com.example.whodm.retrofitdemo.model.topic.TopicData;

/**
 * Created by X on 2016/8/11.
 */
public interface TopicDataCallback {
    void onTopicDataSuccess(TopicData topicData);

    void onTopicDataFail();

    void onTopicDataNothing();
}
