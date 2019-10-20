package com.msisuzney.tv_demo.lbpresenter;

import android.support.v17.leanback.widget.Presenter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.msisuzney.tv_waterfallayout.R;


/**
 * Created by xin1.chen
 * on 2019/7/25
 */
public class TitlePresenter extends Presenter {
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.block_title, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Object item) {
        if (viewHolder instanceof MyViewHolder) {
            MyViewHolder vh = (MyViewHolder) viewHolder;
            String title = (String) item;
            vh.titleTV.setText(title);
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
