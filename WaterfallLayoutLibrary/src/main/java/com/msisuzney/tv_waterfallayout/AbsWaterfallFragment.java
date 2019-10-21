package com.msisuzney.tv_waterfallayout;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v17.leanback.R;
import android.support.v17.leanback.widget.*;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collection;


public abstract class AbsWaterfallFragment extends Fragment {

    public static final String TAG = "AbsWaterfallFragment";
    private VerticalGridView mVerticalGridView;
    private StateChangeObservable stateChangeObservable;
    private int leftPadding;
    private int topPadding;
    private int rightPadding;
    private int bottomPadding;
    private ArrayObjectAdapter mAdapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        stateChangeObservable = initStateChangeObservable();
    }

    @Nullable
    @Override
    public final View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_waterfall, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAdapter = initAdapter();
        if (mAdapter == null) {
            throw new NullPointerException("mAdapter must not be null");
        }
        mVerticalGridView = view.findViewById(R.id.vgv);
        mVerticalGridView.setPadding(leftPadding, topPadding, rightPadding, bottomPadding);
        if (mOnRowSelectedListener != null) {
            mVerticalGridView.setOnChildViewHolderSelectedListener(mOnRowSelectedListener);
        }
        mVerticalGridView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                stateChangeObservable.onScrollStateChanged(recyclerView, newState);
            }
        });
        mVerticalGridView.setItemAnimator(null);

        ItemBridgeAdapter itemBridgeAdapter = new ItemBridgeAdapter(mAdapter);
        mVerticalGridView.setAdapter(itemBridgeAdapter);
    }

    protected StateChangeObservable initStateChangeObservable() {
        return new StateChangeObservable<StateChangedObserver>(){};
    }


    @Override
    public void onResume() {
        super.onResume();
        if (isVisible) {//第一个Fragment加载时或者现加载Fragment时setUserVisibleHint在onAttach之前调用的，保存可见状态到onResume时通知
            Log.d(TAG, "visible@" + hashCode() + "," + isVisible);
            stateChangeObservable.notifyFragmentVisibilityChanged(isVisible);
        }
    }

    /**
     * Fragment是否可见
     */
    private boolean isVisible;

    //onAttach之前或者Fragment已经创建好时可见调用
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisible = isVisibleToUser;
        if (isResumed()) {//onCreateView执行后mSelector!= null,才开始监听
            Log.d(TAG, "visible@" + hashCode() + "," + isVisibleToUser);
            stateChangeObservable.notifyFragmentVisibilityChanged(isVisible);
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        stateChangeObservable.notifyFragmentPause();
    }

    /**
     * 设置RecyclerView的padding
     *
     * @param left
     * @param top
     * @param right
     * @param bottom
     */
    protected void setPadding(int left, int top, int right, int bottom) {
        this.leftPadding = left;
        this.topPadding = top;
        this.rightPadding = right;
        this.bottomPadding = bottom;
    }


    /**
     * 有多个View更新或者获取时，使用runnable封装，保证操作按顺序进行
     *
     * @param runnable
     */
    protected void postRefreshRunnable(Runnable runnable) {
        mVerticalGridView.post(runnable);
    }



    private OnRowSelectedListener mOnRowSelectedListener;

    /**
     * Sets an item selection listener.
     */
    public void setOnRowSelectedListener(OnRowSelectedListener listener) {
        mOnRowSelectedListener = listener;
    }


    /**
     * Sets the object adapter for the fragment.
     */
    public abstract ArrayObjectAdapter initAdapter();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        stateChangeObservable.unregisterAll();
    }

    //===ArrayObjectAdapter中的方法===

    protected void addAll(int index, Collection objects) {
        mAdapter.addAll(index, objects);
    }

    protected void remove(Object item) {
        mAdapter.remove(item);
    }

    protected int size() {
        return mAdapter.size();
    }

    protected void add(Object item) {
        mAdapter.add(item);
    }

    protected void replace(int position, Object item) {
        mAdapter.replace(position, item);
    }

    protected void add(int index, Object item) {
        mAdapter.add(index, item);
    }

    protected int indexOf(Object item) {
        return mAdapter.indexOf(item);
    }

    protected void notifyArrayItemRangeChanged(int positionStart, int itemCount) {
        mAdapter.notifyArrayItemRangeChanged(positionStart, itemCount);
    }

    protected void clear() {
        mAdapter.clear();
    }

    protected void addOnScrollListener(RecyclerView.OnScrollListener scrollChangeListener) {
        mVerticalGridView.addOnScrollListener(scrollChangeListener);
    }

    protected void scrollToPosition(int position) {
        mVerticalGridView.smoothScrollToPosition(position);
    }

    protected int removeItems(int position, int count) {
        return mAdapter.removeItems(position, count);
    }

}
