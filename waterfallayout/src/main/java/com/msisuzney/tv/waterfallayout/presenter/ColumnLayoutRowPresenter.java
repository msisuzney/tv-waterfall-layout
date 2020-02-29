package com.msisuzney.tv.waterfallayout.presenter;

import com.msisuzney.tv.waterfallayout.R;
import com.msisuzney.tv.waterfallayout.leanback.Presenter;
import com.msisuzney.tv.waterfallayout.leanback.PresenterSelector;
import com.msisuzney.tv.waterfallayout.model.ColumnLayoutCollection;
import com.msisuzney.tv.waterfallayout.model.ColumnLayoutItem;
import com.msisuzney.tv.waterfallayout.view.ColumnLayout;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;

import java.util.List;


class ColumnLayoutRowPresenter extends Presenter {
    private PresenterSelector blockPresenterSelector;


    ColumnLayoutRowPresenter(PresenterSelector blockPresenterSelector) {
        this.blockPresenterSelector = blockPresenterSelector;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = new ColumnLayout(parent.getContext());
        return new ColumnViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Object item) {
        ColumnLayoutCollection collection = (ColumnLayoutCollection) item;
        ColumnViewHolder columnViewHolder = (ColumnViewHolder) viewHolder;
        AbsoluteLayout parentView = columnViewHolder.getAbsoluteLayout();
        ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(collection.getWidth(), collection.getHeight());
        parentView.setLayoutParams(lp);


        List<ColumnLayoutItem> items = collection.getItems();
        if (items != null) {
            for (ColumnLayoutItem layoutItem : items) {
                Presenter presenter = blockPresenterSelector.getPresenter(layoutItem.getData());
                if (presenter != null) {
                    ViewHolder vh = presenter.onCreateViewHolder(parentView);
                    vh.view.setTag(R.id.lb_view_data_tag, layoutItem.getData());
                    vh.view.setTag(R.id.lb_view_holder_tag, vh);
                    presenter.onBindViewHolder(vh, layoutItem.getData());
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
