package Util;

public class BakRMB {
	//����������˺ţ����˺�Ϊ11λ����������̻����+01,�ò������
	String merchantAcctId = "1001214986301";
	//���뷽ʽ��1���� UTF-8; 2 ���� GBK; 3���� GB2312 Ĭ��Ϊ1,�ò������
	String inputCharset = "1";
	//����֧�������ҳ���ַ���ò���һ����Ϊ�ռ��ɡ�
	String pageUrl = "";
	//����������֧������ĺ�̨��ַ���ò��������д������Ϊ�ա�
	//String bgUrl = "http://219.233.173.50:8801/RMBPORT22/receive.jsp";
	String bgUrl ="http://192.168.122.70:8080/RMBPORT22/receive.jsp";
	
	//���ذ汾���̶�ֵ��v2.0,�ò������
	String version =  "v3.0";
	//�������࣬1����������ʾ��2����Ӣ����ʾ��Ĭ��Ϊ1,�ò������
	String language =  "1";
	//ǩ������,��ֵΪ4������PKI���ܷ�ʽ,�ò������
	String signType =  "4";
	//֧��������,����Ϊ�ա�
	String payerName= ""; 
	//֧������ϵ���ͣ�1 ��������ʼ���ʽ��2 �����ֻ���ϵ��ʽ������Ϊ�ա�
	String payerContactType =  "1";
	//֧������ϵ��ʽ����payerContactType���ö�Ӧ��payerContactTypeΪ1������д�����ַ��payerContactTypeΪ2������д�ֻ����롣����Ϊ�ա�
	String payerContact =  "888888888@qq.com";
	//�̻������ţ����²���ʱ�������嶩���ţ��̻����Ը����Լ������ŵĶ�������������ֵ������Ϊ�ա�
	String orderId = new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
	//����������ԡ��֡�Ϊ��λ���̻�������1�ֲ��Լ��ɣ������Դ�����ԡ��ò������
	String orderAmount = "1";
	//�����ύʱ�䣬��ʽ��yyyyMMddHHmmss���磺20071117020101������Ϊ�ա�
	String orderTime = new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
	//��Ʒ���ƣ�����Ϊ�ա�
	String productName= "��׿�ֻ�SUMSUNG"; 
	//��Ʒ����������Ϊ�ա�
	String productNum = "523";
	//��Ʒ���룬����Ϊ�ա�
	String productId = "00000000000000022331";
	//��Ʒ����������Ϊ�ա�
	String productDesc = "";
	//��չ�ֶ�1���̻����Դ����Լ���Ҫ�Ĳ�����֧�����Ǯ��ԭֵ���أ�����Ϊ�ա�
	String ext1 = "";
	//��չ�Զ�2���̻����Դ����Լ���Ҫ�Ĳ�����֧�����Ǯ��ԭֵ���أ�����Ϊ�ա�
	String ext2 = "";
	//֧����ʽ��һ��Ϊ00���������е�֧����ʽ�����������ֱ���̻�����ֵΪ10�����
	String payType = "00";
	//���д��룬���payTypeΪ00����ֵ����Ϊ�գ����payTypeΪ10����ֵ������д��������ο������б�
	String bankId = "";
	//ͬһ������ֹ�ظ��ύ��־��ʵ�ﹺ�ﳵ��1�������Ʒ��0��1����ֻ���ύһ�Σ�0������֧�����ɹ�����¿������ύ����Ϊ�ա�
	String redoFlag = "";
	//��Ǯ���������ʻ��ţ����̻���ţ���Ϊ�ա�
	String pid = "";
}
