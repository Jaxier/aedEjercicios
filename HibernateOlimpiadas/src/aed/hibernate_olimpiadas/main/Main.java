package aed.hibernate_olimpiadas.main;

import org.hibernate.Session;

import aed.hibernate_olimpiadas.util.HibernateUtil;


public class Main {
	public static void main(String[] args) {
		// Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		Session sesion = HibernateUtil.getSessionFactory().openSession();
		sesion.beginTransaction();
		
		/*Deportistas contacto = new Deportistas();
		contacto.setId(1);
		contacto.setNombreDeportista("Ignacio");
		contacto.setDniDeportista("12.345.678-Z");
		contacto.setPaisDeportista("1");
		sesion.save(contacto);
		sesion.getTransaction().commit();*/
		
	}
}