package com.msisuzney.tv.demo;

import android.os.AsyncTask;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.msisuzney.tv.demo.bean.FooterBean;
import com.msisuzney.tv.demo.bean.TabBean;
import com.msisuzney.tv.demo.bean.TitleBean;
import com.msisuzney.tv.demo.lbpresenter.BlockPresenterSelector;
import com.msisuzney.tv.demo.lbpresenter.FooterViewPresenter;
import com.msisuzney.tv.demo.lbpresenter.TitlePresenter;
import com.msisuzney.tv.waterfallayout.leanback.Presenter;
import com.msisuzney.tv.waterfallayout.leanback.PresenterSelector;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.msisuzney.tv.waterfallayout.RowsFragment;
import com.msisuzney.tv.waterfallayout.OnItemKeyListener;
import com.msisuzney.tv.waterfallayout.StateChangeObservable;
import com.msisuzney.tv.waterfallayout.model.AbsoluteLayoutCollection;
import com.msisuzney.tv.waterfallayout.model.AbsoluteLayoutItem;
import com.msisuzney.tv.waterfallayout.model.HorizontalLayoutCollection;
import com.msisuzney.tv.waterfallayout.model.HorizontalLayoutItem;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
/**
 * @uthor: chenxin
 * @date: 2019-12-20
 * @email: chenxin7930@qq.com
 */
public class WaterfallFragment extends RowsFragment implements OnItemKeyListener {

    //运营位间距 = FOCUS_PADDING * 2（焦点的预留位置）+ COLUMN_ITEM_PADDING * 2 = 48
    public static int COLUMN_ITEM_PADDING = 10;
    //    public static int COLUMN_TITLE_HEIGHT = 100;
    //行左右的margin,实际要扣除运营位间距
    public static int COLUMN_LEFT_RIGHT_MARGIN = 120 - COLUMN_ITEM_PADDING;
    //title布局，没有COLUMN_ITEM_PADDING
//    public static int COLUMN_LEFT_RIGHT_MARGIN2 = 120;
    public static int COLUMN_WIDTH = 1920 - 2 * COLUMN_LEFT_RIGHT_MARGIN; // = 1728

    private MyStateChangeObservable observable;
    private FooterViewPresenter footerViewPresenter = new FooterViewPresenter();
    private TitlePresenter titleViewPresenter = new TitlePresenter();

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
//                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
//                    GlideApp.with(getContext()).resumeRequests();
//                } else {
//                    GlideApp.with(getContext()).pauseRequests();
//                }
            }
        });
        try {
            new LoadDataAsyncTask(this).execute(getContext().getAssets().open("data.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected PresenterSelector initOtherPresenterSelector() {
        return new PresenterSelector() {
            @Override
            public Presenter getPresenter(Object item) {
                if (item instanceof FooterBean) {
                    return footerViewPresenter;
                } else if (item instanceof TitleBean) {
                    return titleViewPresenter;
                }
                return null;
            }
        };
    }

    @Override
    protected StateChangeObservable initStateChangeObservable() {
        observable = new MyStateChangeObservable();
        return observable;
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void onClick(Object item) {
        Toast.makeText(getContext().getApplicationContext(), "click", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onKey(View v, KeyEvent event, Object item) {
        return false;
    }

    private void updateData(TabBean tabBean) {
        //TabBean中的位置大小只是比例，需要把TabBean转换成实际像素

        List<Object> rows = new ArrayList<>();
        for (int i = 0; i < tabBean.getResult().size(); i++) {
            TabBean.ResultBean tabColumn = tabBean.getResult().get(i);
            if (!TextUtils.isEmpty(tabColumn.getColumnTitle())) {//有标题，添加一个标题bean
                rows.add(new TitleBean(tabColumn.getColumnTitle()));
            }
            if (tabColumn.getType() == TabBean.ResultBean.TYPE_HORIZONTAL_LAYOUT) {//是水平滑动布局的栏目
                //宽度固定，根据宽度的比例计算高度
                float gridWH = (float) (COLUMN_WIDTH * 1.0 / tabColumn.getColumns());
                int height = (int) (gridWH * tabColumn.getRows());
                HorizontalLayoutCollection horizontalLayoutCollection = new HorizontalLayoutCollection(ViewGroup.LayoutParams.MATCH_PARENT, height);
                List<HorizontalLayoutItem> items = new ArrayList<>();
                for (int j = 0; j < tabColumn.getHorizontalLayoutList().size(); j++) {
                    HorizontalLayoutItem item = new HorizontalLayoutItem();
                    TabBean.ResultBean.HorizontalLayoutListBean bean = tabColumn.getHorizontalLayoutList().get(j);
                    int w = (int) (gridWH * bean.getW());
                    int h = (int) (gridWH * bean.getH());
                    item.setHeight(h);
                    item.setWidth(w);
                    item.setData(bean);
                    items.add(item);
                }
                horizontalLayoutCollection.setItems(items);
                rows.add(horizontalLayoutCollection);
            } else if (tabColumn.getType() == TabBean.ResultBean.TYPE_ABSOLUTE_LAYOUT) { //是绝对布局的栏目，计算每行中每个运营位的绝对位置
                List<AbsoluteLayoutItem> items = new ArrayList<>();
                //网格的实际宽高
                float gridWH = (float) (COLUMN_WIDTH * 1.0 / tabColumn.getColumns());
                int height = (int) (gridWH * tabColumn.getRows());
                AbsoluteLayoutCollection absoluteLayoutCollection = new AbsoluteLayoutCollection(ViewGroup.LayoutParams.MATCH_PARENT, height);
                for (int j = 0; j < tabColumn.getAbsLayoutList().size(); j++) {
                    TabBean.ResultBean.AbsLayoutListBean block = tabColumn.getAbsLayoutList().get(j);
                    int x = (int) (gridWH * block.getX());
                    int y = (int) (gridWH * block.getY());
                    int w = (int) (gridWH * (block.getW()));
                    int h = (int) (gridWH * (block.getH()));
                    x += COLUMN_LEFT_RIGHT_MARGIN;
                    AbsoluteLayoutItem item = new AbsoluteLayoutItem();
                    item.setX(x);
                    item.setY(y);
                    item.setWidth(w);
                    item.setHeight(h);
                    item.setData(block);
                    items.add(item);
                }
                absoluteLayoutCollection.setItems(items);
                rows.add(absoluteLayoutCollection);
            }
        }


        postRefreshRunnable(() -> {
            if (isAdded()) {
                int size = size();
                addAll(size, rows);
                add(new FooterBean());
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
