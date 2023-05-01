package application;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class ArchivoXML {
	
	public ArchivoXML() {
		
	}
	
	public static void crearXml(String dificultad, String inUsuario, int inPuntaje) {
		String textoAGuardar = inUsuario + " " + inPuntaje + "/";
		
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			DOMImplementation implementation = builder.getDOMImplementation();
		
			Document documento = implementation.createDocument(null, "HighScores", null);
			documento.setXmlVersion("1.0");
			
			Element dificultades = documento.createElement("Dificultades");
			Element facil = documento.createElement("facil");
			Element medio = documento.createElement("Medio");
			Element dificil = documento.createElement("Dificil");
			
			dificultades.appendChild(facil);
			dificultades.appendChild(medio);
			dificultades.appendChild(dificil);
			
			Element usuariosFacil = documento.createElement("Usuarios");
			Element usuariosMedio = documento.createElement("Usuarios");
			Element usuariosDificil = documento.createElement("Usuarios");
			
			facil.appendChild(usuariosFacil);
			medio.appendChild(usuariosMedio);
			dificil.appendChild(usuariosDificil);
			
			switch (dificultad) {
			case "FACIL":
				Text textoFacil = documento.createTextNode(textoAGuardar);
				facil.appendChild(textoFacil);
				break;
			
			case "MEDIO":
				Text textoMedio = documento.createTextNode(textoAGuardar);
				medio.appendChild(textoMedio);
				break;
				
			case "DIFICIL":
				Text textoDificil = documento.createTextNode(textoAGuardar);
				dificil.appendChild(textoDificil);
				
				break;
			default:
				break;
			}
			
			documento.getDocumentElement().appendChild(dificultades);
			
			Source source = new DOMSource(documento);
			Result result = new StreamResult(new File("HighScores.xml"));
			
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.transform(source, result);
			
			
		} catch (ParserConfigurationException | TransformerFactoryConfigurationError | TransformerException e) {
			System.out.println(e.getMessage());
		}
	}

		 

		
	}

