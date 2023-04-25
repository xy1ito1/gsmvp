package com.mvpgs.view;

import com.gslibrary.base.BaseView;

import java.util.List;

/*********************************************
 ***       河南坚磐科技电子有限公司        ***
 ***                                       ***
 ***       Created by HC on 2017/4/24.       ***
 *********************************************/

public interface LoginView extends BaseView {

    void loginSuccess(List<String> mlist);

    void loginFail(String message);

}
