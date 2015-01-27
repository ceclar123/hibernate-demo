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

public class QueryTest2 {

	public static void main(String[] args) {
		/*
		 * 多表查询
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
				// 采用子查询
				String hql = "select a from AlbumEntity a where exists(from UserEntity u where a.userID=u.id) and a.userID=:userID";
				Query query = s.createQuery(hql.toString());
				query.setLong("userID", 1L);
				List<AlbumEntity> list = query.list();

				// 遍历查询结果
				System.out.println("多条查询结果-查询结果:");
				for (Iterator<AlbumEntity> iterator = list.iterator(); iterator
						.hasNext();) {
					AlbumEntity user = iterator.next();
					System.out.println("id=" + user.getId() + ",age="
							+ user.getAlbumName() + user.getAlbumDesc());
				}

				// 连接查询
				// hql =
				// "select a from AlbumEntity a,UserEntity u where a.userID=u.id and a.userID=:userID";
				hql = "select a from AlbumEntity a inner join UserEntity u where a.userID=u.id and a.userID=:userID";
				query = s.createQuery(hql.toString());
				query.setLong("userID", 1L);
				list = query.list();

				// 遍历查询结果
				System.out.println("多条查询结果-查询结果:");
				for (Iterator<AlbumEntity> iterator = list.iterator(); iterator
						.hasNext();) {
					AlbumEntity user = iterator.next();
					System.out.println("id=" + user.getId() + ",age="
							+ user.getAlbumName() + user.getAlbumDesc());
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

	}
}