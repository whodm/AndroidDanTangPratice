package com.example.whodm.retrofitdemo.model.headerindex;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by whodm on 2016/8/7.
 */
public class HeaderData {
    public List<Candidate> candidates = new ArrayList<Candidate>();
    public List<Channel> channels = new ArrayList<Channel>();

    public List<Candidate> getCandidates() {
        return candidates;
    }

    public void setCandidates(List<Candidate> candidates) {
        this.candidates = candidates;
    }

    public List<Channel> getChannels() {
        return channels;
    }

    public void setChannels(List<Channel> channels) {
        this.channels = channels;
    }
}
