package Main.utilities;

import java.util.HashMap;
import java.util.Map;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlParseHandler {
	private Document doc;
	
	public XmlParseHandler(Document doc){
		this.doc = doc;
	}

	public Map<String, String> getChildNodesAttrValues(String nodeStr,String attrStr){
		Map<String, String> map = new HashMap<String, String>();
		NodeList nodeL = doc.getElementsByTagName(nodeStr).item(0).getChildNodes();
		for (int i=0;i<nodeL.getLength();i++){
			if(nodeL.item(i) instanceof Element){
				map.put(nodeL.item(i).getNodeName(), getAttribueValue(nodeL.item(i),attrStr));
			}
		}
		return map;
	}
	
	public Map<String, String> getChildNodesValues(String nodeStr){
		Map<String, String> map = new HashMap<String, String>();
		NodeList nodeL = doc.getElementsByTagName(nodeStr).item(0).getChildNodes();
		for (int i=0;i<nodeL.getLength();i++){
			if(nodeL.item(i) instanceof Element){
				map.put(nodeL.item(i).getNodeName(), nodeL.item(i).getTextContent());
			}
		}
		return map;
	}
	
	public String getChildNodeAttrValue(String parentStr, String nodeStr,String attrStr){
		String returnStr = "";
		NodeList nodeL = doc.getElementsByTagName(parentStr).item(0).getChildNodes();
		for (int i=0;i<nodeL.getLength();i++){
			if(nodeL.item(i) instanceof Element){
				if(nodeL.item(i).getNodeName().equals(nodeStr)){
					returnStr = getAttribueValue(nodeL.item(i),attrStr);
					break;
				}
			}
		}
		return returnStr;
	}
	
	public String getChildNodeValue(String parentStr,String nodeStr){
		String returnStr = "";
		NodeList nodeL = doc.getElementsByTagName(parentStr).item(0).getChildNodes();
		for (int i=0;i<nodeL.getLength();i++){
			if(nodeL.item(i) instanceof Element){
				if(nodeL.item(i).getNodeName().equals(nodeStr)){
					returnStr = nodeL.item(i).getTextContent();
					break;
				}
			}
		}
		return returnStr;
	}
	
	public String getNodeValue(String str) {
		String nodeValue = null;
		try{
			nodeValue = doc.getElementsByTagName(str).item(0).getTextContent();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nodeValue;
	}
	
	public String getAttribueValue(Node node,String attrName){
		NamedNodeMap attrM = node.getAttributes();
		for(int i=0;i<attrM.getLength();i++){
			if(attrM.item(i).getNodeName().equals(attrName)){
				return attrM.item(i).getNodeValue();
			}
		}
		return null;
	}
	
	public String getAttribueValue(String nodeName,String attrName){
		Node node = doc.getElementsByTagName(nodeName).item(0);
		NamedNodeMap attrM = node.getAttributes();
		for(int i=0;i<attrM.getLength();i++){
			if(attrM.item(i).getNodeName().equals(attrName)){
				return attrM.item(i).getNodeValue();
			}
		}
		return null;
	}
	
}
