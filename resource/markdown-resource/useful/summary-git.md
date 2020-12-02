## hexo强制提交
git push -u -f origin master   

## 删除远程仓库的目录
- 1.git rm -r --cached target/    
- 2.git commit -m "删除target目录"     
- 3.git push -u -f origin master 

## git rebase
- 1.git rebase -i HEAD~12    
第一步 修改提交 第一个为pick，其他都为s。然后wq!保存    
第二步 修改注释 修改每一次的注释，然后wq保存。    
    
- 2.git push -u -f origin master     

## 检出项目( 模仿sourcetree )
```shell
git -c filter.lfs.smudge= -c filter.lfs.required=false -c diff.mnemonicprefix=false -c core.quotepath=false --no-optional-locks clone --branch develop 
https://gitlab.cmread.com:2443/nabiz/reward-service.git D://soooooooft//git_//lang_chao_project//langchaoOther//composite//reward-service
```

## 清除sourcetree的密码
C:\Users\Administrator\AppData\Local\Atlassian\SourceTree\passwd

## 生成公钥私钥
 ssh-keygen -t rsa -C "youremail@example.com"
 
## 常用git命令
git add .  
git commit -m '注释' 
git push origin master
git -rm 
git pull origin master 
git branch -v 
git log