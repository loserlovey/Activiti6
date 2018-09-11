package com.zhou;

import org.activiti.engine.*;
import org.junit.Test;

/**
 * �����Զ��������������
 * 
 * @author zhouweixin
 *
 */
public class TestMyProcessEngineConfig {
	@Test
	public void test1() {
		MyProcessEngineConfiguration config = (MyProcessEngineConfiguration)ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("my-process-cfg.xml", "myProcessEngineConfiguration");
		ProcessEngine processEngine = config.buildProcessEngine();
		System.out.println("��������: " + config.getName());
		System.out.println("��������: " + processEngine.getName());
	}	
}
