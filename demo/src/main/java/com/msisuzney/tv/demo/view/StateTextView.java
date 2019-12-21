package com.msisuzney.tv.demo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.msisuzney.tv.demo.MyStateChangedObserver;

/**
 * @author: chenxin
 * @date: 2019-12-21
 * @email: chenxin7930@qq.com
 */
public class StateTextView extends AppCompatTextView implements MyStateChangedObserver {
    public StateTextView(Context context) {
        super(context);
    }

    public StateTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public StateTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            setText("RecyclerView STATE == SCROLL_STATE_IDLE");
        } else if (newState == RecyclerView.SCROLL_STATE_SETTLING) {
            setText("RecyclerView STATE == SCROLL_STATE_SETTLING");
        } else if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
            setText("RecyclerView STATE == SCROLL_STATE_DRAGGING");
        }
    }
}
