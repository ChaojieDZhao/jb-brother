```
/\*\*(\n.*)

/\*\*(\n.*){0,10}\*/

/\*\*(\n.*){0,1}\*/\n

\n.*alldios.*.com

\n.*@author.*

\n.*@name.*

(\n.*alldios.*.com)|(\n.*@name.*)|(\n.*@author.*)

 (.*)\n   $1<br/>\n
 
 ^[0-9a-zA-z].*(\n^[0-9a-zA-z].*){0,20}
 
 ^[a-zA-z].*(\n^[a-zA-z].*){0,20}
 
 ([0-9]{1,3})
 
 ([0-9]{1,3})、 {1,3}
 
  {1,3}={1,3}
  
  ([\u4e00-\u9fa5])([a-z]{1,8})([\u4e00-\u9fa5])   #匹配   中word文 
```


# 替换jar包
```
<dependency>
	<groupId>$1</groupId>
	<artifactId>$1</artifactId>
	<version>0.0.1-snapshot</version>
	<scope>system</scope>
	<systemPath>\${project.basedir}/src/main/webapp/WEB-INFO/lib/$1.jar</systemPath>
</dependency>


([a-z0-9-_.]*).jar

```

# 替换封号
```
”([\u4e00-\u9fa5a-z0-9A-z]{0,9})“


“$1”
```

```
# 将英文字幕中符号后面跟个空格改为一个
([?!,]{1,2}) {0,2}([a-zA-Z])
$1 $2

# 将中文字幕中符号后面跟个空格改为一个
([？！]{1,2}) {0,2}(.)
$1 $2

# 删除单独数字一行
^\d{1,3}\n

# 注释替换，把注释替换为空四个字符
(\w) {2,8}#  替换为  $1    #    

# 去除句首的中文对话符号
^ -([\u4e00-\u9fa5])

# 去除句首的英文对话符号
^- ([a-zA-Z])
```

