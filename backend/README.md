## 测试

所有mapper经过单元测试

controller只有pop_product经过测试


## 配置数据库ip

找到配置文件：

backend/src/main/resources/application.yml

搜索 yourip，改为数据库ip



## 运行

### 1. 方法1

   用idea等IDE打开，待maven安装好依赖，

   运行启动类：

   backend/src/main/java/com/ddbs/DDBSPreApplication.java

### 2. 方法2

   在目录 backend/ 下，

   运行 `mvn clean package` 打包，生成 jar包，留意位置

   然后直接运行：

   ```bash
   java -jar backend/target/backend-1.0-SNAPSHOT.jar --server.port=8082 # 指定port
   ```

   



