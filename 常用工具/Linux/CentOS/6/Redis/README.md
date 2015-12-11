## Redis ���� ##

### ��һ��������������

	(��װZookeeper��Ҫ��װJDK)

	��̨Centos server׼����֮�󣬼������ǵ�IP��ַ�ֱ�Ϊ

	192.168.1.2, 192.168.1.181, 192.168.1.121 

	�����ǵ�host�ֱ�����Ϊz1.zookeeper.com, z2.zookeeper.com, z3.zookeeper.com

### �ڶ��������� Redis ��װ��

	[root@root ~]# cd /usr/local/src
	
	[root@root src]# wget http://download.redis.io/releases/redis-2.8.3.tar.gz

	
### ����������װ
** ��ѹ **

	[root@root src]# tar zxvf redis-2.8.3.tar.gz
	
** ���� **
	
	[root@root src]# cd redis-2.8.3
	[root@root redis-2.8.3]# make

	
** ���� **

	ִ����󣬻��ڵ�ǰĿ¼�е�srcĿ¼��������Ӧ��ִ���ļ����磺redis-server redis-cli�ȣ�
	������/usr/local/Ŀ¼�д���redisλ��Ŀ¼����Ӧ�����ݴ洢Ŀ¼�������ļ�Ŀ¼�ȣ�
	[root@root redis-2.8.3]# mkdir /usr/local/redis/{conf,run,db} -pv
	[root@root redis-2.8.3]# cd /usr/local/src/redis-2.8.3/
	[root@root redis-2.8.3]# cp redis.conf /usr/local/redis/conf
	[root@root redis-2.8.3]# cd src
	[root@root src]# cp redis-benchmark redis-check-aof redis-check-dump redis-cli redis-server mkreleasehdr.sh /usr/local/redis/
	
	����Redis��װ�����

### ���Ĳ�������

	��������������һ�£����鿴��Ӧ�Ķ˿��Ƿ��Ѿ�������
	
	[root@root ~]# /usr/local/redis/redis-server /usr/local/redis/conf/redis.conf
	
### ���岽�������ļ�

	��ʱ�������е����ö���Ĭ�ϵģ����Կ���redis��ǰ̨���У�Ҳ������redis�ں�̨���У�����Ҫ�޸�һ��redis�������ļ��ˣ���Ҫ��redis.conf����ļ���
	��������ļ�˵�����£�

	
	daemonize yes  #---Ĭ��ֵno���ò������ڶ���redis�����Ƿ����ػ�ģʽ���С�---
	pidfile /usr/local/webserver/redis/run/redis.pid  #Ĭ��ֵ/var/run/redis.pid��ָ��redis����Ľ��̺��ļ�·�������ػ�ģʽ����ʱ��Ҫ���ñ�������
	port 6379   #Ĭ��ֵ6379��ָ��redis����Ķ˿�
	# bind 127.0.0.1  #��ip��Ĭ���Ǳ������������豸��
	timeout 0   #�ͻ��˿���n���Ͽ����ӣ�Ĭ���� 0 ��ʾ���Ͽ���
	loglevel notice  ###���÷���˵���־���������м���ѡ��
		debug����¼��ϸ��Ϣ�����ڿ�������ԣ�
		verbose���ṩ�ܶ����õ���Ϣ�������ֲ���debug��ô�꾡��Ĭ�Ͼ�����һѡ�
		notice���ʶ����ѣ������ڲ�Ʒ������
		warning������ʾ��Ҫ�ľ�����Ϣ��
	logfile stdout   ##ָ����־�����·����Ĭ��ֵstdout����ʾ�������Ļ���ػ�ģʽʱ�������/dev/null��
	���Ҫ�����־��syslog�У���������syslog-enabled yes��Ĭ�ϸ�ѡ��ֵΪno��
	# syslog-enabled no
	databases 16   ###ָ�����ݿ��������Ĭ��Ϊ16����Ĭ��ʹ�õ����ݿ���DB 0��
	----����Ϊ������ص�����:------
	#   save <seconds> <changes>  ##ָ���೤ʱ��ˢ�¿��������̣����ѡ������������ֵ��ֻ�е���������ֵ������ʱ�Żᴥ�����������ö��ּ�������Ĭ�ϵĲ����ļ��о������ˣ�
	save 900 1��ÿ900��(15����)����һ�μ�ֵ���ʱ��������
	save 300 10��ÿ300��(5����)����10�μ�ֵ���ʱ��������
	save 60 10000��ÿ60������10000�μ�ֵ���ʱ��������
	save 900 1
	save 300 10
	save 60 10000
	rdbcompression yes  ##Ĭ��ֵyes����dump���ݿ�ʱʹ��LZFѹ���ַ����������CPU��Դ�ȽϽ��ţ���������Ϊno��ѡ��ѹ����
	rdbchecksum yes
	# The filename where to dump the DB  ���ݿ��ļ���
	dbfilename dump.rdb  ##Ĭ��ֵdump.rdb��dump���ļ�ϵͳ�е��ļ���
	dir /usr/local/webserver/redis/db  ##Ĭ��ֵ./������ǰĿ¼��dump���������ļ��Ĵ洢·����
	----����Ϊ������ص����ã�����Ĭ���ǲ����õģ������Ĭ�ϵĲ����ļ����б��������ע��----
	# slaveof <masterip> <masterport>  ##ָ������ip�Ͷ˿ڣ����ڴ���һ���������
	# masterauth <master-password>  ##���master����������Ļ����˴�Ҳ�������ã�
	slave-serve-stale-data yes  ##Ĭ��ֵyes����slave��ʧ��master�˵����ӣ����߸������ڴ�����ôslave�����������ֱ��֣�
	��������ֵΪyesʱ��slaveΪ������Ӧ�ͻ������󣬾��������Ѳ�ͬ������û������(�����ڳ���ͬ���������)��
	��������ֵΪnoʱ��slave�᷵��"SYNC with master in progreee"�Ĵ�����Ϣ��
	slave-read-only yes  ##Ĭ�ϴ�Redis��ֻ��ģʽ
	# repl-ping-slave-period 10  ###Ĭ��ֵ10��ָ��slave����ping master�����ڣ�
	# repl-timeout 60  ##Ĭ��ֵ60��ָ����ʱʱ�䡣ע�Ȿ�������������������ݺ�ping��Ӧ��ʱ�䡣
	------����Ϊ��ȫ��ص�����------
	# requirepass foobared  ###ָ��һ�����룬�ͻ�������ʱҲ��Ҫͨ��������ܳɹ����ӣ�
	# rename-command CONFIG b840fc02d524045429941cc15f59e41cb7be6c52  ###�ض���������罫CONFIG�������Ϊһ���ܸ��ӵ����֣�
	# rename-command CONFIG ""  ȡ��������
	-----����Ϊ��Դ���Ʒ��������------
	# maxclients 10000  ##ָ���ͻ��˵���󲢷���������Ĭ����û�����ƣ�ֱ��redis�޷������µĽ���Ϊֹ�����øò���ֵΪ0Ҳ��ʾ�����ƣ�����ò���ָ����ֵ�����������Ӵﵽָ��ֵʱ��redis��ر����������ӣ�������'max number of clients reached'�Ĵ�����Ϣ��
	# maxmemory <bytes>  ###����redis����ʹ���ڴ档���ﵽ����ڴ��redis�᳢�԰������õĻ��ղ���ɾ����ֵ������޷�ɾ����ֵ�����߱�����������Ϊ���������ôredis�ͻ��򷢳��ڴ�����󷵻ش�����Ϣ������redis��Ϊһ��LRU�Ļ���ʱ��������Ϊ���á�
	# maxmemory-policy volatile-lru  ###Ĭ��ֵvolatile-lru��ָ��������ԣ������м��ַ�����
	volatile-lru -> remove the key with an expire set using an LRU algorithm
	allkeys-lru -> remove any key accordingly to the LRU algorithm
	volatile-random -> remove a random key with an expire set
	allkeys->random -> remove a random key, any key
	volatile-ttl -> remove the key with the nearest expire time (minor TTL)
	noeviction -> don't expire at all, just return an error on write operations
	# maxmemory-samples 3    ###Ĭ��ֵ3��LRU����СTTL���Բ����Ͻ��Ĳ��ԣ����Ǵ�Լ����ķ�ʽ����˿���ѡ��ȡ��ֵ�Ա��顣
	-----����ΪAPPEND������----
	ONLYģʽ�����ã�Ĭ�������redis�����첽��ʽdump���ݵ������ϣ��������������ܻᵼ�¶�ʧ��������(���������ͻȻ崻�)��������ݱȽ���Ҫ����ϣ����ʧ����������ֱд��ģʽ������ģʽ��redis�Ὣ���н��յ���д����ͬ����appendonly.aof�ļ��У����ļ�����redis��������ʱ���ڴ����ؽ��������ݡ�ע������ģʽ������Ӱ��ǳ�֮��
	appendonly no  ##Ĭ��ֵno��ָ���Ƿ�����ֱдģʽ��
	# appendfilename appendonly.aof  ###ֱдģʽ��Ĭ���ļ���appendonly.aof
	appendfsync������fsync()��ʽ�ò���ϵͳд���ݵ������ϣ�����ͬ����ʽ�������м���ģʽ��
		always��ÿ�ζ����ã����簲ȫ�����ٶ�������
		everysec��ÿ��ͬ������Ҳ��Ĭ�Ϸ�ʽ��
		no��������fsync���ɲ���ϵͳ������ʱͬ����������ģʽ��
		no-appendfsync-on-rewrite��Ĭ��ֵno����AOF fsync��������Ϊalways��everysec����̨������̻�ִ�д�����I/O������ĳЩlinux������redis���ܻ����������fsync()���á�
		auto-aof-rewrite-percentage��Ĭ��ֵ100
		auto-aof-rewrite-min-size��Ĭ��ֵ64mb
	# appendfsync always
	appendfsync everysec
	# appendfsync no
	-----����Ϊ�߼�������ص�����----
	hash-max-zipmap-entries��Ĭ��ֵ512����ĳ��map��Ԫ�ظ����ﵽ���ֵ�������������Ԫ�صĳ���û�дﵽ�趨��ֵʱ����HASH�ı������һ������ķ�ʽ(����Ч�����ڴ�)��������������Ĳ������ʹ�������������ֵ������Ԫ�ظ�����
	hash-max-zipmap-value��Ĭ��ֵ64������map��Ԫ�ص�ֵ����󳤶ȣ�������
	list-max-ziplist-entries��Ĭ��ֵ512����hash���ƣ�����������list����Ҳ���������ķ�ʽ�Խ�ʡ�ռ䡣
	list-max-ziplist-value��Ĭ��ֵ64
	set-max-intset-entries��Ĭ��ֵ512����set�����е����ݶ�����ֵ���ͣ�����set������Ԫ�ص�����������ָ��ֵʱ��ʹ������ı��뷽ʽ��
	zset-max-ziplist-entries��Ĭ��ֵ128����hash��list���ơ�
	zset-max-ziplist-value��Ĭ��ֵ64
	activerehashing��Ĭ��ֵyes�����������Ƿ��Զ��ؽ�hash��Active rehashingÿ100΢��ʹ��1΢��cpuʱ������������Redis��hash���ؽ���ͨ��һ��lazy��ʽ��д��hash��Ĳ���Խ�࣬��Ҫִ��rehashing�Ĳ���ҲԽ�࣬�����������ǰ���У���ôrehashing������һֱִ�С������ʵʱ��Ҫ��ϸߣ����Խ���redisʱ��ʱ���ֵ�2΢����ӳ٣����������activerehashingΪno������������Ϊyes���Խ�ʡ�ڴ�ռ䡣

	����Redis�İ�װ�����õ��˽�����
	
	
### ��������Redis���

	��װ (��֤�Լ���python�汾��2.7֮��)
	CENTOS 6.X ϵ��Ĭ�ϰ�װ�� Python 2.6 ��Ŀǰ��������Ҫ��ʹ�� Python 2.7 ���������汾֮�仹���в��ٲ���ģ������� Python 2.6 �¾���������⡣��װ2.7�汾��
	wget https://www.python.org/ftp/python/2.7.8/Python-2.7.8.tgz
	tar xf Python-2.7.8.tgz
	cd Python-2.7.8
	./configure --prefix=/usr/local
	make && make install

	yum install python-setuptools
	yum install python-devel
	easy_install pip

	pip install tornado		// ����װ�Ļ�������redis-live.py��ʱ�򱨴�
	pip install redis
	pip install python-dateutil
	pip install argparse

	yum install git
	cd /root
	git clone https://github.com/ kumarnitin/RedisLive.git
	���� �� git clone https://github.com/nkrode/RedisLive.git

	###�޸�redis-live.conf�ļ�
	{
		"RedisServers":
		[ 
			{
				"server": "10.20.111.188",
				"port" : 6379
			}
			 
		],
	 
		"DataStoreType" : "redis",
	 
		"RedisStatsServer":
		{
			"server" : "10.20.111.188",
			"port" : 6380
		},
		 
		"SqliteStatsStore" :
		{
			"path":  "to your sql lite file"
		}
	}
	###�޸����
	����redis
	/usr/local/redis/redis-server /usr/local/redis/conf/redis.conf
	###������ط���ÿ30����һ��
	./redis-monitor.py --duration=30
	###�ٴο���һ���նˣ�����/root/RedisLive/srcĿ¼������web����
	./redis-live.py
   ����������룺 http://your_ip:8888/index.html   ���ɿ�����ͼ
