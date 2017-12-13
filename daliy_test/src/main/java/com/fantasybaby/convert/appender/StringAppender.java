package com.fantasybaby.convert.appender;

import java.io.Serializable;

import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.layout.PatternLayout;
import org.apache.logging.log4j.message.Message;
@Plugin(name = "StringAppender", category = "Core", elementType = "appender", printObject = true)
public class StringAppender extends AbstractAppender   {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6693008036076097546L;

	protected StringAppender(String name, Filter filter, Layout<? extends Serializable> layout) {
		super(name, filter, layout);
	}
	

	public StringAppender(String name, Filter filter, Layout<? extends Serializable> layout, boolean ignoreExceptions) {
		super(name, filter, layout, ignoreExceptions);
	}

	@Override
	public void append(LogEvent event) {
		Layout<? extends Serializable> layout2 = this.getLayout();
		Message message = event.getMessage();
		message.getFormat();
		final byte[] bytes = getLayout().toByteArray(event);//日志二进制文件，输出到指定位置就行
        //下面这个是要实现的自定义逻辑
        System.out.println("-----"+new String(bytes));
		System.out.println("----------------");
	}
	
	// 下面这个方法可以接收配置文件中的参数信息
    @PluginFactory
    public static StringAppender createAppender(@PluginAttribute("name") String name,
            @PluginElement("Filter") final Filter filter,
            @PluginElement("Layout") Layout<? extends Serializable> layout,
            @PluginAttribute("ignoreExceptions") boolean ignoreExceptions) {
        if (name == null) {
            LOGGER.error("No name provided for MyCustomAppenderImpl");
            return null;
        }
        if (layout == null) {
            layout = PatternLayout.createDefaultLayout();
        }
        return new StringAppender(name, filter, layout, ignoreExceptions);
    }

}
