package com.msisuzney.tv_waterfallayout;


import java.util.List;

public class RowData {
    //行的宽高
    private int width, height;
    private List<BlockData> blocks;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public List<BlockData> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<BlockData> blocks) {
        this.blocks = blocks;
    }
}
