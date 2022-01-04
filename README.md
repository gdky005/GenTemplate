# GenTemplate

## 如何打包 Jar 文件

1. 首先必须使用 Idea 进行打包，操作路径是：Build->Build Artifacts->zkteam:jar->Build。 
2. 如果没有 zkteam:jar, 请先在主项目上右键 -> Open Module Settings -> Artifacts -> 点击加号 -> Jar- > From modules with dependencies -> Mian Class 和 Module 都选择上 -> 直接确定。 然后继续执行 1 操作。
3. 运行 1 以后，直接在 项目路径下的 build/classes/artifacts/zkteam_jar/ 目录下找到文件，直接运行即可。
4. 运行方式是：java -jar zkteam.jar ， 注意最后是路径，需要和资源绑定到一起进行运行，否则会提示缺少文件。

参考文档： https://blog.csdn.net/weixin_33843947/article/details/92311224。