## Nginx ���� ##

### ׼���� 

	1�����ȸ��Լ���centos��װ���ߣ��˴�ѡ��ȫ����װ��
	yum groupinstall -y "Development tools"

### ��һ���������ļ� /etc/yum.repos.d/nginx.repo

	vi /etc/yum.repos.d/nginx.repo
	
	���༭����Ϊ��
	
	[nginx]
	name=nginx repo
	baseurl=http://nginx.org/packages/centos/6/x86_64/
	gpgcheck=0
	enabled=1

### �ڶ�������װ

	һ����װ�����yum install nginx
	
### ������������

	�����ļ���nginx.conf
	
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
					##/usr�µ�tomcat
					#server 127.0.0.1:8180;
					##/opt�µ�tomcat
					server 127.0.0.1:8190;
			}
			server {
					listen 8100;

					location / {
							##������ת��puppetmaster���ؾ���
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
