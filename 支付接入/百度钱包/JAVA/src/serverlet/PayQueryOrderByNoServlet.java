package serverlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import baifubao.BfbSdkComm;
//import org.dom4j.Document;  



public class PayQueryOrderByNoServlet extends HttpServlet {
 
	
	private ServletConfig scon = null;
	
	public PayQueryOrderByNoServlet() {
		super();
	}
	
	public void destroy() {
		super.destroy(); 
	}
	/* ͨ���ٸ��������Ų�ѯ�ӿڲ�ѯ������Ϣ�����ظö����Ƿ��Ѿ�֧���ɹ�
	 *
	 * @param string $order_no        	
	 * @return string | boolean ����֧���ɹ����ض�����ѯ��������������������ѯʧ���Լ�֧��״̬����֧���ɹ������������false
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	/**
	 *����post����ķ���
	 * 
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        /**
         * 1�����ñ���
         */
		response.setContentType("text/html;charset=gbk");  
		response.setHeader("content-type","text/html;charset=gbk");
		request.setCharacterEncoding("gbk");
		response.setCharacterEncoding("gbk");
		
		PrintWriter out = response.getWriter();
		//��ӡ��־
	    Logger logger =new  baifubao.BfbSdkComm().printLog("PayQueryOrderByNoServlet");

		logger.log(Level.INFO, "response���룺"+response.getCharacterEncoding());
		/**
		 * 2����ȡ web.xml�ĳ���ֵ
	     */
	    //��Ʒ�����
	    String  service_code=
	    		"service_code="+scon.getServletContext().getInitParameter("BFB_QUERY_INTERFACE_SERVICE_ID");
	    //�̻���
	    String sp_no="sp_no=" +scon.getServletContext().getInitParameter("SP_NO");
	   
	    String output_charset="output_charset=" +scon.getServletContext().getInitParameter("BFB_INTERFACE_ENCODING");
	    //���ظ�ʽ
	    String output_type="output_type=" +scon.getServletContext().getInitParameter("BFB_INTERFACE_OUTPUT_FORMAT");
	    //�汾
	    String version="version=" +scon.getServletContext().getInitParameter("BFB_INTERFACE_VERSION");
	    //���ܷ�ʽmd5����hash
	    String sign_method="sign_method=" +scon.getServletContext().getInitParameter("SIGN_METHOD_MD5");
	    //��Կ
	    String SP_KEY=scon.getServletContext().getInitParameter("SP_KEY");
	    //�ύ��ַ
	    String BFB_QUERY_ORDER_URL=scon.getServletContext().getInitParameter("BFB_QUERY_ORDER_URL");
	    /**
	     *3����ȡpay_unlogin.htmlҳ���ύ�ı���ֵ_������
	     */
	    //������
	    String order_no ="order_no="+request.getParameter("order_no");
	    
		//���ύ������ֵ��ֵ
		String[]array={
				order_no,
				output_type,
				output_charset,
				service_code,
				sign_method,
				sp_no,
				version};
		/**
		 * 4������bfb_sdk_comm��create_baifubao_pay_order_url�������ɰٸ�����ʱ����֧���ӿ�URL(����Ҫ��¼)
		 *
		 */
		String getURL=new BfbSdkComm().create_baifubao_pay_order_url(array,array,BFB_QUERY_ORDER_URL);
        /**
         * 5����ٸ������𶩵���ѯ����
         */
	    HttpClient httpClient =  new  HttpClient();    
	    httpClient.getParams().setParameter(
	    	      HttpMethodParams.HTTP_CONTENT_CHARSET, "gbk");
	    // ���� Http ���ӳ�ʱΪ5��    
	    httpClient.getHttpConnectionManager().getParams().setConnectionTimeout( 5000 );   
	     /* ���� GetMethod �������ò��� */    
	    GetMethod getMethod =  new  GetMethod(getURL);    
	     // ���� get ����ʱΪ 5 ��    
	    getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT,  5000 );   
	      // �����������Դ����õ���Ĭ�ϵ����Դ�����������    
	    getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new  DefaultHttpMethodRetryHandler());         
	    String responseStr = "" ;    
	     /**
	      * 6�� ִ�� HTTP GET ����
	      */ 
	     try  
	     {    
	         int  statusCode = httpClient.executeMethod(getMethod); 
	         /*  �жϷ��ʵ�״̬�� */    
	         if  (statusCode != HttpStatus.SC_OK) 
	         {    
	            out.println( "HttpStatus.SC_OK :" + getMethod.getStatusLine());   
	          }    
	        /**
	         *  7�� �����ٸ������صĲ�ѯ���(���ؽ����xml��ʽ��)
	         **/    
	        // HTTP��Ӧͷ����Ϣ������򵥴�ӡ                 	         
	        // ��ȡ HTTP ��Ӧ���ݣ�����򵥴�ӡ��ҳ����    
            String result = getMethod.getResponseBodyAsString();
            logger.log(Level.INFO, "getMethod.getResponseBodyAsString()���ص�XMLԭ��:" +result+"XMLԭ�ķ���ֵ"+"<br/>");
	        //�������ص�XML
	        Document doc = null;
	        doc = DocumentHelper.parseText(result); 
	        // ��ȡ���ڵ�
	        Element root = doc.getRootElement(); 
	        List listTemp = root.elements();  
	        String []resultArry=new String[listTemp.size()];
	        //ƴ�ӷ��ؽ��������ӡ��ҳ��
	        StringBuffer strBur=new StringBuffer();
	        //ѭ��ȡxml��ֵ������������resultArry
	        int i=0;
	        for(Iterator its =  listTemp.iterator();its.hasNext();){   
	            Element chileEle = (Element)its.next();  
	            resultArry[i]=chileEle.getName()+"="+chileEle.getStringValue();
	            strBur.append(resultArry[i]+"<br/>");
	            i++;
	        }   
	        out.println("��ѯ�����ӡ============"+"<br/>");
	        out.println(strBur.toString());
	        out.println("��ѯ�����ӡ����============"+"<br/>");
	        //��ȡǩ���ֶ�
	        String signTemp=resultArry[resultArry.length-1];
	        String resultSign=signTemp.substring(signTemp.indexOf("=")+1,signTemp.length());
	        System.out.println("��ѯ���ص�sing:"+resultSign);
	        logger.log(Level.INFO, "���ڵ㣺" + root.getName()); // �õ����ڵ����	  
	        //��ȡ�����ؽ���Ժ���Ҫ�ٽ���һ��ǩ����ȷ�����ؽ���ǰٸ�������
	        String[] SignArry=new String [resultArry.length-1];
	        for(int j=0;j<resultArry.length-1;j++)
	        {
	        	SignArry[j]=resultArry[j];
	        }
    	    //�жϲ�ѯ����Ľ��,0��ѯ�ɹ�����ϸ�����ο��ĵ�
    	   if(root.element("query_status").getText().equals("0"))
    	   {   
    		   out.println("��ѯ�ɹ�: "+"<br/>");
    		   logger.log(Level.INFO, "��ѯ�ɹ�:\n\r  ");
    		   /**
   	            * 8�� ����ǩ������make_sign���Է��ؽ��������ǩ
   	            **/  
	    	   String RspLocalSign=new BfbSdkComm().make_sign(SignArry);
	    	   out.println("����ǩ����            :" + RspLocalSign + "����ǩ����<br/>  ");
	    	   out.println("�ٸ�������ǩ����:" + resultSign + "�ٸ�������ǩ����<br/> " );
	    	   logger.log(Level.INFO, "����ǩ����           : " + RspLocalSign + "����ǩ����  \n\r");
	    	   logger.log(Level.INFO, "�ٸ�������ǩ����: " + resultSign + "�ٸ�������ǩ����" );
	    	   if(RspLocalSign.equalsIgnoreCase(resultSign))
	    	   {
	    		   out.println("��ѯ�ɹ�����֤ǩ��ͨ�� <br/>  ");
	    		   logger.log(Level.INFO, "��ѯ�ɹ�����֤ǩ��ͨ��\n\r  ");
	    	   }else 
	    	   {
	    		   out.println("��ѯ�ɹ�����֤ǩ��ʧ�� <br/>  ");
	    		   logger.log(Level.INFO, "�����ѯ�����쳣������httpClient\n\r" );
	    	   }
    	   }
	 
	      }catch (HttpException e) {    
	         // �����������쳣��������Э�鲻�Ի��߷��ص�����������   
	        out.println( "HttpException�쳣" );   
	        logger.log(Level.INFO, "HttpException�쳣���������" );
	      } catch (DocumentException e) 
	      {
		        out.println("����xml����");
		        logger.log(Level.INFO, "����xml����\n\r");
		  } 
	      finally  
	      {    
	         /* �ͷ����� */    
	    	  getMethod.releaseConnection();    
	      }    
    }   
	public String getServletInfo() {
		return "pay_query_order_by_noServlet";
	}
	
	public void init(ServletConfig config) throws ServletException {
		scon = config;
	}

	
}
