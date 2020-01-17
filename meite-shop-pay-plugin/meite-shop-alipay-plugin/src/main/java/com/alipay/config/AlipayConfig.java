package com.alipay.config;
/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

import java.io.FileWriter;
import java.io.IOException;

public class AlipayConfig {

	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016101900726542";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCFKWDZIXHOFbWWxfavHpmy7bVL+8Om0+lMP5VLz8veeyK35eLVozl+uY9yus3DanAi5w8oZtdCvQiMAYZ0T/Fp0gfZQ9qOyM2J5bZ6CAw/v2qwZ+xtNpyAeVOBfBKbfRD4vQEMw9jCE0Nw/eIH649onpqkDJ5TMhtGRHxpGuqapUpbKsuXEbwc+pZtaTYcwyENooz459MLwFAY7AOvMvohv5rDZSm0eK1DHIYraP7HU8AEBNtMgFZZ98JOflf6zgSCWPyTdvWNSkNUXmFCg3IjiY3o6ufQ1qp9NiF8Q311a5r1dAac7Gx0UEWHYixwm+YABlmtQzSHjapyWzd6tajXAgMBAAECggEAAUctY8gbZIP20zShMnsTtJDtfYY0EYTVJ2Mw1qJu7Xq3EZbGP5Cqe5niU5KRaP+7hco77gJWrJBN5bIaDUG7Km3Wn3I0RcyNlHY88xziqTX4GytULJQqml0PvR1ovE9N+2Us/mdezDYNHqeBJNxQ6wsiptJsLpY7hsgffY7Bk6xg3EQc2xqcZa/kDPejHkNgxIaCdPxSl5JGXT7Dp5ht6Tm47BKaSP9IArcSXK4qXDTXL+cRBdMFoFWiCXRRH1GAhYUXm4PJ8An8MSJjWzQB3qaZxX3ftgqZIAkEoUwaaxkCKT9AmOYtgaDKZ0+6mjcpB8q5HRpE37+zrmTIQBLxCQKBgQDY1pQAJ4eqS2LJgsoemmJDPQ1oJ/MJC3o19Hu13vien2knRhpi7QoZlnfFF5C5F8QV2Z3agNq1Tr+08oZCgZ2XddWMEvR3/bevxaSv//mNoXHjGS939OZk9QLyTdpYIkKnOCXaOnTetdmA5qOCPMLnBuzl5LdbvHAP65gS8VZpZQKBgQCdNgzGDnwHHZV3GSVdjZKB34dQKcVXrbU87qm7JhHb2mXbW0xCQu2OYol0QZ7ekIw/oy09e3NioOM++iVuwPkjx+NwQUOhChwL+sABMoSSby3hT6xLq8mKDnM4Ab/zlvRYKc4IgeWvMlYyEW21NssO3YYoj2lDAwvtg7mFDZFDiwKBgFfKmOVvG8hg+UApCfhV/U7H2zO+aPHSicpqj+PUwMhINmTGR0yiOCKf/yH3JosqCiPmlbivztz1Yzy5rI4kuE9oT+uEiSJDwSkTVlhDxSAIq3mM6uEgcF+Tq3u69ZoVVPMrEnhGjqbU7Zj4yF6khyzOav+xGGO16ZEoi8uvlpLZAoGAM1DBOqTftzLg5s8+cxsTB3F/KZt43vXEgu8kWAJFMg5H0SIr8jUGdcOUOm5w/RURsnQBAGCbeUYq5xOfxFcY+6TziGDFQnuaTA69PabPvzyrLTBkfQ7F6lptIYrwrQJJH74FkqFT56v2lsE5nj0UavXEJNkq8sS7z/t/Qv76u6ECgYBlXcox74ghxZhwjfVM5o020DpgCJDFEuAYaTNLGcCWKWArkvjQiunzYRPzu+DeQXUkWHC6fLIS3b8De7Kz7hUHHxQwUYGU+ZXWZQYr39tvlB6QiVIn6acRmUIPPZ7LIEg8aCxSuO2sDvk2ToGHmnyO4SpqffbvxOufdnTHQWVFIQ==";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAiAGoH2Hy0jxm98hPn+GgyFghxcBLaUMewBhNZBNmZcgBTr3gslrDByQGIULyJiSXifXfZ0Zo4Clk0Bjen1Y/zpztwUZvLjzaGeFQX4K5Ofi3jBTD1oCzWLR+5bBCWP3uMiOpjAjd2rig+OW9T9PU0RXpxOTP19NVXqcmSwQ/tsp5nmT1VnA5NdNqxpEd0NQkill+gAXg9CfBxNyCmdCelAXfvAF8qhB8ZCQ3DSyF4gm0Aj5Hn/CE6P4of48ieBktrSKzzGCfa5HjRKN3Be9MRZBLBSUkjnkhy7Sjwch/e2IHPsUjSYhHoinFjZ9JfmBzG6arx3a6qOfyQWYGRB2TpQIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://工程公网访问地址/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://工程公网访问地址/alipay.trade.page.pay-JAVA-UTF-8/return_url.jsp";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

