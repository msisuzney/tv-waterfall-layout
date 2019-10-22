package com.msisuzney.tv_waterfallayout.presenter;

import android.support.v17.leanback.R;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.HorizontalGridView;
import android.support.v17.leanback.widget.ItemBridgeAdapter;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.PresenterSelector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.msisuzney.tv_waterfallayout.bean.HorizontalLayoutCollection;

import java.util.ArrayList;
import java.util.List;

public class HorizontalLayoutRowPresenter extends Presenter {
    private PresenterSelector blockPresenterSelector;

    public HorizontalLayoutRowPresenter(PresenterSelector blockPresenterSelector) {
        this.blockPresenterSelector = blockPresenterSelector;
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
        ArrayObjectAdapter objectAdapter = new ArrayObjectAdapter(blockPresenterSelector);
        ItemBridgeAdapter adapter = new ItemBridgeAdapter(objectAdapter);
        horizontalGridView.setAdapter(adapter);
        List<Object> objects = new ArrayList<>();
        for (int i = 0; i < collection.getItems().size(); i++) {
            objects.add(collection.getItems().get(i).getData());
        }
        objectAdapter.addAll(0, objects);
    }

    @Override
    public void onUnbindViewHolder(ViewHolder viewHolder) {
        HorizontalItemViewHolder itemViewHolder = (HorizontalItemViewHolder) viewHolder;
//        itemViewHolder.horizontalGridView
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