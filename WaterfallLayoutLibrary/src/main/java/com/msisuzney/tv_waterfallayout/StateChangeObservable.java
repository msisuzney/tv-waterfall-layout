package com.msisuzney.tv_waterfallayout;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public abstract class StateChangeObservable<T extends StateChangedObserver> {

    void notifyFragmentPause() {
        for (int i = mObservers.size() - 1; i >= 0; i--) {
            mObservers.get(i).onFragmentPause();
        }
    }

    void notifyFragmentVisibilityChanged(boolean isVisible) {
        for (int i = mObservers.size() - 1; i >= 0; i--) {
            mObservers.get(i).onFragmentVisibilityChanged(isVisible);
        }
    }

    void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        for (int i = mObservers.size() - 1; i >= 0; i--) {
            mObservers.get(i).onScrollStateChanged(recyclerView, newState);
        }
    }

    /**
     * The list of observers.  An observer can be in the list at most
     * once and will never be null.
     */
    protected final ArrayList<T> mObservers = new ArrayList<T>();


    public void registerObserver(T observer) {
        if (observer == null) {
            throw new IllegalArgumentException("The observer is null.");
        }
        synchronized (mObservers) {
            if (mObservers.contains(observer)) {
                throw new IllegalStateException("Observer " + observer + " is already registered.");
            }
            mObservers.add(observer);
        }
    }

    public void unregisterObserver(T observer) {
        if (observer == null) {
            throw new IllegalArgumentException("The observer is null.");
        }
        synchronized (mObservers) {
            int index = mObservers.indexOf(observer);
            if (index == -1) {
                throw new IllegalStateException("Observer " + observer + " was not registered.");
            }
            mObservers.remove(index);
        }
    }

    /**
     * Remove all registered observers.
     */
    public void unregisterAll() {
        synchronized (mObservers) {
            mObservers.clear();
        }
    }
}