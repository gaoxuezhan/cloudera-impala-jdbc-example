#!/bin/bash
#引入java环境变量
. /etc/profile
 
#取得当前.sh文件所在的目录
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )" 
#引入class所在的文件夹
classes=$DIR/classes
#lib folder  
libs=$DIR/lib
#将classes和包jar加入classpath，注意用的是冒号":"分割的
classpath=$classes:$libs/*:.
# 执行java的调用过程，格式如下：
# java -classpath $classpath 主函数类入口 
java -classpath $classpath:lib/* ImpalaExecutor 
echo "shell over.."
