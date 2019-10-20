package com.msisuzney.tv_demo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.PresenterSelector;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.msisuzney.tv_demo.bean.TabBean;
import com.msisuzney.tv_demo.lbpresenter.BlockPresenterSelector;
import com.msisuzney.tv_waterfallayout.AbsRowWaterfallFragment;
import com.msisuzney.tv_waterfallayout.AbsWaterfallFragment;
import com.msisuzney.tv_waterfallayout.BlockData;
import com.msisuzney.tv_waterfallayout.OnItemKeyListener;
import com.msisuzney.tv_waterfallayout.RowData;
import com.msisuzney.tv_waterfallayout.StateChangeObservable;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class WaterfallFragment extends AbsRowWaterfallFragment implements OnItemKeyListener {

    //对应xml文件中的给焦点预留的padding
    private static int FOCUS_PADDING = 6;
    //色块间距 = FOCUS_PADDING * 2（焦点的预留位置）+ COLUMN_ITEM_PADDING * 2 = 48
    public static int COLUMN_ITEM_PADDING = 18;
    public static int COLUMN_TITLE_HEIGHT = 100;
    //栏目左右的margin,实际要扣除色块间距
    public static int COLUMN_LEFT_RIGHT_MARGIN = 120 - FOCUS_PADDING - COLUMN_ITEM_PADDING;
    //视频栏目,title，没有COLUMN_ITEM_PADDING
    public static int COLUMN_LEFT_RIGHT_MARGIN2 = 120 - FOCUS_PADDING;
    private static int COLUMN_WIDTH = 1920 - 2 * COLUMN_LEFT_RIGHT_MARGIN; // = 1728

    //    private int tTotalPages = 4;
    private MyStateChangeObservable observable;

    @Override
    public PresenterSelector initBlockPresenterSelector() {
        return new BlockPresenterSelector(observable, this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    GlideApp.with(getContext()).resumeRequests();
                } else {
                    GlideApp.with(getContext()).pauseRequests();
                }
            }
        });
    }

    @Override
    protected StateChangeObservable initStateChangeObservable() {
        observable = new MyStateChangeObservable();
        return observable;
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            new LoadDataAsyncTask(this).execute(getContext().getAssets().open("tab1.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //    @Override
//    protected PresenterSelector initOtherPresenterSelector() {
//        return new PresenterSelector() {
//            @Override
//            public Presenter getPresenter(Object item) {
//                if (item instanceof LoadingBean) {
//                    return new LoadingViewPresenter(null);
//                }
//                return null;
//            }
//        };
//    }


    @Override
    public void onClick(Object item) {

    }

    @Override
    public boolean onKey(View v, KeyEvent event, Object item) {
        return false;
    }

    private void updateData(TabBean tabBean) {
        //TabColumns中的位置大小只是比例，需要把TabColumn转换成实际像素ColumnData

        List<RowData> columnDataList = new ArrayList<>();
        //计算每个栏目中每个色块的绝对位置
        for (int i = 0; i < tabBean.getResult().size(); i++) {
            TabBean.ResultBean tabColumn = tabBean.getResult().get(i);
            RowData columnData = new RowData();
            List<BlockData> blockDataList = new ArrayList<>();
            //网格的实际宽高
            float gridWH = (float) (COLUMN_WIDTH * 1.0 / tabColumn.getColumns());
            int height = (int) (gridWH * tabColumn.getRows());

            if (!TextUtils.isEmpty(tabColumn.getColumnTitle())) {
                height += COLUMN_TITLE_HEIGHT;
                BlockData blockData = new BlockData();
                blockData.setData(tabColumn.getColumnTitle());
                blockData.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
                blockData.setHeight(COLUMN_TITLE_HEIGHT);
                blockData.setX(COLUMN_LEFT_RIGHT_MARGIN2);
                blockData.setY(COLUMN_ITEM_PADDING);
                blockDataList.add(blockData);
            }
            for (int j = 0; j < tabColumn.getBlockList().size(); j++) {
                TabBean.ResultBean.BlockListBean block = tabColumn.getBlockList().get(j);
                int x = (int) (gridWH * block.getX());

                int y = (int) (gridWH * block.getY());//+ block.getTopOffset();
                if (!TextUtils.isEmpty(tabColumn.getColumnTitle())) {
                    y += COLUMN_TITLE_HEIGHT;
                }
                int w = (int) (gridWH * (block.getW()));
                int h = (int) (gridWH * (block.getH()));
                x += COLUMN_LEFT_RIGHT_MARGIN;


                BlockData blockData = new BlockData();
                blockData.setX(x);
                blockData.setY(y);
                blockData.setWidth(w);
                blockData.setHeight(h);
                blockData.setData(block);
                blockDataList.add(blockData);
            }

            columnData.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
            columnData.setHeight(height);
            columnData.setBlocks(blockDataList);
            columnDataList.add(columnData);
        }


        postRefreshRunnable(() -> {
            if (isAdded()) {
                int size = size();
                addAll(size, columnDataList);
            }
        });
    }

    private static class LoadDataAsyncTask extends AsyncTask<InputStream, Void, TabBean> {

        private WeakReference<WaterfallFragment> ref;

        public LoadDataAsyncTask(WaterfallFragment waterfallFragment) {
            super();
            ref = new WeakReference<WaterfallFragment>(waterfallFragment);
        }

        @Override
        protected TabBean doInBackground(InputStream... inputStreams) {
            try {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStreams[0]);
                TabBean tabBean = new Gson().fromJson(inputStreamReader, TabBean.class);
                return tabBean;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(TabBean tabBean) {
            super.onPostExecute(tabBean);
            WaterfallFragment wf = ref.get();
            if (wf != null && tabBean != null) {
                wf.updateData(tabBean);
            }
        }
    }
}
