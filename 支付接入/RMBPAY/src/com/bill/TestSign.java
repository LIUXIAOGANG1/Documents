package com.bill;

import java.io.UnsupportedEncodingException;

public class TestSign {
   public static void main (String []args) throws UnsupportedEncodingException{
	   StringBuffer sb=new StringBuffer();
	   sb.append("msg=error&fee=1&bankDealId=140110539388&bankId=PSBC&ext1=test&payAmount=2&dealId=1356807733&orderTime=20140110160416&signMsg=F2361C4D5E325D9DC2896EB3DE2ADB6C&payType=10&language=1&errCode=&version=v2.0&ext2=&signType=1&orderAmount=2&orderId=winda20140110160416&dealTime=20140110160548&merchantAcctId=1001738982301&payResult=10");
	   String forSign=sb.toString();
	   String signMsg = MD5Util.md5Hex(forSign.getBytes("utf-8")).toUpperCase();
	   System.out.print(signMsg);
   }
}
