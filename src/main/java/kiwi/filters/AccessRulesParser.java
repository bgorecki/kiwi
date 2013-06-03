package kiwi.filters;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
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

import kiwi.models.DbUzytkownikEntity;

public class AccessRulesParser {
	static final String PATH = "path";
	static final String USER = "user";
	
	private Reader inputFile;
	
	public AccessRulesParser(String filename) throws FileNotFoundException {
		inputFile = new BufferedReader(new FileReader(filename));
	}
	
	public Map<String, List<String>> readRulesMap() throws XMLStreamException, FactoryConfigurationError {
		Map<String, List<String>> rules = new LinkedHashMap<String, List<String>>();
		
		String path = null;
		List<String> users = new LinkedList<String>();
		
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
						if(type.equals(DbUzytkownikEntity.ADMIN_ROLE))
							users.add(DbUzytkownikEntity.ADMIN_ROLE);
						else if(type.equals(DbUzytkownikEntity.PRZEWOZNIK_ROLE)) 
							users.add(DbUzytkownikEntity.PRZEWOZNIK_ROLE);	
					}
				}
				
			} else if(event.isEndElement()) {
				String elementType = event.asEndElement().getName().getLocalPart();
				if(elementType.equals(PATH)) {
					if(path != null && !users.isEmpty()) {
						rules.put(path, users);
						
						path = null;
						users = new LinkedList<String>();
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
