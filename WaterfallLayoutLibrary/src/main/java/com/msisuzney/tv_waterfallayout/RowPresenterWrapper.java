package com.msisuzney.tv_waterfallayout;


import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.PresenterSelector;

/**
 * RowPresenterWrapper
 */
public class RowPresenterWrapper extends PresenterSelector {

    private RowPresenter mRowPresenter;
    private PresenterSelector otherPresenterSelector;

    public RowPresenterWrapper(PresenterSelector blockPresenterSelector) {
        mRowPresenter = new RowPresenter(blockPresenterSelector);
    }

    /**
     * 瀑布流中中不是栏目的选择器，比如加载更多的提示View
     *
     * @param otherPresenterSelector
     */
    public void setOtherPresenterSelector(PresenterSelector otherPresenterSelector) {
        this.otherPresenterSelector = otherPresenterSelector;
    }

    @Override
    public final Presenter getPresenter(Object item) {
        if (item instanceof RowData) {
            return mRowPresenter;
        } else if (otherPresenterSelector != null) {
            return otherPresenterSelector.getPresenter(item);
        }
        return null;
    }

}
