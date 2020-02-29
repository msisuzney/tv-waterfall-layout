package com.msisuzney.tv.demo.viewfactory.presenter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.msisuzney.tv.demo.R;
import com.msisuzney.tv.waterfallayout.leanback.Presenter;

/**
 * @author: chenxin
 * @date: 2020-02-29
 * @email: chenxin7930@qq.com
 */
public class ColumnFocusChangeListenerTextViewPresenter extends Presenter {
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.block_focus_state_view, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Object item) {

    }

    @Override
    public void onUnbindViewHolder(ViewHolder viewHolder) {

    }
}
