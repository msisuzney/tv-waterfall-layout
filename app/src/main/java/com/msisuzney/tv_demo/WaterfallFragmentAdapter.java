package com.msisuzney.tv_demo;

import android.annotation.SuppressLint;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.ViewGroup;

import java.util.List;

public class WaterfallFragmentAdapter extends FragmentStatePagerAdapter {
    private Fragment currentFragment;
    private List<String> titles;
    private List<Fragment> tabFragments;

    public Fragment getCurrentFragment() {
        return currentFragment;
    }

    public WaterfallFragmentAdapter(FragmentManager fm, List<String> titles, List<Fragment> tabFragmentList) {
        super(fm);
        this.tabFragments = tabFragmentList;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return tabFragments.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position < titles.size()) {
            return titles.get(position);
        }
        return "";
    }

    @Override
    public int getCount() {
        if (tabFragments != null) {
            return tabFragments.size();
        }
        return 0;
    }

    @Override
    public Parcelable saveState() {
        return null;
    }


    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        currentFragment = (Fragment) object;
//        Log.d("TabFragmentAdapter", " setPrimaryItem  pos:" + position);
        super.setPrimaryItem(container, position, object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        Log.d("TabFragmentAdapter", " instantiateItem  pos:" + position);
        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.destroyItem(container, position, object);
        Log.d("TabFragmentAdapter", " destroyItem  pos:" + position);
    }
}

