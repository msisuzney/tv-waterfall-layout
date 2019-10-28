# TV_WaterfallLayout
一种基于Android Leanback库改造的Android TV竖直排版布局  
示例效果如下：
<div align=center>
<img src="demo.gif" width = "480" height = "270" alt="演示" /> 
</div>  

### 特性  

- 以行做为基本的单元，行的布局可以是**HorizontalGridView**或者**AbsoluteLayout**，也可以自定义**行**的布局
- 获得焦点的行自动居中显示
- 快速滑动时不会出现焦点移动不合理的情况
- 支持焦点自动换行，当焦点View在屏幕右边缘时按下右键，焦点会换行到下一行的第一个View，左边缘同理换行到上一行最后一个View  

### 使用
#### 1.设计理念
延用了Leanback中的**Model -> Presenter -> View**的理念：  
  
<div align=center>
<img src="mpv.png" width = "318" height = "180" alt="演示" /> 
</div>  

Presenters根据不同的数据创建不同的View，具体见[android/tv-samples](https://github.com/android/tv-samples)  

#### 2.使用方式

```java
public class MyFragment extends AbsRowFragment {


    @Override
    protected PresenterSelector initBlockPresenterSelector() {
        //1.提供所有行中的运营位的Presenters，用于创建对应的View
        return new PresenterSelector() {
            @Override
            public Presenter getPresenter(Object item) {
                return new ImageViewPresenter(null);
            }
        };
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //2. 构造水平滑动布局的Model
        HorizontalLayoutItem item = new HorizontalLayoutItem();
        item.setWidth(200);
        item.setHeight(200);
        List<HorizontalLayoutItem> items = new ArrayList<>();
        items.add(item);
        HorizontalLayoutCollection horizontalLayoutCollection =
                new HorizontalLayoutCollection(ViewGroup.LayoutParams.MATCH_PARENT, 200);
        horizontalLayoutCollection.setItems(items);

        //3. 构造绝对布局的Model
        AbsoluteLayoutItem item1 = new AbsoluteLayoutItem();
        item1.setHeight(200);
        item1.setWidth(200);
        item1.setX(200);
        item1.setY(10);
        List<AbsoluteLayoutItem> items1 = new ArrayList<>();
        items1.add(item1);
        AbsoluteLayoutCollection absoluteLayoutCollection =
                new AbsoluteLayoutCollection(ViewGroup.LayoutParams.MATCH_PARENT, 400);
        absoluteLayoutCollection.setItems(items1);
        
        //4. 添加到布局中
        add(horizontalLayoutCollection);
        add(absoluteLayoutCollection);
    }
}


```
详细使用见demo代码
