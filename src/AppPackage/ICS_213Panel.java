package AppPackage;

import java.io.File;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Parsers.ICS_213Parser;
import Reports.ICS_213Report;
import java.awt.Color;

public class ICS_213Panel extends FormPanel {

	private static final long serialVersionUID = 1L;
	
	// Set up initial variables
	private JTextField tfIncidentName;
	private JTextField tfFrom;
	private JTextField tfTo;
	private JTextField tfSubject;
	private JTextField tfDateTime;
	private JTextArea taMessage;
	private JTextField tfApprovedName;
	private JTextField tfApprovedPosTitle;

	public ICS_213Panel() {
		setLayout(null);

		// Set Up Labels
		JLabel lblIncidentName = new JLabel("1. Incident Name");
		lblIncidentName.setBounds(10, 10, 104, 14);
		
		JLabel lblTo = new JLabel("2. To");
		lblTo.setBounds(10, 35, 104, 14);

		JLabel lblFrom = new JLabel("3. From");
		lblFrom.setBounds(405, 35, 101, 14);

		JLabel lblSubject = new JLabel("4. Subject");
		lblSubject.setBounds(10, 60, 104, 14);

		JLabel lblDateTime = new JLabel("5/6. Date / Time");
		lblDateTime.setBounds(405, 10, 101, 14);

		JLabel lblMessage = new JLabel("7. Message");
		lblMessage.setBounds(10, 85, 104, 14);

		JLabel lblApprovedBy = new JLabel("8. Approved By");
		lblApprovedBy.setBounds(10, 449, 104, 14);

		JLabel lblApprovedPosTitle = new JLabel("Position / Title");
		lblApprovedPosTitle.setBounds(405, 449, 82, 14);

		// Set Up Text Fields
		tfIncidentName = new JTextField();
		tfIncidentName.setBackground(Color.WHITE);
		tfIncidentName.setEditable(false);
		tfIncidentName.setBounds(110, 7, 285, 20);
		tfIncidentName.setColumns(10);

		tfTo = new JTextField();
		tfTo.setBackground(Color.WHITE);
		tfTo.setEditable(false);
		tfTo.setBounds(110, 32, 285, 20);
		tfTo.setColumns(10);

		tfFrom = new JTextField();
		tfFrom.setBackground(Color.WHITE);
		tfFrom.setEditable(false);
		tfFrom.setBounds(497, 32, 285, 20);
		tfFrom.setColumns(10);

		tfSubject = new JTextField();
		tfSubject.setBackground(Color.WHITE);
		tfSubject.setEditable(false);
		tfSubject.setBounds(110, 57, 672, 20);
		tfSubject.setColumns(10);
		
		tfDateTime = new JTextField();
		tfDateTime.setBackground(Color.WHITE);
		tfDateTime.setEditable(false);
		tfDateTime.setBounds(497, 7, 285, 20);
		tfDateTime.setColumns(10);

		tfApprovedName = new JTextField();
		tfApprovedName.setBackground(Color.WHITE);
		tfApprovedName.setEditable(false);
		tfApprovedName.setBounds(110, 446, 285, 20);
		tfApprovedName.setColumns(10);
		
		tfApprovedPosTitle = new JTextField();
		tfApprovedPosTitle.setBackground(Color.WHITE);
		tfApprovedPosTitle.setEditable(false);
		tfApprovedPosTitle.setBounds(497, 446, 285, 20);
		tfApprovedPosTitle.setColumns(10);	
		
		// Create and add the message TextArea + ScrollPane
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(110, 85, 672, 353);
		taMessage = new JTextArea();
		scrollPane.setViewportView(taMessage);
		taMessage.setEditable(false);
		taMessage.setLineWrap(true);

		// Add Labels to the panel
		this.add(lblIncidentName);
		this.add(lblTo);
		this.add(lblFrom);
		this.add(lblSubject);
		this.add(lblDateTime);
		this.add(lblMessage);
		this.add(lblApprovedBy);
		this.add(lblApprovedPosTitle);
		
		// Add TextFields to the panel
		this.add(tfIncidentName);
		this.add(tfTo);
		this.add(tfFrom);
		this.add(tfSubject);
		this.add(scrollPane);
		this.add(tfDateTime);
		this.add(tfApprovedPosTitle);
		this.add(tfApprovedName);


	}

	public void parse(File file) {

		// Create report and parser
		ICS_213Report report = new ICS_213Report();
		ICS_213Parser parser = new ICS_213Parser();

		// Parse the file and put it into an empty report
		report = parser.parse(file);

		// Set the text fields to the contents of the report
		try {
			tfIncidentName.setText(report.incidentName);
			tfDateTime.setText(report.dateTime);
			tfTo.setText(report.toName.toUpperCase());
			tfFrom.setText(report.fromName.toUpperCase());
			tfSubject.setText(report.subject);
			tfApprovedPosTitle.setText(report.approvedPosTitle);
			tfApprovedName.setText(report.approvedName);
			taMessage.setText(report.message);
			
		} catch (Exception e) {} // Do nothing on thrown exception
	}
}
