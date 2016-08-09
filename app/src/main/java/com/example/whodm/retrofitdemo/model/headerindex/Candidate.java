package com.example.whodm.retrofitdemo.model.headerindex;

/**
 * Created by whodm on 2016/8/7.
 */
public class Candidate {
    public Boolean editable;
    public Integer id;
    public String name;

    public Boolean getEditable() {
        return editable;
    }

    public void setEditable(Boolean editable) {
        this.editable = editable;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
