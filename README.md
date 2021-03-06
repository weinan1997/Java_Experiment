# 葫芦娃大作业总结

### 魏楠 151220120

## 程序使用简介

当进入载入界面后，有三个选项，分别是

1. 开始（空格）
2. 保存（S)
3. 回放（L)

按下空格键后，将会开始一轮新的战斗；在载入界面先按下S将会进入保存模式，再按下空格后这次战斗过程将会被保存起来；在载入界面按下L将会回放上一次保存战斗的过程（注：在战斗或回放中不要按到任何键以免引起一些我懒得处理的bug）

## 战斗简介

战斗在一个$14 \times 14$草地上进行，正义方为葫芦娃七兄弟和爷爷组成的长蛇列队，邪恶方为蛇精、蝎子精和6个小喽啰组成的雁行列队。

每次两方的位置随机生成，每个生物的战斗值也在一开始被随机初始化。

一旦战斗开始，每方生物开始行动，当碰上敌对势力生物时将展开战斗，战斗结果根据双方的战斗值随机产生，失败的一方将被从地图上去除。

一旦某一方的生物全部死亡，战斗停止。

## 实现细节

下面讲讲写程序过程中对一些具体问题的实现方法。

#### 图片来源

由于自己PS水平不佳，所以借用了吴政亿同学P好的图，在此表示感谢！

#### 队列生成

根据队列的种类不同，调用不同的队列生成函数，每次随机产生起始位置，并检查队列所占位置是否已有生物，如果有则重新产生随机数。

#### 生物移动

为每个生物创建一个线程，每次`run()`执行一步移动，然后调用`Thread.sleep(100)`使线程睡眠0.1s。所有线程共享一个锁，确保每次只有一个线程移动，且每次`sleep`期间不会有其他线程移动。

每个生物移动的方向预设是完全随机的，后来发现这样做会使战斗要打非常之久，所以改为每次随机之前检查此生物所在行列是否有敌对生物，如果有则向此生物前进。

#### 回放

采用实现最简单的方法——把每张图存起来然后回访的时候一张张地放。

*在这里有一个我没解决的bug：直接用IDE运行的时候，保存和回放是没有问题的，但是用Maven打包生成的.jar运行时就无法保存和回放。一开始我以为是路径问题，将存储路径改为了用`getResource()`获取，但是问题仍旧没得以解决*

#### 绘图

绘图也有个奇怪的地方：我把图片大小设置成$700*700$但是下方总有一点画不出来，后来我给`Height`加了20才画出来，但是存图的时候底下又多了20的黑边

## 总结

此次大实验确实让我受益匪浅，第一次接触了OO编程，跟以往的感觉很不一样。

当然此次实验最后结果还是有很多不尽如人意的地方：

1. 一些bug没有被改正
2. 代码风格到后期陷于混乱，出现了底层依赖高层等不好的现象
3. 内容不够丰富

不过对我第一次写OO的我，能写出这么个能跑的小程序，我也觉得很不错了。当然，这个时候也要找找客观原因——ddl最后几天我发烧了，整个人状态非常不好，大多是bug都是在这一时段写出来的，有些解决了，有的则如之前所述没能解决。当然最主要的还是我个人编程水平的问题，还有待进一步提高。~~拔尖班编程水平都很差——某学姐语~~。

通过学习Java我收获了很多，也激发了我进一步学习面向对象编程的兴趣，在此感谢曹老师和余老师一学期来的辛勤讲授！