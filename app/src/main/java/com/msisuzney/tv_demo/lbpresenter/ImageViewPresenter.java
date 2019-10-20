package com.msisuzney.tv_demo.lbpresenter;

import android.graphics.Color;
import android.support.v17.leanback.widget.Presenter;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.msisuzney.tv_demo.GlideApp;
import com.msisuzney.tv_demo.WaterfallFragment;
import com.msisuzney.tv_demo.bean.TabBean;
import com.msisuzney.tv_waterfallayout.OnItemKeyListener;
import com.msisuzney.tv_waterfallayout.R;

import java.util.LinkedList;

public class ImageViewPresenter extends Presenter {

    private static LinkedList<MyViewHolder> sViewHolderPool = new LinkedList<>();

    private static Interpolator sOvershootInterpolator = new OvershootInterpolator();
    private static RoundedCorners roundedCorners = new RoundedCorners(8);
    private static RequestOptions options = RequestOptions.bitmapTransform(roundedCorners);

    private OnItemKeyListener onItemKeyListener;

    public ImageViewPresenter(OnItemKeyListener onItemKeyListener) {
        this.onItemKeyListener = onItemKeyListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent) {
        MyViewHolder viewHolder = sViewHolderPool.peekFirst();
        if (viewHolder == null) {
            return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.block_image, parent, false));
        } else {
            return sViewHolderPool.removeFirst();
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Object item) {
        if (viewHolder instanceof MyViewHolder) {
            TabBean.ResultBean.BlockListBean imageBean = (TabBean.ResultBean.BlockListBean) item;
            MyViewHolder vh = (MyViewHolder) viewHolder;
            View containerView = vh.view;
            containerView.setOnKeyListener((v, keyCode, event) -> {
                if (onItemKeyListener.onKey(v, event, imageBean)) {
                    return true;
                }
                return false;
            });
            final ImageView imageView = vh.imageView;
            final FrameLayout imageDock = vh.imageDock;
            FrameLayout.LayoutParams imageDockLp = (FrameLayout.LayoutParams) imageDock.getLayoutParams();
            imageDockLp.setMargins(WaterfallFragment.COLUMN_ITEM_PADDING, WaterfallFragment.COLUMN_ITEM_PADDING,
                    WaterfallFragment.COLUMN_ITEM_PADDING, WaterfallFragment.COLUMN_ITEM_PADDING);
            imageDock.setLayoutParams(imageDockLp);
            GlideApp.with(imageView)
                    .load(R.drawable.tu)
                    .apply(options)
                    .into(imageView);
            containerView.setOnFocusChangeListener((v, hasFocus) -> {
                if (hasFocus) {
                    imageDock.setBackgroundColor(Color.RED);
                    v.animate().scaleX(1.1f).scaleY(1.1f).setInterpolator(sOvershootInterpolator).setDuration(150).start();
                    v.bringToFront();
                } else {
                    imageDock.setBackgroundResource(0);
                    v.animate().scaleX(1.0f).scaleY(1.0f).setDuration(150).start();
                }
            });
            if (onItemKeyListener != null) {
                containerView.setOnClickListener(v -> {
                    onItemKeyListener.onClick(imageBean);
                });
            }
        }
    }


    @Override
    public void onUnbindViewHolder(ViewHolder viewHolder) {
        if (viewHolder instanceof MyViewHolder) {
            MyViewHolder myViewHolder = (MyViewHolder) viewHolder;
            myViewHolder.view.setOnKeyListener(null);
            myViewHolder.view.setOnFocusChangeListener(null);
            Glide.with(myViewHolder.imageView).clear(myViewHolder.imageView);
            sViewHolderPool.addLast(myViewHolder);
        }
    }


    public static class MyViewHolder extends ViewHolder {

        public ImageView imageView;
        public FrameLayout imageDock;

        public MyViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.image);
            imageDock = view.findViewById(R.id.image_dock);

        }
    }


}
