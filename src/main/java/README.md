# 面向葫芦娃编程阶段性总结
### 1.面向对象好处都有啥
- 代码逻辑更清楚
- 每段代码的独立性更强
- oo式设计让每个对象的职责划分更清楚
- 便于扩展和修改

### 2.面向对象——从入门到放弃
具体在代码中，我是如何实现从面向过程到面向对象编程的的转变呢？我的具体实现方法有以下几点，看起来比较object-oriented:

- 使用Creature作为父类，将葫芦娃、蝎子精、爷爷等其他对象统统作为其子类继承而来。作为Creature的父类仅含有Position一个成员，及其相应的获取和修改方法。同时含有report方法作为所有生物的共同特点，不同的生物重载此方法以发出不同的声音
- 将sort作为interface提取出来，排序本身作为一个调用接口，方便扩展更多的排序方式。同时排序的对象为Creature[]组成的Queue，对任何对象均可排序，只要其有明确的Greater定义
- 对不同对象职责的划分。比较显著的一点（自认为）就是，在为生物寻找空位的过程中，对不同的需求要有不同的方法。首先就是Position类里面测试一个给定位置是否为空的方法isEmpty(),其次在BattleGround类中，又有checkAreaEmpty()和findEmptyPosition()来具体完成位置查找工作
- 暂时想不出来更多了

### 3.一些问题
- 感觉BattleGround类还是臃肿了一点，应该可以做进一步抽象，目前还没想到比较好的办法
- 存在一些小bugs
- 第一次写OO，有时候还不太适应这种思维
	