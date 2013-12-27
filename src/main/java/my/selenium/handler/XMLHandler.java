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
	private String configfile = "config.xml";
	private SAXReader reader = new SAXReader();
	private Document doc;
	
	public XMLHandler() throws DocumentException{
			doc = new SAXReader().read(configfile);
	}
	
	public List<Node> getNodes(String xpath) {
		List<Node> list = doc.selectNodes(xpath);
		return list;
	}
	
	public Node getNode(String xpath) {
		return doc.selectSingleNode(xpath);
	}
	
	public String getText(String xpath){
		return doc.selectSingleNode(xpath).getText();
	}
}
