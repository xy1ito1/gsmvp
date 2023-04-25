package com.mvpgs.app;

import com.gslibrary.app.SAppLication;

/*********************************************
 ***       河南坚磐科技电子有限公司        ***
 ***                                       ***
 ***       Created by HC on 2017/4/24.       ***
 *********************************************/

public class MyApplication extends SAppLication {

    @Override
    public void onCreate() {
        super.onCreate();
        setXutilsDebug(true);
    }
}
