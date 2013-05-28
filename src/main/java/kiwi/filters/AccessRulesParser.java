package kiwi.filters;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.XMLEvent;

import kiwi.model.AdminUser;
import kiwi.model.AirlineCompanyUser;
import kiwi.model.User;

public class AccessRulesParser {
	static final String PATH = "path";
	static final String USER = "user";
	
	private Reader inputFile;
	
	public AccessRulesParser(String filename) throws FileNotFoundException {
		inputFile = new BufferedReader(new FileReader(filename));
	}
	
	public Map<String, List<Class<? extends User>>> readRulesMap() throws XMLStreamException, FactoryConfigurationError {
		Map<String, List<Class<? extends User>>> rules = new LinkedHashMap<String, List<Class<? extends User>>>();
		
		String path = null;
		List<Class<? extends User>> users = new LinkedList<Class<? extends User>>();
		
		XMLEventReader eventReader = XMLInputFactory.newInstance().createXMLEventReader(inputFile);
		while(eventReader.hasNext()) {
			XMLEvent event = eventReader.nextEvent();
		
			if(event.isStartElement()) {
				String elementType = event.asStartElement().getName().getLocalPart();
				if(elementType.equals(PATH)) {
					path = getAttribute(event, "value");
				} else if(elementType.equals(USER)) {
					if(path != null) {
						String type = getAttribute(event, "type");
						if(type.equals("admin"))
							users.add(AdminUser.class);
						else if(type.equals("airlineCompany")) 
							users.add(AirlineCompanyUser.class);
					}
				}
				
			} else if(event.isEndElement()) {
				String elementType = event.asEndElement().getName().getLocalPart();
				if(elementType.equals(PATH)) {
					if(path != null && !users.isEmpty()) {
						rules.put(path, users);
						
						path = null;
						users = new LinkedList<Class<? extends User>>();
					}
				}
			}
		}
		
		return rules;
	}
	
	private String getAttribute(XMLEvent event, String attribName) {
		QName attribQName = new QName(event.asStartElement().getName().getNamespaceURI(), attribName);
		Attribute attrib = event.asStartElement().getAttributeByName(attribQName);
		return attrib.getValue();
	}
}
