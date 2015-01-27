package org.bond.test;

import java.util.Iterator;
import java.util.List;

import org.bond.entity.UserEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class QueryTest1 {

	public static void main(String[] args) {
		try {
			/*
			 * �����ѯ
			 */

			SessionFactory sf = null;
			try {

				Configuration cfg = new Configuration().configure();
				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
						.applySettings(cfg.getProperties()).build();
				sf = cfg.buildSessionFactory(serviceRegistry);
				Session s = null;

				try {
					s = sf.openSession();

					// ������ѯ
					String hql = "select u from UserEntity u where u.id=:id";
					Query query = s.createQuery(hql.toString());
					query.setLong("id", 1L);
					UserEntity obj = (UserEntity) query.uniqueResult();
					if (obj != null) {
						System.out.println("������ѯ���-��ѯ���:");
						System.out.println("id=" + obj.getId() + ",age="
								+ obj.getLoginName() + obj.getUserName());
					}

					// ������ѯ
					hql = "select u from UserEntity u where u.loginName=:loginName";
					query = s.createQuery(hql.toString());
					query.setString("loginName", "root");
					List<UserEntity> list = query.list();

					// ������ѯ���
					System.out.println("������ѯ���-��ѯ���:");
					for (Iterator<UserEntity> iterator = list.iterator(); iterator
							.hasNext();) {
						UserEntity user = iterator.next();
						System.out.println("id=" + user.getId() + ",age="
								+ user.getLoginName() + user.getUserName());
					}

					// ��ҳ��ѯ
					hql = "select u from UserEntity u where 1=1 order by u.id";
					query = s.createQuery(hql.toString());
					query.setFirstResult(1);// ��һ������λ��
					query.setMaxResults(3);// ��������

					list = query.list();

					// ������ѯ���
					System.out.println("��ҳ��ѯ���-��ѯ���:");
					for (Iterator<UserEntity> iterator = list.iterator(); iterator
							.hasNext();) {
						UserEntity user = iterator.next();
						System.out.println("id=" + user.getId() + ",age="
								+ user.getLoginName() + user.getUserName());
					}

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (s != null) {
						s.close();
					}
				}

			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				if (sf != null) {
					sf.close();
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
}
