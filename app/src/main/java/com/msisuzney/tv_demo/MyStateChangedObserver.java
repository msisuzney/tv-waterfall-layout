package com.msisuzney.tv_demo;


import com.msisuzney.tv_waterfallayout.StateChangedObserver;

/**
 * Created by xin1.chen
 * on 2019/9/9
 */
public interface MyStateChangedObserver extends StateChangedObserver {

    default void showOrDismissDialog(boolean show) {
    }
}
