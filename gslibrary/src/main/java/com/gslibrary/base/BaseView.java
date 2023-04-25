package com.gslibrary.base;


public interface BaseView {

    /**
     * 展示进度框
     */
    void showLoading();

    /**
     * 隐藏进度狂
     */
    void dismissLoading();

    /**
     * Toast消息
     */
    void showMessage();

    /**
     * 默认填充数据
     */
    void loadData();

}
