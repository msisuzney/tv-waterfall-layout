package com.msisuzney.tv_waterfallayout.models;

import java.util.List;

public final class HorizontalLayoutCollection extends Collection {
    private List<HorizontalLayoutItem> items;

    public List<HorizontalLayoutItem> getItems() {
        return items;
    }

    public void setItems(List<HorizontalLayoutItem> items) {
        this.items = items;
    }
}