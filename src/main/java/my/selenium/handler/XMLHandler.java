/**
 * 
 */
package my.selenium.handler;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

/**
 * @author guka
 *
 */
public class XMLHandler {
	public static void main (String[] args) throws DocumentException{
		SAXReader sr = new SAXReader();
		  Document doc = sr.read("config.xml");
		  @SuppressWarnings("unchecked")
		List<? extends Node> list = doc.selectNodes("//test");		  
		  for(Node obj:list){
		   
		   Node el = (Node)obj;
		   System.out.println(el.getPath());
		  }
	}
}
