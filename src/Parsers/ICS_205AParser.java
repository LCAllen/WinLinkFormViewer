package Parsers;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import Reports.ICS_205AReport;

public class ICS_205AParser {

	public ICS_205AParser() {
	}

	public ICS_205AReport parse(File file) {

		ICS_205AReport report = new ICS_205AReport();

		try {
			// Build Document
			DocumentBuilderFactory docBuildFactory = DocumentBuilderFactory
			    .newInstance();
			DocumentBuilder docBuilder = docBuildFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(file);

			// Normalize the document
			doc.getDocumentElement().normalize();

			XPath xPath = XPathFactory.newInstance().newXPath();

			String expression = "/RMS_Express_Form/variables";
			NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(doc,
			    XPathConstants.NODESET);

			// Remove the null so it doesnt show up in the report.
			report.assignments = "";
			
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node nNode = nodeList.item(i);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;

					report.incidentName = eElement.getElementsByTagName("incident_name")
					    .item(0).getTextContent();

					report.dateFrom = eElement.getElementsByTagName("datetimefrom").item(0)
					    .getTextContent();


					report.dateTo = eElement.getElementsByTagName("datetimeto").item(0)
					    .getTextContent();

					for (int j = 1; j < 21; j++) {
						report.assignments = report.assignments + eElement.getElementsByTagName("assignment" + j).item(0)
					    .getTextContent() + "\t";
					    
					  report.assignments = report.assignments + eElement.getElementsByTagName("name" + j).item(0)
					    .getTextContent() + "\t";
					    
					  report.assignments = report.assignments + eElement.getElementsByTagName("method" + j).item(0)
					    .getTextContent() + "\n";
					  
					  
					}
					
					report.approvedName = eElement.getElementsByTagName("preparedname")
					    .item(0).getTextContent();
					
					report.approvedDateTime = eElement.getElementsByTagName("date_time").item(0)
					    .getTextContent();
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return (report);
	}
}