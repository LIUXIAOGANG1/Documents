## ZooKeeper���� ##

### ׼���� 

	��������̨centos����Ϊ���һ����С�ļ�Ⱥ��������̨������������̨���������

### ��һ��������������

	(��װZookeeper��Ҫ��װJDK)

	��̨Centos server׼����֮�󣬼������ǵ�IP��ַ�ֱ�Ϊ

	192.168.1.2, 192.168.1.181, 192.168.1.121 

	�����ǵ�host�ֱ�����Ϊz1.zookeeper.com, z2.zookeeper.com, z3.zookeeper.com

### �ڶ���������Zookeeper
	��http://zookeeper.apache.org/releases.htmlȥ�������°汾Zookeeper-3.4.5�İ�װ��zookeeper-3.4.5.tar.gz.
### ����������װZookeeper
** ��ѹ **

	����ٶ���root��Ϊ�û������а�װ����zookeeper-3.4.5.tar.gz����/optĿ¼�£�Ȼ��ִ�С�tar zxf zookeeper-3.4.5.tar.gz����ѹ��

** ���� **

	��conf/zoo_sample.cfg����һ������Ϊzoo.cfg��Ҳ����confĿ¼�¡�

	Ȼ��������ֵ�޸����е����ã�

	tickTime=2000       

	dataDir=/var/lib/zookeeper/

	initLimit=5

	syncLimit=2

	clientPort=2181

	server.1=0.0.0.0:2888:3888(���õ�ǰ����Ϊ��0.0.0.0:2888:3888)

	server.2=z2.zookeeper.com:2888:3888

	server.3=z3.zookeeper.com:2888:3888


** ����/var/lib/zookeeper����Ŀ¼��������server id�ļ� **

	mkdir /var/lib/zookeeper

	cd /var/lib/zookeeper

	�ڸ�Ŀ¼�´�����Ϊmyid���ļ�������Ϊ1�����ֵ��server���ı䣬������ᵽ��

	echo 1 > myid 

** ���������ļ� **

	��server1���Ѿ����úõ�/opt/zookeeper-3.4.5/��/var/lib/zookeeper����Ŀ¼�ֱ𿽱���server2��server3��
	Ȼ�����ϵ�/var/lib/zookeeper/myid�������޸�Ϊ2��3���ظ����ò��裬ע���޸�server.2=0.0.0.0:2888:3888
	server.3=0.0.0.0:2888:3888

### ���Ĳ�������Zookeeper

	(ע��������һ̨��ʱ����ܻᱨ����ʾz2.zookeeper.com��z3.zookeeper.com����ȫ���������֮��Ͳ��ᱨ����)

	cd /opt/zookeeper-3.4.5/bin

	sh zkServer.sh start(����ZooKeeper)

	sh zkServer.sh stop(�ر�ZooKeeper)