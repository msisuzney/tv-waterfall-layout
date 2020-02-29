package com.msisuzney.tv.waterfallayout.view;

import android.view.View;
import android.view.ViewParent;

/**
 * 可以监听整个栏目焦点变化的接口
 *
 * @author: chenxin
 * @date: 2020-01-06
 */
public interface ColumnFocusChangeListener {

    void onColumnGainFocus();

    void onColumnLoseFocus();

    /*

    ViewRootImpl.java
    @Override
    public ViewParent getParent() {
        return null;
    }

     */
    default void attachToColumnLayout(View me) {
        ViewParent parent = me.getParent();
        if (parent instanceof ColumnLayout) {
            ColumnLayout columnLayout = (ColumnLayout) parent;
            columnLayout.addColumnFocusChangeListener(this);
        } else {
            while (parent != null) {
                parent = parent.getParent();
                if (parent instanceof ColumnLayout) {
                    break;
                }
            }
            if (parent != null) {
                ColumnLayout columnLayout = (ColumnLayout) parent;
                columnLayout.addColumnFocusChangeListener(this);
            }
        }
    }

    default void detachFromColumnLayout(View me) {
        ViewParent parent = me.getParent();
        if (parent instanceof ColumnLayout) {
            ColumnLayout columnLayout = (ColumnLayout) parent;
            columnLayout.removeColumnFocusChangeListener(this);
        } else {
            while (parent != null) {
                parent = parent.getParent();
                if (parent instanceof ColumnLayout) {
                    break;
                }
            }
            if (parent != null) {
                ColumnLayout columnLayout = (ColumnLayout) parent;
                columnLayout.removeColumnFocusChangeListener(this);
            }
        }
    }
}
