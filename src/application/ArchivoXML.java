package application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class ArchivoXML {

    private static final String XML_FILE_PATH = "archivoScores.xml";

    public static void crearXml(String dificultad, String inUsuario, int inPuntaje) {

        String texto = inUsuario + " " + inPuntaje; 

        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc;
            Element rootElement;

            File file = new File(XML_FILE_PATH);
            if (file.exists()) {
                // Si el archivo XML ya existe, cargar el documento existente
                doc = docBuilder.parse(file);
                rootElement = doc.getDocumentElement();
            } else {
                // Si el archivo XML no existe, crear un nuevo documento y un elemento raíz
                doc = docBuilder.newDocument();
                rootElement = doc.createElement("scores");
                doc.appendChild(rootElement);
            }
            
            // Crear un nuevo elemento con el texto proporcionado
            Element nuevoElemento = doc.createElement("score");
            nuevoElemento.setAttribute("difficulty", dificultad);
            Text nuevoTexto = doc.createTextNode(texto);
            nuevoElemento.appendChild(nuevoTexto);
            rootElement.appendChild(nuevoElemento);

            // Guardar los cambios en el archivo XML
            FileWriter writer = new FileWriter(file);
            writer.write(docToString(doc));
            writer.close();

            System.out.println("Elemento agregado al archivo XML exitosamente.");

        } catch (ParserConfigurationException | IOException | org.xml.sax.SAXException e) {
            e.printStackTrace();
        }
    }

    // Método para convertir un Document a String
    private static String docToString(Document doc) {
        try {
            javax.xml.transform.TransformerFactory tf = javax.xml.transform.TransformerFactory.newInstance();
            javax.xml.transform.Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(javax.xml.transform.OutputKeys.OMIT_XML_DECLARATION, "yes");
            java.io.StringWriter writer = new java.io.StringWriter();
            transformer.transform(new javax.xml.transform.dom.DOMSource(doc), new javax.xml.transform.stream.StreamResult(writer));
            return writer.getBuffer().toString();
        } catch (Exception e) {
            return null;
        }
    }

}


