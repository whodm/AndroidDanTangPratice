package com.example.whodm.retrofitdemo.model.bottomstyle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by whodm on 2016/8/7.
 */
public class BottomStyleData {
    public List<ChannelGroup> channel_groups = new ArrayList<ChannelGroup>();

    public List<ChannelGroup> getChannel_groups() {
        return channel_groups;
    }

    public void setChannel_groups(List<ChannelGroup> channel_groups) {
        this.channel_groups = channel_groups;
    }
}
