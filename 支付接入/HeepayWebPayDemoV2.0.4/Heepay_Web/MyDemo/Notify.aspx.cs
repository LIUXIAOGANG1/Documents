﻿using System;
using System.Collections;
using System.Configuration;
using System.Data;
using System.Linq;
using System.Web;
using System.Web.Security;
using System.Web.UI;
using System.Web.UI.HtmlControls;
using System.Web.UI.WebControls;
using System.Web.UI.WebControls.WebParts;
using System.Xml.Linq;

using System.Text;

public partial class Notify : System.Web.UI.Page
{
    String result { get; set; }
    String pay_message { get; set; }
    String agent_id { get; set; }
    String jnet_bill_no { get; set; }
    String agent_bill_id { get; set; }
    String pay_type { get; set; }
    String pay_amt { get; set; }
    String remark { get; set; }
    String returnSign { get; set; }
    String sign { get; set; }

    protected void Page_Load(object sender, EventArgs e)
    {
        #region 获取参数值
        result = Request["result"];
        pay_message = Request["pay_message"];
        agent_id = Request["agent_id"];
        jnet_bill_no = Request["jnet_bill_no"];
        agent_bill_id = Request["agent_bill_id"];
        pay_type = Request["pay_type"];
        pay_amt =Request["pay_amt"];
        remark =Request["remark"];
        returnSign =Request["sign"];

        sign = GetSign();
        #endregion

        //比较MD5签名结果 是否相等
        if (sign.Equals(returnSign))
        {
            Response.Write("ok");
        }
        else
        {
            Response.Write("error");
        }
        Response.End();
    }

    /// <summary>
    /// 获取sign的值
    /// 具体的字符串拼接说明，请参考汇元网公司汇付宝支付网关商户开发指南2.0.2.doc
    /// </summary>
    /// <returns></returns>
    private string GetSign()
    {
        StringBuilder sbSign = new StringBuilder();
        sbSign.Append("result=" + result)
            .Append("&agent_id=" + agent_id)
            .Append("&jnet_bill_no=" + jnet_bill_no)
            .Append("&agent_bill_id=" + agent_bill_id)
            .Append("&pay_type=" + pay_type)
            .Append("&pay_amt=" + pay_amt)
            .Append("&remark=" + remark)
            .Append("&key=" + "商户密钥");

        return this.MD5Hash(sbSign.ToString()).ToLower();
    }

    /// <summary>
    /// 32 位
    /// </summary>
    /// <param name="str">需要转换的字符串</param>
    /// <returns></returns>
    private string MD5Hash(string str)
    {
        return FormsAuthentication.HashPasswordForStoringInConfigFile(str, "md5").ToLower();
    }
}

