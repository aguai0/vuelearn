# vuelearn

## 1.介绍
vuelearn项目脚手架,项目已内置所有必须的配置文件并已做好调试工作，迁移后仅需做简单配置即可使用。

## 2.启动
调试阶段，需要分前后端启动，请分别移步至  
[vuelearn-fe 前端项目启动](master/vuelearn-fe/README.md#1)
[vuelearn-os 后端项目启动](master/vuelearn-os/README.md#2)

## 3.目录结构
```
├── vuelearn-api        远程服务SDK(jar包)
├── vuelearn-fe         vue.js前端(nodejs项目)
├── vuelearn-os         java服务端(web项目)

```
fe,os项目目录结构移步至  
[vuelearn-fe 前端项目目录结构](master/vuelearn-fe/README.md#3)
[vuelearn-os 后端项目目录结构](master/vuelearn-os/README.md#3)

## 4.移植

### 远程仓库设置
1. 设置好github的 **SSH keys**
2. 在github建好你的项目仓库

### 本地设置
1. `git clone https://github.com/aguai0/vuelearn.git`
2. 在根目录下全局替换 **vuelearn** ,以及包结构中的 **vuelearn** 为你的项目名
3. `rm -rf .git`
4. `git init`
5. `git add .`
6. `git commit -m "first commit"`
6. `git remote add origin https://github.com/aguai0/vuelearn.git`
7. `git push -u origin master`

## 5.部署
项目在maven:package过程会自动将fe,os项目的打包结果合并至 `项目/target/vuelearn` 下，可以使用普通tomcat项目的UDA部署任务即可。

## 6.开发指南
fe,os项目开发指南移步至  
[vuelearn-fe 前端项目开发指南](master/vuelearn-fe/README.md#4)
[vuelearn-os 后端项目开发指南](master/vuelearn-os/README.md#4)


## 7.技术规范  
fe.os项目开发指南移步至  
[vuelearn-fe 前端项目技术规范](master/vuelearn-fe/README.md#5)
[vuelearn-os 后端项目技术规范](master/vuelarn-os/README.md#5)
