package com.msisuzney.tv.waterfallayout;

import android.view.KeyEvent;
import android.view.View;
/**
 * @author: chenxin
 * @date: 2019-12-20
 * @email: chenxin7930@qq.com
 */
public interface OnItemKeyListener {
    void onClick(Object item);

    boolean onKey(View v, KeyEvent event, Object item);
}
