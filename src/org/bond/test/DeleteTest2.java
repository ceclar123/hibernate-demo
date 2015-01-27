package org.bond.test;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class DeleteTest2 {

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

				String hql = "delete UserEntity u where u.id=:id";
				Query query = s.createQuery(hql.toString());

				query.setLong("id", 3L);
				int iRtn = query.executeUpdate();
				tx.commit();

				System.out.println("删除数据成功:" + iRtn);
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
