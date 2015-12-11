## CentOS 配置 ##

### 1. 查看CentOS版本 

    [root@vm ~]# cat /etc/redhat-release
    CentOS release 6.6 (Final)

### 2. 查看CentOS内核

	[root@vm ~]# uname -a
	Linux vm.centos.com 2.6.32-504.el6.x86_64 #1 SMP Wed Oct 15 04:27:16 UTC 2014 x86_64 x86_64 x86_64 GNU/Linux
	
### 5. 修改主机名
**临时修改**

	[root@vm ~]# hostname vm.centos.com

**永久修改**

	[root@vm ~]# vi  /etc/sysconfig/network
	
**将里面的HOSTNAME这一行修改成 HOSTNAME=NEWNAME，其中NEWNAME就是你要设置的HOSTNAME**
	
### 4. 关闭防火墙
**重启后永久性生效**

    开启：chkconfig iptables on
    关闭：chkconfig iptables off
**即时生效，重启后失效**
	
	开启：service iptables start
	关闭：service iptables stop

在开启了防火墙时，做如下设置，开启相关端口，修改 /etc/sysconfig/iptables 文件，添加以下内容

	-A INPUT -m state --state NEW -m tcp -p tcp --dport 80 -j ACCEPT   #允许80端口通过防火墙
	-A INPUT -m state --state NEW -m tcp -p tcp --dport 3306 -j ACCEPT   #允许3306端口通过防火墙

备注：很多网友把这两条规则添加到防火墙配置的最后一行，导致防火墙启动失败

	iptables -I INPUT -p tcp --dport 8080 -j ACCEPT

正确的应该是添加到默认的22端口这条规则的下面
 
### 5. 关闭SELinux

	vi /etc/selinux/config  # 改为 SELINUX=disabled
保存退出，重启服务器
 
### 6. 禁用SELinux
**永久禁用，需要重启生效**

	sed -i 's/SELINUX=enforcing/SELINUX=disabled/g' /etc/selinux/config

**临时禁用，不需要重启**

	setenforce 0

### 7. 配置JDK
**检验系统原版本**

	[root@vm ~]# java -version
	java version "1.6.0_24"
	OpenJDK Runtime Environment (IcedTea6 1.11.1) (rhel-1.45.1.11.1.el6-x86_64)
	OpenJDK 64-Bit Server VM (build 20.0-b12, mixed mode)

**进一步查看JDK信息**

	[root@vm ~]# rpm -qa | grep java
	tzdata-java-2012c-1.el6.noarch
	java-1.6.0-openjdk-1.6.0.0-1.45.1.11.1.el6.x86_64

**卸载OpenJDK，执行以下操作**

	[root@vm ~]# rpm -e --nodeps tzdata-java-2012c-1.el6.noarch
	[root@vm ~]# rpm -e --nodeps java-1.6.0-openjdk-1.6.0.0-1.45.1.11.1.el6.x86_64

**安装JDK**

上传新的jdk-7-linux-x64.rpm软件到/usr/local/执行以下操作：

	[root@vm local]# rpm -ivh jdk-7-linux-x64.rpm

JDK默认安装在/usr/java中。

**验证安装**

执行以下操作，查看信息是否正常：

	[root@vm bin]# java
	[root@vm bin]# javac
	[root@vm bin]# java -version
	java version "1.7.0"
	Java(TM) SE Runtime Environment (build 1.7.0-b147)
	Java HotSpot(TM) 64-Bit Server VM (build 21.0-b17, mixed mode)
	
恭喜，安装成功！

**配置环境变量**

我的机器安装完jdk-7-linux-x64.rpm后不用配置环境变量也可以正常执行javac、java –version操作，因此我没有进行JDK环境变量的配置。但是为了以后的不适之需，这里还是记录一下怎么进行配置，操作如下： 
修改系统环境变量文件

	vi + /etc/profile

向文件里面追加以下内容：

	JAVA_HOME=/usr/java/jdk1.7.0
	JRE_HOME=/usr/java/jdk1.7.0/jre
	PATH=$PATH:$JAVA_HOME/bin:$JRE_HOME/bin
	CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar:$JRE_HOME/lib
	export JAVA_HOME JRE_HOME PATH CLASSPATH

**使修改生效**

	[root@vm local]# source /etc/profile    //使修改立即生效 
	[root@vm local]# echo $PATH    //查看PATH值

**查看系统环境状态**

	[root@vm ~]# echo $PATH
	/usr/local/cmake/bin:/usr/lib64/qt-3.3/bin:/usr/local/sbin:/usr/local/bin:/sbin:/bin:/usr/sbin:/usr/bin:/usr/java/jdk1.7.0/bin:/usr/java/jdk1.7.0/jre/bin:/root/bin
	
### 8. VMware克隆CentOS网卡配置

	[root@vm bin]# vi /etc/udev/rules.d/70-persistent-net.rules
**删除掉 关于 eth0 的信息。修改 第二条 eth1 的网卡的名字为 eth0**

	[root@vm bin]# vi /etc/sysconfig/network-scripts/ifcfg-eth0
**修改MAC地址与70-persistent-net.rules中对应**