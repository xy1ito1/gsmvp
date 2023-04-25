package com.mvpgs.presenter;

import com.gslibrary.base.BasePresenter;
import com.gslibrary.http.XutilsHttp;
import com.mvpgs.utils.MyCallBack;
import com.mvpgs.utils.RMPparams;
import com.mvpgs.utils.UrlConston;
import com.mvpgs.view.LoginView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*********************************************
 ***       河南坚磐科技电子有限公司        ***
 ***                                       ***
 ***       Created by HC on 2017/4/24.       ***
 *********************************************/

public class LoginPresenter extends BasePresenter<LoginView> {

    public void onBtnClick(String username, String password) {
        sendHttp(UrlConston.url, new RMPparams().getLogin(username, password));
    }

    public void sendHttp(String url, Map<String, String> params) {
        XutilsHttp.getInstance().get(url, params, new MyCallBack() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                        List<String> mlist = new ArrayList<String>();
                        for (int i = 0; i < 10; i++) {
                            mlist.add("" + i);
                        }
                        mView.loginSuccess(mlist);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFail(String result) {
                mView.loginFail(result);
            }


        });
    }

    @Override
    public void onStart() {
        mView.loadData();
    }
}
