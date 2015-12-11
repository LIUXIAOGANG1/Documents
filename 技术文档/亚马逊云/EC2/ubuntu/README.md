## AWS 部署VPN ##

### 1. 安装pptpd 

    ubuntu@ip-172-31-36-133:~$ sudo apt-get install pptpd

### 2. 修改/etc/pptpd.conf文件, 在最先面添加以下2行
	
	localip 192.168.9.1 
	remoteip 192.168.9.11-30

	
### 5. 修改/etc/ppp/options.pptpd文件, 加上谷歌的dns

	ms-dns 8.8.8.8 
	ms-dns 8.8.4.4
 
### 6. 修改/etc/ppp/chap-secrets文件, 配置你自己VPN的用户名/密码,格式如下:
	<username> pptpd <passwd> *

	例如 gmfvpn pptpd passwrd *

### 7. 修改/etc/sysctl.conf文件, 添加以下内容(默认里面有这行, 把注释去掉也可)

	net.ipv4.ip_forward=1

### 8. 执行sudo /sbin/sysctl -p重新加载配置


### 9. 启用iptables的NAT configuration

	sudo iptables -t nat -A POSTROUTING -o eth0 -j MASQUERADE

### 10. 为了保证每次EC2实例重启后能自启动, 还要修改/etc/rc.local文件, 在exit 0这行上面加上:

	iptables -t nat -A POSTROUTING -o eth0 -j MASQUERADE

### 11. 重启pptpd服务

	sudo /etc/init.d/pptpd restart
	

## 特别注意 ##

	如果分配了弹性IP，没有绑定事例的话一定要释放这个ip，不然超过15分钟后就要扣钱的(只有绑定到运行中的ec2上才不扣钱)。
	没有分配的可以分配一个弹性IP，绑定自己的事例，这样不会
	每次重启而致使公网ip更改。

