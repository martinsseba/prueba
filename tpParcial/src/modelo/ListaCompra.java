package modelo;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;

//ESTE SE ENCARGA DE HACER EL XML, PARA LA LISTA DE ARTICULOS A COMPRAR.

public class ListaCompra 
{
	
	
	public ListaCompra()
	{
		
	}
	
	public int numFacturaUltima()
	{
		int numFact;
		String sFichero = "ListaCompra.xml";
		File fichero = new File(sFichero);
		if (fichero.exists())
		{
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			try
			{
				DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
				Document doc = docBuilder.parse (new File("ListaCompra.xml"));
				

				NodeList listaFacNum = doc.getElementsByTagName("numeroFacturaUltima");
			
				numFact = Integer.parseInt(listaFacNum.item(0).getTextContent());
				
		
				
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				numFact = -1;
			}
			
		}
		else
		{
			numFact = 0;
		}

		
		return numFact;
	}
	
	public boolean creaListaCompra(ArrayList<FacturaItem> listaFacturaItem)
	{
		boolean resulExitoso = true;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try
		{
			DocumentBuilder builder = factory.newDocumentBuilder();
			DOMImplementation implementation = builder.getDOMImplementation();
			
			//Ultima factura va a tener que tener un metodo, que se fije si el archivo UML
			// existe, sacar el numeroFacturaUltima, sino arrancar de la 1.
/******************* FALTA HACER ACA!!!!*/
			
			int ultimaFactura = new DAOFactura().proxId()-1;
			Document document = implementation.createDocument(null, "ListaCompra", null);
			document.setXmlVersion("1.0"); // asignamos la version de nuestro XML
				
			Element raiz1 = document.createElement("numeroFacturaUltima"); // creamos el elemento raiz
			Text textoRaiz = document.createTextNode(Integer.toString(ultimaFactura));
			
			Element raiz2 = document.createElement("listaArticulos");
			
			Element art, idArt, codArt,nomArt,cantArt;
			Text textIdArt, textCodArt, textNomArt,textCantArt;
			
			//Esto  se repite por cada elemento de la listaItemFactura
			for(FacturaItem facturaItem: listaFacturaItem)
			{
				art = document.createElement("articulo");
								
				idArt = document.createElement("idArticulo");
				textIdArt = document.createTextNode(Integer.toString(facturaItem.getArticulo().getIdArticulo()));
				idArt.appendChild(textIdArt);
				codArt = document.createElement("codigo");
				textCodArt = document.createTextNode(facturaItem.getArticulo().getCodigoArticulo());
				codArt.appendChild(textCodArt);
				nomArt = document.createElement("nombre");
				textNomArt = document.createTextNode(facturaItem.getArticulo().getArticulo());
				nomArt.appendChild(textNomArt);
				cantArt = document.createElement("cantidad");
				textCantArt = document.createTextNode(Integer.toString(facturaItem.getCantidad()));
				cantArt.appendChild(textCantArt);
				
				art.appendChild(idArt);
				art.appendChild(codArt);
				art.appendChild(nomArt);
				art.appendChild(cantArt);
				
				raiz2.appendChild(art);
			}
			// fin de lo que se repite.
				
			document.getDocumentElement().appendChild(raiz1);
			raiz1.appendChild(textoRaiz);
			document.getDocumentElement().appendChild(raiz2);

			//pegamos la raiz al documento
			
			
			Source source = new DOMSource(document);
			Result result = new StreamResult(new java.io.File("listaCompra.xml")); //nombre del archivo
			

			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.transform(source, result);
			
			/*esto es para mostrar en pantalla.*/
			//Result console= new StreamResult(System.out);			
			//transformer.transform(source, console);
		}
		catch(Exception e)
		{
			System.err.println("Error: "+e);
			resulExitoso = false;
		}
		
		return resulExitoso;
	}


}
