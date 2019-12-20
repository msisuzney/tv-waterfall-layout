package com.msisuzney.tv.waterfallayout.model;

import java.util.List;
/**
 * @uthor: chenxin
 * @date: 2019-12-20
 * @email: chenxin7930@qq.com
 */
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
