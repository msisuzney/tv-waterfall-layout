package com.msisuzney.tv.demo.bean;

import java.util.List;
/**
 * @author: chenxin
 * @date: 2019-12-20
 * @email: chenxin7930@qq.com
 */
public class TabBean {


    /**
     * errcode : 0
     * description : 成功执行
     * result : [{"columnTitle":"我是标题～","columns":28,"rows":7,"absLayoutList":[{"posterUrl":"","x":0,"y":0,"w":14,"h":7},{"x":14,"y":0,"w":8,"h":7},{"x":22,"y":0,"w":6,"h":7}]},{"columns":10,"rows":3,"absLayoutList":[{"posterUrl":"","x":0,"y":0,"w":2,"h":3},{"x":2,"y":0,"w":2,"h":3},{"x":4,"y":0,"w":2,"h":3},{"x":6,"y":0,"w":2,"h":3},{"x":8,"y":0,"w":2,"h":3}]},{"type":1,"columns":10,"rows":2,"horizontalLayoutList":[{"w":2,"h":2,"posterUrl":""},{"w":2,"h":2,"posterUrl":""},{"w":2,"h":2,"posterUrl":""},{"w":2,"h":2,"posterUrl":""},{"w":2,"h":2,"posterUrl":""},{"w":2,"h":2,"posterUrl":""},{"w":2,"h":2,"posterUrl":""},{"w":2,"h":2,"posterUrl":""},{"w":2,"h":2,"posterUrl":""},{"w":2,"h":2,"posterUrl":""}]},{"columnTitle":"我是Title","columns":28,"rows":7,"absLayoutList":[{"posterUrl":"","x":0,"y":0,"w":14,"h":7},{"x":14,"y":0,"w":7,"h":7},{"x":21,"y":0,"w":7,"h":7}]},{"columns":10,"rows":3,"absLayoutList":[{"posterUrl":"","x":0,"y":0,"w":2,"h":3},{"x":2,"y":0,"w":2,"h":3},{"x":4,"y":0,"w":2,"h":3},{"x":6,"y":0,"w":2,"h":3},{"x":8,"y":0,"w":2,"h":3}]},{"columns":10,"rows":2,"absLayoutList":[{"posterUrl":"","x":0,"y":0,"w":10,"h":2}]},{"columnTitle":"我是Title","columns":28,"rows":7,"absLayoutList":[{"posterUrl":"","x":0,"y":0,"w":14,"h":7},{"x":14,"y":0,"w":7,"h":7},{"x":21,"y":0,"w":7,"h":7}]},{"columns":10,"rows":3,"absLayoutList":[{"posterUrl":"","x":0,"y":0,"w":2,"h":3},{"x":2,"y":0,"w":2,"h":3},{"x":4,"y":0,"w":2,"h":3},{"x":6,"y":0,"w":2,"h":3},{"x":8,"y":0,"w":2,"h":3}]},{"columns":10,"rows":2,"absLayoutList":[{"posterUrl":"","x":0,"y":0,"w":10,"h":2}]},{"columnTitle":"我是Title","columns":28,"rows":7,"absLayoutList":[{"posterUrl":"","x":0,"y":0,"w":14,"h":7},{"x":14,"y":0,"w":7,"h":7},{"x":21,"y":0,"w":7,"h":7}]},{"columns":10,"rows":3,"absLayoutList":[{"posterUrl":"","x":0,"y":0,"w":2,"h":3},{"x":2,"y":0,"w":2,"h":3},{"x":4,"y":0,"w":2,"h":3},{"x":6,"y":0,"w":2,"h":3},{"x":8,"y":0,"w":2,"h":3}]},{"columns":10,"rows":2,"absLayoutList":[{"posterUrl":"","x":0,"y":0,"w":10,"h":2}]},{"columnTitle":"我是Title","columns":28,"rows":7,"absLayoutList":[{"posterUrl":"","x":0,"y":0,"w":14,"h":7},{"x":14,"y":0,"w":7,"h":7},{"x":21,"y":0,"w":7,"h":7}]},{"columns":10,"rows":3,"absLayoutList":[{"posterUrl":"","x":0,"y":0,"w":2,"h":3},{"x":2,"y":0,"w":2,"h":3},{"x":4,"y":0,"w":2,"h":3},{"x":6,"y":0,"w":2,"h":3},{"x":8,"y":0,"w":2,"h":3}]},{"columns":10,"rows":2,"absLayoutList":[{"posterUrl":"","x":0,"y":0,"w":10,"h":2}]},{"columnTitle":"我是Title","columns":28,"rows":7,"absLayoutList":[{"posterUrl":"","x":0,"y":0,"w":14,"h":7},{"x":14,"y":0,"w":7,"h":7},{"x":21,"y":0,"w":7,"h":7}]},{"columns":10,"rows":3,"absLayoutList":[{"posterUrl":"","x":0,"y":0,"w":2,"h":3},{"x":2,"y":0,"w":2,"h":3},{"x":4,"y":0,"w":2,"h":3},{"x":6,"y":0,"w":2,"h":3},{"x":8,"y":0,"w":2,"h":3}]},{"columns":10,"rows":2,"absLayoutList":[{"posterUrl":"","x":0,"y":0,"w":10,"h":2}]},{"columnTitle":"我是Title","columns":28,"rows":7,"absLayoutList":[{"posterUrl":"","x":0,"y":0,"w":14,"h":7},{"x":14,"y":0,"w":7,"h":7},{"x":21,"y":0,"w":7,"h":7}]},{"columns":10,"rows":3,"absLayoutList":[{"posterUrl":"","x":0,"y":0,"w":2,"h":3},{"x":2,"y":0,"w":2,"h":3},{"x":4,"y":0,"w":2,"h":3},{"x":6,"y":0,"w":2,"h":3},{"x":8,"y":0,"w":2,"h":3}]},{"columns":10,"rows":2,"absLayoutList":[{"posterUrl":"","x":0,"y":0,"w":10,"h":2}]},{"columnTitle":"我是Title","columns":28,"rows":7,"absLayoutList":[{"posterUrl":"","x":0,"y":0,"w":14,"h":7},{"x":14,"y":0,"w":7,"h":7},{"x":21,"y":0,"w":7,"h":7}]},{"columns":10,"rows":3,"absLayoutList":[{"posterUrl":"","x":0,"y":0,"w":2,"h":3},{"x":2,"y":0,"w":2,"h":3},{"x":4,"y":0,"w":2,"h":3},{"x":6,"y":0,"w":2,"h":3},{"x":8,"y":0,"w":2,"h":3}]},{"columns":10,"rows":2,"absLayoutList":[{"posterUrl":"","x":0,"y":0,"w":10,"h":2}]}]
     */

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
        /**
         * columnTitle : 我是标题～
         * columns : 28
         * rows : 7
         * absLayoutList : [{"posterUrl":"","x":0,"y":0,"w":14,"h":7},{"x":14,"y":0,"w":8,"h":7},{"x":22,"y":0,"w":6,"h":7}]
         * type : 1
         * horizontalLayoutList : [{"w":2,"h":2,"posterUrl":""},{"w":2,"h":2,"posterUrl":""},{"w":2,"h":2,"posterUrl":""},{"w":2,"h":2,"posterUrl":""},{"w":2,"h":2,"posterUrl":""},{"w":2,"h":2,"posterUrl":""},{"w":2,"h":2,"posterUrl":""},{"w":2,"h":2,"posterUrl":""},{"w":2,"h":2,"posterUrl":""},{"w":2,"h":2,"posterUrl":""}]
         */
        public static final int TYPE_HORIZONTAL_LAYOUT = 1;
        public static final int TYPE_ABSOLUTE_LAYOUT = 0;
        private String columnTitle;
        private int columns;
        private int rows;
        // 1:水平滑动布局的数据，2：绝对布局的数据
        private int type;
        private List<AbsLayoutListBean> absLayoutList;
        private List<HorizontalLayoutListBean> horizontalLayoutList;

        public String getColumnTitle() {
            return columnTitle;
        }

        public void setColumnTitle(String columnTitle) {
            this.columnTitle = columnTitle;
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

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public List<AbsLayoutListBean> getAbsLayoutList() {
            return absLayoutList;
        }

        public void setAbsLayoutList(List<AbsLayoutListBean> absLayoutList) {
            this.absLayoutList = absLayoutList;
        }

        public List<HorizontalLayoutListBean> getHorizontalLayoutList() {
            return horizontalLayoutList;
        }

        public void setHorizontalLayoutList(List<HorizontalLayoutListBean> horizontalLayoutList) {
            this.horizontalLayoutList = horizontalLayoutList;
        }

        public static class AbsLayoutListBean {
            /**
             * posterUrl :
             * x : 0
             * y : 0
             * w : 14
             * h : 7
             */

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

        public static class HorizontalLayoutListBean {
            /**
             * w : 2
             * h : 2
             * posterUrl :
             */

            private int w;
            private int h;
            private String posterUrl;

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

            public String getPosterUrl() {
                return posterUrl;
            }

            public void setPosterUrl(String posterUrl) {
                this.posterUrl = posterUrl;
            }
        }
    }
}
