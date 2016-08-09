package com.example.whodm.retrofitdemo.model.hotword;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by whodm on 2016/8/7.
 */
public class HotWordData {
    public List<String> hot_words = new ArrayList<String>();
    public Object placeholder;

    public List<String> getHot_words() {
        return hot_words;
    }

    public void setHot_words(List<String> hot_words) {
        this.hot_words = hot_words;
    }

    public Object getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(Object placeholder) {
        this.placeholder = placeholder;
    }
}
