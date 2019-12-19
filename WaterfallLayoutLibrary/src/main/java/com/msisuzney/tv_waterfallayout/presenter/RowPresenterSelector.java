package com.msisuzney.tv_waterfallayout.presenter;

import com.msisuzney.tv_waterfallayout.leanback.Presenter;
import com.msisuzney.tv_waterfallayout.leanback.PresenterSelector;

import com.msisuzney.tv_waterfallayout.model.AbsoluteLayoutCollection;
import com.msisuzney.tv_waterfallayout.model.HorizontalLayoutCollection;

public final class RowPresenterSelector extends PresenterSelector {

    private AbsoluteLayoutRowPresenter absLayoutAbsoluteLayoutRowPresenter;
    private HorizontalLayoutRowPresenter horizontalLayoutRowPresenter;
    private PresenterSelector otherPresenterSelector;

    public RowPresenterSelector(PresenterSelector blockPresenterSelector) {
        absLayoutAbsoluteLayoutRowPresenter = new AbsoluteLayoutRowPresenter(blockPresenterSelector);
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
        if (item instanceof AbsoluteLayoutCollection) {
            return absLayoutAbsoluteLayoutRowPresenter;
        } else if (item instanceof HorizontalLayoutCollection) {
            return horizontalLayoutRowPresenter;
        } else {
            return otherPresenterSelector.getPresenter(item);
        }
    }
}
