package AppPackage;

import java.awt.CardLayout;
import java.awt.Choice;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

// Project started by Levi Cherry-Allen for the Amateur Radio community.

public class JFrameMain extends JFrame implements Printable, ActionListener {

	private static final long serialVersionUID = 1L;

	private static JFrame frame;
	// Panels
	private JPanel mainPane;
	private JPanel bottomPanel;
	private JPanel topPanel;

	// Buttons
	private JButton btnParse;
	private JButton btnLoadXml;
	// private JButton btnPrint;

	// bottomPanel selection information
	private Choice form;
	private int currentCard = 0;

	// File Information
	private JFileChooser fileChooser;
	private File file;
	private JTextField tfFilePath;

	// Launch application
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				frame = new JFrameMain();
				frame.setVisible(true);
			}
		});
	}

	// Create JFrame
	public JFrameMain() {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(
		    JFrameMain.class.getResource("/icon.png")));

		setTitle("WinLink Form Viewer");

		// Setup JPanel
		mainPane = new JPanel();
		mainPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		mainPane.setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		setResizable(false);
		setContentPane(mainPane);

		// Create and place the top panel
		topPanel = new JPanel();
		topPanel.setBounds(0, 0, 794, 86);
		topPanel.setLayout(null);
		mainPane.add(topPanel);

		// Create and place buttons
		btnLoadXml = new JButton("Browse");
		btnLoadXml.setBounds(10, 15, 102, 23);
		btnParse = new JButton("Parse");
		btnParse.setBounds(10, 49, 102, 23);
		topPanel.add(btnLoadXml);
		topPanel.add(btnParse);

		// Create and place the File Path text box
		tfFilePath = new JTextField();
		tfFilePath.setBounds(122, 16, 662, 20);
		topPanel.add(tfFilePath);
		tfFilePath.setColumns(10);

		// Create and place the drop down
		form = new Choice();
		form.add(Constants.ICS_213);
		form.add(Constants.ICS_205A);
		form.setBounds(122, 49, 273, 20);
		topPanel.add(form);

		// Create and place Print button
		// btnPrint = new JButton("Print");
		// btnPrint.addActionListener(this);
		// btnPrint.setBounds(695, 49, 89, 23);
		// topPanel.add(btnPrint);

		// Create the bottom half of the application
		bottomPanel = new JPanel();
		bottomPanel.setBounds(0, 86, 794, 485);
		bottomPanel.setLayout(new CardLayout(0, 0));

		// Add "Cards" to the panel representing the possible drop down options.
		ICS_213Panel ics_213Panel = new ICS_213Panel();
		bottomPanel.add(ics_213Panel, Constants.ICS_213);

		ICS_205APanel ics_205APanel = new ICS_205APanel();
		bottomPanel.add(ics_205APanel, Constants.ICS_205A);

		// Add the panel and cards to the main JFrame.
		mainPane.add(bottomPanel);

		// Create the filter to show only XML files
		FileNameExtensionFilter xmlFilter = new FileNameExtensionFilter(
		    "XML Files (*.xml)", "xml");

		// Create the fileChooser and apply properties
		fileChooser = new JFileChooser(System.getProperty("user.home"));
		fileChooser.setDialogTitle("Open schedule file");
		fileChooser.setFileFilter(xmlFilter);

		// Action Listeners
		form.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent arg0) {
				if (arg0.getItem().toString().equals(Constants.ICS_213)) {

					CardLayout cards = (CardLayout) (bottomPanel.getLayout());
					cards.show(bottomPanel, Constants.ICS_213);
					currentCard = 0;
				}
				else {
					if (arg0.getItem().toString().equals(Constants.ICS_205A)) {

						CardLayout cards = (CardLayout) (bottomPanel.getLayout());
						cards.show(bottomPanel, Constants.ICS_205A);
						currentCard = 1;
					}
				}
			}
		});

		btnLoadXml.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// Select File
				fileChooser.showOpenDialog(getParent());

				// Set File Path Text Box if retrieved file is not null
				if (fileChooser.getSelectedFile() != null) {
					tfFilePath.setText(fileChooser.getSelectedFile().getAbsolutePath());
				}
			}
		});

		btnParse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Set the file to the file path indicated
				file = new File(tfFilePath.getText());

				// Call the parse function of the appropriate panel
				((FormPanel) bottomPanel.getComponent(currentCard)).parse(file);
			}
		});
	}

	// Print button function
	public void actionPerformed(ActionEvent e) {

		PrinterJob job = PrinterJob.getPrinterJob();
		job.setPrintable(this);
		boolean ok = job.printDialog();
		if (ok) {
			try {
				job.print();
			}
			catch (PrinterException ex) {
			}
		}

	}

	// https://docs.oracle.com/javase/tutorial/2d/printing/printable.html
	// https://docs.oracle.com/javase/tutorial/2d/printing/gui.html
	public int print(Graphics g, PageFormat pf, int page) throws PrinterException {

		if (page > 0) { // We have only one page, and 'page' is zero-based
			return NO_SUCH_PAGE;
		}

		// User (0,0) is typically outside the imageable area, so we must translate
		// by the X and Y values in the PageFormat to avoid clipping
		Graphics2D g2d = (Graphics2D) g;
		g2d.translate(pf.getImageableX(), pf.getImageableY());

		// Now print the window and its visible contents
		frame.printAll(g);

		// tell the caller that this page is part of the printed document
		return PAGE_EXISTS;
	}

}