package com.example.whodm.retrofitdemo.callback;

import com.example.whodm.retrofitdemo.model.channel.ChannelData;

/**
 * Created by X on 2016/8/12.
 */
public interface ChannelCallback {
    void onChannelSuccess(ChannelData channelData);

    void onChannelFail();

    void onChannelNothing();
}
