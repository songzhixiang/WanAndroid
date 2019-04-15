package com.andysong.wanandroid.ui.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.andysong.wanandroid.R;
import com.andysong.wanandroid.core.BaseFragment;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.DefaultWebClient;

import butterknife.BindView;

/**
 * @author andysong
 * @data 2019/4/15
 * @discription xxx
 */
public class ArticleDetailsFragment extends BaseFragment {

    @BindView(R.id.fl_container)
    FrameLayout mContanier;

    private AgentWeb mAgentWeb;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_article;
    }

    @Override
    protected void initEventAndData(@Nullable Bundle savedInstanceState) {
        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(mContanier,new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator(getResources().getColor(R.color.colorPrimary), 2)//设置进度条颜色与高度，-1为默认值，高度为2，单位为dp。
                .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK) //严格模式 Android 4.2.2 以下会放弃注入对象 ，使用AgentWebView没影响。
                .setMainFrameErrorView(R.layout.agentweb_error_page, -1) //参数1是错误显示的布局，参数2点击刷新控件ID -1表示点击整个布局都刷新， AgentWeb 3.0.0 加入。
                .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.DISALLOW)//打开其他页面时，弹窗质询用户前往其他应用 AgentWeb 3.0.0 加入。
                .interceptUnkownUrl() //拦截找不到相关页面的Url AgentWeb 3.0.0 加入。
                .createAgentWeb()//创建AgentWeb。
                .ready()//设置 WebSettings。
                .go(getUrl()); //WebView载入该url地址的页面并显示。
    }

    private String getUrl() {
        return getArguments().getString("url");
    }


    public static ArticleDetailsFragment newInstance(String url) {

        Bundle args = new Bundle();
        args.putString("url",url);
        ArticleDetailsFragment fragment = new ArticleDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onDestroyView() {
        if (isInited && null!=mAgentWeb) {

            mAgentWeb.getWebLifeCycle().onDestroy();
        }
        super.onDestroyView();
    }

    @Override
    public void onPause() {
        if (isInited && null!=mAgentWeb){
            mAgentWeb.getWebLifeCycle().onPause();
        }

        super.onPause();
    }

    @Override
    public void onResume() {
        if (isInited && null!=mAgentWeb) {

            mAgentWeb.getWebLifeCycle().onResume();//恢复
        }
        super.onResume();
    }
}
