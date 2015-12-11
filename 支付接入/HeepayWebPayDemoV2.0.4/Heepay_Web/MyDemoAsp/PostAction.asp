<!--#include file="md5.asp" -->
<%
	' 
	'	判断是否登陆
	'
	
	'
	'	银行交易金额最大为10w
	'
	
	'
	'	接收参数，请加上判断 是否符合当前要求
	'	比如钱是否为空了，格式等问题
	'
	
	
	'
	'	注意   关于网站编码问题
	'	请注意，我司接口编码为gb2312 
	'	请在传递参数时 进行相应的转码
	
	
	'
	'以下仅为参考
	'
	
	'获取ip
	
	dim onlineip
	onlineip = getIP()
	
	' 获取url 
	dim URL
	URL=GetUrl()
	URL=Replace(URL,"PostAction.asp","")
	
	dim versionType,agent_id,agent_bill_id,agent_bill_time,pay_type,pay_code,pay_amt,notify_url,return_url,user_ip,goods_name,goods_num,goods_note,remark,sign,signStr
	
	versionType=1
	agent_id="商户ID"
	agent_bill_id=request("agentbillid")
	agent_bill_time=     Replace(Replace(Replace(now(),"-","")," ",""),":","")
	pay_type=request("pay_type")
	pay_code=request("bank_type")
	pay_amt=request("TradeAmt")
	notify_url=URL & "Notify.asp"
	return_url=URL & "Return.asp"
	user_ip=onlineip
	goods_name=request("goodsname")
	goods_num=request("goodsnum")
	goods_note=request("goods_note")
	remark=request("remark")
	
	'如果需要测试，请把取消关于is_test的注释  订单会显示详细信息
	'is_test="1"
	'if(is_test=="1")
	'{
	'	is_test="1"
	'}
	
	key = "商户密钥"
	
	signStr=""
	signStr  = signStr & "version=" & versionType
	signStr  = signStr & "&agent_id=" & agent_id
	signStr  = signStr & "&agent_bill_id=" & agent_bill_id
	signStr  = signStr & "&agent_bill_time=" & agent_bill_time
	signStr  = signStr & "&pay_type=" & pay_type
	signStr  = signStr & "&pay_amt=" & pay_amt
	signStr  = signStr & "&notify_url=" & notify_url
	signStr  = signStr & "&return_url=" & return_url
	signStr  = signStr & "&user_ip=" & user_ip
	'if (is_test == "1"){
		'signStr  = signStr & "&is_test=" & is_test
	'}
	signStr = signStr & "&key=" & key
	
	'获取sign密钥
	sign=""
	sign=LCase(md5(signStr))
	
	Private Function getIP()
		Dim strIPAddr
		If Request.ServerVariables("HTTP_X_FORWARDED_FOR") = "" OR InStr(Request.ServerVariables("HTTP_X_FORWARDED_FOR"), "unknown") > 0 Then
		strIPAddr = Request.ServerVariables("REMOTE_ADDR")
		ElseIf InStr(Request.ServerVariables("HTTP_X_FORWARDED_FOR"), ",") > 0 Then
		strIPAddr = Mid(Request.ServerVariables("HTTP_X_FORWARDED_FOR"), 1, InStr(Request.ServerVariables("HTTP_X_FORWARDED_FOR"), ",")-1)
		ElseIf InStr(Request.ServerVariables("HTTP_X_FORWARDED_FOR"), "") > 0 Then
		strIPAddr = Mid(Request.ServerVariables("HTTP_X_FORWARDED_FOR"), 1, InStr(Request.ServerVariables("HTTP_X_FORWARDED_FOR"), "")-1)
		Else
		strIPAddr = Request.ServerVariables("HTTP_X_FORWARDED_FOR")
		End If
		getIP = Trim(Mid(strIPAddr, 1, 30))
	End Function
	
	Function GetUrl()
	  Dim ScriptAddress,Servername,qs
	  ScriptAddress = CStr(Request.ServerVariables("SCRIPT_NAME"))
	  Servername = CStr(Request.ServerVariables("Server_Name"))
	  qs=Request.QueryString
	  if qs<>"" then
	  GetUrl ="http://"& Servername & ScriptAddress &"?"&qs
	  else
	  GetUrl ="http://"& Servername & ScriptAddress
	  end if
	End Function
%>

<form id='frmSubmit' method='post' name='frmSubmit' action='https://pay.heepay.com/Payment/Index.aspx'>
<input type='hidden' name='version' value='<%= versionType %>' />
<input type='hidden' name='agent_id' value='<%= agent_id %>' />
<input type='hidden' name='agent_bill_id' value='<%= agent_bill_id  %>' />
<input type='hidden' name='agent_bill_time' value='<%=  agent_bill_time %>' />
<input type='hidden' name='pay_type' value='<%= pay_type %>' />
<input type='hidden' name='pay_code' value='<%= pay_code  %>' />
<input type='hidden' name='pay_amt' value='<%= pay_amt  %>' />
<input type='hidden' name='notify_url' value='<%=  notify_url %>' />
<input type='hidden' name='return_url' value='<%=  return_url %>' />
<input type='hidden' name='user_ip' value='<%= user_ip  %>' />
<input type='hidden' name='goods_name' value='<%= Server.URLEncode(goods_name) %>' />
<input type='hidden' name='goods_num' value='<%=  Server.URLEncode(goods_num) %>' />
<input type='hidden' name='goods_note' value='<%= Server.URLEncode(goods_note)  %>' />
<input type='hidden' name='remark' value='<%=remark %>' />
<% 
'if (is_test == '1'){
	'response.Write("<input type='hidden' name='is_test' value='" is_test "' />")
'}
%>
<input type='hidden' name='sign' value='<%= sign  %>' />
</form>
<script language='javascript'>
window.onload=function(){document.frmSubmit.submit()}
</script>



