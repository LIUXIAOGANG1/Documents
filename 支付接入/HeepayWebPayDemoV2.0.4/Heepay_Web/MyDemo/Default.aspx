<%@ Page Language="C#" AutoEventWireup="true" EnableViewStateMac="false" CodeFile="Default.aspx.cs"
    Inherits="_Default" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title>无标题页</title>

    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>

    <script>
        
        $(function(){
            $("#Button1").click(function(){
                if($("#pay_type").val()!="" &&$("#agentbillid").val()!="" &&$("#goodsname").val()!="" &&$("#remark").val()!="")
                {document.form1.submit();}
                else
                {alert("*必填项，请正确填写！");return false;}
                return false;
            });
            
            $("#pay_type").change(function(){
                var obj = $(this);
                if(obj.val()=="20")
                {
                    $("#bankid").show();
                }
                else{
                    $("#bankid").hide();
                }
            });
			
        });
        
    </script>

    <style>
        .tab
        {
            border-collapse: collapse;
            width: 700px;
            border: #999 1px dashed;
        }
        .tab td
        {
            padding: 5px;
            border: #999 1px dashed;
        }
    </style>
</head>
<body>
    <form id="form1" action="PostAction.aspx" target="_blank">
    <div style="height: 50px">
    </div>
    <div>
        <table class="tab" width="100%" border="0" align="center">
            <tr>
                <td align="center" colspan="2">
                    Test汇付宝（<span style="color: Red;">*</span>必填项）<span style="color:#F00">测试案例</span>
                </td>
            </tr>
            <tr>
                <td align="left">
                    支付类型：<span style="color: Red">*</span>
                </td>
                <td>
                    <select name="pay_type" id="pay_type">
						<option value="">==请选择==</option>
						<option value="0">汇付宝支付</option><option value="1">会员余额支付</option><option value="2">汇元个人会员余额支付</option><option value="3">汇元商家会员余额支付</option><option value="7">汇元网自己的卡类支付</option><option value="8">吉祥卡</option><option value="10">骏卡支付</option><option value="12">手机卡支付</option><option value="13">神州行</option><option value="14">联通卡</option><option value="15">电信卡</option><option value="20">银行支付</option><option value="35">盛大卡支付</option><option value="40">第三方卡支付</option><option value="42">网易一卡通</option><option value="43">征途一卡通</option><option value="44">完美一卡通</option><option value="45">金山一卡通</option><option value="46">搜狐一卡通</option><option value="47">久游一卡通</option><option value="48">乐都一卡通</option><option value="49">盛科一卡通</option><option value="50">中广一卡通</option><option value="51">麒麟一卡通</option><option value="52">梦工厂网络</option>
					</select>
                </td>
            </tr>
			
            <tr id="bankid" style="display: none">
                <td align="left">
                    支付类型编码：
                </td>
                <td>
                    <select name="bank_type" id="bank_type">
					<option value="">==请选择==</option>
					<option value="001">中国工商银行</option>
					<option value="002">招商银行</option>
					<option value="003">中国建设银行</option>
					<option value="004">中国银行</option>
					<option value="005">中国农业银行</option>
					<option value="006">交通银行</option>
					<option value="007">上海浦东发展银行</option>
					<option value="016">广东发展银行</option>
					<option value="015">中信银行</option>
					<option value="010">中国光大银行</option>
					<option value="011">兴业银行</option>
					<option value="012">深圳发展银行</option>
					<option value="013">中国民生银行</option>
					<option value="014">华夏银行</option>
					<option value="020">中国邮政储蓄银行</option>
				</select>
                </td>
            </tr>
			
            <tr>
                <td align="left" style="width: 150px">
                    定单号：<span style="color: Red;">*</span>
                </td>
                <td>
                    <input  id="agentbillid" name= "agentbillid" value="<%= Guid.NewGuid().ToString().Replace("-","") %>"/>
                   商户系统内部的定单号（<span style="color: Red;">要保证唯一</span>）
                    
                </td>
            </tr>
            <tr>
                <td align="left">
                    支付金额：<span style="color: Red">*</span>
                </td>
                <td>
                    <input name="TradeAmt" type="text" id="TradeAmt" value="0.01" />元 小数点后保留两位
                    12.37
                </td>
            </tr> 
            <tr>
                <td align="left" style="width: 150px">
                    商品名称：<span style="color: Red;">*</span>
                </td>
                <td>
                    <input name="goodsname" type="text" value="商品<%= Guid.NewGuid().ToString().Replace("-","").Substring(0,4) %>" id="goodsname" />长度最长50字符
                </td>
            </tr>
            <tr>
                <td align="left" style="width: 150px">
                    产品数量：
                </td>
                <td>
                    <input name="goodsnum" type="text" id="goodsnum" value="1" />长度最长20字符
                </td>
            </tr>
            <tr>
                <td align="left" style="width: 150px">
                    商户返回信息：<span style="color: Red;">*</span>
                </td>
                <td>
                    <input name="remark" type="text" value="返回<%= Guid.NewGuid().ToString().Replace("-","").Substring(0,4) %>" id="remark" />
                    商户自定义原样返回,最长50字符
                </td>
            </tr>
            <tr>
                <td>
                    支付说明：
                </td>
                <td>
                    <input name="goods_note" type="text" value="说明<%= Guid.NewGuid().ToString().Replace("-","").Substring(0,4) %>" id="goods_note" />支付说明, 长度50字符
                </td>
            </tr>
            <tr>
                <td align="center" colspan="2">
                    <input type="submit" name="Button1" value="测试" id="Button1" />
                </td>
            </tr>
        </table>
    </div>
    </form>
</body>
</html>

