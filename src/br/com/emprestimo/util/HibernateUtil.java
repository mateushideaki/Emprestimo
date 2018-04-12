package br.com.emprestimo.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private SessionFactory sessionFactory;

	public HibernateUtil() {
		sessionFactory = new Configuration().configure().buildSessionFactory();
		
	}
	
	public SessionFactory getSessionFactory(){
		return this.sessionFactory;
	}

}
