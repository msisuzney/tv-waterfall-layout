package com.msisuzney.tv.demo.lbpresenter;

import com.msisuzney.tv.demo.MyStateChangeObservable;
import com.msisuzney.tv.demo.bean.TabBean;
import com.msisuzney.tv.waterfallayout.leanback.Presenter;
import com.msisuzney.tv.waterfallayout.leanback.PresenterSelector;

import com.msisuzney.tv.waterfallayout.OnItemKeyListener;

/**
 * @author: chenxin
 * @date: 2019-12-20
 * @email: chenxin7930@qq.com
 */
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
