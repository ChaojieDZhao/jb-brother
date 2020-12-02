 bin/flume-ng agent -c conf -f conf/testlog2flume.properties --name a1 -Dflume.root.logger=INFO,console

flume-ng ：flume 命令 
agent：运行一个Flume Agent 
-c：在指定目录下使用配置 use configs in directory 
-f：指定配置文件，这个配置文件必须在全局选项的–conf（-c）参数定义的目录下 
–name:Agent的名称（必填） 
-Dflume.root.logger=INFO,console 该参数将会把flume的日志输出到console