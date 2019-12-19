package com.msisuzney.tv_waterfallayout.model;

import java.util.List;

public final class AbsoluteLayoutCollection extends Collection {


    public AbsoluteLayoutCollection(int width, int height) {
        super(width, height);
    }

    private List<AbsoluteLayoutItem> items;

    public List<AbsoluteLayoutItem> getItems() {
        return items;
    }

    public void setItems(List<AbsoluteLayoutItem> items) {
        this.items = items;
    }
}
