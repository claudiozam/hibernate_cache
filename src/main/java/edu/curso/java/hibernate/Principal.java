package edu.curso.java.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class Principal {

	public static void main(String[] args) {

		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		Transaction transaction = session.beginTransaction();
		
		
	
		Query<Producto> consultaProductos1 = session.createQuery("from Producto as p order by p.nombre", Producto.class);
		consultaProductos1.setCacheable(true);
		List<Producto> productos = consultaProductos1.list();
		
		for (Producto p : productos) {
			System.out.println(p);
		}
		

		Query<Producto> consultaProductos2 = session.createQuery("from Producto as p order by p.nombre", Producto.class);
		consultaProductos1.setCacheable(true);
		productos = consultaProductos1.list();
		
		for (Producto p : productos) {
			System.out.println(p);
		}
		
		transaction.commit();
		session.close();
		
		System.out.println("----------------------------------------");
		
		Session session2 = HibernateUtil.getSessionFactory().openSession();
		
		Producto productoCache1 = session2.get(Producto.class, 16L);
		System.out.println(productoCache1);

		Producto productoCache2 = session2.get(Producto.class, 16L);
		System.out.println(productoCache2);

		
		session2.close();
		
		System.out.println("----------------------------------------");

		
		Session session3 = HibernateUtil.getSessionFactory().openSession();
		
		Producto productoCache3 = session3.get(Producto.class, 16L);
		System.out.println(productoCache3);


		
		session3.close();
		
		HibernateUtil.close();
		
		
	}

}
