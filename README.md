# GenTemplate
GenTemplate

## 使用说明：

运行项目生产 jar, 参见目录：

```

├── out
│   ├── artifacts
│   │   └── zkteam_jar
│   │       └── zkteam.jar

```


### 使用命令

##### 1. 通常使用方法：`java -jar zkteam.jar`
##### 2. 添加参数：`java -Dn=hello -Dp=com.abc.abc -Dd=/Users/WangQing/GenTemplate/out/artifacts/zkteam_jar -jar zkteam.jar `
 相关参数说明：

 - -Dn 后面的 hello 表示文件夹名字
 - -Dp 后面的 p 表示包名
 - -Dd 后面的路径表示生成路径
##### 3. 添加末尾参数：`java -jar zkteam.jar Dahai dd.bb.df.df /Users/WangQing/GenTemplate/out/artifacts/zkteam_jar`
 后面的顺序默认是：
 - 文件名字，
 - 包名，
 - 生成文件目录


## 附加
1. 添加 Zip 的第三方库，作为保留，后续可能会使用。 https://github.com/srikanth-lingala/zip4j