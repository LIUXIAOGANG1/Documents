<!--#include file="md5.asp" -->
<%
	'
	'判断是否登陆  
	'
	
	'
	'接收参数，请加上判断 是否符合当前要求
	'比如钱是否为空了，格式等问题
	'
	
	
	'以下仅为参考
	
	dim result,pay_message,agent_id,jnet_bill_no,agent_bill_id,pay_type,pay_amt,remark,returnSign,key,sign
	
	result=request("result")
	pay_message=request("pay_message")
	agent_id=request("agent_id")
	jnet_bill_no=request("jnet_bill_no")
	agent_bill_id=request("agent_bill_id")
	pay_type=request("pay_type")
	
	pay_amt=request("pay_amt")
	remark=request("remark")
	
	returnSign=request("sign")

	key = "商户密钥"
	
	signStr=""
	signStr  = signStr & "result=" & result
	signStr  = signStr & "&agent_id=" & agent_id
	signStr  = signStr & "&jnet_bill_no=" & jnet_bill_no
	signStr  = signStr & "&agent_bill_id=" & agent_bill_id
	signStr  = signStr & "&pay_type=" & pay_type
	signStr  = signStr & "&pay_amt=" & pay_amt
	signStr  = signStr & "&remark=" & remark
	signStr = signStr & "&key=" & key
	
	sign=""
	sign=LCase(md5(signStr))
	
	if(sign==returnSign){   '比较MD5签名结果 是否相等 确定交易是否成功  成功返回ok 否则返回error
			response.Write("ok")
	}
	else{
			response.Write("error")
	}
	
%>
