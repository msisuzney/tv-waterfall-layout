package com.msisuzney.tv_demo.lbpresenter;

import android.support.v17.leanback.widget.Presenter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.msisuzney.tv_demo.WaterfallFragment;
import com.msisuzney.tv_demo.bean.LoadingBean;
import com.msisuzney.tv_waterfallayout.R;


/**
 * 每个Fragment对应一个LoadingView
 * Created by xin1.chen
 * on 2019/7/29
 */
public class LoadingViewPresenter extends Presenter {

    private View mLoadingView;

    public LoadingViewPresenter(View mLoadingView) {
        this.mLoadingView = mLoadingView;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.block_loading_state, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Object item) {
        if (viewHolder instanceof MyViewHolder) {
            MyViewHolder vh = (MyViewHolder) viewHolder;
            LoadingBean loadingBean = (LoadingBean) item;
            ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 200);
            lp.topMargin = WaterfallFragment.COLUMN_ITEM_PADDING;
            vh.view.setLayoutParams(lp);
            vh.textView.setText(loadingBean.getContent());
        }
    }

    @Override
    public void onUnbindViewHolder(ViewHolder viewHolder) {

    }

    public static class MyViewHolder extends ViewHolder {

        TextView textView;

        public MyViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.text);
        }
    }

}
