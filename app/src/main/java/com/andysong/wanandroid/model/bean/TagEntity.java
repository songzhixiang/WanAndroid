package com.andysong.wanandroid.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author AndySong on 2019/3/20
 * @Blog https://github.com/songzhixiang
 */
public class TagEntity implements Parcelable {

    private String name;
    private String url;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.url);
    }

    public TagEntity() {
    }

    protected TagEntity(Parcel in) {
        this.name = in.readString();
        this.url = in.readString();
    }

    public static final Parcelable.Creator<TagEntity> CREATOR = new Parcelable.Creator<TagEntity>() {
        @Override
        public TagEntity createFromParcel(Parcel source) {
            return new TagEntity(source);
        }

        @Override
        public TagEntity[] newArray(int size) {
            return new TagEntity[size];
        }
    };
}
