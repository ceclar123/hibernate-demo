package org.bond.test;

import java.util.Iterator;
import java.util.List;

import org.bond.entity.AlbumEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class QueryTest3 {

	public static void main(String[] args) {
		/*
		 * 聚合函数使用
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
				// count
				String hql = "select count(*) from AlbumEntity a where 1=1";
				Query query = s.createQuery(hql.toString());
				Object obj = query.uniqueResult();
				System.out.println("计算结果count(*):" + obj);

				// max
				hql = "select max(a.id) from AlbumEntity a where 1=1";
				query = s.createQuery(hql.toString());
				obj = query.uniqueResult();
				System.out.println("计算结果max(id):" + obj);

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
	}
}
