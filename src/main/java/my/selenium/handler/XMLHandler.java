/**
 * 
 */
package my.selenium.handler;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
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
		//List<Node> list = doc.selectNodes("//*");
		Node node = doc.selectSingleNode("/testCases/module");
		System.out.println(node.getPath());
		//System.out.println(list.size());
		//for (Node element:list){
	//		System.out.println(element.getPath());
		//}
	}
}
