package Util;

public class BakRMB {
	//人民币网关账号，该账号为11位人民币网关商户编号+01,该参数必填。
	String merchantAcctId = "1001214986301";
	//编码方式，1代表 UTF-8; 2 代表 GBK; 3代表 GB2312 默认为1,该参数必填。
	String inputCharset = "1";
	//接收支付结果的页面地址，该参数一般置为空即可。
	String pageUrl = "";
	//服务器接收支付结果的后台地址，该参数务必填写，不能为空。
	//String bgUrl = "http://219.233.173.50:8801/RMBPORT22/receive.jsp";
	String bgUrl ="http://192.168.122.70:8080/RMBPORT22/receive.jsp";
	
	//网关版本，固定值：v2.0,该参数必填。
	String version =  "v3.0";
	//语言种类，1代表中文显示，2代表英文显示。默认为1,该参数必填。
	String language =  "1";
	//签名类型,该值为4，代表PKI加密方式,该参数必填。
	String signType =  "4";
	//支付人姓名,可以为空。
	String payerName= ""; 
	//支付人联系类型，1 代表电子邮件方式；2 代表手机联系方式。可以为空。
	String payerContactType =  "1";
	//支付人联系方式，与payerContactType设置对应，payerContactType为1，则填写邮箱地址；payerContactType为2，则填写手机号码。可以为空。
	String payerContact =  "888888888@qq.com";
	//商户订单号，以下采用时间来定义订单号，商户可以根据自己订单号的定义规则来定义该值，不能为空。
	String orderId = new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
	//订单金额，金额以“分”为单位，商户测试以1分测试即可，切勿以大金额测试。该参数必填。
	String orderAmount = "1";
	//订单提交时间，格式：yyyyMMddHHmmss，如：20071117020101，不能为空。
	String orderTime = new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
	//商品名称，可以为空。
	String productName= "安卓手机SUMSUNG"; 
	//商品数量，可以为空。
	String productNum = "523";
	//商品代码，可以为空。
	String productId = "00000000000000022331";
	//商品描述，可以为空。
	String productDesc = "";
	//扩展字段1，商户可以传递自己需要的参数，支付完快钱会原值返回，可以为空。
	String ext1 = "";
	//扩展自段2，商户可以传递自己需要的参数，支付完快钱会原值返回，可以为空。
	String ext2 = "";
	//支付方式，一般为00，代表所有的支付方式。如果是银行直连商户，该值为10，必填。
	String payType = "00";
	//银行代码，如果payType为00，该值可以为空；如果payType为10，该值必须填写，具体请参考银行列表。
	String bankId = "";
	//同一订单禁止重复提交标志，实物购物车填1，虚拟产品用0。1代表只能提交一次，0代表在支付不成功情况下可以再提交。可为空。
	String redoFlag = "";
	//快钱合作伙伴的帐户号，即商户编号，可为空。
	String pid = "";
}
