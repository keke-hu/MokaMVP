package com.cdwx.moka.ui.fragment;

import android.view.View;

import com.cdwx.moka.R;
import com.cdwx.moka.model.MainInfoBean;
import com.cdwx.moka.presenter.MainFragmentPresenter;
import com.cdwx.moka.presenter.impl.MainFragmentPresenterImpl;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by KeKe on 2017/4/11.
 */

public class MainFragment extends BaseFragment<MainFragmentPresenterImpl>
        implements MainFragmentPresenter.View {
    @BindView(R.id.view_pager)
    com.cdwx.moka.widget.AutoScrollViewPager viewPager;
    @BindView(R.id.pageview)
    com.cdwx.moka.widget.PageIndicatorView pageview;

    @Override
    protected void loadData() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initInject() {
    }

    @Override
    public void refreshView(MainInfoBean mainInfoBean) {

    }

    @OnClick({R.id.rl_model, R.id.rl_actor, R.id.rl_anchor, R.id.rl_online})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_model:
                break;
            case R.id.rl_actor:
                break;
            case R.id.rl_anchor:
                break;
            case R.id.rl_online:
                break;
        }
    }
}
