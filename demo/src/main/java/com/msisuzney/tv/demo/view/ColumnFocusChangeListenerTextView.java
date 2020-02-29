package com.msisuzney.tv.demo.view;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.msisuzney.tv.waterfallayout.view.ColumnFocusChangeListener;

/**
 * @author: chenxin
 * @date: 2020-02-29
 * @email: chenxin7930@qq.com
 */
public class ColumnFocusChangeListenerTextView extends AppCompatTextView implements ColumnFocusChangeListener {
    public ColumnFocusChangeListenerTextView(Context context) {
        super(context);
    }

    public ColumnFocusChangeListenerTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ColumnFocusChangeListenerTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onColumnGainFocus() {
        setText("the column has gained focus");
        textViewMarquee(this, true);
    }

    @Override
    public void onColumnLoseFocus() {
        setText("the column lost focus");
        textViewMarquee(this, false);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        attachToColumnLayout(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        detachFromColumnLayout(this);
    }

    static void textViewMarquee(TextView view, boolean enable) {
        if (enable) {
            view.setMaxLines(1);
            view.setTextColor(Color.RED);
            view.setEllipsize(TextUtils.TruncateAt.MARQUEE);
            view.setMarqueeRepeatLimit(-1);
            view.setSelected(true);
        } else {
            view.setMaxLines(1);
            view.setEllipsize(TextUtils.TruncateAt.END);
            view.setTextColor(Color.BLACK);
        }
    }
}
