package com.fantasybaby.strategy.colors;

import java.io.File;
import java.io.FileFilter;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fantasybaby.strategy.colors.annotation.MessageType;

public class MyColor {
	private  ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
	private static Logger _logger = LoggerFactory.getLogger(MyColor.class);
	private static String BASICP_ACKAGE = "com.fantasybaby.strategy.colors.impl";
	private IColors myColor;
	private String colorType;
	private static String POINT = ".";
	private List<Class<? extends IColors>> childClass;
	public MyColor(String messageType) {
		//TODO validate messageType
		init();
		this.colorType = messageType;
		initColorInstance();
	}
	public void initColorInstance(){
		try {

			for (Class<? extends IColors> class1 : childClass) {
				MessageType annotation = class1.getAnnotation(MessageType.class);
				String value = annotation.value();
				if(value.equalsIgnoreCase(this.colorType)){
					myColor = class1.newInstance();
				}
			}
			if(myColor == null){
				throw new Exception("没有该子类实例");
			}
		} catch (Exception e) {
			_logger.error(e.getMessage());
		}
	}
	
	public String showColor(){
		return myColor.printColor();
	}
	@SuppressWarnings("unchecked")
	private void init(){
		this.childClass = new ArrayList<Class<? extends IColors>>();
		File[] resources = getResources();
		try {
			for (File file : resources) {
				String fullClassName =BASICP_ACKAGE +POINT+file.getName().replace(".class", "");
				_logger.info("load " + fullClassName);
				Class<?> loadClass = classLoader.loadClass(fullClassName);
				if(IColors.class.isAssignableFrom(loadClass)){
					_logger.info("add class " + loadClass.toString());
					this.childClass.add((Class<? extends IColors>)loadClass);
				}
			}
		} catch (Exception e) {
			_logger.error(e.getMessage());
		}
		
	}
	   //获取扫描的包下面所有的class文件
    public File[] getResources(){
        try {
            File file = new File(classLoader.getResource(BASICP_ACKAGE.replace(".", "/")).toURI());
            return file.listFiles(new FileFilter() {
                public boolean accept(File pathname) {
                    if (pathname.getName().endsWith(".class")) {
                        return true;
                    }
                    return false;
                }
            });
        } catch (URISyntaxException e) {
            throw new RuntimeException("未找到对应的类文件");
        }
    }
}
