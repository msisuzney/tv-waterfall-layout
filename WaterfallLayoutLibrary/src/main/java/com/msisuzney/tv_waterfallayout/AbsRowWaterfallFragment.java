package com.msisuzney.tv_waterfallayout;

import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.PresenterSelector;

import java.util.List;

/**
 * 以行作为最小单元的瀑布流布局<br/>
 * <strong>行:</strong><br/>
 * 每行都是一个绝对布局{@link android.widget.AbsoluteLayout}，
 * 行的数据格式必须用{@link RowData} 提供大小，以及使用{@link RowData#setBlocks(List)} 设置行中的数据<br/>
 * <strong>行中运营位：</strong><br/>
 * 所有的运营位以绝对位置布局放进<strong>行</strong>中,需要重写 {@link AbsRowWaterfallFragment#initBlockPresenterSelector} 提供行中的运营位的布局位置
 * 运营位必须用{@link BlockData}提供x,y,width,height,以及使用{@link BlockData#setData(Object)} 设置运营位的数据
 * <br/>
 * <br/>
 * 如果瀑布流布局还有其他不是行的布局需要重写{@link AbsRowWaterfallFragment#initOtherPresenterSelector()} ()} 提供其他与<strong>行</strong>同级的布局Presenter
 */
public abstract class AbsRowWaterfallFragment extends AbsWaterfallFragment {


    @Override
    public final ArrayObjectAdapter initAdapter() {
        PresenterSelector blockSelector = initBlockPresenterSelector();
        if (blockSelector == null) {
            throw new RuntimeException("BlockPresenterSelector must not be null");
        }
        PresenterSelector otherPresenterSelector = initOtherPresenterSelector();
        RowPresenterWrapper mSelector = new RowPresenterWrapper(blockSelector);
        mSelector.setOtherPresenterSelector(otherPresenterSelector);

        return new ArrayObjectAdapter(mSelector);
    }

    /**
     * 行中的运营位的选择器，不能为null
     *
     * @return
     */
    protected abstract PresenterSelector initBlockPresenterSelector();

    /**
     * 与行同级，但不是行的选择器
     *
     * @return
     */
    protected PresenterSelector initOtherPresenterSelector() {
        return null;
    }


}
