<?php
	/*
	判断是否登陆  
	*/
	
	/*
	接收参数，请加上判断 是否符合当前要求
	比如钱是否为空了，格式等问题
	*/
	
	/*
	以下仅为参考
	*/
	
	
	$result=$_GET['result'];
	$pay_message=$_GET['pay_message'];
	$agent_id=$_GET['agent_id'];
	$jnet_bill_no=$_GET['jnet_bill_no'];
	$agent_bill_id=$_GET['agent_bill_id'];
	$pay_type=$_GET['pay_type'];
	
	$pay_amt=$_GET['pay_amt'];
	$remark=$_GET['remark'];
	
	$returnSign=$_GET['sign'];
	
	$key = '商户密钥';
	
	$signStr='';
	$signStr  = $signStr . 'result=' . $result;
	$signStr  = $signStr . '&agent_id=' . $agent_id;
	$signStr  = $signStr . '&jnet_bill_no=' . $jnet_bill_no;
	$signStr  = $signStr . '&agent_bill_id=' . $agent_bill_id;
	$signStr  = $signStr . '&pay_type=' . $pay_type;
	
	$signStr  = $signStr . '&pay_amt=' . $pay_amt;
	$signStr  = $signStr .  '&remark=' . $remark;
	
	$signStr = $signStr . '&key=' . $key;
	
	$sign='';
	$sign=md5($signStr);
	//请确保 notify.php 和 return.php 判断代码一致
	if($sign==$returnSign){   //比较MD5签名结果 是否相等 确定交易是否成功  成功返回ok 否则返回error
		echo 'ok';	
	}
	else{
		echo 'error';	
	}
	
?>
