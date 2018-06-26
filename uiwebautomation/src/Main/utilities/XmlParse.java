/**
 * 
 */
package Main.utilities;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;


public class XmlParse {

    private static Document configDoc;
    
    public static XmlParseHandler getConfigDocInstance(String configPath){
    	if (configDoc == null){
    		return new XmlParseHandler(buildConfigDoc(configPath));
    	} 
    	return new XmlParseHandler(configDoc);
    }
	
	private static Document buildConfigDoc(String confpath) {	
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			configDoc = builder.parse(confpath);
			configDoc.normalize();
			return configDoc;
		} catch (Exception e) {
			e.getMessage();
			return null;
		} 
	}
	
}
