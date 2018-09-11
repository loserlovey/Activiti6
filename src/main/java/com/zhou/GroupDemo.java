package com.zhou;

import org.junit.Test;

import java.util.List;
import org.activiti.engine.*;
import org.activiti.engine.identity.Group;

/**
 * �û������ɾ��Ĳ���
 * 
 * @author zhouweixin
 *
 */
public class GroupDemo {

	/**
	 * ������:��������
	 */
	@Test
	public void test1() {
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

		// ��ݷ���
		IdentityService identityService = processEngine.getIdentityService();

		// ������
		Group group = identityService.newGroup("1");
		group.setName("����Ա");
		group.setType("manager");

		// ������
		identityService.saveGroup(group);

		// ��ѯ�û���
		Group g = identityService.createGroupQuery().groupId("1").singleResult();
		System.out.println("test1-{id = " + g.getId() + ", name = " + g.getName() + ", type = " + g.getType() + "}");
	}

	/**
	 * ������:�Զ������������
	 */
	@Test
	public void test2() {
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

		// ��ݷ���
		IdentityService identityService = processEngine.getIdentityService();

		// ������
		Group group = identityService.newGroup("");
		group.setName("����Ա");
		group.setType("manager");
		group.setId(null);

		// ������
		identityService.saveGroup(group);

		// ��ѯ�û���
		List<Group> groups = identityService.createGroupQuery().groupNameLike("����Ա").list();

		System.out.println("test2");
		groups.stream().forEach(g -> {
			System.out.println("{id = " + g.getId() + ", name = " + g.getName() + ", type = " + g.getType() + "}");
		});
	}

	/**
	 * ������: rev_��ֵΪ0������, Ϊ1���޸�
	 */
	@Test
	public void test3() {
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

		// ��ݷ���
		IdentityService identityService = processEngine.getIdentityService();

		// ��ѯ�û���:��ʱ��ѯ����g��revision����>0, �ٴα���ʱ��Ϊ�Ǹ��²���
		Group g = identityService.createGroupQuery().groupId("1").singleResult();

		System.out.println("test3");
		System.out.println("�޸�ǰ-{id = " + g.getId() + ", name = " + g.getName() + ", type = " + g.getType() + "}");

		// �޸ĺ󱣴�, ÿ�α��涼��ʹrevision��1
		g.setName("����Ա-�޸ĺ�");
		g.setType("manager-�޸ĺ�");
		identityService.saveGroup(g);

		// �ٴβ�ѯ
		g = identityService.createGroupQuery().groupId("1").singleResult();
		System.out.println("�޸ĺ�-{id = " + g.getId() + ", name = " + g.getName() + ", type = " + g.getType() + "}");
	}

	/**
	 * ɾ���û���:����ɾ���û����û���֮��Ĺ�ϵ����,Ȼ��ɾ�����û���
	 */
	@Test
	public void test4() {
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

		// ��ݷ���
		IdentityService identityService = processEngine.getIdentityService();

		Group g = identityService.createGroupQuery().groupId("1").singleResult();
		System.out.println("test4");
		System.out.println("ɾ��ǰ-{id = " + g.getId() + ", name = " + g.getName() + ", type = " + g.getType() + "}");

		// ִ��ɾ������:����ɾ���û����û���֮��Ĺ�ϵ����,Ȼ��ɾ�����û���(����ɾ��)
		identityService.deleteGroup("1");

		// �ٴβ�ѯ
		g = identityService.createGroupQuery().groupId("1").singleResult();
		if (g != null) {
			System.out.println("ɾ����-{id = " + g.getId() + ", name = " + g.getName() + ", type = " + g.getType() + "}");
		} else {
			System.out.println("ɾ����-��ѯ���Ϊ��");
		}
	}
}
