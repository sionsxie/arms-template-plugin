### Android Studio 4.1中的模板插件基本说明

# 1.使用JetBrains IntelliJ 插件模板

    打开 [intellij-platform-plugin-template](https://github.com/JetBrains/intellij-platform-plugin-template)
    点击那个绿色按钮 Use this template 把这个项目copy到自己的Git下，然后下载下来进行相应的修改，如下图
![](https://raw.githubusercontent.com/sionsxie/arms-template-plugin/main/imgs/1.png)

# 2.修改相应位置
    gradle.properties
           //这个是插件的Group 和包名相同就行
           pluginGroup = com.github.sionsxie.armstemplateplugin
           //这个是插件名字
           pluginName = arms-template-plugin
           //这个是插件版本 随意改
           pluginVersion = 0.0.3
           //如果不想调试各种版本兼容的话可以把 build.gradle.kts里的intellij里的updateSinceUntilBuild设置为false
           //这个是最小支持版本，就是Android Studio关于里的那个 #AI-201.8743.12. 这个AI后面的第一个数字 201
           pluginSinceBuild = 201
           //这个是最大支持版本，一般不用改
           pluginUntilBuild = 203.*
           //![版本来源说明]
           //(https://plugins.jetbrains.com/docs/intellij/android-studio.html?from=jetbrains.org#matching-versions-of-the-intellij-platform-with-the-android-studio-version)
           platformVersion = 201.8743.12
           //是否下载ide源码
           platformDownloadSources = true
           //这个是平台插件复制过去就ok了
           platformPlugins = java, com.intellij.java, org.jetbrains.android, android, org.jetbrains.kotlin
    build.gradle.kts
            dependencies {
                detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.16.0")
                //增加 wizard-template.jar 来源在 Android Studio\plugins\android\lib\wizard-template.jar
                compileOnly(files("libs/wizard-template.jar"))
            }
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
   - 入口必须为WizardTemplateProvider的派生类
   - widgets里的widgets控件必须有default 属性，不然菜单里看不到模板
   - wizard-template.jar这个jar和JetBrains里的wizardTemplate.plugin-xx.x.x.jar里面api可能有点不一样，已本地的为准
   - 如果出现中文乱码在 build.gradle.kts tasks里的withType<JavaCompile> 加 options.encoding = "UTF-8"
