## ActiveMQ的部署 ##

### 第一步：服务器配置

	安装ActiveMQ需要先装JDK（1.4以上即可），
	设置JAVA_HOME环境变量。

### 第二步：下载ActiveMQ
	到http://activemq.apache.org去下载最新版本.
	
### 第三步：安装ActiveMQ
** 解压 **

	直接解压ActiveMQ压缩包到 /usr/local
	
	默认配置下，执行解压目录下bin/activemq 即可正常运行。之后，可以通过访问http://your_ip:8161/admin查看ActiveMQ的运行情况（默认用户名和密码为admin/admin）。

** 配置 **
	
	ActiveMQ的配置存放在安装目录的conf/activemq.xml文件中。
	因为ActiveMQ采用了Jetty作为容器，因此Jetty相关的配置在conf/jetty.xml文件中。