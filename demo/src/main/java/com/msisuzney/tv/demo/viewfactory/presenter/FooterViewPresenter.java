package com.msisuzney.tv.demo.viewfactory.presenter;

import com.msisuzney.tv.demo.R;
import com.msisuzney.tv.waterfallayout.leanback.Presenter;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * @author: chenxin
 * @date: 2019-12-20
 * @email: chenxin7930@qq.com
 */
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
