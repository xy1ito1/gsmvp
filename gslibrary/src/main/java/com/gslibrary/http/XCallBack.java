package com.gslibrary.http;

import com.gslibrary.utils.LogUtils;

/**
 * 通用网络请求回调
 */
public abstract class XCallBack {

    public final static String Tag = "CallBack";

    //统一性的逻辑处理
    public boolean onResponse(String result) {
        LogUtils.i(Tag, result);
        return false;
    }

    public abstract void onFail(String result) ;

}