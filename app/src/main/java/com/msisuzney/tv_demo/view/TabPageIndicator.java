/*
 * Copyright (C) 2011 The Android Open Source Project
 * Copyright (C) 2011 Jake Wharton
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.msisuzney.tv_demo.view;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.msisuzney.tv_waterfallayout.R;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * This widget implements the dynamic action bar tab behavior that can change
 * across different configurations or circumstances.
 */
public class TabPageIndicator extends HorizontalScrollView implements PageIndicator {
    /**
     * Title text used when no title is provided by the adapter.
     */
    private static final CharSequence EMPTY_TITLE = "";

    /**
     * Interface for a callback when the selected tab has been reselected.
     */
    public interface OnTabReselectedListener {
        /**
         * Callback when the selected tab has been reselected.
         *
         * @param position Position of the current center item.
         */
        void onTabReselected(int position);
    }

    public interface OnTabFocusChangeListener {
        /**
         * Callback when tab focus change.
         *
         * @param view
         * @param hasFocus
         */
        public void onFocusChange(View view, boolean hasFocus);
    }

    private Runnable mTabSelector;
    private OnKeyListener onTabKeyListener;


    private final OnClickListener mTabClickListener = new OnClickListener() {
        public void onClick(View view) {
            TabView tabView = (TabView) view;
            final int oldSelected = mViewPager.getCurrentItem();
            final int newSelected = tabView.getIndex();
            mViewPager.setCurrentItem(newSelected);
            if (oldSelected == newSelected && mTabReselectedListener != null) {
                mTabReselectedListener.onTabReselected(newSelected);
            }
        }
    };
    private final OnFocusChangeListener mTabFocusChangeListener = new OnFocusChangeListener() {

        @Override
        public void onFocusChange(View view, boolean hasFocus) {
            if (hasFocus) {
                TabView tabView = (TabView) view;
                final int oldSelected = mViewPager.getCurrentItem();
                final int newSelected = tabView.getIndex();
                if (oldSelected != newSelected) {
                    mViewPager.setCurrentItem(newSelected);
                }
                if (oldSelected == newSelected && mTabReselectedListener != null) {
                    mTabReselectedListener.onTabReselected(newSelected);
                }
            }
            if (mOnTabFocusChangeListener != null) {
                mOnTabFocusChangeListener.onFocusChange(view, hasFocus);
            }

        }

    };

    private final LinearLayout mTabLayout;

    private ViewPager mViewPager;
    private ViewPager.OnPageChangeListener mListener;

    private int mMaxTabWidth;
    private int mSelectedTabIndex;

    private OnTabReselectedListener mTabReselectedListener;
    private OnTabFocusChangeListener mOnTabFocusChangeListener;

    public TabPageIndicator(Context context) {
        this(context, null);
    }

    public TabPageIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        setHorizontalScrollBarEnabled(false);

        mTabLayout = new LinearLayout(context);
        addView(mTabLayout, new LayoutParams(MATCH_PARENT, MATCH_PARENT));
    }

    public void setOnTabReselectedListener(OnTabReselectedListener listener) {
        mTabReselectedListener = listener;
    }

    public void setOnTabFocusChangeListener(OnTabFocusChangeListener listener) {
        mOnTabFocusChangeListener = listener;
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        final boolean lockedExpanded = widthMode == MeasureSpec.EXACTLY;
        setFillViewport(lockedExpanded);

        final int childCount = mTabLayout.getChildCount();
        if (childCount > 1 && (widthMode == MeasureSpec.EXACTLY || widthMode == MeasureSpec.AT_MOST)) {
            if (childCount > 2) {
                mMaxTabWidth = (int) (MeasureSpec.getSize(widthMeasureSpec) * 0.4f);
            } else {
                mMaxTabWidth = MeasureSpec.getSize(widthMeasureSpec) / 2;
            }
        } else {
            mMaxTabWidth = -1;
        }

        final int oldWidth = getMeasuredWidth();
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        final int newWidth = getMeasuredWidth();

        if (lockedExpanded && oldWidth != newWidth) {
            // Recenter the tab display if we're at a new (scrollable) size.
            setCurrentItem(mSelectedTabIndex);
        }
    }

    private void animateToTab(final int position) {
        final View tabView = mTabLayout.getChildAt(position);
        if (mTabSelector != null) {
            removeCallbacks(mTabSelector);
        }
        mTabSelector = new Runnable() {
            public void run() {
//                int tabViewLeft = tabView.getLeft();
//                int halfWidth = getWidth() / 2;
                final int scrollX = tabView.getLeft() + tabView.getWidth() / 2 - getWidth() / 2;
                smoothScrollTo(scrollX, 0);
                mTabSelector = null;
            }
        };
        post(mTabSelector);
    }

    public void setOnTabKeyListener(OnKeyListener onTabKeyListener) {
        this.onTabKeyListener = onTabKeyListener;
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (mTabSelector != null) {
            // Re-post the selector we saved
            post(mTabSelector);
        }
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mTabSelector != null) {
            removeCallbacks(mTabSelector);
        }
    }


    private void addTab(int index, int totalTabView, CharSequence text) {
        final TabView tabView = new TabView(getContext(), text);
        tabView.mIndex = index;
        tabView.setOnClickListener(mTabClickListener);
//        tabView.setText(text);
        LinearLayout.LayoutParams ll = new LinearLayout.LayoutParams(WRAP_CONTENT, MATCH_PARENT);
        int lfmargin = 12;
        ll.setMargins(lfmargin, 0, lfmargin, 0);

        if (index == 0) {
            tabView.setOnKeyListener(new OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {//第一个TabView消耗掉左键
                        return true;
                    }
                    return false;
                }
            });
        } else if (index == totalTabView - 1) {//最后一个TabView，消耗掉右键，避免焦点传给Bar
            tabView.setOnKeyListener(new OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
                        return true;
                    }
                    return false;
                }
            });
        }
        mTabLayout.addView(tabView, ll);
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {
        if (mListener != null) {
            mListener.onPageScrollStateChanged(arg0);
        }
    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
        if (mListener != null) {
            mListener.onPageScrolled(arg0, arg1, arg2);
        }
    }

    @Override
    public void onPageSelected(int arg0) {
        setCurrentItem(arg0);
        if (mListener != null) {
            mListener.onPageSelected(arg0);
        }
    }

    @Override
    public void setViewPager(ViewPager view) {
        if (mViewPager == view) {
            return;
        }
        if (mViewPager != null) {
            mViewPager.setOnPageChangeListener(null);
        }
        final PagerAdapter adapter = view.getAdapter();
        if (adapter == null) {
            throw new IllegalStateException("ViewPager does not have adapter instance.");
        }
        mViewPager = view;
        view.addOnPageChangeListener(this);
        notifyDataSetChanged();
    }

    public void notifyDataSetChanged() {
        mTabLayout.removeAllViews();
        PagerAdapter adapter = mViewPager.getAdapter();
        final int count = adapter.getCount();
        for (int i = 0; i < count; i++) {
            CharSequence title = adapter.getPageTitle(i);
            if (title == null) {
                title = EMPTY_TITLE;
            }
            addTab(i, count, title);
        }
        if (mSelectedTabIndex > count) {
            mSelectedTabIndex = count - 1;
        }
        setCurrentItem(mSelectedTabIndex);
        requestLayout();
    }

    @Override
    public void setViewPager(ViewPager view, int initialPosition) {
        setViewPager(view);
        setCurrentItem(initialPosition);
    }

    public int getSelectedTabIndex() {
        return mSelectedTabIndex;
    }

    @Override
    public void setCurrentItem(int item) {
        if (mViewPager == null) {
            throw new IllegalStateException("ViewPager has not been bound.");
        }
        mSelectedTabIndex = item;
        mViewPager.setCurrentItem(item);

        final int tabCount = mTabLayout.getChildCount();
        for (int i = 0; i < tabCount; i++) {
            final TabView child = (TabView) mTabLayout.getChildAt(i);
            final boolean isSelected = (i == item);
            if (isSelected) {
                child.setTextViewSelected(true);
                animateToTab(item);
            } else {
                child.setTextViewSelected(false);
            }
        }
    }

    public void setCurrentItemFocus(int item) {
        if (mViewPager == null) {
            throw new IllegalStateException("ViewPager has not been bound.");
        }
        if (mSelectedTabIndex != item) {
            mSelectedTabIndex = item;
            mViewPager.setCurrentItem(item);
        }

        final int tabCount = mTabLayout.getChildCount();
        final TabView focusView = (TabView) mTabLayout.getChildAt(item);
        if (focusView != null) {
            focusView.requestFocus();//在setTextViewSelected之前调用，先触发onfocuschange方法
        }
        for (int i = 0; i < tabCount; i++) {
            final TabView child = (TabView) mTabLayout.getChildAt(i);
            final boolean isSelected = (i == item);
            if (isSelected) {
                animateToTab(item);
                child.setTextViewSelected(true);
            } else {
                child.setTextViewSelected(false);
            }
        }
    }

    @Override
    public void setOnPageChangeListener(OnPageChangeListener listener) {
        mListener = listener;
    }


    /**
     * 显示的情况
     * 1）有焦点被选中，字体高亮，显示背景，不显示hintStrip
     * 2）被选中无焦点，字体高亮，不显示背景，显示hintStrip
     * 3）其余，字体普通，不显示背景，不显示hintStrip
     */
    private class TabView extends LinearLayout {
        private int mIndex;
        private TabTextView textView;
        private View hintStrip;
        //onFocusChange在setTextViewSelected之前调用，所以要保存焦点状态，方便显示选中的情况
        private boolean hasFocus;

        public int getIndex() {
            return mIndex;
        }

        public void setIndex(int index) {
            mIndex = index;
        }

        public TabView(Context context, CharSequence text) {
            super(context);
            setOrientation(VERTICAL);
            setFocusable(true);
            setDescendantFocusability(FOCUS_BLOCK_DESCENDANTS);

            textView = new TabTextView(getContext());
            textView.setBackgroundResource(R.drawable.tab_text_view_bg);
            textView.setText(text);
            textView.setDuplicateParentStateEnabled(true);
            int lrpadding = 24;
            int tbpadding = 5;
            textView.setPadding(lrpadding, tbpadding, lrpadding, tbpadding);
            LayoutParams textViewLp = new LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
            addView(textView, textViewLp);

            hintStrip = new View(getContext());
            hintStrip.setBackgroundResource(R.drawable.tab_text_view_focused_bg);
            hintStrip.setVisibility(INVISIBLE);
            int hintStripWidth;
            if (TextUtils.isEmpty(text)) {
                hintStripWidth = 20;
            } else {
                hintStripWidth = text.length() * 15;
            }
            LayoutParams hsLp = new LayoutParams(hintStripWidth, 6);
            hsLp.gravity = Gravity.CENTER_HORIZONTAL;
            addView(hintStrip, hsLp);

            setOnFocusChangeListener(new OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    TabView.this.hasFocus = hasFocus;
                    if (!hasFocus) {
                        hintStrip.setVisibility(VISIBLE);
                    } else {
                        hintStrip.setVisibility(INVISIBLE);
                    }
                    if (mTabFocusChangeListener != null) {
                        mTabFocusChangeListener.onFocusChange(v, hasFocus);
                    }
                }
            });
            setOnKeyListener((v, keyCode, event) -> {
                if (onTabKeyListener != null) {
                    return onTabKeyListener.onKey(v, keyCode, event);
                }
                return false;
            });
        }

        /**
         * 被动切换选中TabView时调用
         *
         * @param selected
         */
        public void setTextViewSelected(boolean selected) {
            if (hasFocus && selected) {//有焦点，被选中
                textView.setTextColor(Color.RED);
                hintStrip.setVisibility(INVISIBLE);
            } else if (selected) {//Pager左右间切换的情况
                textView.setTextColor(Color.RED);
                hintStrip.setVisibility(VISIBLE);
            } else {
                textView.setTextColor(Color.GRAY);
                hintStrip.setVisibility(INVISIBLE);
            }
        }
    }

    private class TabTextView extends AppCompatTextView {

        public TabTextView(Context context) {
            this(context, null, 0);
        }

        public TabTextView(Context context, AttributeSet attrs) {
            this(context, attrs, 0);
        }

        public TabTextView(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);

            setTextSize(34);
            setTextColor(Color.GRAY);
            setGravity(Gravity.CENTER);
        }

        @Override
        public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);

            // Re-measure if we went beyond our maximum size.
            if (mMaxTabWidth > 0 && getMeasuredWidth() > mMaxTabWidth) {
                super.onMeasure(MeasureSpec.makeMeasureSpec(mMaxTabWidth, MeasureSpec.EXACTLY), heightMeasureSpec);
            }
        }
    }
}
