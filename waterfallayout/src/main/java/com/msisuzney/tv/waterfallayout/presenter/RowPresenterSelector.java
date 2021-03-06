package com.msisuzney.tv.waterfallayout.presenter;

import com.msisuzney.tv.waterfallayout.leanback.Presenter;
import com.msisuzney.tv.waterfallayout.leanback.PresenterSelector;

import com.msisuzney.tv.waterfallayout.model.ColumnLayoutCollection;
import com.msisuzney.tv.waterfallayout.model.HorizontalLayoutCollection;
/**
 * @author: chenxin
 * @date: 2019-12-20
 * @email: chenxin7930@qq.com
 */
public final class RowPresenterSelector extends PresenterSelector {

    private ColumnLayoutRowPresenter absLayoutColumnLayoutRowPresenter;
    private HorizontalLayoutRowPresenter horizontalLayoutRowPresenter;
    private PresenterSelector otherPresenterSelector;

    public RowPresenterSelector(PresenterSelector blockPresenterSelector) {
        absLayoutColumnLayoutRowPresenter = new ColumnLayoutRowPresenter(blockPresenterSelector);
        horizontalLayoutRowPresenter = new HorizontalLayoutRowPresenter(blockPresenterSelector);
    }


    /**
     * 瀑布流中不是行的选择器，比如加载更多的提示View
     *
     * @param otherPresenterSelector
     */
    public void setOtherPresenterSelector(PresenterSelector otherPresenterSelector) {
        this.otherPresenterSelector = otherPresenterSelector;
    }


    @Override
    public Presenter getPresenter(Object item) {
        if (item instanceof ColumnLayoutCollection) {
            return absLayoutColumnLayoutRowPresenter;
        } else if (item instanceof HorizontalLayoutCollection) {
            return horizontalLayoutRowPresenter;
        } else {
            return otherPresenterSelector.getPresenter(item);
        }
    }
}
