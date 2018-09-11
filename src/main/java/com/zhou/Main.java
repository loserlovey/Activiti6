package com.zhou;

import java.util.Map;

import org.activiti.engine.*;
import org.junit.Test;

/**
 * ���������������
 * 
 * @author zhouweixin
 *
 */
public class Main {
	/**
	 * Ĭ�Ϸ�������
	 */
	@Test
	public void test1() {
		// Ĭ�ϼ���resource�µ�activiti.cfg.xml�����ļ�
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
		System.out.println("test1-��������: " + processEngine.getName());
	}

	/**
	 * ʵ�ֵ�Ч��ͬtest1, ��test1�ڲ�ִ�е�ϸ��
	 */
	@Test
	public void test2() {
		// ������test1��ִ��ϸ��, ��ִ��init����ʱ, ��������������󶼻���ProcessEngines��Map������
		ProcessEngines.init();
		Map<String, ProcessEngine> processEngines = ProcessEngines.getProcessEngines();
		
		if(processEngines.containsKey("default")) {
			ProcessEngine processEngine = processEngines.get("default");
			System.out.println("test2-��������: " + processEngine.getName());
		}
	}
	
	/**
	 * ͨ�����ô���
	 */
	@Test
	public void test3() {
		// ���������ļ�
		ProcessEngineConfiguration config = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("my-activiti-cfg.xml", "myProcessEngineConfiguration");
		
		// �޸�����
		config.setProcessEngineName("myProcessEngine");
		ProcessEngine processEngine = config.buildProcessEngine();
		System.out.println("test3-��������\n"+ProcessEngines.getProcessEngines());
		processEngine.close();
	}
	
	/**
	 * ���������ע���ע��
	 */
	@Test
	public void test4() {
		// ���ص�ͬʱ��ע����������
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
		System.out.println("test4-���غ�-��������������� : " + ProcessEngines.getProcessEngines().size());
		System.out.println(ProcessEngines.getProcessEngines());
		
		// ע��
		ProcessEngines.unregister(processEngine);
		System.out.println("test4-ע����-��������������� : " + ProcessEngines.getProcessEngines().size());
		System.out.println(ProcessEngines.getProcessEngines());
		processEngine.close();
		
		// �����������´���
		ProcessEngineConfiguration processEngineConfiguration = processEngine.getProcessEngineConfiguration();
		processEngineConfiguration.setProcessEngineName("newName");
		processEngine = processEngineConfiguration.buildProcessEngine();
		
		// ע��		
		ProcessEngines.registerProcessEngine(processEngine);
		System.out.println("test4-ע���-��������������� : " + ProcessEngines.getProcessEngines().size());
		System.out.println(ProcessEngines.getProcessEngines());
		processEngine.close();
	}
}
