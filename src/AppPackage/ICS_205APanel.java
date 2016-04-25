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
	private JTextField tfIncidentName;
	
	private JTextField tfDateTo;
	private JTextField tfDateFrom;

	private JTextArea taAssignments;
	
	private JTextField tfApprovedName;
	private JTextField tfApprovedDate;

	public ICS_205APanel() {
		setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(102, 35, 680, 403);
		this.add(scrollPane);
		taAssignments = new JTextArea();
		scrollPane.setViewportView(taAssignments);
		taAssignments.setEditable(false);
		taAssignments.setLineWrap(true);

		// Setup Labels
		JLabel lblApprovedBy = new JLabel("Approved By");
		lblApprovedBy.setBounds(10, 449, 82, 14);
		this.add(lblApprovedBy);

		JLabel lblMessage = new JLabel("Assignments");
		lblMessage.setBounds(10, 35, 82, 14);
		this.add(lblMessage);

		JLabel lblDateTimeTo = new JLabel("To");
		lblDateTimeTo.setBounds(656, 10, 22, 14);
		this.add(lblDateTimeTo);

		JLabel lblIncidentName = new JLabel("Incident Name");
		lblIncidentName.setBounds(10, 10, 82, 14);
		this.add(lblIncidentName);

		tfIncidentName = new JTextField();
		tfIncidentName.setBackground(Color.WHITE);
		tfIncidentName.setEditable(false);
		tfIncidentName.setBounds(102, 7, 293, 20);
		tfIncidentName.setColumns(10);

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

		JLabel lblDateTimeFrom = new JLabel("Operational Period: From");
		lblDateTimeFrom.setBounds(405, 10, 150, 14);
		add(lblDateTimeFrom);

		this.add(tfIncidentName);
		this.add(tfDateTo);
		this.add(tfApprovedName);

		tfDateFrom = new JTextField();
		tfDateFrom.setBackground(Color.WHITE);
		tfDateFrom.setEditable(false);
		tfDateFrom.setBounds(552, 7, 93, 20);
		add(tfDateFrom);
		tfDateFrom.setColumns(10);

		JLabel lblApprovedDate = new JLabel("Date");
		lblApprovedDate.setBounds(415, 449, 41, 14);
		add(lblApprovedDate);

		tfApprovedDate = new JTextField();
		tfApprovedDate.setBackground(Color.WHITE);
		tfApprovedDate.setEditable(false);
		tfApprovedDate.setColumns(10);
		tfApprovedDate.setBounds(456, 446, 326, 20);
		add(tfApprovedDate);
	}

	public void parse(File file) {

		ICS_205AReport report = new ICS_205AReport();
		ICS_205AParser parser = new ICS_205AParser();

		report = parser.parse(file);

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
