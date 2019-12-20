package com.msisuzney.tv_waterfallayout.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import androidx.annotation.Nullable;
import androidx.leanback.R;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * 一种实现焦点换行的View，当View在屏幕右边缘时按下右键，焦点会换行到下一行的第一个View，左边缘同理换行到上一行最后一个View<br/>
 * <p>
 * 使用 {@linkplain FocusLineFeedFrameLayout#setEdgeDistance(int)}
 * 设置距离屏幕边缘多少像素以内的View被认为是可以焦点换行的View<br/>
 * <p>
 * 使用{@linkplain FocusLineFeedFrameLayout#setFocusLineFeed(boolean)}}
 * 设置是否开启焦点换行
 */
public class FocusLineFeedFrameLayout extends FrameLayout {

    private final static String TAG = "FLFFL";
    private ViewGroup rootView;
    private Rect mRootViewRect = new Rect();
    private Rect mTempDrawingRect = new Rect();
    private int currentKeyCode = -1;
    private final int DEFAULT_DISTANCE = 120;
    /**
     * 距离屏幕边缘edgeDistance像素以内的子View被认为是换行View
     */
    private int edgeDistance = DEFAULT_DISTANCE;

    /**
     * 是否开启焦点换行
     */
    private boolean focusLineFeed = true;

    private boolean DEBUG = false;

    public FocusLineFeedFrameLayout(Context context) {
        super(context);
    }

    public FocusLineFeedFrameLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public FocusLineFeedFrameLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }


    private void init(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.FocusLineFeedFrameLayout);
        setFocusLineFeed(a.getBoolean(R.styleable.FocusLineFeedFrameLayout_focusLineFeed, true));
        setEdgeDistance(a.getDimensionPixelSize(R.styleable.FocusLineFeedFrameLayout_edgeDistance, DEFAULT_DISTANCE));
        a.recycle();
    }

    public int getEdgeDistance() {
        return edgeDistance;
    }

    public void setEdgeDistance(int edgeDistance) {
        this.edgeDistance = edgeDistance;
    }

    public boolean isFocusLineFeed() {
        return focusLineFeed;
    }

    public void setFocusLineFeed(boolean focusLineFeed) {
        this.focusLineFeed = focusLineFeed;
    }

    @Override
    public final void getFocusedRect(Rect r) {
        if (focusLineFeed && isFocused()) {
            if (DEBUG) Log.d(TAG, "=========== focus start ==============");
            rootView.getDrawingRect(mRootViewRect);
            if (DEBUG) Log.d(TAG, "focus:true" + ",mRootViewRect:" + mRootViewRect);
            getDrawingRect(mTempDrawingRect);
            if (DEBUG) Log.d(TAG, "focus:true" + ",mTempDrawingRect:" + mTempDrawingRect);
            //转换子View到根布局的坐标系
            rootView.offsetDescendantRectToMyCoords(this, mTempDrawingRect);
            if (DEBUG) Log.d(TAG, "focus:true" + ",my location:" + mTempDrawingRect);
            //判断子View是否满足换行的条件
            if ((mTempDrawingRect.right + edgeDistance) > mRootViewRect.right && currentKeyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
                getDrawingRect(mTempDrawingRect);
                rootView.offsetDescendantRectToMyCoords(this, mTempDrawingRect);
                if (DEBUG) Log.d(TAG, "focus:true" + ",my location2:" + mTempDrawingRect);
                if (DEBUG)
                    Log.d(TAG, "focus:true" + ",measuredWidth:" + getMeasuredWidth() + ",measuredHeight:" + getMeasuredHeight());
                mTempDrawingRect.left = 0 - getMeasuredWidth();
                mTempDrawingRect.right = 0;
                mTempDrawingRect.bottom = mTempDrawingRect.bottom + getMeasuredHeight();
                mTempDrawingRect.top = mTempDrawingRect.top + getMeasuredHeight();
                if (DEBUG) Log.d(TAG, "focus:true" + ",focus rect location:" + mTempDrawingRect);
                //将focus rect的坐标系还原到子View的坐标系
                rootView.offsetRectIntoDescendantCoords(this, mTempDrawingRect);
                r.set(mTempDrawingRect);
            } else if (mTempDrawingRect.left - edgeDistance < mRootViewRect.left && currentKeyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
                getDrawingRect(mTempDrawingRect);
                rootView.offsetDescendantRectToMyCoords(this, mTempDrawingRect);
                mTempDrawingRect.left = mRootViewRect.right;
                mTempDrawingRect.right = mRootViewRect.right + getMeasuredWidth();
                mTempDrawingRect.bottom = mTempDrawingRect.bottom - getMeasuredHeight();
                mTempDrawingRect.top = mTempDrawingRect.top - getMeasuredHeight();
                rootView.offsetRectIntoDescendantCoords(this, mTempDrawingRect);
                r.set(mTempDrawingRect);
            } else {
                super.getFocusedRect(r);
            }
            if (DEBUG) Log.d(TAG, "=========== focus end ==============");
        } else {
            super.getFocusedRect(r);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        currentKeyCode = keyCode;
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        rootView = (ViewGroup) getRootView();
    }

}
