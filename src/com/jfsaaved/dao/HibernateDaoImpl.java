package com.jfsaaved.dao;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class HibernateDaoImpl {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public String getCircleCount() {
		String hql = "select count(*) from Circle";
		@SuppressWarnings("rawtypes")
		Query query = this.sessionFactory.openSession().createQuery(hql);
		return query.getResultList().get(0).toString();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


}
