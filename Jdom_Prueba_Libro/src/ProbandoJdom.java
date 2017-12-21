import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class ProbandoJdom {

	public static void main(String[] args) throws FileNotFoundException, JDOMException, IOException {
		// CrearArbolNodos
		// Creamos el builder basado en SAX
		SAXBuilder builder = new SAXBuilder();
		// Construimos el arbol DOM a partir del fichero xml
		Document documentJDOM = builder.build(new FileInputStream("Alumnos.xml"));

		// recorrido del arbol
		// Obtenemos la etiqueta raíz
		Element raiz = documentJDOM.getRootElement();
		// Recorremos los hijos de la etiqueta raíz
		List<Element> hijosRaiz = raiz.getChildren();
		for (Element hijo : hijosRaiz) {
			// Obtenemos el nombre y su contenido de tipo texto
			String nombre = hijo.getName();
			String texto = hijo.getValue();
			
			
			
			for (Element element : hijo.getChildren()) {
				System.out.println("CICLO -> "+element.getAttributeValue("ciclo")+" <- CICLO");
				
				for (Element element2 : element.getChildren()) {
					System.out.println(element2.getName().toUpperCase() + ": " + element2.getValue() );
				}
				System.out.println("\n");
			}
			
			// Resultado de la etiqueta
//			System.out.println(hijo.getChild("alumno").getChildText("nombre"));
			
//			System.out.println("\nEtiqueta: " + nombre + ". Texto: " + texto);
			
			// Obtenemos el atributo id si lo hubiera
			String id = hijo.getAttributeValue("id");
			if (id != null) {
				System.out.println("\tId: " + id);

			}
		}

		CrearNuevaEtiqueta(documentJDOM);

	}

	public static void CrearNuevaEtiqueta(Document doc) {
		// Creamos una nueva etiqueta
		Element etiquetaNueva = new Element("etiquetaNueva");

		// Añadimos un atributo
		etiquetaNueva.setAttribute("atributoNuevo", "Es un nuevo atributo");
		// Añadimos contenido
		etiquetaNueva.setText("Contenido dentro de la nueva etiqueta");
		// La añadimos como hija a una etiqueta ya existente

		doc.getRootElement().addContent(etiquetaNueva);

		// escribirlo en un fichero de salida con el nuevo hijo

		try {
			XMLOutputter out = new XMLOutputter(Format.getPrettyFormat());

			FileOutputStream file = new FileOutputStream("salida1.xml");

			out.output(doc, file);
			// file.flush();
			file.close();
			// out.output(doc,System.out); para que tambien lo visualice por pantalla
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
