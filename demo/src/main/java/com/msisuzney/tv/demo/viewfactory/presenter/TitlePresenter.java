package com.msisuzney.tv.demo.viewfactory.presenter;

import com.msisuzney.tv.demo.R;
import com.msisuzney.tv.demo.bean.TitleBean;
import com.msisuzney.tv.waterfallayout.leanback.Presenter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * @author: chenxin
 * @date: 2019-12-20
 * @email: chenxin7930@qq.com
 */
public class TitlePresenter extends Presenter {
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.title_view, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Object item) {
        if (viewHolder instanceof MyViewHolder) {
            MyViewHolder vh = (MyViewHolder) viewHolder;
            TitleBean titleBean = (TitleBean) item;
            vh.titleTV.setText(titleBean.getTitle());
        }
    }

    @Override
    public void onUnbindViewHolder(ViewHolder viewHolder) {

    }

    public static class MyViewHolder extends ViewHolder {
        TextView titleTV;

        public MyViewHolder(View view) {
            super(view);
            titleTV = (TextView) view;
        }
    }

}
