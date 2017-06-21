package com.bill.recreation.mvp.ui.activities;

import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.widget.TextView;

import com.bill.recreation.mvp.ui.activities.base.BaseActivity;
import com.bill.recreation.R;

import butterknife.BindView;

/**
 * @author Bill
 * @version 1.0 2016/11/10
 */
public class AboutActivity extends BaseActivity {

    @BindView(R.id.msg_tv)
    TextView mMsgTv;

    @Override
    public int getLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    public void initInjector() {

    }

    @Override
    public void initViews() {
        mMsgTv.setAutoLinkMask(Linkify.ALL);
        mMsgTv.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
