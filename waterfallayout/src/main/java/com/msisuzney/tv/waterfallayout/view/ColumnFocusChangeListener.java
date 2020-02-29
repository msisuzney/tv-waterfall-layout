package com.msisuzney.tv.waterfallayout.view;

/**
 * 可以监听整个栏目焦点变化的接口
 *
 * @author: chenxin
 * @date: 2020-01-06
 */
public interface ColumnFocusChangeListener {

    void onColumnGainFocus();

    void onColumnLoseFocus();
}
