package com.mvpgs.activity;

import android.view.View;
import android.widget.ListView;

import com.gslibrary.adapter.CommonAdapter;
import com.gslibrary.adapter.ViewHolder;
import com.gslibrary.base.BaseMvpActivity;
import com.gslibrary.pulltorefresh.Mode;
import com.gslibrary.pulltorefresh.OnRefreshListener2;
import com.gslibrary.pulltorefresh.PullToRefreshBase;
import com.gslibrary.pulltorefresh.PullToRefreshListView;
import com.gslibrary.weight.textview.SuperTextView;
import com.mvpgs.R;
import com.mvpgs.presenter.LoginPresenter;
import com.mvpgs.view.LoginView;

import java.util.ArrayList;
import java.util.List;

/*********************************************
 ***             ***
 ***                                       ***
 ***       Created by HC on 2017/4/24.       ***
 *********************************************/

public class LoginActivity extends BaseMvpActivity<LoginPresenter> implements LoginView {

    private LoginPresenter loginPresenter;

    private SuperTextView bt;
    private PullToRefreshListView listview;
    private List<String> mlist = new ArrayList<String>();
    private CommonAdapter<String> mAdapter;

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void showMessage() {

    }

    @Override
    public void loadData() {
        listview.autoRefresh();
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.main_activity);
        loginPresenter = new LoginPresenter();
    }

    @Override
    public void initView() {
        bt = (SuperTextView) findViewById(R.id.bt);
        listview = (PullToRefreshListView) findViewById(R.id.listview);
        listview.setMode(Mode.BOTH);

        mAdapter = new CommonAdapter<String>(mContext, mlist, R.layout.layout) {
            @Override
            public void convert(ViewHolder helper, String item) {
                helper.setText(R.id.tv, item);
            }
        };
        listview.setAdapter(mAdapter);
    }


    @Override
    public void initListen() {
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPresenter.onBtnClick("郑州", "8e02612c49de6528d80a429fc865faa5");
            }
        });

        listview.setOnRefreshListener(new OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                if(!mlist.isEmpty()){
                    mlist.clear();
                    mAdapter.notifyDataSetChanged();
                }
                loginPresenter.onBtnClick("郑州", "8e02612c49de6528d80a429fc865faa5");
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                loginPresenter.onBtnClick("郑州", "8e02612c49de6528d80a429fc865faa5");
            }
        });

    }

    @Override
    public LoginPresenter initPresenter() {
        return loginPresenter;
    }

    @Override
    public void loginSuccess(List<String> mlist) {
        toastShow("登录成功");
        this.mlist.addAll(mlist);
        mAdapter.notifyDataSetChanged();
        listview.onRefreshComplete();
    }

    @Override
    public void loginFail(String message) {
        toastShow(message);
        listview.onRefreshComplete();
    }
}
