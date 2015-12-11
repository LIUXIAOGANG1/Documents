## Apache���� ##

### ׼���� 

	���ΰ�װApache�汾Ϊ2.4���ϣ���װǰ����ϵͳ�Դ��汾��������ڴ˰汾��Ҫ�Ƚ���ж�ء�
	
	�鿴���а汾��� httpd -v
	ж�ز�����# rpm -qa|grep httpd
	��������г����й���Apache���������Ҫ��ж�أ�������Ϊ�Ӵ�������ϵ��Ҫ���б��д�������һ��ж�ء��磺
	root@server ~]# rpm -qa|grep httpd 
	httpd-2.2.3-11.el5_2.centos.4 
	httpd-manual-2.2.3-11.el5_2.centos.4 
	
	# rpm �Ce httpd-manual-2.2.3-11.el5_2.centos.4 
	# rpm -e httpd-2.2.3-11.el5_2.centos.4
	ע�⣺ж�ص�ʱ�����ж�ز�����ϵͳһ�����ʾ����������ϵ�������г������İ������ƣ���ж����ʾ�����İ��Ϳ�����


### ��һ����Apache����
	
	����apr
		http://apr.apache.org/
		
	����apr-util
        http://apr.apache.org/  
    ����pcre
        http://www.pcre.org/
    ����httpd
        http://httpd.apache.org/
    ����openssl
       http://www.openssl.org/source/openssl-1.0.0a.tar.gz


	��http://zookeeper.apache.org/releases.htmlȥ�������°汾Zookeeper-3.4.5�İ�װ��zookeeper-3.4.5.tar.gz.
### �ڶ����� Apache��װ
	
** ׼�� **
	ȷ���Ƚ����˰�װlinux�ر����ÿ⣨Linux�бر�����֧�ֿ�İ�װ��http://blog.csdn.net/clevercode/article/details/45438401��

	# yum install -y gcc gdb strace gcc-c++ autoconf libjpeg libjpeg-devel libpng libpng-devel freetype freetype-devel libxml2 libxml2-devel zlib zlib-devel glibc glibc-devel glib2 glib2-devel bzip2 bzip2-devel ncurses ncurses-devel curl curl-devel e2fsprogs patch e2fsprogs-devel krb5-devel libidn libidn-devel openldap-devel nss_ldap openldap-clients openldap-servers libevent-devel libevent uuid-devel uuid mysql-devel

	
** ���÷���ǽ80�˿� **

	#�޸ķ���ǽ���ã� 
    # vi /etc/sysconfig/iptables
    #��������� 
    -A INPUT -m state --state NEW -m tcp -p tcp --dport 80 -j ACCEPT
    #��������ǽ 
    # service iptables restart


** ��װapr **

	1) ��ѹ
    # cd /usr/local/src/apache
    # tar zxvf  apr-1.5.1.tar.gz
    # cd apr-1.5.1
    2) ����
    # ./configure --prefix=/usr/local/apr
    3) ����
    # make
    4����װ
    # make install

** ��װapr-util **

	1)��ѹ
    # cd /usr/local/src/apache
    # tar zxvf apr-util-1.5.3.tar.gz
    # cd apr-util-1.5.3
    2)����
    # ./configure --prefix=/usr/local/apr-util --with-apr=/usr/local/apr/bin/apr-1-config
    3) ����
    # make
    4) ��װ
    # make install
	
	
** ������ʽ�ⰲװ **

	1����ѹpcre-8.12.tar.gz
    # cd /usr/local/src/apache 
    # tar zxvf pcre-8.12.tar.gz
    2�������ѹ���Ŀ¼
    # cd pcre-8.12 
    3������
    #  ./configure
    4) ����
    #  make
    5) ��װ
    #  make install

	
** ��װopenssl **

	1����ѹ
	#  tar zxvf openssl-1.0.0a.tar.gz
	2�������ѹ���Ŀ¼
	#  cd openssl-1.0.0a
	3������
	#  ./config --prefix=/usr/local/openssl
	4) ����
    #  make
    5) ��װ
    #  make install

	
** ��װapache **

	1)��ѹ
    # cd /usr/local/src/apache
    # tar -zvxf httpd-2.4.tar.gz
    # cd httpd-2.4.9
    2������
    # mkdir -p /usr/local/apache2
    # ./configure --prefix=/usr/local/apache2 --with-apr=/usr/local/apr --with-apr-util=/usr/local/apr-util --with-ssl=/usr/local/openssl --enable-ssl --enable-module=so --enable-rewrite --enable-cgid --enable-cgi
    3)����
    # make
    4)��װ
    #  make install


### ������������

** ����ServerName **

	# vi /usr/local/apache2/conf/httpd.conf
    1���ҵ���#ServerName www.example.com:80
    �޸�Ϊ��ServerName localhost:80

	2��ȥ������ģ��ǰ��#
	# LoadModule proxy_module modules/mod_proxy.so
	# LoadModule proxy_connect_module modules/mod_proxy_connect.so
	# LoadModule proxy_http_module modules/mod_proxy_http.so

	3�����ʼ�������# Secure (SSL/TLS) connectionsǰ����
	ProxyRequests On 
	ProxyVia On
	<Proxy "*">
	  Require ip 192.168.1
	</Proxy>


** ���apache����ϵͳ�������� **

	vi /etc/profile  #���apache����ϵͳ��������
    ��������������һ��
    export PATH=$PATH:/usr/local/apache2/bin


** ��apache���뵽ϵͳ���� **

	# cp /usr/local/apache2/bin/apachectl /etc/rc.d/init.d/httpd
    vi /etc/init.d/httpd  
    ��#!/bin/sh���������������
    #chkconfig:2345 10 90
    #description:Activates/Deactivates Apache Web Server


** ����Ŀ¼��������Ȩ�� **

	chown  daemon.daemon  -R /usr/local/apache2/htdocs  #����Ŀ¼������
    chmod   700 /usr/local/apache2/htdocs  -R #����apache��վĿ¼Ȩ��


** ���ÿ������� **

	# chkconfig httpd on

** ����ֹͣ���� **

	1)����
    # service httpd start
    2��ֹͣ
    # service httpd stop
    3������
    # service httpd restart
    4���鿴�˿�״̬
    # netstat -an | grep 80
