package com.zhou;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.activiti.engine.*;
import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Test;

public class DatabaseConfigDemo {

	/**
	 * DBCP����Դ-�����ļ�
	 */
	@Test
	public void test1() {
		try {
			ProcessEngineConfiguration config = ProcessEngineConfiguration
					.createProcessEngineConfigurationFromResource("my-activiti-dbcp-1.xml");
			DataSource dataSource = config.getDataSource();
			System.out.println("test1-����Դ���� = " + dataSource);
			DatabaseMetaData metaData = dataSource.getConnection().getMetaData();
			System.out.println("test1-�������� = " + metaData);
			System.out.println("test1-����Դ�� = " + dataSource.getClass().getName());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * DBCP����Դ-����
	 */
	@Test
	public void test2() {
		try {
			// ��������Դ,��������Ӧ����
			BasicDataSource dataSource = new BasicDataSource();
			dataSource.setUsername("postgres");
			dataSource.setPassword("postgres");
			dataSource.setUrl("jdbc:postgresql://127.0.0.1:5432/activiti6ui");
			dataSource.setDriverClassName("org.postgresql.Driver");

			System.out.println("test2-�������� = " + dataSource.getConnection().getMetaData());

			ProcessEngineConfiguration config = ProcessEngineConfiguration
					.createProcessEngineConfigurationFromResource("my-activiti-dbcp-2.xml");

			config.setDataSource(dataSource);

			System.out.println("test2-����Դ���� = " + dataSource);
			System.out.println("test2-����Դ�� = " + dataSource.getClass().getName());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
