2011/10/14
NOTE1: 
fyjengine是一个3D UI引擎 ， 开期开发基于opengl es 1.x API. 后期将考虑同时支持
opengl es 2.0.
开发此引擎的目的是总结一下之前学习的一些opengl方面的知识和使用经验，开发出一个使用方便的的
3D UI引擎，简化3D UI的开发。

NOTE2:
目录源码初步分为以下几个包：
+fyjengine (引擎全局类，提供访问引擎其他类的全局入口。)
+fyjengine.core(核心包，提供引擎的基础类)
+fyjengine.gui(提供GUI相关的类，如静态文本，按钮等)
+fyjengine.io(提供文件以及资源访问方法)
+fyjengine.scene(引擎场景维护与管理)

