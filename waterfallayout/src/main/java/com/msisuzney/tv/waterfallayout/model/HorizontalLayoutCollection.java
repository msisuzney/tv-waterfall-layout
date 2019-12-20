package com.msisuzney.tv.waterfallayout.model;

import java.util.List;
/**
 * @author: chenxin
 * @date: 2019-12-20
 * @email: chenxin7930@qq.com
 */
public final class HorizontalLayoutCollection extends Collection {

    public HorizontalLayoutCollection(int width, int height) {
        super(width, height);
    }

    private List<HorizontalLayoutItem> items;

    public List<HorizontalLayoutItem> getItems() {
        return items;
    }

    public void setItems(List<HorizontalLayoutItem> items) {
        this.items = items;
    }
}
