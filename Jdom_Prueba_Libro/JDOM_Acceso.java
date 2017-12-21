import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Parent;
import org.jdom2.input.DOMBuilder;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;


public class JDOM_Acceso {
	static Document documentJDOM ;
	public static void Visualizar_XML() {
		
		cargarArbol();
		RecorrerArbol();
		
		
	}
	
	static void cargarArbol() {
		// Creamos el builder basado en SAX
		
		SAXBuilder builder = new SAXBuilder();
		
		// Construimos el arbol DOM a partir del fichero xml
			try {
				documentJDOM = builder.build(new FileInputStream("Alumnos.xml"));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JDOMException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
	}
	
	
	private static void RecorrerArbol() {
//////  Recorrer el arbol
		
	// Obtenemos la etiqueta raíz
	Element raiz = documentJDOM.getRootElement();
	// Recorremos los hijos de la etiqueta raíz
	// List<Element> hijosRaiz = raiz.getChildren("Alumnos");
	// for(Element hijo: hijosRaiz){
	// Obtenemos el nombre y su contenido de tipo texto
	
	// Como Alumnos hay un solo grupo, me puedo ahorrar el for	
	List<Element> hijosRaiz = raiz.getChildren("Alumnos");
	Element hijo = (Element) hijosRaiz.get(0);
		
		String nombre = hijo.getName();
		String texto = hijo.getValue();
		System.out.println("\nEtiqueta: "+nombre+ " texto: "+texto); // Obtenem	os el atributo id si lo hubiera
		List<Element> hijosA = hijo.getChildren("alumno");
		
		for(Element hijosAlumno: hijosA){
			 texto = hijosAlumno.getValue();
			 nombre = hijosAlumno.getName();
			 System.out.println("\nEtiqueta: "+nombre+". Texto: "+texto); // Obtenem	os el atributo id si lo hubiera
			String curso = hijosAlumno.getAttributeValue("curso");
			String ciclo = hijosAlumno.getAttributeValue("ciclo");
			
			List<Element> hijosTelefono= hijosAlumno.getChildren("telefono");
			Element TelefonoElement = (Element)hijosTelefono.get( 0 );
			String tipo =  TelefonoElement.getAttributeValue("tipo");
			System.out.println("\tCurso: "+curso+" Ciclo: "+ciclo+" tipo: "+tipo);
 		}	
}

	public static void Escribir_XML() throws IOException {			// añadir un alumno
		
		// Creamos una nueva etiqueta
		Element etiquetaNueva = new Element("alumno");
		// Añadimos un atributo
		etiquetaNueva.setAttribute("curso", "4");
		etiquetaNueva.setAttribute("ciclo", "Desarrollo"); 
		
		Element etiquetaNombre = new Element("nombre");
		etiquetaNombre.setText("nombreNuevo");
		
		Element etiquetaTelefono = new Element("telefono");
		etiquetaTelefono.setAttribute("tipo", "fijo");
		etiquetaTelefono.setText("TeelfonoNuevo");
		
		Element etiquetaPoblacion = new Element("poblacion");
		etiquetaPoblacion.setText("PoblacionNuevo");
		// Añadimos contenido
		etiquetaNueva.addContent(etiquetaNombre);
		etiquetaNueva.addContent(etiquetaTelefono);
		etiquetaNueva.addContent(etiquetaPoblacion);
		
		Element raiz = documentJDOM.getRootElement(); //obtengo la raiz
						
		List<Element> hijosRaiz = raiz.getChildren("Alumnos");// Podemos Obtener una etiqueta hija del raiz por nombre
		
		Element etiquetaAlumnos = (Element) hijosRaiz.get(0); // tomo el elemento que será padre
		// La añadimos como hija a una etiqueta ya existente	
		etiquetaAlumnos.addContent(etiquetaNueva);
		
	/*	
		<alumno curso="1" ciclo="Desarrollo">
		<nombre>Pepito Perez</nombre>
		<telefono tipo="fijo">922454545</telefono>
		<poblacion>LaLaguna</poblacion>
	</alumno>
	*/
		
	//Serializar visualizando en pantalla
		  Format format = Format.getPrettyFormat();  // para que salga indentado
		  XMLOutputter outputter = new XMLOutputter(format);
		  try {
			outputter.output(documentJDOM, System.out);  //docuemento nuevo se visualiza por pantalla

		  } catch (IOException e) {
			e.printStackTrace();
			}  

	// serializamos a un documento nuevo  
		  XMLOutputter out = new XMLOutputter(Format.getPrettyFormat());
		  FileOutputStream file;
		try {
			file = new FileOutputStream("NuevoFichero.xml");
			out.output(documentJDOM, file); 
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
