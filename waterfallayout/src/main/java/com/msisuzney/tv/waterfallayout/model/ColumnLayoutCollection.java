package com.msisuzney.tv.waterfallayout.model;

import java.util.List;

public final class ColumnLayoutCollection extends Collection {


    public ColumnLayoutCollection(int width, int height) {
        super(width, height);
    }

    private List<ColumnLayoutItem> items;

    public List<ColumnLayoutItem> getItems() {
        return items;
    }

    public void setItems(List<ColumnLayoutItem> items) {
        this.items = items;
    }
}
