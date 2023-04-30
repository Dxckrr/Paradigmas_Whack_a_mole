package application;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ArchivoXML {
	
	public ArchivoXML() {
		
	}
	
	private void crearArchivoXML() {

		    try {
		        // Crear una nueva instancia del DocumentBuilder
		        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

		        // Crear un nuevo documento XML
		        Document doc = dBuilder.newDocument();

		        // Crear el elemento raíz
		        Element rootElement = doc.createElement("partida");
		        doc.appendChild(rootElement);

		        // Crear un elemento jugador
		        Element jugador = doc.createElement("jugador");
		        jugador.setAttribute("nombre", "Juan");
		        rootElement.appendChild(jugador);

		        // Crear un elemento puntuacion y asignarle un valor
		        Element puntuacion = doc.createElement("puntuacion");
		        puntuacion.appendChild(doc.createTextNode("500"));
		        jugador.appendChild(puntuacion);

		        // Crear un elemento highscore y asignarle un valor
		        Element highscore = doc.createElement("highscore");
		        highscore.appendChild(doc.createTextNode("800"));
		        jugador.appendChild(highscore);

		        // Escribir el contenido del documento XML a un archivo
		        File file = new File("partida.xml");
		        TransformerFactory transformerFactory = TransformerFactory.newInstance();
		        Transformer transformer = transformerFactory.newTransformer();
		        DOMSource source = new DOMSource(doc);
		        StreamResult result = new StreamResult(file);
		        transformer.transform(source, result);

		        // Confirmar que el archivo se ha generado correctamente
		        System.out.println("El archivo XML se ha generado correctamente.");

		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		  }
		

		
	}

