package Parsers;

import java.io.File;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import Reports.ICS_213Report;

public class ICS_213Parser {

	public ICS_213Parser() {
	}

	public ICS_213Report parse(File file) {

		ICS_213Report report = new ICS_213Report();

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

			for (int i = 0; i < nodeList.getLength(); i++) {
				Node nNode = nodeList.item(i);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;

					report.incidentName = eElement.getElementsByTagName("incident_name")
					    .item(0).getTextContent();

					report.toName = eElement.getElementsByTagName("to_name").item(0)
					    .getTextContent();

					report.fromName = eElement.getElementsByTagName("from_name").item(0)
					    .getTextContent();

					report.subject = eElement.getElementsByTagName("subjectline").item(0)
					    .getTextContent();

					report.dateTime = eElement.getElementsByTagName("datetime").item(0)
					    .getTextContent();

					// Handle initial vs reply documents
					if (eElement.getElementsByTagName("msgoriginalbody").item(0) != null) {
						report.message = "Original Message:" + '\n' + eElement.getElementsByTagName("msgoriginalbody").item(0)
					    .getTextContent();
					}
					else {
						report.message = eElement.getElementsByTagName("message").item(0)
						    .getTextContent();
					}
					
					if (eElement.getElementsByTagName("reply").item(0) != null) {
						report.message = report.message + '\n' + '\n' + 
							"-------------------------------------------------------------------------" + 
							'\n' + "Reply:" +
							'\n' + eElement.getElementsByTagName("reply").item(0)
					    .getTextContent();
					}
					
					report.approvedName = eElement.getElementsByTagName("approved_name")
					    .item(0).getTextContent();

					report.approvedPosTitle = eElement
					    .getElementsByTagName("approved_postitle").item(0)
					    .getTextContent();
				}
			}
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Malformed XML File");
			
			report.incidentName = "";
			report.toName = "";
			report.fromName = "";
			report.subject = "";
			report.dateTime = "";
			report.message = "";
			report.approvedName = "";
			report.approvedPosTitle = "";
			
		}
		return (report);
	}
}