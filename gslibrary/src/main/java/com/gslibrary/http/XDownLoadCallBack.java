package com.gslibrary.http;

import java.io.File;

/*
 * 下载接口回调
 */
public interface XDownLoadCallBack {
    void onstart();

    void onLoading(long total, long current, boolean isDownloading);

    void onSuccess(File result);

    void onFail(String result);
}