package aed.hibernate_olimpiadas.main;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;

import aed.hibernate_olimpiadas.tablas.Deportistas;
import aed.hibernate_olimpiadas.tablas.Paises;
import aed.hibernate_olimpiadas.util.HibernateUtil;


public class Main {
	public static void main(String[] args) {
		// Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		Session sesion = HibernateUtil.getSessionFactory().openSession();
		
		
		Paises nuevoPais;
		List<Paises> listaPaises = new ArrayList<Paises>();
		sesion.beginTransaction();
		for (int i = 1; i < 10; i++) {
			nuevoPais = new Paises();
			
			nuevoPais.setCodPais(""+i);
			nuevoPais.setNombrePais("España"+i);
			
			listaPaises.add(nuevoPais);
			
			sesion.save(nuevoPais);
			sesion.flush();
			
					
		}	
		sesion.getTransaction().commit();
		sesion.beginTransaction();
		
		Deportistas contacto = new Deportistas();
		
		
		contacto.setNombreDeportista("Ignacio");
		contacto.setDniDeportista("12.345.678-Z");
		contacto.setDeportistaPais(listaPaises.get(0));
		sesion.save(contacto);
		sesion.getTransaction().commit();
		
		
		sesion.close();
	}
	public void eliminar() {
		
	}
}