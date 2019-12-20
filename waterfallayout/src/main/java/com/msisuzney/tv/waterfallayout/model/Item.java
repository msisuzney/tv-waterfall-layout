package com.msisuzney.tv.waterfallayout.model;

/**
 * @author: chenxin
 * @date: 2019-12-20
 * @email: chenxin7930@qq.com
 */
public abstract class Item {

    //具体的数据类型以及数据
    private Object bean;

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }
}
