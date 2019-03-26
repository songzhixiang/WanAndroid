package com.andysong.wanandroid.utils.itemdecoration;

/**
 * @author AndySong on 2019/3/25
 * @Blog https://github.com/songzhixiang
 */
public class GroupData {
    private String title;
    //在分组内的位置
    private int position;
    // 组的成员个数
    private int groupLength;

    public GroupData(String title) {
        this.title = title;
    }

    public GroupData title(String title) {
        this.title = title;
        return this;
    }

    public GroupData position(int position) {
        this.position = position;
        return this;
    }

    public GroupData groupLength(int groupLength) {
        this.groupLength = groupLength;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public int getPosition() {
        return position;
    }

    public int getGroupLength() {
        return groupLength;
    }

    public boolean isFirstViewInGroup() {
        return position == 0;
    }

    public boolean isLastViewInGroup() {
        return position == groupLength - 1 && position >= 0;
    }

    public boolean isFirstLineInGroup(int spanCount) {
        return position < spanCount;
    }

    public boolean isLastLineInGroup(int spanCount) {
        return position / spanCount + 1 == groupLength / spanCount + (groupLength % spanCount == 0 ? 0 : 1);
    }
}

