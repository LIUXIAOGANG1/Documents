using System;
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
using System.Text.RegularExpressions;
using System.Net;
using System.Net.Sockets;

public partial class PostAction : System.Web.UI.Page
{
    public string agent_id { get; set; }
    public string pay_type { get; set; }
    public string pay_amt { get; set; }
    public string remark { get; set; }
    public string key { get; set; }
    public string sign { get; set; }
    public int version { get; set; }
    public string agent_bill_id { get; set; }
    public string agent_bill_time { get; set; }
    public string pay_flag { get; set; }
    public string notify_url { get; set; }
    public string return_url { get; set; }
    public string user_ip { get; set; }
    public string goods_name { get; set; }
    public string goods_num { get; set; }
    public string goods_note { get; set; }
    public int is_test { get; set; }
    public string pay_code { get; set; }

    protected void Page_Load(object sender, EventArgs e)
    {
        #region 获取参数值
        version = 1;                                                            //当前接口版本号 1  
        user_ip = GetIP();                                                      //用户所在客户端的真实ip。如 127.127.12.12
        goods_name = HttpUtility.UrlEncode(Request["goodsname"]);                                   //商品名称, 长度最长50字符
        //pay_flag = TypeConvert.GetString(Request["txtpayflag"]); ;                 //支付标记，1=容许其他高于指定支付类型折扣的支付，0=不容许（默认）(如传了此参数，则要参加MD5的验证)
        agent_bill_id = Request["agentbillid"];                              //商户系统内部的定单号（要保证唯一）。长度最长50字符
        goods_note = HttpUtility.UrlEncode(Request["goods_note"]);                                  //支付说明, 长度50字符
        remark = HttpUtility.UrlEncode(Request["remark"]);                                           //商户自定义 原样返回,长度最长50字符
        is_test = 0; //是否测试 1 为测试
        pay_type = Request["pay_type"];                                          //支付类型见7.1表                   
        if ("20".Equals(pay_type))
        {
            pay_code = Request["bank_type"];       //银行
        }
        pay_amt = Request["TradeAmt"];                                       //订单总金额 不可为空，单位：元，小数点后保留两位。12.37
        goods_num = Request["goodsnum"];                                     //产品数量,长度最长20字符
        agent_bill_time = DateTime.Now.ToString("yyyyMMddHHmmss");              //提交单据的时间yyyyMMddHHmmss 20100225102000
        agent_id = "商户ID";                                                      //商户编号
        key = "商户密钥";                                                          //商户密钥

        /*
        //如果需要测试，请把取消关于is_test的注释  订单会显示详细信息

        is_test = "1";
        */
        Regex _Regex = new Regex("http((.|\n)*?)PostAction", RegexOptions.Multiline | RegexOptions.IgnoreCase);
        string _URL = _Regex.Match(Request.Url.AbsoluteUri).Value.Replace("PostAction", "").Replace("PostAction", "");
        notify_url = _URL + "Return.aspx";
        return_url = _URL + "Notify.aspx";

        sign = GetSign();

        #endregion
    }


    /// <summary>
    /// 获取sign的值
    /// </summary>
    /// <returns></returns>
    private string GetSign()
    {
        StringBuilder _StringSign = new StringBuilder();
        //注意拼接顺序,详情请看《汇付宝即时到帐接口开发指南2.0.4.pdf》
        _StringSign.Append("version=" + version)
            .Append("&agent_id=" + agent_id)
            .Append("&agent_bill_id=" + agent_bill_id)
            .Append("&agent_bill_time=" + agent_bill_time)
            .Append("&pay_type=" + pay_type)
            .Append("&pay_amt=" + pay_amt)
            .Append("&notify_url=" + notify_url)
            .Append("&return_url=" + return_url)
            .Append("&user_ip=" + user_ip);
        if (is_test == 1)
        {
            _StringSign.Append("&is_test=" + is_test);
        }
        _StringSign.Append("&key=" + key);
        //Response.Write(_StringSign.ToString());
        return this.MD5Hash(_StringSign.ToString()).ToLower();
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

    /// <summary>
    /// 获取ip
    /// </summary>
    /// <returns></returns>
    private string GetIP()
    {
        IPAddress[] arrIPAddresses = Dns.GetHostAddresses(Dns.GetHostName());
        foreach (IPAddress ip in arrIPAddresses)
        {
            if (ip.AddressFamily.Equals(AddressFamily.InterNetwork))
            {
                return ip.ToString();
            }
        }
        return "";
    }
}

