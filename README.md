# TV_WaterfallLayout
一种基于Android Leanback库改造的Android TV竖直排版布局  
示例效果如下：

<img src="demo.gif" width = "480" height = "270" alt="演示" /> 

### 特性  

- 以行做为基本的单元，行的布局可以是**HorizontalGridView**或者**AbsoluteLayout**，也可以自定义**行**的布局
- 获得焦点的行自动居中显示
- 快速滑动时不会出现焦点移动不合理的情况
- 支持焦点自动换行，当焦点View在屏幕右边缘时按下右键，焦点会换行到下一行的第一个View，左边缘同理换行到上一行最后一个View