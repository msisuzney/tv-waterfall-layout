package com.msisuzney.tv_demo.lbpresenter;

import com.msisuzney.tv_demo.bean.TitleBean;
import com.msisuzney.tv_waterfallayout.leanback.Presenter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.msisuzney.tv_waterfallayout.R;


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
