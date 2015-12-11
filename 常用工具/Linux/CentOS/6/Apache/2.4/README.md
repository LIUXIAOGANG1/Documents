## Apache配置 ##

### 准备： 

	本次安装Apache版本为2.4以上，安装前需检查系统自带版本，如果低于此版本需要先进行卸载。
	
	查看现有版本命令： httpd -v
	卸载操作：# rpm -qa|grep httpd
	此命令会列出所有关于Apache的组件，需要都卸载，理论上为接触依赖关系需要从列表中从下往上一次卸载。如：
	root@server ~]# rpm -qa|grep httpd 
	httpd-2.2.3-11.el5_2.centos.4 
	httpd-manual-2.2.3-11.el5_2.centos.4 
	
	# rpm –e httpd-manual-2.2.3-11.el5_2.centos.4 
	# rpm -e httpd-2.2.3-11.el5_2.centos.4
	注意：卸载的时候如果卸载不掉，系统一般会提示包的依赖关系，并且列出依赖的包的名称，先卸载提示依赖的包就可以了


### 第一步：Apache下载
	
	下载apr
		http://apr.apache.org/
		
	下载apr-util
        http://apr.apache.org/  
    下载pcre
        http://www.pcre.org/
    下载httpd
        http://httpd.apache.org/
    下载openssl
       http://www.openssl.org/source/openssl-1.0.0a.tar.gz


	到http://zookeeper.apache.org/releases.html去下载最新版本Zookeeper-3.4.5的安装包zookeeper-3.4.5.tar.gz.
### 第二步： Apache安装
	
** 准备 **
	确保先进行了安装linux必备常用库（Linux中必备常用支持库的安装：http://blog.csdn.net/clevercode/article/details/45438401）

	# yum install -y gcc gdb strace gcc-c++ autoconf libjpeg libjpeg-devel libpng libpng-devel freetype freetype-devel libxml2 libxml2-devel zlib zlib-devel glibc glibc-devel glib2 glib2-devel bzip2 bzip2-devel ncurses ncurses-devel curl curl-devel e2fsprogs patch e2fsprogs-devel krb5-devel libidn libidn-devel openldap-devel nss_ldap openldap-clients openldap-servers libevent-devel libevent uuid-devel uuid mysql-devel

	
** 配置防火墙80端口 **

	#修改防火墙配置： 
    # vi /etc/sysconfig/iptables
    #添加配置项 
    -A INPUT -m state --state NEW -m tcp -p tcp --dport 80 -j ACCEPT
    #重启防火墙 
    # service iptables restart


** 安装apr **

	1) 解压
    # cd /usr/local/src/apache
    # tar zxvf  apr-1.5.1.tar.gz
    # cd apr-1.5.1
    2) 配置
    # ./configure --prefix=/usr/local/apr
    3) 编译
    # make
    4）安装
    # make install

** 安装apr-util **

	1)解压
    # cd /usr/local/src/apache
    # tar zxvf apr-util-1.5.3.tar.gz
    # cd apr-util-1.5.3
    2)配置
    # ./configure --prefix=/usr/local/apr-util --with-apr=/usr/local/apr/bin/apr-1-config
    3) 编译
    # make
    4) 安装
    # make install
	
	
** 正则表达式库安装 **

	1）解压pcre-8.12.tar.gz
    # cd /usr/local/src/apache 
    # tar zxvf pcre-8.12.tar.gz
    2）进入解压后的目录
    # cd pcre-8.12 
    3）配置
    #  ./configure
    4) 编译
    #  make
    5) 安装
    #  make install

	
** 安装openssl **

	1）解压
	#  tar zxvf openssl-1.0.0a.tar.gz
	2）进入解压后的目录
	#  cd openssl-1.0.0a
	3）配置
	#  ./config --prefix=/usr/local/openssl
	4) 编译
    #  make
    5) 安装
    #  make install

	
** 安装apache **

	1)解压
    # cd /usr/local/src/apache
    # tar -zvxf httpd-2.4.tar.gz
    # cd httpd-2.4.9
    2）编译
    # mkdir -p /usr/local/apache2
    # ./configure --prefix=/usr/local/apache2 --with-apr=/usr/local/apr --with-apr-util=/usr/local/apr-util --with-ssl=/usr/local/openssl --enable-ssl --enable-module=so --enable-rewrite --enable-cgid --enable-cgi
    3)编译
    # make
    4)安装
    #  make install


### 第三步：配置

** 配置ServerName **

	# vi /usr/local/apache2/conf/httpd.conf
    1）找到：#ServerName www.example.com:80
    修改为：ServerName localhost:80

	2）去掉下列模块前的#
	# LoadModule proxy_module modules/mod_proxy.so
	# LoadModule proxy_connect_module modules/mod_proxy_connect.so
	# LoadModule proxy_http_module modules/mod_proxy_http.so

	3）在问价最后面的# Secure (SSL/TLS) connections前加上
	ProxyRequests On 
	ProxyVia On
	<Proxy "*">
	  Require ip 192.168.1
	</Proxy>


** 添加apache服务系统环境变量 **

	vi /etc/profile  #添加apache服务系统环境变量
    在最后添加下面这一行
    export PATH=$PATH:/usr/local/apache2/bin


** 把apache加入到系统启动 **

	# cp /usr/local/apache2/bin/apachectl /etc/rc.d/init.d/httpd
    vi /etc/init.d/httpd  
    在#!/bin/sh下面添加以下两行
    #chkconfig:2345 10 90
    #description:Activates/Deactivates Apache Web Server


** 更改目录所有者与权限 **

	chown  daemon.daemon  -R /usr/local/apache2/htdocs  #更改目录所有者
    chmod   700 /usr/local/apache2/htdocs  -R #更改apache网站目录权限


** 设置开机启动 **

	# chkconfig httpd on

** 启动停止重启 **

	1)启动
    # service httpd start
    2）停止
    # service httpd stop
    3）重启
    # service httpd restart
    4）查看端口状态
    # netstat -an | grep 80
