### Android Studio 4.1中的模板插件基本说明

## 有已经编译好了的plugin，在Releases目录下，可以直接用

## 支持最新Android Studio
    支持ideal 203 ~ 241.* 版本



## 下面几张图是修改的基本的配置和查询版本
![](https://raw.githubusercontent.com/sionsxie/arms-template-plugin/main/imgs/4.png)
![](https://raw.githubusercontent.com/sionsxie/arms-template-plugin/main/imgs/5.png)
![](https://raw.githubusercontent.com/sionsxie/arms-template-plugin/main/imgs/6.png)

# 1.使用JetBrains IntelliJ 插件模板

    打开 [intellij-platform-plugin-com.github.sionsxie.armstemplateplugin.template](https://github.com/JetBrains/intellij-platform-plugin-com.github.sionsxie.armstemplateplugin.template)
    点击那个绿色按钮 Use this com.github.sionsxie.armstemplateplugin.template 把这个项目copy到自己的Git下，然后下载下来进行相应的修改，如下图
![](https://raw.githubusercontent.com/sionsxie/arms-template-plugin/main/imgs/1.png)

# 2.修改相应位置
    gradle.properties
           //这个是插件的Group 和包名相同就行
           pluginGroup = com.github.sionsxie.armstemplateplugin
           //这个是插件名字
           pluginName = arms-template-plugin
           //这个是插件版本 随意改
           pluginVersion = 0.0.3
           //主要修改下面这两值
           //如果不想调试各种版本兼容的话可以把 build.gradle.kts里的intellij里的updateSinceUntilBuild设置为false
           //这个是最小支持版本，就是Android Studio关于里的那个 #AI-201.8743.12. 这个AI后面的第一个数字 201
           pluginSinceBuild = 201
           //这个是最大支持版本，as版本更新之后，一般改改这里就行了（as对应的ideal的版本）
           pluginUntilBuild = 203.*

           //这个是平台插件复制过去就ok了
           platformPlugins = java, com.intellij.java, org.jetbrains.android, android, org.jetbrains.kotlin

    src\main\resources\META-INF\plugin.xml
            //添加三个插件依赖
            <depends>org.jetbrains.android</depends>
            <depends>org.jetbrains.kotlin</depends>
            <depends>com.intellij.modules.java</depends>

# 3.添加自己的模板代码
    参考other里的代码

# 4.生成插件Jar
    如下图，运行那个都行
![](https://raw.githubusercontent.com/sionsxie/arms-template-plugin/main/imgs/2.png)
![](https://raw.githubusercontent.com/sionsxie/arms-template-plugin/main/imgs/3.png)


tip：
   - 主要就修改 pluginUntilBuild 这个最大支持版本号的值就行了，修改之后直接运行 buildPlugin 这个任务即可，打包成jar即可，打包的jar在 build/libs 目录下
   - 不想自己生成就直接下载我打包好的插件即可，在Releases目录下
   - 如果出现中文乱码在 build.gradle.kts tasks里的withType<JavaCompile> 加 options.encoding = "UTF-8"
