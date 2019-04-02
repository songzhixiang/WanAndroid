package com.andysong.wanandroid.ui.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.andysong.wanandroid.R;
import com.andysong.wanandroid.core.BaseMVPFragment;
import com.andysong.wanandroid.model.bean.ArticleEntity;
import com.andysong.wanandroid.model.bean.History;
import com.andysong.wanandroid.ui.contract.SearchContract;
import com.andysong.wanandroid.ui.presenter.SearchPresenter;
import com.andysong.wanandroid.widget.EmojiRainLayout;
import com.blankj.utilcode.util.LogUtils;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author AndySong on 2019/4/1
 * @Blog https://github.com/songzhixiang
 */
public class SearchFragment extends BaseMVPFragment<SearchPresenter> implements TextWatcher, TextView.OnEditorActionListener, SearchContract.View {
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
    @BindView(R.id.recycler_search_history)
    RecyclerView mRecyclerViewHistory;
    @BindView(R.id.ll_search_history)
    ConstraintLayout llSearchHistory;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.emoji_rainLayout)
    EmojiRainLayout mEmojiRainLayout;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_search;
    }

    @Override
    protected void initEventAndData(@Nullable Bundle savedInstanceState) {
        //emoji init
        mEmojiRainLayout.addEmoji(R.drawable.emoji_1_3);
        mEmojiRainLayout.addEmoji(R.drawable.emoji_2_3);
        mEmojiRainLayout.addEmoji(R.drawable.emoji_3_3);
        mEmojiRainLayout.addEmoji(R.drawable.emoji_4_3);
        mEmojiRainLayout.addEmoji(R.drawable.emoji_5_3);

        //
        setHasOptionsMenu(true);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbarSearch);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbarSearch.setNavigationOnClickListener(v->onBackPressedSupport());
        mEdSearch.addTextChangedListener(this);
        mEdSearch.setOnEditorActionListener(this);
    }


    @OnClick({R.id.iv_edit_clear, R.id.tv_search_clean})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_edit_clear:
                mPresenter.queryHistory();
                break;
            case R.id.tv_search_clean:
                mPresenter.search(mEdSearch.getText().toString().trim(),1);
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
    public void showSearchResult(List<ArticleEntity> articleEntityList) {

    }

    @Override
    public void showSearchHistroy(List<History> historyList) {
        LogUtils.e(historyList.size());
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
    public void showErrorMsg(@NotNull String msg) {

    }

    @Override
    public void stateError() {

    }

    @Override
    public void stateEmpty() {

    }

    @Override
    public void stateLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void stateMain() {

    }
}
