package com.gslibrary.base;

/**
 * basePresenter类
 * @param <T>
 */
public abstract class BasePresenter<T> {
    public T mView;

    public void attach(T mView) {
        this.mView = mView;
        onStart();
    }

    public void dettach() {
        mView = null;
    }

    /**
     * 开始填充数据
     */
    public abstract void onStart();

}