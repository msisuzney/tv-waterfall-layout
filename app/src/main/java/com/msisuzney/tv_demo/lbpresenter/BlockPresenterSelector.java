package com.msisuzney.tv_demo.lbpresenter;

import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.PresenterSelector;

import com.msisuzney.tv_demo.MyStateChangeObservable;
import com.msisuzney.tv_demo.bean.TabBean;
import com.msisuzney.tv_waterfallayout.OnItemKeyListener;


/**
 * 行中的运营位的选择器
 */
public class BlockPresenterSelector extends PresenterSelector {

    private ImageViewPresenter imageViewPresenter;
    private TitlePresenter titlePresenter;
    private ImageViewPresenter2 imageViewPresenter2;

    public BlockPresenterSelector(MyStateChangeObservable observable,
                                  OnItemKeyListener onItemKeyListener) {
        imageViewPresenter = new ImageViewPresenter(onItemKeyListener);
        imageViewPresenter2 = new ImageViewPresenter2(onItemKeyListener);
        titlePresenter = new TitlePresenter();
    }

    @Override
    public Presenter getPresenter(Object item) {
        if (item instanceof String) {
            return titlePresenter;
        } else if (item instanceof TabBean.ResultBean.AbsLayoutListBean) {
            return imageViewPresenter;
        } else if (item instanceof TabBean.ResultBean.HorizontalLayoutListBean) {
            return imageViewPresenter2;
        }
        return null;
    }
}
