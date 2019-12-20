package com.msisuzney.tv_waterfallayout;

import android.view.KeyEvent;
import android.view.View;

public interface OnItemKeyListener {
    void onClick(Object item);

    boolean onKey(View v, KeyEvent event, Object item);
}
