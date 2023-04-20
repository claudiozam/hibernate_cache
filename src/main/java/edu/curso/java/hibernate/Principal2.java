package edu.curso.java.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class Principal2 {

	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		
		
		Query<Producto> productosCache1 = session.createQuery("from Producto as p order by p.nombre", Producto.class);
		productosCache1.setCacheable(true);
		List<Producto> productos1 = productosCache1.getResultList();
		for (Producto producto : productos1) {
			System.out.println(producto);
		}
		

		Query<Producto> productosCache2 = session.createQuery("from Producto as p order by p.nombre", Producto.class);
		productosCache2.setCacheable(true);
		List<Producto> productos2 = productosCache2.getResultList();
		for (Producto producto : productos2) {
			System.out.println(producto);
		}
		
		transaction.commit();
		session.close();
		
		HibernateUtil.close();
		
		
	}

}
