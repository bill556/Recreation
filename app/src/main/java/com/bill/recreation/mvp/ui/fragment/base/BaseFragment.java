package com.bill.recreation.mvp.ui.fragment.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bill.recreation.App;
import com.bill.recreation.utils.MyUtils;
import com.bill.recreation.di.component.DaggerFragmentComponent;
import com.bill.recreation.di.component.FragmentComponent;
import com.bill.recreation.di.module.FragmentModule;
import com.bill.recreation.mvp.presenter.base.BasePresenter;
import com.squareup.leakcanary.RefWatcher;

import butterknife.ButterKnife;
import rx.Subscription;

/**
 * @author Bill
 * @version 1.0 2016/11/10
 */
public abstract class BaseFragment<T extends BasePresenter> extends Fragment {

    public FragmentComponent getFragmentComponent() {
        return mFragmentComponent;
    }

    protected FragmentComponent mFragmentComponent;
    protected T mPresenter;

    private View mFragmentView;

    public abstract void initInjector();

    public abstract void initViews(View view);

    public abstract int getLayoutId();

    protected Subscription mSubscription;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragmentComponent = DaggerFragmentComponent.builder()
                .applicationComponent(((App) getActivity().getApplication()).getApplicationComponent())
                .fragmentModule(new FragmentModule(this))
                .build();
        initInjector();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mFragmentView == null) {
            mFragmentView = inflater.inflate(getLayoutId(), container, false);
            ButterKnife.bind(this, mFragmentView);
            initViews(mFragmentView);
        }
        return mFragmentView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = App.getRefWatcher(getActivity());
        refWatcher.watch(this);

        if (mPresenter != null) {
            mPresenter.onDestroy();
        }

        MyUtils.cancelSubscription(mSubscription);
    }
}
