package com.gslibrary.app;

import android.app.Application;

import org.xutils.x;

public class SAppLication extends Application {
    private Application mContext;

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = this;
        x.Ext.init(this);
    }

    public Application getmContext() {
        return mContext;
    }

    public void setXutilsDebug(boolean isDebug) {
        x.Ext.setDebug(isDebug);
    }
}
