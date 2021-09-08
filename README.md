# neeq
## 项目结构
本仓库包含三个项目
+ tradingDemo项目是模拟服务器端的微服务项目
+ ubsShell项目是提前编写好的Shell项目，包括Http请求发送类等
+ shellGene项目是Shell代码生成项目，基于Velocity生成代码

## 项目运行
+ 下载项目到本地
+ 运行tradingDemo项目，生成Target目录下的class文件，用于ShellGene获取项目代码生成需要的信息
+ shellGene项目中的ShellGenerator文件中需要修改两个变量，对应两个路径
  + path————tradingDemo的项目路径，用于扫描tradingDemo项目的信息
  + targetPath————ubsShell的项目路径，用于代码生成
+ 运行ShellGene项目，为ubsShell生成代码
+ 运行ubsShell项目，输入命令行，向tradingDemo项目发送Http请求
