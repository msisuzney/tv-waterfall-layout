package com.msisuzney.tv_waterfallayout.models;

public abstract class Item {

    //具体的数据
    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
