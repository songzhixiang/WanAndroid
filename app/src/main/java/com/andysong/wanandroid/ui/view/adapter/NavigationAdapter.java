package com.andysong.wanandroid.ui.view.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.design.internal.FlowLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.andysong.wanandroid.R;
import com.andysong.wanandroid.core.BaseFragment;
import com.andysong.wanandroid.model.bean.ArticleEntity;
import com.andysong.wanandroid.model.bean.NavigationInfoEntity;
import com.andysong.wanandroid.ui.view.ArticleDetailsFragment;
import com.andysong.wanandroid.utils.CommonExKt;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;
import java.util.Random;

/**
 * @author AndySong on 2019/3/27
 * @Blog https://github.com/songzhixiang
 */
public class NavigationAdapter extends BaseQuickAdapter<NavigationInfoEntity, BaseViewHolder> {

//    private Random random;

    private BaseFragment mBaseFragment;

    public BaseFragment getBaseFragment() {
        return mBaseFragment;
    }

    public void setBaseFragment(BaseFragment baseFragment) {
        mBaseFragment = baseFragment;
    }

    public NavigationAdapter(@Nullable List<NavigationInfoEntity> data) {
        super(R.layout.item_navigation, data);
//        random = new Random();
    }

    @Override
    protected void convert(BaseViewHolder helper, NavigationInfoEntity item) {
        helper.setText(R.id.tv_title, item.getName());
        FlowLayout layoutFlow = helper.getView(R.id.layout_flow);
        layoutFlow.removeAllViews();
        for (final ArticleEntity articleEntity : item.getArticles()) {
            View child = View.inflate(mContext, R.layout.layout_tag_navi, null);
            TextView textView = child.findViewById(R.id.tv_tag);
            textView.setText(articleEntity.getTitle());
//            textView.setTextColor(CommonExKt.getRandomColor(random));
            textView.setTextColor(Color.parseColor("#ea4c89"));
            child.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((BaseFragment)getBaseFragment().getParentFragment()).start((ArticleDetailsFragment
                            .newInstance(TextUtils
                                            .isEmpty(articleEntity.getProjectLink())?
                                            articleEntity.getLink():
                                            articleEntity.getProjectLink()
                                    ,articleEntity.getTitle())));
                }
            });

            layoutFlow.addView(child);
        }
    }
}
