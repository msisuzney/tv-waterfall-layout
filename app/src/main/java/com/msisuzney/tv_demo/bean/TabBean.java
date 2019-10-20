package com.msisuzney.tv_demo.bean;

import java.util.List;

public class TabBean {


    private String errcode;
    private String description;
    private List<ResultBean> result;

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {

        private String columnTitle;
        private List<BlockListBean> blockList;
        private int columns;
        private int rows;

        public String getColumnTitle() {
            return columnTitle;
        }


        public int getColumns() {
            return columns;
        }

        public void setColumns(int columns) {
            this.columns = columns;
        }

        public int getRows() {
            return rows;
        }

        public void setRows(int rows) {
            this.rows = rows;
        }

        public void setColumnTitle(String columnTitle) {
            this.columnTitle = columnTitle;
        }

        public List<BlockListBean> getBlockList() {
            return blockList;
        }

        public void setBlockList(List<BlockListBean> blockList) {
            this.blockList = blockList;
        }

        public static class BlockListBean {

            private String posterUrl;
            private int x;
            private int y;
            private int w;
            private int h;

            public String getPosterUrl() {
                return posterUrl;
            }

            public void setPosterUrl(String posterUrl) {
                this.posterUrl = posterUrl;
            }

            public int getX() {
                return x;
            }

            public void setX(int x) {
                this.x = x;
            }

            public int getY() {
                return y;
            }

            public void setY(int y) {
                this.y = y;
            }

            public int getW() {
                return w;
            }

            public void setW(int w) {
                this.w = w;
            }

            public int getH() {
                return h;
            }

            public void setH(int h) {
                this.h = h;
            }


        }
    }
}
