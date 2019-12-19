package com.msisuzney.tv_demo.lbpresenter;

import com.msisuzney.tv_waterfallayout.leanback.Presenter;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.msisuzney.tv_waterfallayout.R;

public class FooterViewPresenter extends Presenter {
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.footer_view, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Object item) {

    }

    @Override
    public void onUnbindViewHolder(ViewHolder viewHolder) {

    }
}
