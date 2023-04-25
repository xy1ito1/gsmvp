package com.gslibrary.pulltorefresh;

import android.view.View;

/**
 * Simple Listener to listen for any callbacks to Refresh.
 *
 * @author Chris Banes
 */
public interface OnRefreshListener<V extends View> {

    /**
     * onRefresh will be called for both a Pull from start, and Pull from
     * end
     */
    public void onRefresh(final PullToRefreshBase<V> refreshView);

}