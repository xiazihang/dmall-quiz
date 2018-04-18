# 京西商城

### 如何下载 
- 点击上面的`作业下载`链接，进行下载

### 如何启动

- 启动Mysql镜像和jenkins镜像
```
docker-compose up -d
```
- 数据库迁移
```
 ./gradlew flywayC
 ./gradlew flywayM
```
- 将`dmallQuiz.json`文件中的IP地址替换为自己本机公网IP
- 本地启动项目
````
./gradlew bootrun

````
- 浏览器访问`localhost:8088`配置jenkins环境(newman执行环境)

- 在jenkins上持续集成测试

### 注意事项

- 使用命令`newman run collection.json -e dmallQuiz.json`运行测试文件