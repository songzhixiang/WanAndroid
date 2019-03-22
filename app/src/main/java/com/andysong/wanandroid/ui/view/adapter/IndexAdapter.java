package com.andysong.wanandroid.ui.view.adapter;

import android.support.annotation.Nullable;

import com.andysong.wanandroid.R;
import com.andysong.wanandroid.model.bean.ArticleEntity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.text.MessageFormat;
import java.util.List;

/**
 * @author AndySong on 2019/3/22
 * @Blog https://github.com/songzhixiang
 */
public class IndexAdapter extends BaseQuickAdapter<ArticleEntity, BaseViewHolder> {
    public IndexAdapter(@Nullable List<ArticleEntity> data) {
        super(R.layout.item_article,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ArticleEntity item) {
        helper.setText(R.id.tv_chapterName, MessageFormat.format("{0}/{1}", item.getSuperChapterName(), item.getChapterName()));
        helper.setText(R.id.tv_title,item.getTitle());
        helper.setText(R.id.tv_publishTime,item.getNiceDate());
        helper.setText(R.id.tv_author, item.getAuthor());
    }
}
