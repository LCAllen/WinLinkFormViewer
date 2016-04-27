package AppPackage;

import java.io.File;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Parsers.ICS_205AParser;
import Reports.ICS_205AReport;
import java.awt.Color;

public class ICS_205APanel extends FormPanel {

	private static final long serialVersionUID = 1L;

	// Set up initial variables
	private JTextField tfIncidentName;
	private JTextField tfDateFrom;
	private JTextField tfDateTo;
	private JTextArea taAssignments;
	private JTextField tfApprovedName;
	private JTextField tfApprovedDate;

	public ICS_205APanel() {
		setLayout(null);

		// Setup Labels
		JLabel lblIncidentName = new JLabel("Incident Name");
		lblIncidentName.setBounds(10, 10, 82, 14);

		JLabel lblDateTimeFrom = new JLabel("Operational Period: From");
		lblDateTimeFrom.setBounds(405, 10, 150, 14);

		JLabel lblDateTimeTo = new JLabel("To");
		lblDateTimeTo.setBounds(656, 10, 22, 14);

		JLabel lblMessage = new JLabel("Assignments");
		lblMessage.setBounds(10, 35, 82, 14);

		JLabel lblApprovedBy = new JLabel("Approved By");
		lblApprovedBy.setBounds(10, 449, 82, 14);
		
		JLabel lblApprovedDate = new JLabel("Date");
		lblApprovedDate.setBounds(415, 449, 41, 14);
		
		// Set up TextFields
		tfIncidentName = new JTextField();
		tfIncidentName.setBackground(Color.WHITE);
		tfIncidentName.setEditable(false);
		tfIncidentName.setBounds(102, 7, 293, 20);
		tfIncidentName.setColumns(10);

		tfDateFrom = new JTextField();
		tfDateFrom.setBackground(Color.WHITE);
		tfDateFrom.setEditable(false);
		tfDateFrom.setBounds(552, 7, 93, 20);
		tfDateFrom.setColumns(10);
		
		tfDateTo = new JTextField();
		tfDateTo.setBackground(Color.WHITE);
		tfDateTo.setEditable(false);
		tfDateTo.setBounds(678, 7, 104, 20);
		tfDateTo.setColumns(10);

		tfApprovedName = new JTextField();
		tfApprovedName.setBackground(Color.WHITE);
		tfApprovedName.setEditable(false);
		tfApprovedName.setBounds(102, 446, 293, 20);
		tfApprovedName.setColumns(10);

		tfApprovedDate = new JTextField();
		tfApprovedDate.setBackground(Color.WHITE);
		tfApprovedDate.setEditable(false);
		tfApprovedDate.setColumns(10);
		tfApprovedDate.setBounds(456, 446, 326, 20);
		
		// Set up TextArea + ScrollPane
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(102, 35, 680, 403);
		taAssignments = new JTextArea();
		scrollPane.setViewportView(taAssignments);
		taAssignments.setEditable(false);
		taAssignments.setLineWrap(true);

		// Add Labels to the panel
		this.add(lblIncidentName);
		this.add(lblDateTimeFrom);
		this.add(lblDateTimeTo);
		this.add(lblMessage);
		this.add(lblApprovedBy);
		this.add(lblApprovedDate);
		
		// Add TextFields to the panel
		this.add(tfIncidentName);
		this.add(tfDateFrom);
		this.add(tfDateTo);
		this.add(scrollPane);
		this.add(tfApprovedName);
		this.add(tfApprovedDate);
	}

	public void parse(File file) {

		// Create report and parser
		ICS_205AReport report = new ICS_205AReport();
		ICS_205AParser parser = new ICS_205AParser();

		// Parse the file and put it into an empty report
		report = parser.parse(file);

		// Set the text fields to the contents of the report
		try {
			tfIncidentName.setText(report.incidentName);
			tfDateFrom.setText(report.dateFrom);
			tfDateTo.setText(report.dateTo);
			taAssignments.setText(report.assignments);
			tfApprovedName.setText(report.approvedName);
			tfApprovedDate.setText(report.approvedDateTime);

		}
		catch (Exception e) {
		} // Do nothing on thrown exception
	}
}
