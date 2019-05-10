package com.fantasybaby.util;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author: liuxi
 * @time: 2019/5/9 21:49
 */
public class TestSendMessage {
    public static void soapSpecialConnection() throws Exception {
        String s = new String();
        StringBuilder soapHeader = new StringBuilder();
//soapUI自动生成的request xml路径，写入传入参数
        File file = new File("D:\\INXML.txt");
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
// 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                soapHeader.append(tempString);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        System.out.println("soapHeader=" + soapHeader);
        //设置soap请求报文的相关属性
        //url从soapUI的request1的RAW标签的POST获取，url中不要有空格
        String url = "http://192.168.1.207/web/ws/r/aws_ttsrv2_toptest";
        URL u = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) u.openConnection();
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setUseCaches(false);
        conn.setDefaultUseCaches(false);
        //Host，Content-Type，SOAPAction从soapUI的request1的RAW标签的Host，Content-Typ，SOAPActione获取
        conn.setRequestProperty("Host", "192.168.1.207");
        conn.setRequestProperty("Content-Type", "text/xml;charset=UTF-8");
        conn.setRequestProperty("Accept-Encoding", "gzip,deflate");
        conn.setRequestProperty("Content-Length", String.valueOf(soapHeader.length()));
        conn.setRequestProperty("SOAPAction", "");
        conn.setRequestProperty("Endpoint", "http://192.168.1.207/web/ws/r/aws_ttsrv2_toptest");
        conn.setRequestMethod("POST");
        //定义输出流
        OutputStream output = conn.getOutputStream();
        if (null != soapHeader) {
            byte[] b = soapHeader.toString().getBytes("utf-8");
//发送soap请求报文
            output.write(b, 0, b.length);
        }
        output.flush();
        output.close();
//定义输入流，获取soap响应报文
        InputStream input = conn.getInputStream();
//需设置编码格式，否则会乱码
        s = IOUtils.toString(input, "UTF-8");
        input.close();
        System.out.println("输出的xml=" + s);
    }

    public static void main(String[] args) {
        try {
            soapSpecialConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*Properties props = System.getProperties();
        props.setProperty("org.apache.cxf.stax.allowInsecureParser", "1");
        props.setProperty("UseSunHttpHandler", "true");
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
//
        try {
            Client client = dcf.createClient("http://localhost:8088/mockTIPTOPServiceGateWayBinding?wsdl");

            Object[] res = client.invoke("KCReplenishmentBillFeedback", "test echo");
            System.out.println("Echo response: " + res[0]);

        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
}
