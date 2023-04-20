package edu.curso.java.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

public class Principal3 {

	public static void main(String[] args) {

		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		Transaction transaction = session.beginTransaction();
		
		Query<Producto> consultaProductos = session.createQuery("from Producto as p where p.precio > :precio order by p.nombre ", Producto.class);
		consultaProductos.setParameter("precio", 2000.0);
		
		List<Producto> productos = consultaProductos.list();
		
		for (Producto p : productos) {
			System.out.println(p);
		}

		System.out.println("---------------------------");
		
		Query<Producto> consultaProductoPorId = session.createQuery("from Producto as p where p.id = 21", Producto.class);
		Producto productoRecuperado = consultaProductoPorId.uniqueResult();
		
		System.out.println(productoRecuperado);

		Query<Long> consultaContarProductos = session.createQuery("select count (*) from Producto as p", Long.class);
		Long cantidadProductos = consultaContarProductos.uniqueResult();
		System.out.println(cantidadProductos);

		
		Query<Double> consultaPrecioMaxProductos = session.createQuery("select max(p.precio) from Producto as p", Double.class);
		Double precioMax = consultaPrecioMaxProductos.uniqueResult();
		System.out.println(precioMax);
		
		
		
		Query<String> consultaNombresolor = session.createQuery("select p.nombre from Producto as p where p.id = 21", String.class);
		String nombreSolo = consultaNombresolor.uniqueResult();
		System.out.println(nombreSolo);
		
		Query<Object[]> consultaNombreYPrecio = session.createQuery("select p.nombre, p.precio from Producto as p where p.id = 21", Object[].class);
		Object[] nombreYPrecio = consultaNombreYPrecio.uniqueResult();
		String nombreObj = (String) nombreYPrecio[0];
		Double precioObj = (Double) nombreYPrecio[1];

		Query<Object[]> consultaNombreYPrecioLista = session.createQuery("select p.nombre, p.precio from Producto as p");

		for(Object[] row : consultaNombreYPrecioLista.getResultList()) {
			System.out.println(row[0]);
			System.out.println(row[1]);
		}

		System.out.println("-----------------------------------------------");
		
		Query<Producto> consultaProductosPaginado = session.createQuery("from Producto as p order by p.nombre ", Producto.class);
		consultaProductosPaginado.setFirstResult(2);
		consultaProductosPaginado.setMaxResults(2);
		
		List<Producto> productosPaginados = consultaProductosPaginado.getResultList();
		
		System.out.println(productosPaginados.size());
		for (Producto p : productosPaginados) {
			System.out.println(p);
		}
		
		
		System.out.println("---------------------------");
		
		NativeQuery<Producto> consultaNativa = session.createNativeQuery("select id, nombre, precio from Producto");
		consultaNativa.addEntity(Producto.class);
		List<Producto> productosNativos = consultaNativa.getResultList();
		for (Producto p : productosNativos) {
			System.out.println(p);
		}
		
		transaction.commit();
		session.close();
		
		HibernateUtil.close();
		
		
	}

}
