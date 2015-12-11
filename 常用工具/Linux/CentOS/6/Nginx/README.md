## Nginx 配置 ##

### 准备： 

	1、首先给自己的centos安装工具，此处选择全部安装。
	yum groupinstall -y "Development tools"

### 第一步：创建文件 /etc/yum.repos.d/nginx.repo

	vi /etc/yum.repos.d/nginx.repo
	
	并编辑内容为：
	
	[nginx]
	name=nginx repo
	baseurl=http://nginx.org/packages/centos/6/x86_64/
	gpgcheck=0
	enabled=1

### 第二步：安装

	一键安装，命令：yum install nginx
	
### 第三步：配置

	配置文件：nginx.conf
	
	user  nginx;
	worker_processes  1;

	error_log  /var/log/nginx/error.log warn;
	pid        /var/run/nginx.pid;


	events {
		worker_connections  2048;
	}
	http {
		include       /etc/nginx/mime.types;
		default_type  application/octet-stream;

		log_format  main  '$remote_addr - $remote_user [$time_local] "$request" '
						  '$status $body_bytes_sent "$http_referer" '
						  '"$http_user_agent" "$http_x_forwarded_for"';

		access_log  /var/log/nginx/access.log  main;

		sendfile        on;
		#tcp_nopush     on;

		keepalive_timeout  65;

		#gzip  on;

			upstream puppetmaster {
					##/usr下的tomcat
					#server 127.0.0.1:8180;
					##/opt下的tomcat
					server 127.0.0.1:8190;
			}
			server {
					listen 8100;

					location / {
							##设置跳转到puppetmaster负载均衡
							proxy_pass http://puppetmaster;
							proxy_redirect off;
							proxy_set_header Host $host;
							proxy_set_header X-Real-IP $remote_addr;
							proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
							proxy_set_header X-Client-Verify $ssl_client_verify;
							proxy_set_header X-SSL-Subject $ssl_client_s_dn;
							proxy_set_header X-SSL-Issuer $ssl_client_i_dn;
							proxy_buffer_size 10m;
							proxy_buffers 1024 10m;
							proxy_busy_buffers_size 10m;
							proxy_temp_file_write_size 10m;
							proxy_read_timeout 120;
					}
			}
	}
