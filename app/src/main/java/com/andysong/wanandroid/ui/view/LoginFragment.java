package com.andysong.wanandroid.ui.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.widget.Button;

import com.andysong.wanandroid.R;
import com.andysong.wanandroid.Spark;
import com.andysong.wanandroid.core.BaseFragment;
import com.andysong.wanandroid.core.BaseMVPFragment;
import com.andysong.wanandroid.ui.contract.LoginContract;
import com.andysong.wanandroid.ui.presenter.LoginPresenter;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author AndySong on 2019/3/20
 * @Blog https://github.com/songzhixiang
 */
public class LoginFragment extends BaseMVPFragment<LoginPresenter> implements LoginContract.View {

    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.frameLayout)
    ConstraintLayout mFrameLayout;

    private Spark mSpark;

    public static LoginFragment newInstance() {

        Bundle args = new Bundle();

        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    protected void initEventAndData(@Nullable Bundle savedInstanceState) {
        mSpark = new Spark.Builder()
                .setView(mFrameLayout)
                .setDuration(4000)
                .setAnimList(Spark.ANIM_BLUE_PURPLE)
                .build();
        mSpark.startAnimation();
    }



    @OnClick(R.id.btn_login)
    public void onViewClicked() {
        mPresenter.requestLogin();
    }


    @Override
    public void onPause() {
        super.onPause();
        if (isInited&&null!=mSpark){
            mSpark.stopAnimation();
        }
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void jumpToIndex() {

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
