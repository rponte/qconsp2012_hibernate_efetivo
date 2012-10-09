package br.com.triadworks.util;

import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

import br.com.triadworks.model.Produto;

public class HibernateUtils {

	private static SessionFactory factory;
	
	static {
		Configuration cfg = new Configuration()
			.addAnnotatedClass(Produto.class)
			.configure();
		factory = cfg.buildSessionFactory();
	}
	
	public static Session getSession() {
		return factory.openSession();
	}
	
	public static StatelessSession getStatelessSession() {
		return factory.openStatelessSession();
	}
	
}
