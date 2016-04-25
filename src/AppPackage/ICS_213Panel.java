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
	private JTextField tfIncidentName;
	private JTextField tfTo;
	private JTextField tfFrom;
	private JTextField tfSubject;
	private JTextField tfApprovedPosTitle;
	private JTextField tfApprovedName;

	private JTextArea taMessage;
	private JTextField tfDateTime;

	public ICS_213Panel() {
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(102, 85, 680, 353);
		this.add(scrollPane);
		taMessage = new JTextArea();
		scrollPane.setViewportView(taMessage);
		taMessage.setEditable(false);
		taMessage.setLineWrap(true);

		// Setup Labels
		JLabel lblApprovedBy = new JLabel("Approved By");
		lblApprovedBy.setBounds(10, 449, 104, 14);
		this.add(lblApprovedBy);

		JLabel lblApprovedPosTitle = new JLabel("Position / Title");
		lblApprovedPosTitle.setBounds(405, 449, 82, 14);
		this.add(lblApprovedPosTitle);

		JLabel lblMessage = new JLabel("Message");
		lblMessage.setBounds(10, 85, 82, 14);
		this.add(lblMessage);

		JLabel lblTo = new JLabel("To");
		lblTo.setBounds(10, 35, 82, 14);
		this.add(lblTo);

		JLabel lblFrom = new JLabel("From");
		lblFrom.setBounds(405, 35, 36, 14);
		this.add(lblFrom);

		JLabel lblSubject = new JLabel("Subject");
		lblSubject.setBounds(10, 60, 82, 14);
		this.add(lblSubject);

		JLabel lblIncidentName = new JLabel("Incident Name");
		lblIncidentName.setBounds(10, 10, 82, 14);
		this.add(lblIncidentName);

		tfIncidentName = new JTextField();
		tfIncidentName.setBackground(Color.WHITE);
		tfIncidentName.setEditable(false);
		tfIncidentName.setBounds(102, 7, 293, 20);
		tfIncidentName.setColumns(10);

		tfTo = new JTextField();
		tfTo.setBackground(Color.WHITE);
		tfTo.setEditable(false);
		tfTo.setBounds(102, 32, 293, 20);
		tfTo.setColumns(10);

		tfFrom = new JTextField();
		tfFrom.setBackground(Color.WHITE);
		tfFrom.setEditable(false);
		tfFrom.setBounds(482, 32, 300, 20);
		tfFrom.setColumns(10);

		tfSubject = new JTextField();
		tfSubject.setBackground(Color.WHITE);
		tfSubject.setEditable(false);
		tfSubject.setBounds(102, 57, 680, 20);
		tfSubject.setColumns(10);

		tfApprovedPosTitle = new JTextField();
		tfApprovedPosTitle.setBackground(Color.WHITE);
		tfApprovedPosTitle.setEditable(false);
		tfApprovedPosTitle.setBounds(497, 446, 285, 20);
		tfApprovedPosTitle.setColumns(10);

		tfApprovedName = new JTextField();
		tfApprovedName.setBackground(Color.WHITE);
		tfApprovedName.setEditable(false);
		tfApprovedName.setBounds(102, 446, 293, 20);
		tfApprovedName.setColumns(10);

		JLabel lblDateTime = new JLabel("Date / Time");
		lblDateTime.setBounds(405, 10, 68, 14);
		add(lblDateTime);

		this.add(tfIncidentName);
		this.add(tfTo);
		this.add(tfFrom);
		this.add(tfSubject);
		this.add(tfApprovedPosTitle);
		this.add(tfApprovedName);

		tfDateTime = new JTextField();
		tfDateTime.setBackground(Color.WHITE);
		tfDateTime.setEditable(false);
		tfDateTime.setBounds(482, 7, 300, 20);
		add(tfDateTime);
		tfDateTime.setColumns(10);
	}

	public void parse(File file) {

		ICS_213Report report = new ICS_213Report();
		ICS_213Parser parser = new ICS_213Parser();

		report = parser.parse(file);

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
