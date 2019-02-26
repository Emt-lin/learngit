### Git

​	stage：暂存区

​	pwd:	显示当前目录

​	git add 文件名

​	git commit -m "本次提交说明内容"

​	git status 	掌握工作区的状态

​	git diff	可以查看修改内容。

​	git log	可以告诉我们历史记录

​	git log --pretty=oneline 	如果嫌输出信息太多，看得眼花缭乱的

​	HEAD  表示当前版本    HEAD^   表示上一个版本   一次类推

​	git reset 	回退到上一个版本    git reset --hard HEAD^

​	cat readme.txt   查看当前内容

​	`git reflog`用来记录你的每一次命令，查看命令历史，以便要回到未来的哪个版本。

​	`git diff HEAD -- readme.txt   命令可以查看工作区和版本库里面最新版本的区别

​	`git checkout -- file	`	可以丢弃工作区的修改

​		一种是`readme.txt`自修改后还没有被放到暂存区，现在，撤销修改就回到和版本库一模一样的状态；

一种是`readme.txt`已经添加到暂存区后，又作了修改，现在，撤销修改就回到添加到暂存区后的状态。

​	`git reset HEAD <file> `  可以把暂存区的修改撤销掉（unstage）

​	确实要从版本库中删除该文件，那就用命令`git rm`删掉，并且 git commit  

 										git rm test.txt

​	`git checkout`其实是用版本库里的版本替换工作区的版本，无论工作区是修改还是删除，都可以“一键还原”。 git checkout -- test.txt

`Your identification has been saved in RSRABCK90.                                
Your public key has been saved in RSRABCK90.pub.                                `

`The key fingerprint is:                                                         
SHA256:X75MtGBS3fpTiLuwR9dUL8/vdempZzs0tvfGQb9RYw8 934841028@qq.com             `

```
git remote add origin git@github.com:michaelliao/learngit.git
```

```
git remote add origin https://github.com/Pusenlin/learngit.git //关联
git push -u origin master //推送
```

```
git push origin master 	//后面用这个推送
```

在c/user/hp/.ssh    id_rsa.pub

git clone`克隆一个本地库  

​	git clone  https://github.com/Pusenlin/gitskills.git

​	git clone git@github.com:Pusenlin/gitskills.git