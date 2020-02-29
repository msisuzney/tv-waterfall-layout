package com.msisuzney.tv.waterfallayout.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AbsoluteLayout;

import java.util.ArrayList;

/**
 * @author: chenxin
 * @date: 2020-01-06
 */
public class ColumnLayout extends AbsoluteLayout implements ViewTreeObserver.OnGlobalFocusChangeListener {
    public ColumnLayout(Context context) {
        super(context);
    }

    public ColumnLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ColumnLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        getRootView().getViewTreeObserver().addOnGlobalFocusChangeListener(this);
    }

    private ArrayList<ColumnFocusChangeListener> registeredFocusChangedViews = new ArrayList<>();

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getRootView().getViewTreeObserver().removeOnGlobalFocusChangeListener(this);
        registeredFocusChangedViews.clear();
    }

    void addColumnFocusChangeListener(ColumnFocusChangeListener childView) {
        registeredFocusChangedViews.add(childView);
    }

    void removeColumnFocusChangeListener(ColumnFocusChangeListener childView) {
        registeredFocusChangedViews.remove(childView);
    }

    private boolean hasColumnFocus = false;

    @Override
    public void onGlobalFocusChanged(View oldFocus, View newFocus) {
        if (registeredFocusChangedViews.size() == 0) return;
        if (hasFocus()) {
            if (!hasColumnFocus) {
                hasColumnFocus = true;
                for (ColumnFocusChangeListener view : registeredFocusChangedViews) {
                    view.onColumnGainFocus();
                }
            }
        } else {
            if (hasColumnFocus) {
                hasColumnFocus = false;
                for (ColumnFocusChangeListener view : registeredFocusChangedViews) {
                    view.onColumnLoseFocus();
                }
            }

        }
    }

}
