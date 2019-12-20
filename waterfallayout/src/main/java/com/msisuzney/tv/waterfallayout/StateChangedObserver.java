package com.msisuzney.tv.waterfallayout;


import androidx.recyclerview.widget.RecyclerView;
/**
 * @author: chenxin
 * @date: 2019-12-20
 * @email: chenxin7930@qq.com
 */
/**
 * 状态变化监听类
 */
public interface StateChangedObserver {

    /**
     * fragment is being paused
     */
    default void onFragmentPause() {
    }

    /**
     * Visibility of the fragment is changed
     *
     * @param isVisible
     */
    default void onFragmentVisibilityChanged(boolean isVisible) {
    }

    /**
     * Callback method to be invoked when RecyclerView's scroll state changes.
     *
     * @param recyclerView The RecyclerView whose scroll state has changed.
     *                     //     * @param newState     The updated scroll state. One of {SCROLL_STATE_IDLE},
     *                     //     *                     { SCROLL_STATE_DRAGGING} or {SCROLL_STATE_SETTLING}.
     */
    default void onScrollStateChanged(RecyclerView recyclerView, int newState) {
    }

}



