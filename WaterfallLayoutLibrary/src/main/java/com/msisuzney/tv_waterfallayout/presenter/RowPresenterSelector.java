package com.msisuzney.tv_waterfallayout.presenter;

import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.PresenterSelector;

import com.msisuzney.tv_waterfallayout.bean.AbsLayoutCollection;
import com.msisuzney.tv_waterfallayout.bean.HorizontalLayoutCollection;

public class RowPresenterSelector extends PresenterSelector {

    private AbsoluteLayoutRowPresenter absLayoutAbsoluteLayoutRowPresenter;
    private HorizontalLayoutRowPresenter horizontalLayoutRowPresenter;

    public RowPresenterSelector(PresenterSelector blockPresenterSelector) {
        absLayoutAbsoluteLayoutRowPresenter = new AbsoluteLayoutRowPresenter(blockPresenterSelector);
        horizontalLayoutRowPresenter = new HorizontalLayoutRowPresenter(blockPresenterSelector);
    }

    @Override
    public Presenter getPresenter(Object item) {
        if (item instanceof AbsLayoutCollection) {
            return absLayoutAbsoluteLayoutRowPresenter;
        } else if (item instanceof HorizontalLayoutCollection) {
            return horizontalLayoutRowPresenter;
        }
        return null;
    }
}
