package org.bond.test;

import java.util.Random;

import org.bond.entity.UserEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.omg.CORBA.IRObject;

public class UpdateTest2 {

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

				String hql = "update UserEntity u set u.userName='宋祖德' where u.id=:id";
				Query query = s.createQuery(hql.toString());
				query.setLong("id", 4L);
				int iRtn = query.executeUpdate();
				tx.commit();

				System.out.println("更新数据成功:" + iRtn);
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
