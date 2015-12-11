## Memechached 配置 ##

### 准备： 

	给自己的centos安装工具，此处选择全部安装。
	yum groupinstall -y "Development tools"

### 第一步：安装libevent
	
	cd libevent-2.0.22-stable
	./configure --prefix=/usr/local/libevent
	make
	make install

### 第二步：安装memcached-1.2.2

	cd memcached-1.2.2
	./configure --prefix=/usr/local/memcached --with-libevent=/usr/local/libevent
	Make
	make install
	###安装错误###
	memcached.c: 在函数'add_iov'中:
	memcached.c:696:30: 错误: 'IOV_MAX'未声明(在此函数内第一次使用)
	memcached.c:696:30: 附注: 每个未声明的标识符在其出现的函数内只报告一次
	make[2]: *** [memcached-memcached.o] 错误 1
	需要修改memcached.c文件:
	/* FreeBSD 4.x doesn't have IOV_MAX exposed. */
	#ifndef IOV_MAX
	#if defined(__FreeBSD__) || defined(__APPLE__)
	# define IOV_MAX 1024
	#endif
	#endif
	改成:
	/* FreeBSD 4.x doesn't have IOV_MAX exposed. */
	#ifndef IOV_MAX
	# define IOV_MAX 1024
	#endif

	
### 第三步：启动memcached

	/usr/local/bin/memcached -m 32m -p 11211 -d -u root -P /var/run/memcached.pid -c 256
	###启动错误###
	[root@test memcached-1.2.2]# /usr/local/memcached/bin/memcached
	/usr/local/memcached/bin/memcached: error while loading shared libraries: libevent-2.0.so.5: cannot open shared object file: No such file or directory
	需要修改：
	ln -s /usr/local/libevent/lib/libevent-2.0.so.5 /lib64/libevent-2.0.so.5

### 注意
	启动参数说明：
	-d 选项是启动一个守护进程。
	-u root 表示启动memcached的用户为root。
	-m 是分配给Memcache使用的内存数量，单位是MB，默认64MB。
	-M return error on memory exhausted (rather than removing items)。
	-u 是运行Memcache的用户，如果当前为root 的话，需要使用此参数指定用户。
	-l 是监听的服务器IP地址，默认为所有网卡。
	-p 是设置Memcache的TCP监听的端口，最好是1024以上的端口。
	-c 选项是最大运行的并发连接数，默认是1024。
	-P 是设置保存Memcache的pid文件。
	-f <factor> chunk size growth factor (default: 1.25)。
	-I Override the size of each slab page. Adjusts max item size(1.4.2版本新增)。  也可以启动多个守护进程，但是端口不能重复


	用telnet测试memcached
	Yum install telnet
	telnet 127.0.0.1 11211
	Trying 127.0.0.1…
	Connected to zou.yunhao (127.0.0.1).
	Escape character is ‘^]’.
	set key 0 10 6   //10表示过期时间10秒，6表示将要存入数据字节为6（这里result为6）
	result
	STORED
	get key
	VALUE key 0 6
	result
	END


## Memacached监控 ##

** 安装apache服务 **

	yum install httpd

** 安装php服务 **

	yum install php
	yum install php-devel

** 安装memcached在php的扩展 **
	A:安装扩展包
	yum install php-pear
	yum install php-pecl-memcache
	
	B: 安装Memcache
	tar zxvf memcache-2.2.1.tgz
	cd memcache-2.2.1
	/usr/bin/phpize
	./configure --enable-memcache  --enable-shared  --with-php-config=/usr/bin/php-config --with-zlib-dir

	注：此时可能因为没有安装zlib-devel包直接make的话报错
	yum install zlib-devel

	make && make install
	最后会显示
	Installing shared extensions:     /usr/lib64/php/modules/
	把显示的信息放到下一步中的extension_dir中。

	C: 修改php.ini配置文件
	vi /etc/php.ini
	然后修改php.ini 
	;extension_dir = "./" 
	修改为 
	;extension_dir = "/usr/lib64/php/modules/" 
	并添加一行 
	;extension=memcache.so  
	让php支持memcached
	
	D: 重启apache服务
	service httpd restart


** 安装MemAdmin程序 **
	
	A: 安装memAdmin程序包
	tar –zxvf memadmin-1.0.12.tar.gz
	mv memadmin  /var/www/html
	
	B: 修改/etc/httpd/conf/httpd.conf配置文件
	vi /etc/httpd/conf/httpd.conf
	修改如下：
	1、DocumentRoot "/var/www/html"
	2、DirectoryIndex index.html index.html.var index.php
	3、
	<Directory"/var/www/html">
	cumentRoot"/var/www/html"
	   Options Indexes FollowSymLinks
	   AllowOverride None
	   Order allow,deny
	   Allow from all
	</Directory>
	
	C: 修改selinux配置
	vi /etc/selinux/config
	设置SELINUX=disabled


** 测试 **
	启动memcached
	/usr/local/bin/memcached -m 32m -p 11211 -d -u root -P /var/run/memcached.pid -c 256

	如果末尾加上 ： -vv >> /tmp/memcached.log
	则表示memcached日志输出到/tmp/memcached.log文件下。
	可以tail –f /tmp/memcached.log查看日志输出。

	以上步骤完成后，可以在浏览器输入http://your_ip/memAdmin/index.php,用户密码均默认为admin,如果要修改用户、密码可以通过/var/www/html/memAdmin/config.php文件进行修改

	部署完之后访问可能遇到403错误，重启一下服务器就可以了
