package com.msisuzney.tv_demo;


import com.msisuzney.tv_waterfallayout.StateChangeObservable;

/**
 * Created by xin1.chen
 * on 2019/9/9
 */
public class MyStateChangeObservable extends StateChangeObservable<MyStateChangedObserver> {
    void notifyShowOrHideDialog(boolean show) {
        for (int i = mObservers.size() - 1; i >= 0; i--) {
            mObservers.get(i).showOrDismissDialog(show);
        }
    }
}
