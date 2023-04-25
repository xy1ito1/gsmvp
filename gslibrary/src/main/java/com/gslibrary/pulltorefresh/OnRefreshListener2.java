package com.gslibrary.pulltorefresh;

import android.view.View;

/**
 * An advanced version of the Listener to listen for callbacks to Refresh.
	 * This listener is different as it allows you to differentiate between Pull
	 * Ups, and Pull Downs.
	 * 
	 * @author Chris Banes
	 */
	public interface OnRefreshListener2<V extends View> {
		// TODO These methods need renaming to START/END rather than DOWN/UP

		/**
		 * onPullDownToRefresh will be called only when the user has Pulled from
		 * the start, and released.
		 */
		public void onPullDownToRefresh(final PullToRefreshBase<V> refreshView);

		/**
		 * onPullUpToRefresh will be called only when the user has Pulled from
		 * the end, and released.
		 */
		public void onPullUpToRefresh(final PullToRefreshBase<V> refreshView);

	}