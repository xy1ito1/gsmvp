package com.gslibrary.pulltorefresh;

import android.view.View;

/**
 * Listener that allows you to be notified when the user has started or
 * finished a touch event. Useful when you want to append extra UI events
 * (such as sounds). See (
 * {@link PullToRefreshAdapterViewBase#setOnPullEventListener}.
 *
 * @author Chris Banes
 */
public interface OnPullEventListener<V extends View> {

    /**
     * Called when the internal state has been changed, usually by the user
     * pulling.
     *
     * @param refreshView - View which has had it's state change.
     * @param state       - The new state of View.
     * @param direction   - One of {@link Mode#PULL_FROM_START} or
     *                    {@link Mode#PULL_FROM_END} depending on which direction
     *                    the user is pulling. Only useful when <var>state</var> is
     *                    {@link State#PULL_TO_REFRESH} or
     *                    {@link State#RELEASE_TO_REFRESH}.
     */
    public void onPullEvent(final PullToRefreshBase<V> refreshView, State state, Mode direction);

}