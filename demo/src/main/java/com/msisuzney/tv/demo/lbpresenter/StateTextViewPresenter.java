package com.msisuzney.tv.demo.lbpresenter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.msisuzney.tv.demo.MyStateChangeObservable;
import com.msisuzney.tv.demo.R;
import com.msisuzney.tv.demo.bean.StateBean;
import com.msisuzney.tv.demo.view.StateTextView;
import com.msisuzney.tv.waterfallayout.leanback.Presenter;

/**
 * @author: chenxin
 * @date: 2019-12-21
 * @email: chenxin7930@qq.com
 */
public class StateTextViewPresenter extends Presenter {

    private MyStateChangeObservable stateChangeObservable;

    public StateTextViewPresenter(MyStateChangeObservable stateChangeObservable) {
        this.stateChangeObservable = stateChangeObservable;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.block_state_text_view, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Object item) {
        if (item instanceof StateBean) {
            StateTextView stateTextView = (StateTextView) viewHolder.view;
            if (stateChangeObservable != null) {
                stateChangeObservable.registerObserver(stateTextView);
            }
        }
    }

    @Override
    public void onUnbindViewHolder(ViewHolder viewHolder) {
        StateTextView stateTextView = (StateTextView) viewHolder.view;
        if (stateChangeObservable != null) {
            stateChangeObservable.unregisterObserver(stateTextView);
        }
    }
}
