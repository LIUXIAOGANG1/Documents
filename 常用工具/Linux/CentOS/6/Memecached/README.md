## Memechached ���� ##

### ׼���� 

	���Լ���centos��װ���ߣ��˴�ѡ��ȫ����װ��
	yum groupinstall -y "Development tools"

### ��һ������װlibevent
	
	cd libevent-2.0.22-stable
	./configure --prefix=/usr/local/libevent
	make
	make install

### �ڶ�������װmemcached-1.2.2

	cd memcached-1.2.2
	./configure --prefix=/usr/local/memcached --with-libevent=/usr/local/libevent
	Make
	make install
	###��װ����###
	memcached.c: �ں���'add_iov'��:
	memcached.c:696:30: ����: 'IOV_MAX'δ����(�ڴ˺����ڵ�һ��ʹ��)
	memcached.c:696:30: ��ע: ÿ��δ�����ı�ʶ��������ֵĺ�����ֻ����һ��
	make[2]: *** [memcached-memcached.o] ���� 1
	��Ҫ�޸�memcached.c�ļ�:
	/* FreeBSD 4.x doesn't have IOV_MAX exposed. */
	#ifndef IOV_MAX
	#if defined(__FreeBSD__) || defined(__APPLE__)
	# define IOV_MAX 1024
	#endif
	#endif
	�ĳ�:
	/* FreeBSD 4.x doesn't have IOV_MAX exposed. */
	#ifndef IOV_MAX
	# define IOV_MAX 1024
	#endif

	
### ������������memcached

	/usr/local/bin/memcached -m 32m -p 11211 -d -u root -P /var/run/memcached.pid -c 256
	###��������###
	[root@test memcached-1.2.2]# /usr/local/memcached/bin/memcached
	/usr/local/memcached/bin/memcached: error while loading shared libraries: libevent-2.0.so.5: cannot open shared object file: No such file or directory
	��Ҫ�޸ģ�
	ln -s /usr/local/libevent/lib/libevent-2.0.so.5 /lib64/libevent-2.0.so.5

### ע��
	��������˵����
	-d ѡ��������һ���ػ����̡�
	-u root ��ʾ����memcached���û�Ϊroot��
	-m �Ƿ����Memcacheʹ�õ��ڴ���������λ��MB��Ĭ��64MB��
	-M return error on memory exhausted (rather than removing items)��
	-u ������Memcache���û��������ǰΪroot �Ļ�����Ҫʹ�ô˲���ָ���û���
	-l �Ǽ����ķ�����IP��ַ��Ĭ��Ϊ����������
	-p ������Memcache��TCP�����Ķ˿ڣ������1024���ϵĶ˿ڡ�
	-c ѡ����������еĲ�����������Ĭ����1024��
	-P �����ñ���Memcache��pid�ļ���
	-f <factor> chunk size growth factor (default: 1.25)��
	-I Override the size of each slab page. Adjusts max item size(1.4.2�汾����)��  Ҳ������������ػ����̣����Ƕ˿ڲ����ظ�


	��telnet����memcached
	Yum install telnet
	telnet 127.0.0.1 11211
	Trying 127.0.0.1��
	Connected to zou.yunhao (127.0.0.1).
	Escape character is ��^]��.
	set key 0 10 6   //10��ʾ����ʱ��10�룬6��ʾ��Ҫ���������ֽ�Ϊ6������resultΪ6��
	result
	STORED
	get key
	VALUE key 0 6
	result
	END


## Memacached��� ##

** ��װapache���� **

	yum install httpd

** ��װphp���� **

	yum install php
	yum install php-devel

** ��װmemcached��php����չ **
	A:��װ��չ��
	yum install php-pear
	yum install php-pecl-memcache
	
	B: ��װMemcache
	tar zxvf memcache-2.2.1.tgz
	cd memcache-2.2.1
	/usr/bin/phpize
	./configure --enable-memcache  --enable-shared  --with-php-config=/usr/bin/php-config --with-zlib-dir

	ע����ʱ������Ϊû�а�װzlib-devel��ֱ��make�Ļ�����
	yum install zlib-devel

	make && make install
	������ʾ
	Installing shared extensions:     /usr/lib64/php/modules/
	����ʾ����Ϣ�ŵ���һ���е�extension_dir�С�

	C: �޸�php.ini�����ļ�
	vi /etc/php.ini
	Ȼ���޸�php.ini 
	;extension_dir = "./" 
	�޸�Ϊ 
	;extension_dir = "/usr/lib64/php/modules/" 
	�����һ�� 
	;extension=memcache.so  
	��php֧��memcached
	
	D: ����apache����
	service httpd restart


** ��װMemAdmin���� **
	
	A: ��װmemAdmin�����
	tar �Czxvf memadmin-1.0.12.tar.gz
	mv memadmin  /var/www/html
	
	B: �޸�/etc/httpd/conf/httpd.conf�����ļ�
	vi /etc/httpd/conf/httpd.conf
	�޸����£�
	1��DocumentRoot "/var/www/html"
	2��DirectoryIndex index.html index.html.var index.php
	3��
	<Directory"/var/www/html">
	cumentRoot"/var/www/html"
	   Options Indexes FollowSymLinks
	   AllowOverride None
	   Order allow,deny
	   Allow from all
	</Directory>
	
	C: �޸�selinux����
	vi /etc/selinux/config
	����SELINUX=disabled


** ���� **
	����memcached
	/usr/local/bin/memcached -m 32m -p 11211 -d -u root -P /var/run/memcached.pid -c 256

	���ĩβ���� �� -vv >> /tmp/memcached.log
	���ʾmemcached��־�����/tmp/memcached.log�ļ��¡�
	����tail �Cf /tmp/memcached.log�鿴��־�����

	���ϲ�����ɺ󣬿��������������http://your_ip/memAdmin/index.php,�û������Ĭ��Ϊadmin,���Ҫ�޸��û����������ͨ��/var/www/html/memAdmin/config.php�ļ������޸�

	������֮����ʿ�������403��������һ�·������Ϳ�����
