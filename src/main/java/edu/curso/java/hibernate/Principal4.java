package edu.curso.java.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class Principal4 {

	public static void main(String[] args) {

		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		Transaction transaction = session.beginTransaction();
		
		/*Persona persona = new Persona();
		persona.setNombre("Juan");
		
		session.save(persona);
		
		Empleado empleado = new Empleado();
		empleado.setNombre("Maria");
		empleado.setSueldo(5000.0);
		
		session.save(empleado);*/
		
		//Long idBuscar = 37L;
		//Empleado empleado = session.get(Empleado.class, idBuscar);
		//System.out.println(empleado.getSueldo());
		
		Query<Empleado> empleadosConsulta = session.createQuery("from Empleado", Empleado.class);
		List<Empleado> empleados = empleadosConsulta.getResultList();
		
		for (Empleado empleado : empleados) {
			System.out.println(empleado.getNombre());
		}
		
		
		transaction.commit();
		session.close();
		
		HibernateUtil.close();
		
		
	}

}
