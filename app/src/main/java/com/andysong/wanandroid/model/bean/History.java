package com.andysong.wanandroid.model.bean;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * @author AndySong on 2019/4/2
 * @Blog https://github.com/songzhixiang
 */
public class History extends RealmObject {
    @PrimaryKey
    private long id;
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
