package com.msisuzney.tv.waterfallayout.presenter;

import com.msisuzney.tv.waterfallayout.R;
import com.msisuzney.tv.waterfallayout.leanback.ArrayObjectAdapter;
import com.msisuzney.tv.waterfallayout.leanback.HorizontalGridView;
import com.msisuzney.tv.waterfallayout.leanback.ItemBridgeAdapter;
import com.msisuzney.tv.waterfallayout.leanback.Presenter;
import com.msisuzney.tv.waterfallayout.leanback.PresenterSelector;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.msisuzney.tv.waterfallayout.model.HorizontalLayoutCollection;
import com.msisuzney.tv.waterfallayout.model.HorizontalLayoutItem;
/**
 * @uthor: chenxin
 * @date: 2019-12-20
 * @email: chenxin7930@qq.com
 */
class HorizontalLayoutRowPresenter extends Presenter {
    private Presenter itemDockPresenter;

    public HorizontalLayoutRowPresenter(PresenterSelector blockPresenterSelector) {
        itemDockPresenter = new Presenter() {
            @Override
            public ViewHolder onCreateViewHolder(ViewGroup parent) {
                FrameLayout dockView = new FrameLayout(parent.getContext());
                return new ViewHolder(dockView);
            }

            @Override
            public void onBindViewHolder(ViewHolder viewHolder, Object item) {
                HorizontalLayoutItem horizontalLayoutItem = (HorizontalLayoutItem) item;
                ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(horizontalLayoutItem.getWidth(), horizontalLayoutItem.getHeight());
                viewHolder.view.setLayoutParams(lp);
                Presenter itemPresenter = blockPresenterSelector.getPresenter(horizontalLayoutItem.getData());
                ViewHolder vh = itemPresenter.onCreateViewHolder((ViewGroup) viewHolder.view);
                itemPresenter.onBindViewHolder(vh, horizontalLayoutItem.getData());
                viewHolder.view.setTag(R.id.lb_view_data_tag, horizontalLayoutItem.getData());
                viewHolder.view.setTag(R.id.lb_view_holder_tag, viewHolder);
                ((ViewGroup) viewHolder.view).addView(vh.view);
            }

            @Override
            public void onUnbindViewHolder(ViewHolder viewHolder) {
                Object data = viewHolder.view.getTag(R.id.lb_view_data_tag);
                ViewHolder vh = (ViewHolder) viewHolder.view.getTag(R.id.lb_view_holder_tag);
                Presenter presenter = blockPresenterSelector.getPresenter(data);
                presenter.onUnbindViewHolder(vh);
                viewHolder.view.setTag(R.id.lb_view_holder_tag, null);
                viewHolder.view.setTag(R.id.lb_view_data_tag, null);
                ((ViewGroup) viewHolder.view).removeAllViews();
            }
        };
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hgv, parent, false);
        return new HorizontalItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Object item) {
        HorizontalLayoutCollection collection = (HorizontalLayoutCollection) item;
        HorizontalItemViewHolder itemViewHolder = (HorizontalItemViewHolder) viewHolder;
        HorizontalGridView horizontalGridView = itemViewHolder.getHorizontalGridView();
        ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(collection.getWidth(), collection.getHeight());
        horizontalGridView.setLayoutParams(lp);
        ArrayObjectAdapter objectAdapter = new ArrayObjectAdapter(itemDockPresenter);
        ItemBridgeAdapter adapter = new ItemBridgeAdapter(objectAdapter);
        horizontalGridView.setAdapter(adapter);
        if (collection.getItems() != null) {
            objectAdapter.addAll(0, collection.getItems());
        }
    }

    @Override
    public void onUnbindViewHolder(ViewHolder viewHolder) {
    }


    public final class HorizontalItemViewHolder extends ViewHolder {

        private HorizontalGridView horizontalGridView;

        public HorizontalItemViewHolder(View view) {
            super(view);
            horizontalGridView = (HorizontalGridView) view;
        }

        public HorizontalGridView getHorizontalGridView() {
            return horizontalGridView;
        }
    }
}