package br.com.triadworks.batch;

import org.apache.commons.lang.time.DurationFormatUtils;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import br.com.triadworks.model.Produto;
import br.com.triadworks.util.HibernateUtils;

public class ImportadorDeProdutos {

	public static void main(String[] args) {
		
		long inicio = System.currentTimeMillis();

		Session session = HibernateUtils.getSession();
		Transaction tx = session.beginTransaction();
		
		for ( int i=0; i < 100000; i++ ) {
			Produto produto = new Produto("Produto #" + i);
			session.save(produto);
			System.out.println("Produto #" + i);
			if (i % 100 == 0) {
				session.flush();
				session.clear();
			}
		}
		
		tx.commit();
		session.close();
		
		long fim = System.currentTimeMillis();
		long duracao = (fim - inicio);
		
		System.out.println("duração: " + DurationFormatUtils.formatDuration(duracao, "mm'm'ss's'"));
		
	}
	
}
