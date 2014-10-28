package br.com.triadworks.batch;

import org.apache.commons.lang.time.DurationFormatUtils;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;

import br.com.triadworks.model.Produto;
import br.com.triadworks.util.HibernateUtils;

public class ImportadorDeProdutosComStatelessSession {

	public static void main(String[] args) {
		
		long inicio = System.currentTimeMillis();
		
		StatelessSession session = HibernateUtils.getStatelessSession();
		Transaction tx = session.beginTransaction();
		
		for ( int i=0; i < 100000; i++ ) {
			Produto produto = new Produto("Produto #" + i);
			session.insert(produto);
			System.out.println("Produto #" + i);
		}
		
		tx.commit();
		session.close();
		
		long fim = System.currentTimeMillis();
		long duracao = (fim - inicio);
		
		System.out.println("[com StatelessSession] duração: " + DurationFormatUtils.formatDuration(duracao, "mm'm'ss's'"));
		
	}
	
}
