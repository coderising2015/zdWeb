package com.zd.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class APPUtils {
	static ApplicationContext ac;
	static{
	   String path="ApplicationContext.xml";
	   ac=new ClassPathXmlApplicationContext(path);
	}
      @SuppressWarnings("unchecked")
	public static <T> T getBean(String bean){
    	  return  (T) ac.getBean(bean);
      }
}
