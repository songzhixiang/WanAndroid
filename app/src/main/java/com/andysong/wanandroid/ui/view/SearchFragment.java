package com.andysong.wanandroid.ui.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.internal.FlowLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.UnderlineSpan;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.andysong.wanandroid.R;
import com.andysong.wanandroid.core.BaseMVPFragment;
import com.andysong.wanandroid.core.RootFragment;
import com.andysong.wanandroid.model.bean.ArticleEntity;
import com.andysong.wanandroid.model.bean.History;
import com.andysong.wanandroid.model.bean.PageList;
import com.andysong.wanandroid.ui.contract.SearchContract;
import com.andysong.wanandroid.ui.presenter.SearchPresenter;
import com.andysong.wanandroid.ui.view.adapter.IndexAdapter;
import com.andysong.wanandroid.utils.helpers.IRefreshPage;
import com.andysong.wanandroid.utils.helpers.RefreshLoadMoreHelper;
import com.andysong.wanandroid.widget.EmojiRainLayout;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author AndySong on 2019/4/1
 * @Blog https://github.com/songzhixiang
 */
public class SearchFragment extends RootFragment<SearchPresenter> implements IRefreshPage,TextWatcher, TextView.OnEditorActionListener, SearchContract.View, BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.ed_search)
    AppCompatEditText mEdSearch;
    @BindView(R.id.iv_edit_clear)
    AppCompatImageView mIvEditClear;
    @BindView(R.id.iv_search)
    AppCompatImageView ivSearch;
    @BindView(R.id.toolbar_search)
    Toolbar mToolbarSearch;

    @BindView(R.id.tv_search_clean)
    TextView tvSearchClean;
    @BindView(R.id.flow_history)
    FlowLayout mFlowLayout;
    @BindView(R.id.ll_search_history)
    ConstraintLayout llSearchHistory;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.emoji_rainLayout)
    EmojiRainLayout mEmojiRainLayout;

    private RefreshLoadMoreHelper<ArticleEntity> refreshLoadMoreHelper;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_search;
    }

    @Override
    protected void initEventAndData(@Nullable Bundle savedInstanceState) {
        super.initEventAndData(savedInstanceState);
        //emoji init
        mEmojiRainLayout.addEmoji(R.drawable.emoji_1_3);
        mEmojiRainLayout.addEmoji(R.drawable.emoji_2_3);
        mEmojiRainLayout.addEmoji(R.drawable.emoji_3_3);
        mEmojiRainLayout.addEmoji(R.drawable.emoji_4_3);
        mEmojiRainLayout.addEmoji(R.drawable.emoji_5_3);


        mToolbarSearch.setNavigationOnClickListener(v->_mActivity.onBackPressed());
        mEdSearch.addTextChangedListener(this);
        mEdSearch.setOnEditorActionListener(this);

        //
        mPresenter.queryHistory();

        //
        refreshLoadMoreHelper = new RefreshLoadMoreHelper<>(this, mSwipeRefreshLayout, mRecyclerView, IndexAdapter.class).addStateView(getStateView());

        refreshLoadMoreHelper.setOnItemClickListener(this);
    }

    @Override
    protected void onRetry() {

    }


    @OnClick({R.id.iv_edit_clear, R.id.tv_search_clean,R.id.iv_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_edit_clear:
                mEdSearch.setText("");
                hideSwipLoading();
                mPresenter.queryHistory();
                break;
            case R.id.tv_search_clean:
                mPresenter.deleteAllHistory();
                break;

            case R.id.iv_search:
                if (mPresenter != null && !TextUtils.isEmpty(mEdSearch.getText().toString().trim())) {
                    mPresenter.search(mEdSearch.getText().toString().trim(),0);
                }else{
                    mEmojiRainLayout.startDropping();
                }
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (editable.length()>0){
            showEditClear();
        }else {
            hideEditClear();
            hideSwipLoading();
            mPresenter.queryHistory();
        }
    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (i == EditorInfo.IME_ACTION_SEARCH) {
            mPresenter.search(textView.getText().toString().trim(),0);
        }
        return false;
    }

    public static SearchFragment newInstance() {

        Bundle args = new Bundle();

        SearchFragment fragment = new SearchFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void showSearchResult(PageList<ArticleEntity> articleEntityList) {
        llSearchHistory.setVisibility(View.GONE);
        mSwipeRefreshLayout.setVisibility(View.VISIBLE);
        if (!articleEntityList.getData().isEmpty())
        {
            refreshLoadMoreHelper.loadSuccess(articleEntityList);
        }else {
            refreshLoadMoreHelper.loadError();
        }

    }

    @Override
    public void showSearchHistroy(List<History> historyList) {

        llSearchHistory.setVisibility(View.VISIBLE);
        mSwipeRefreshLayout.setVisibility(View.GONE);

        mFlowLayout.removeAllViews();
        if (historyList.isEmpty()){
            mFlowLayout.setVisibility(View.GONE);
            return;
        }
        mFlowLayout.setVisibility(View.VISIBLE);
        for (int i = 0; i < historyList.size(); i++) {
            History h = historyList.get(i);
            View child = View.inflate(_mActivity, R.layout.layout_tag_knowledge_tree, null);
            TextView textView = child.findViewById(R.id.tv_tag);

            String name = h.getName();
            SpannableString content = new SpannableString(name);
            content.setSpan(new UnderlineSpan(), 0, name.length(), SpannableString.SPAN_INCLUSIVE_INCLUSIVE);
            textView.setText(content);
            final int pos = i;
            child.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LogUtils.e(h.getName()+"---"+pos);
                }
            });

            mFlowLayout.addView(child);
        }
    }



    @Override
    public void showSwipLoading() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideSwipLoading() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showEditClear() {
        mIvEditClear.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideEditClear() {
        mIvEditClear.setVisibility(View.GONE);
    }

    @Override
    public void showSearchFail(String failMsg) {

    }



    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void loadData() {
        if (mPresenter != null && !TextUtils.isEmpty(mEdSearch.getText().toString().trim())) {
            mPresenter.search(mEdSearch.getText().toString().trim(),refreshLoadMoreHelper.getCurrPage());
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mRecyclerView = null;
        mFlowLayout = null;
        _mActivity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        hideSoftInput();
    }

    @Override
    public void showErrorMsg(@NotNull String msg) {
        ToastUtils.showShort(msg);
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void stateError() {
        refreshLoadMoreHelper.loadError();

    }
}
