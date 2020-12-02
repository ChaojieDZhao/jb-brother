## 什么是Webpack?
WebPack可以看做是模块打包机：它做的事情是，分析你的项目结构，
找到JavaScript模块以及其它的一些浏览器不能直接运行的拓展语言（Scss，TypeScript等），并将其转换和打包为合适的格式供浏览器使用。


```
1 npm init

//全局安装
2 npm install -g webpack
//安装到你的项目目录
npm install --save-dev webpack

3 node_modules/.bin/webpack app/main.js -o public/bundle.js


# 更换源 
npm config set registry https://registry.npm.taobao.org
npm config get registry
npm cache clean --force
npm cache verify

```


## short cmd
npm run start-test

## Source Maps & 服务器安装
调试功能
npm install --save-dev webpack-dev-server
npm run server

## Loaders
鼎鼎大名的Loaders登场了！
Loaders是webpack提供的最激动人心的功能之一了。
通过使用不同的loader，webpack有能力调用外部的脚本或工具，实现对不同格式的文件的处理，比如说分析转换scss为css，或者把下一代的JS文件（ES6，ES7)转换为现代浏览器兼容的JS文件，
对React的开发而言，合适的Loaders可以把React的中用到的JSX文件转换为JS文件。

# nodejs快速安装
```
wget https://nodejs.org/dist/v10.16.0/node-v10.16.0-linux-x64.tar.xz 
tar xf node-v10.16.0-linux-x64.tar.xz
mv node-v10.16.0-linux-x64 node_loc
sudo ln -s  /home/alldios/temp/nodejs/node_loc/bin/node  /usr/local/bin/
sudo ln -s  /home/alldios/temp/nodejs/node_loc/bin/npm  /usr/local/bin/
```


