package com.zhou;

import org.junit.Test;

import java.io.InputStream;

import org.activiti.engine.*;

/**
 * �����������ö���
 * 
 * @author zhouweixin
 *
 */
public class ProcessEngineConfigDemo {
	/**
	 * ��ȡĬ�ϵ������ļ�
	 */
	@Test
	public void test1() {
		// Ĭ�ϼ���:activiti.cfg.xml�ļ�, beanΪprocessEngineConfiguration
		ProcessEngineConfiguration config = ProcessEngineConfiguration
				.createProcessEngineConfigurationFromResourceDefault();
		
		// ��������
		ProcessEngine processEngine = config.buildProcessEngine();
		System.out.println("test1-��������: " + config.getProcessEngineName());
		System.out.println("test1-��������: " + processEngine.getName());
		
		System.out.println();
	}

	/**
	 * ��ȡ�Զ���������ļ���bean��
	 */
	@Test
	public void test2() {
		ProcessEngineConfiguration config = ProcessEngineConfiguration
				.createProcessEngineConfigurationFromResource("my-activiti-cfg.xml", "myProcessEngineConfiguration");

		System.out.println("test2-��������: " + config.getProcessEngineName());
		System.out.println();
	}

	/**
	 * ͨ����������ȡ
	 */
	@Test
	public void test3() {
		try {
			ClassLoader classLoader = ProcessEngineConfigDemo.class.getClassLoader();
			InputStream fis = classLoader.getResourceAsStream("my-activiti-cfg.xml");
			ProcessEngineConfiguration config = ProcessEngineConfiguration
					.createProcessEngineConfigurationFromInputStream(fis, "myProcessEngineConfiguration");
			System.out.println("test3-��������: " + config.getProcessEngineName());
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ͨ����������ʵ����
	 */
	@Test
	public void test4() {
		// ����Ҫ���������ļ�,ʹ��Ĭ��ֵ
		ProcessEngineConfiguration config = ProcessEngineConfiguration.createStandaloneInMemProcessEngineConfiguration();
		
		// Ĭ���õ���h2�ڴ����ݿ�
		System.out.println("test4-dbschemaUpdate: " + config.getDatabaseSchemaUpdate());
		System.out.println("test4-jdbcUrl: " + config.getJdbcUrl());
		System.out.println("test4-jdbcDriver: " + config.getJdbcDriver());
		System.out.println();
	}
	
	/**
	 * ͨ����������ʵ����
	 */
	@Test
	public void test5() {
		// ����Ҫ���������ļ�,ʹ��Ĭ��ֵ
		// StandaloneInMemProcessEngineConfiguration��ĸ�����StandaloneProcessEngineConfiguration
		ProcessEngineConfiguration config = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
		
		// Ĭ���õ���h2�ڴ����ݿ�
		System.out.println("test5-dbschemaUpdate: " + config.getDatabaseSchemaUpdate());
		System.out.println("test5-jdbcUrl: " + config.getJdbcUrl());
		System.out.println("test5-jdbcDriver: " + config.getJdbcDriver());
		System.out.println();
	}
}
