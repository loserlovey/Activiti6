package com.nltech;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

import org.activiti.engine.*;
import org.activiti.engine.identity.Picture;
import org.activiti.engine.identity.User;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.ibatis.io.Resources;
import org.junit.Test;


public class Activiti6Demo {

	String processInstanceId = "";
	
	
	public static void main(String[] args) {
		String[] functionList = {
				"1. �½��û�",
				"2. ��������",
				"3. ��������"
		}; 
		
		Scanner sc = new Scanner(System.in);   
		String str = null;
		
		do {
			for (String string : functionList) {
				System.out.println(string);
			}
			System.out.print("������(1-3):");
			str = sc.nextLine();
			System.out.println(str);
			if (str.compareTo("1") == 0) {
				// ����û�
				AddUsers();
			} else if (str.compareTo("2") == 0) {
				// ���������ļ�
				DeployBPMN();
			} else if (str.compareTo("3") == 0) {
				ProcessInstance processInstance = StartProcess();
				CompleteTask();
			}
		} while (str.compareTo("q") != 0); 
	}

	public static void CompleteTask() {
		// ����Ĭ������(����Ĭ�������ļ�activiti.cfg.xml)
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
		// �������
		TaskService taskService = processEngine.getTaskService();
		
		List<Task> tasks = new ArrayList<Task>();
		tasks = taskService.createTaskQuery().list();
		while (tasks != null) {
			for (Task task : tasks) {
				System.out.println("��ǰ��������: " + task.getName());
				System.out.println("��ɵ�ǰ����: " + task.getName());
				taskService.complete(task.getId());			
			}
			tasks = taskService.createTaskQuery().list();
		}
	}
	
	public static ProcessInstance StartProcess() {
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
		RuntimeService runtimeService = processEngine.getRuntimeService();
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("employeeLeaveProcess");

		System.out.println("��������: " + processInstance.getProcessInstanceId());
		return processInstance;
	}
	
	public static void DeployBPMN() {
		// ����Ĭ������(����Ĭ�������ļ�activiti.cfg.xml)
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

		// �洢����
		RepositoryService repositoryService = processEngine.getRepositoryService();

		// �洢����������
		repositoryService.createDeployment()
			.addClasspathResource("employeeLeaveProcess.bpmn")
			.addClasspathResource("employeeLeaveProcess.png").deploy();
		System.out.println("�������� `employeeLeaveProcess.bpmn`");
	}
	
	public static void AddUsers() {
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
		IdentityService identityService = processEngine.getIdentityService();
		
		System.out.println("��ʼ�½��û�....");
		User user = identityService.newUser("1");
		user.setId("employee");
		user.setFirstName("employee");
		user.setLastName("nltech");
		user.setEmail("employee@nltech.com");
		user.setPassword("test");
		identityService.saveUser(user);
		System.out.println("�½��û� `employee`");
		
		User userManager = identityService.newUser("2");
		userManager.setId("manager");
		userManager.setFirstName("manager");
		userManager.setLastName("nltech");
		userManager.setEmail("manager@nltech.com");
		userManager.setPassword("test");
		identityService.saveUser(userManager);
		System.out.println("�½��û� `manager`");
		
		User userHr = identityService.newUser("3");
		userHr.setId("hr");
		userHr.setFirstName("hr");
		userHr.setLastName("nltech");
		userHr.setEmail("hr@nltech.com");
		userHr.setPassword("test");
		identityService.saveUser(userHr);
		System.out.println("�½��û� `hr`");
	}
}
