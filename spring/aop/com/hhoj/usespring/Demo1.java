package com.hhoj.usespring;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import com.zhu.spring.entity.Student;

public class Demo1 {
	public static void main(String[] args) {
		//ClassPathXmlApplicationContext application=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		ResourceLoader loader=new DefaultResourceLoader();
		Resource r=loader.getResource("applicationContext.xml");
		Resource resource=new ClassPathResource("applicationContext.xml");
		DefaultListableBeanFactory beanFactory=new DefaultListableBeanFactory();
		XmlBeanDefinitionReader reader=new XmlBeanDefinitionReader(beanFactory);
		reader.loadBeanDefinitions(r);
		Student student=(Student)beanFactory.getBean("student");
		System.out.println(student);
		
	}
}
