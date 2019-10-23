package com.msisuzney.tv_waterfallayout.presenter;


import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.PresenterSelector;

import com.msisuzney.tv_waterfallayout.bean.AbsLayoutCollection;
import com.msisuzney.tv_waterfallayout.bean.HorizontalLayoutCollection;

/**
 * RowPresenterWrapper
 */
public class RowPresenterWrapper extends PresenterSelector {

    private PresenterSelector rowPresenterSelector;
    private PresenterSelector otherPresenterSelector;

    public RowPresenterWrapper(PresenterSelector blockPresenterSelector) {
        rowPresenterSelector = new RowPresenterSelector(blockPresenterSelector);
    }

    /**
     * 瀑布流中不是栏目的选择器，比如加载更多的提示View
     *
     * @param otherPresenterSelector
     */
    public void setOtherPresenterSelector(PresenterSelector otherPresenterSelector) {
        this.otherPresenterSelector = otherPresenterSelector;
    }

    @Override
    public final Presenter getPresenter(Object item) {
        if (item instanceof AbsLayoutCollection || item instanceof HorizontalLayoutCollection) {
            return rowPresenterSelector.getPresenter(item);
        } else if (otherPresenterSelector != null) {
            return otherPresenterSelector.getPresenter(item);
        }
        return null;
    }

}
