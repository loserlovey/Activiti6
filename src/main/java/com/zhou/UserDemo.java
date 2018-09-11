package com.zhou;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;

import javax.imageio.ImageIO;

import org.activiti.engine.*;
import org.activiti.engine.identity.Picture;
import org.activiti.engine.identity.User;
import org.apache.ibatis.io.Resources;
import org.junit.Test;

/**
 * �û�
 * 
 * @author zhouweixin
 *
 */
public class UserDemo {

	/**
	 * ���
	 */
	@Test
	public void test1() {
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
		IdentityService identityService = processEngine.getIdentityService();

		User user = identityService.newUser("1");
		user.setFirstName("��");
		user.setLastName("��");
		user.setEmail("*********@163.com");
		user.setPassword("123456");
		identityService.saveUser(user);

		User u = identityService.createUserQuery().userId("1").singleResult();
		System.out.println(String.format("test1-{id = %s, firstName = %s, lastName = %s, email = %s, password = %s}",
				u.getId(), u.getFirstName(), u.getLastName(), u.getEmail(), u.getPassword()));
	}

	/**
	 * ��֤�û�����
	 */
	@Test
	public void test2() {
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
		IdentityService identityService = processEngine.getIdentityService();

		User user = identityService.createUserQuery().userId("1").singleResult();
		System.out
				.println(String.format("test2-user {id = %s, firstName = %s, lastName = %s, email = %s, password = %s}",
						user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword()));

		boolean b1 = identityService.checkPassword("1", "123456");
		boolean b2 = identityService.checkPassword("1", "asdfas");

		System.out.println("123456--��֤���: " + b1);
		System.out.println("asdfas--��֤���: " + b2);
	}

	/**
	 * �����û��Զ�����Ϣ��ͼƬ
	 */
	public void test3() {
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
		IdentityService identityService = processEngine.getIdentityService();

		User user = identityService.newUser("3");
		user.setFirstName("��");
		user.setLastName("��");
		user.setEmail("abc@123.com");
		identityService.saveUser(user);

		// �����û���Ϣ
		identityService.setUserInfo("2", "age", "18");

		try {
			// �����û�ͼƬ
			FileInputStream fis = new FileInputStream(Resources.getResourceAsFile("ask-for-leave.png"));
			BufferedImage img = ImageIO.read(fis);
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			ImageIO.write(img, "png", output);
			byte[] pics = output.toByteArray();
			Picture picture = new Picture(pics, "ask-for-image");
			identityService.setUserPicture("3", picture);
		} catch (Exception e) {

		}
		
		System.out.println("test3-���Խ���!");
	}
	
	public static void main(String[] args) {
		new UserDemo().test3();
	}
}
