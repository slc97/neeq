# neeq
## 项目结构
本仓库包含三个项目
+ ubs-demo项目是模拟服务器端的微服务项目
+ ubs-shell项目是提前编写好的Shell项目，包括Http请求发送类等，用命令行工具替代前端
+ shell-generator-plugin项目是Shell代码生成项目，基于Velocity生成代码

## 项目运行
+ 下载项目到本地
+ 运行ubs-demo项目，生成Target目录下的class文件，用于shell-generator-plugin获取项目代码生成需要的信息
+ install shell-generator-plugin生成可用插件，并在ubs-shell中引入此插件
+ ubs-shell项目中的配置文件中需要修改两个变量，对应两个路径
  + path————扫描的Jar包路径，用于扫描Jar包对应的项目代码信息（默认在ubs-shell根目录下扫描，注意不能有多余的jar包文件）
  + targetPath————ubs-shell的项目路径，用于代码生成
+ 运行ubs-shell中的插件，为ubs-shell生成代码
+ 运行ubs-shell项目，输入命令行，向ubs-demo项目发送Http请求
