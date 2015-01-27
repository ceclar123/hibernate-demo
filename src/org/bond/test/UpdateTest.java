package org.bond.test;

import java.util.Random;

import org.bond.entity.UserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class UpdateTest {

	public static void main(String[] args) {
		SessionFactory sf = null;
		try {

			Configuration cfg = new Configuration().configure();
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
					.applySettings(cfg.getProperties()).build();
			sf = cfg.buildSessionFactory(serviceRegistry);
			Session s = null;
			Transaction tx = null;
			try {
				s = sf.openSession();
				tx = s.beginTransaction();

				int rd = new Random().nextInt();
				UserEntity user = new UserEntity();
				user.setId(2L);
				user.setLoginName("Test-" + rd);
				user.setUserName("测试用户-" + rd);
				user.setPassword("11111111-" + rd);
				user.setMd5psd("2222222-" + rd);
				user.setStatus((short) 1);

				// s.update(user);
				s.saveOrUpdate(user);
				tx.commit();

				System.out.println("更新数据成功");
			} catch (Exception e) {
				if (tx != null) {
					tx.rollback();
				}
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

	}

}
