package com.msisuzney.tv.waterfallayout.presenter;

import com.msisuzney.tv.waterfallayout.R;
import com.msisuzney.tv.waterfallayout.leanback.ItemAlignmentFacet;
import com.msisuzney.tv.waterfallayout.leanback.Presenter;
import com.msisuzney.tv.waterfallayout.leanback.PresenterSelector;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;

import com.msisuzney.tv.waterfallayout.model.AbsoluteLayoutCollection;
import com.msisuzney.tv.waterfallayout.model.AbsoluteLayoutItem;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: chenxin
 * @date: 2019-12-20
 * @email: chenxin7930@qq.com
 */
class AbsoluteLayoutRowPresenter extends Presenter {
    private PresenterSelector blockPresenterSelector;


    public AbsoluteLayoutRowPresenter(PresenterSelector blockPresenterSelector) {
        this.blockPresenterSelector = blockPresenterSelector;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = new AbsoluteLayout(parent.getContext());
        return new ColumnViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Object item) {
        AbsoluteLayoutCollection collection = (AbsoluteLayoutCollection) item;
        ColumnViewHolder columnViewHolder = (ColumnViewHolder) viewHolder;
        AbsoluteLayout parentView = columnViewHolder.getAbsoluteLayout();
        ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(collection.getWidth(), collection.getHeight());
        parentView.setLayoutParams(lp);

        List<AbsoluteLayoutItem> items = collection.getItems();
        if (items != null) {
            for (AbsoluteLayoutItem layoutItem : items) {
                Presenter presenter = blockPresenterSelector.getPresenter(layoutItem.getBean());
                if (presenter != null) {
                    ViewHolder vh = presenter.onCreateViewHolder(parentView);
                    vh.view.setTag(R.id.lb_view_data_tag, layoutItem.getBean());
                    vh.view.setTag(R.id.lb_view_holder_tag, vh);
                    presenter.onBindViewHolder(vh, layoutItem.getBean());
                    AbsoluteLayout.LayoutParams layoutParams = new AbsoluteLayout.LayoutParams(layoutItem.getWidth(),
                            layoutItem.getHeight(), layoutItem.getX(), layoutItem.getY());
                    parentView.addView(vh.view, layoutParams);

                }
            }
        }
    }

    //被RV回收时调用
    @Override
    public void onUnbindViewHolder(ViewHolder viewHolder) {
        ColumnViewHolder columnViewHolder = (ColumnViewHolder) viewHolder;
        AbsoluteLayout parentView = columnViewHolder.getAbsoluteLayout();
        for (int i = 0; i < parentView.getChildCount(); i++) {
            View view = parentView.getChildAt(i);
            Object data = view.getTag(R.id.lb_view_data_tag);
            ViewHolder vh = (ViewHolder) view.getTag(R.id.lb_view_holder_tag);
            Presenter presenter = blockPresenterSelector.getPresenter(data);
            presenter.onUnbindViewHolder(vh);
            view.setTag(R.id.lb_view_holder_tag, null);
            view.setTag(R.id.lb_view_data_tag, null);
        }
        parentView.removeAllViews();
    }


    public final class ColumnViewHolder extends ViewHolder {

        private AbsoluteLayout absoluteLayout;

        public ColumnViewHolder(View view) {
            super(view);
            absoluteLayout = (AbsoluteLayout) view;
        }

        public AbsoluteLayout getAbsoluteLayout() {
            return absoluteLayout;
        }
    }
}
