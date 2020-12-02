# cmd
del /a /f /s /q  "*.repositories" "*.sha1" "*.properties" "*.bom" "*.xml" 

del /a /f /s /q  "*.repositories" 

del /a /f /s /q  "*.sha1"

del /a /f /s /q  "*.properties" 

del /a /f /s /q  "*.bom" 

del /a /f /s /q  "*.pom" 

tasklist | findstr 'java'

taskkill -f -pid 

dir

type  #查看内容

# script
## Java环境变量设置
``` batch
@echo on 
echo  "%~dp0"
echo "%cd%"
set jdk_path=D:\sooooft\java
echo %jdk_path%
rem setting system variables
setx JAVA_HOME  "%jdk_path%"  -m
setx CLASSPATH  ".;%%JAVA_HOME%%\lib\tools.jar;%%JAVA_HOME%%\lib\dt.jar" -m
rem stop a little
echo %Path%
echo %Path%|find /i "%java_home%" && set IsNull=true || set IsNull=false
echo %IsNull%
rem if has been set, it is not set.
if not %IsNull%==true (
reg add "HKEY_LOCAL_MACHINE\SYSTEM\CurrentControlSet\Control\Session Manager\Environment" /v Path /t REG_SZ /d "%Path%%%JAVA_HOME%%\bin;%%JAVA_HOME%%\jre_8\bin;" /f
echo SET_PATH
setx Path "%Path%%%JAVA_HOME%%\bin;" -m
)
pause   

```

## 配置Tomcat服务
>修改service.bat      

"%EXECUTABLE%" //IS//%SERVICE_NAME% ^   
...   
    --Jvm "%CATALINA_BASE%\bin\java_8\jre_8\bin\server\jvm.dll" ^
    --StartMode jvm ^   
    --StopMode jvm ^   
...   
   
``` batch
@echo on
call "%~dp0%service.bat" install Tomcat8
sc config Tomcat8 start= auto
echo
sc config Tomcat8 start= auto 
rem 
wmic service where name="Tomcat8" changestartmode "automatic"
exit

```

## 删除嵌套的svn信息
新建一个文件[delete_svn.bat]，书写文件内容如下，字符编码设置为ANSI。
``` batch
@echo off   
echo ***********************************************************   
echo 清除SVN版本信息                                                                                                 
echo ***********************************************************   
:start   
::启动过程，切换目录   
:set pwd=%cd%   
:cd %1   
echo 工作目录是：& chdir   
:input   
::获取输入，根据输入进行处理   
set source=:   
set /p source=确定要清楚当前目录下的.svn信息吗？[Y/N/Q]   
set "source=%source:"=%"   
if "%source%"=="y" goto clean   
if "%source%"=="Y" goto clean   
if "%source%"=="n" goto noclean   
if "%source%"=="N" goto noclean   
if "%source%"=="q" goto end   
if "%source%"=="Q" goto end   
goto input   
:clean   
::主处理过程，执行清理工作   
@echo on   
@for /d /r %%c in (.svn) do @if exist %%c ( rd /s /q %%c & echo    删除目录%%c)   
@echo off   
echo "当前目录下的svn信息已清除"   
goto end   
:noclean   
::分支过程，取消清理工作   
echo "svn信息清楚操作已取消"   
goto end   
:end   
::退出程序   
cd "%pwd%"   
pause   
```