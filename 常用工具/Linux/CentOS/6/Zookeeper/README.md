## ZooKeeper配置 ##

### 准备： 

	这里以三台centos机器为例搭建一个最小的集群环境，三台服务器或者三台虚拟机均可

### 第一步：服务器配置

	(安装Zookeeper需要先装JDK)

	三台Centos server准备好之后，假设它们的IP地址分别为

	192.168.1.2, 192.168.1.181, 192.168.1.121 

	将它们的host分别命名为z1.zookeeper.com, z2.zookeeper.com, z3.zookeeper.com

### 第二步：下载Zookeeper
	到http://zookeeper.apache.org/releases.html去下载最新版本Zookeeper-3.4.5的安装包zookeeper-3.4.5.tar.gz.
### 第三步：安装Zookeeper
** 解压 **

	这里假定以root作为用户名进行安装，将zookeeper-3.4.5.tar.gz放入/opt目录下，然后执行”tar zxf zookeeper-3.4.5.tar.gz”解压。

** 配置 **

	将conf/zoo_sample.cfg拷贝一份命名为zoo.cfg，也放在conf目录下。

	然后按照如下值修改其中的配置：

	tickTime=2000       

	dataDir=/var/lib/zookeeper/

	initLimit=5

	syncLimit=2

	clientPort=2181

	server.1=0.0.0.0:2888:3888(设置当前机器为：0.0.0.0:2888:3888)

	server.2=z2.zookeeper.com:2888:3888

	server.3=z3.zookeeper.com:2888:3888


** 创建/var/lib/zookeeper快照目录，并创建server id文件 **

	mkdir /var/lib/zookeeper

	cd /var/lib/zookeeper

	在该目录下创建名为myid的文件，内容为1（这个值随server而改变，后面会提到）

	echo 1 > myid 

** 拷贝配置文件 **

	将server1上已经配置好的/opt/zookeeper-3.4.5/和/var/lib/zookeeper两个目录分别拷贝至server2和server3。
	然后将其上的/var/lib/zookeeper/myid的内容修改为2和3并重复配置步骤，注意修改server.2=0.0.0.0:2888:3888
	server.3=0.0.0.0:2888:3888

### 第四步：启动Zookeeper

	(注：启动第一台的时候可能会报错，提示z2.zookeeper.com和z3.zookeeper.com，等全部启动完成之后就不会报错了)

	cd /opt/zookeeper-3.4.5/bin

	sh zkServer.sh start(启动ZooKeeper)

	sh zkServer.sh stop(关闭ZooKeeper)