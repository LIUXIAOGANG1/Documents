<?php
	/*
	判断是否登陆
	*/
	
	/*
		银行交易金额最大为10w
	*/
	
	/*
	接收参数，请加上判断 是否符合当前要求
	比如钱是否为空了，格式等问题
	*/
	
	
	/*
	注意   关于网站编码问题
	请注意，我司接口编码为gb2312
	如涉及编码问题  请根据   iconv 和 urlencode  
	请在传递参数时 进行相应的转码
	例：	urlencode(iconv("UTF-8","gb2312//IGNORE",$goods_name))
	 	urlencode($goods_name)
	*/
	
	/*
	以下仅为参考
	*/
	
	//获取ip
	$onlineip = "";
	if($_SERVER['HTTP_CLIENT_IP']){
		$onlineip=$_SERVER['HTTP_CLIENT_IP'];
	}elseif($_SERVER['HTTP_X_FORWARDED_FOR']){
		$onlineip=$_SERVER['HTTP_X_FORWARDED_FOR'];
	}else{
		$onlineip=$_SERVER['REMOTE_ADDR'];
	}
	// 获取url 
	$URL=$_SERVER['HTTP_HOST'] . $_SERVER['REQUEST_URI'];
	$geturl=str_replace('PostAction.php','',$URL);
	
	$version=1;
	$agent_id="商户ID";
	$agent_bill_id=$_POST['agentbillid'];
	$agent_bill_time=date('YmdHis', time());
	$pay_type=$_POST['pay_type'];
	$pay_code=$_POST['bank_type'];
	$pay_amt=$_POST['TradeAmt'];
	$notify_url=$geturl."Notify.php";
	$return_url=$geturl."Return.php";
	$user_ip=$onlineip;
	$goods_name=$_POST['goodsname'];
	$goods_num=$_POST['goodsnum'];
	$goods_note=$_POST['goods_note'];
	$remark=$_POST['remark'];
	
	//如果需要测试，请把取消关于$is_test的注释  订单会显示详细信息
	//$is_test='1’;
	//if($is_test=='1')
	//{
		//$is_test='1';
	//}
	
	$key = "商户密钥";
	
	$signStr='';
	$signStr  = $signStr . 'version=' . $version;
	$signStr  = $signStr . '&agent_id=' . $agent_id;
	$signStr  = $signStr . '&agent_bill_id=' . $agent_bill_id;
	$signStr  = $signStr . '&agent_bill_time=' . $agent_bill_time;
	$signStr  = $signStr . '&pay_type=' . $pay_type;
	$signStr  = $signStr . '&pay_amt=' . $pay_amt;
	$signStr  = $signStr .  '&notify_url=' . $notify_url;
	$signStr  = $signStr . '&return_url=' . $return_url;
	$signStr  = $signStr . '&user_ip=' . $user_ip;
	//if ($is_test == '1'){
		//$signStr  = $signStr . '&is_test=' . $is_test;
	//}
	$signStr = $signStr . '&key=' . $key;
	
	//获取sign密钥
	$sign='';
	$sign=md5($signStr);
?>

<form id='frmSubmit' method='post' name='frmSubmit' action='https://pay.heepay.com/Payment/Index.aspx'>
<input type='hidden' name='version' value='<?= $version ?>' />
<input type='hidden' name='agent_id' value='<?= $agent_id ?>' />
<input type='hidden' name='agent_bill_id' value='<?= $agent_bill_id  ?>' />
<input type='hidden' name='agent_bill_time' value='<?=  $agent_bill_time ?>' />
<input type='hidden' name='pay_type' value='<?= $pay_type ?>' />
<input type='hidden' name='pay_code' value='<?= $pay_code  ?>' />
<input type='hidden' name='pay_amt' value='<?= $pay_amt  ?>' />
<input type='hidden' name='notify_url' value='<?=  $notify_url ?>' />
<input type='hidden' name='return_url' value='<?=  $return_url ?>' />
<input type='hidden' name='user_ip' value='<?= $user_ip  ?>' />
<input type='hidden' name='goods_name' value='<?= urlencode($goods_name) ?>' />
<input type='hidden' name='goods_num' value='<?=  urlencode($goods_num) ?>' />
<input type='hidden' name='goods_note' value='<?= urlencode($goods_note)  ?>' />
<input type='hidden' name='remark' value='<?= urlencode($remark) ?>' />
<?php 
//if ($is_test == '1'){
	//echo "<input type='hidden' name='is_test' value='".$is_test."' />";
//}
?>
<input type='hidden' name='sign' value='<?= $sign  ?>' />
</form>
<script language='javascript'>
window.onload=function(){document.frmSubmit.submit();}
</script>



