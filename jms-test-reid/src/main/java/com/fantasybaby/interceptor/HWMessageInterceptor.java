package com.fantasybaby.interceptor;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.logging.Logger;

import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.interceptor.AbstractLoggingInterceptor;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.Phase;

public class HWMessageInterceptor extends  AbstractLoggingInterceptor {
	String CDATA_START = "<![CDATA[";
	String CDATA_END = "]]>";
	
	public HWMessageInterceptor(String phase) {
		super(phase);
	}
	public HWMessageInterceptor() {
		super(Phase.RECEIVE);
	}

	public void handleMessage(Message message) throws Fault { 
        try {
            String xml;
            InputStream is = message.getContent(InputStream.class);
            String encoding = (String)message.get(Message.ENCODING);
            xml = IOUtils.toString(is);
            String replaceAll = xml.replaceAll("<!\\[CDATA\\[", "").replaceAll("\\]\\]>", "");
            replaceXml(replaceAll);
            message.setContent(InputStream.class, new ByteArrayInputStream(replaceAll.getBytes(encoding)));
            message.getExchange().put("idtest", replaceAll);
        } catch (Exception e) {
            e.printStackTrace();
        } 
        }
	@Override
	protected Logger getLogger() {
		return null;
	}
	
	protected String replaceXml(String xml) {
		
		
		return "";
	}
}
