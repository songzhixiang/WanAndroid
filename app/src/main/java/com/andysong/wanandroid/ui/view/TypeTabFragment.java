package com.andysong.wanandroid.ui.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.andysong.wanandroid.R;
import com.andysong.wanandroid.core.BaseFragment;
import com.andysong.wanandroid.model.bean.TreeEntity;
import com.andysong.wanandroid.ui.view.adapter.TabPageAdapter;
import com.andysong.wanandroid.utils.helpers.IPageContent;
import com.andysong.wanandroid.utils.helpers.TabHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.realm.annotations.Index;

/**
 * @author andysong
 * @data 2019/4/28
 * @discription xxx
 */
public class TypeTabFragment extends BaseFragment implements IPageContent {

    public static final String KEY_DATA = "data";
    public static final String KEY_POSITION = "position";
    private TreeEntity mTreeEntity;

    @BindView(R.id.tv_toolbar)
    TextView mTextViewToolbar;
    @BindView(R.id.avatar)
    ImageView avatar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tab;
    }

    @Override
    protected void initEventAndData(@Nullable Bundle savedInstanceState) {
        mTreeEntity = getArguments().getParcelable(KEY_DATA);
        int position = getArguments().getInt(KEY_POSITION,0);
        List<TreeEntity> children = mTreeEntity.getChildren();
        mTextViewToolbar.setText(mTreeEntity.getName());
        TabHelper tabHelper = new TabHelper(tabLayout
                ,viewpager
                ,new TabPageAdapter<>(getChildFragmentManager(),children,this));
        tabHelper.setCurrentPosition(position);
    }


    @OnClick(R.id.avatar)
    public void onViewClicked() {

    }

    @Override
    public Fragment getItem(int position) {
        return IndexFragment.newInstance(mTreeEntity.getChildren().get(position).getId());
    }


    public static TypeTabFragment newInstance(TreeEntity treeEntity, int position) {

        Bundle args = new Bundle();
        args.putParcelable(TypeTabFragment.KEY_DATA,treeEntity);
        args.putInt(TypeTabFragment.KEY_POSITION,position);
        TypeTabFragment fragment = new TypeTabFragment();
        fragment.setArguments(args);
        return fragment;
    }


}
