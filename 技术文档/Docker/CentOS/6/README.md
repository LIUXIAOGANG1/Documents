## CentOS 6 部署使用 Docker ##

### 1. 检查Device Mapper

	[root@vm ~]# ls -l /sys/class/misc/device-mapper/
	total 0
	-r--r--r-- 1 root root 4096 Oct 28 19:00 dev
	drwxr-xr-x 2 root root    0 Oct 28 19:00 power
	lrwxrwxrwx 1 root root    0 Oct 28 18:57 subsystem -> ../../../../class/misc
	-rw-r--r-- 1 root root 4096 Oct 28 18:57 uevent
	
	[root@vm ~]# grep device-mapper /proc/devices
	253 device-mapper

### 2. 安装Device Mapper

	[root@vm ~]# yum install -y device-mapper
安装完成后，还需要加载dm_mod模块
	
	[root@vm ~]# modprobe dm_mod
	
### 3. 安装EPEL

	[root@vm ~]# rpm -ivh http://download.fedoraproject.org/pub/epel/6/i386/epel-release-6-8.noarch.rpm

### 4. 安装Docker

	[root@vm ~]# yum -y install docker-io

### 5. 启动Docker

	[root@vm ~]# service docker start
	
### 6. 查看Docker状态
	
	[root@vm ~]# service docker status

### 7. 查看Docker信息

	[root@vm ~]# docker info
	Containers: 0
	Images: 0
	Storage Driver: devicemapper
	 Pool Name: docker-253:0-262853-pool
	 Pool Blocksize: 65.54 kB
	 Backing Filesystem: extfs
	 Data file: /dev/loop0
	 Metadata file: /dev/loop1
	 Data Space Used: 305.7 MB
	 Data Space Total: 107.4 GB
	 Data Space Available: 51.64 GB
	 Metadata Space Used: 729.1 kB
	 Metadata Space Total: 2.147 GB
	 Metadata Space Available: 2.147 GB
	 Udev Sync Supported: true
	 Deferred Removal Enabled: false
	 Data loop file: /var/lib/docker/devicemapper/devicemapper/data
	 Metadata loop file: /var/lib/docker/devicemapper/devicemapper/metadata
	 Library Version: 1.02.89-RHEL6 (2014-09-01)
	Execution Driver: native-0.2
	Logging Driver: json-file
	Kernel Version: 2.6.32-504.el6.x86_64
	Operating System: <unknown>
	CPUs: 1
	Total Memory: 980.8 MiB
	Name: vm.centos.com
	ID: QQ3G:WRLT:EZL4:MBEZ:TFPY:WJMC:HXCZ:SNE5:S7VK:MVRD:QPVS:4AMX

### 8. [Docker官方命令列表](http://docs.docker.com/reference/commandline/cli/)

### 9. 运行一个容器

	[root@vm ~]# docker run -i -t ubuntu /bin/bash
	
### 10. 退出容器

	root@2918496d057d:/# exit
	exit
	[root@vm ~]#
	
### 11. 查看容器列表

	[root@vm ~]# docker ps -a
	CONTAINER ID        IMAGE               COMMAND             CREATED             STATUS                          PORTS               NAMES
	2918496d057d        ubuntu              "/bin/bash"         About an hour ago   Exited (0) About a minute ago                       drunk_banach

### 12. 重新启动容器

	[root@vm ~]# docker start 2918496d057d
	2918496d057d

### 13. 附着到容器上

	[root@vm ~]# docker attach 2918496d057d
	root@2918496d057d:/#

### 14. 容器改名
	
	[root@vm ~]# docker run --name skype_ubuntu -i -t ubuntu /bin/bash
	root@d155c966573e:/#

### 15. 删除容器

	[root@vm ~]# docker rm 2918496d057d
	2918496d057d

