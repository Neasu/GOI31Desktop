package gui;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JSplitPane;
import javax.swing.JToggleButton;
import javax.swing.JTable;
import java.awt.BorderLayout;
import javax.swing.JToolBar;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JMenuBar;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JTabbedPane;
import javax.swing.JMenuItem;
import javax.swing.JMenu;


public class BaukastenMainGUI {

	private JFrame frmTest;
	private JTable table;

	/**
	 * Create the application.
	 */
	public void Init () {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BaukastenMainGUI window = new BaukastenMainGUI();
					window.frmTest.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTest = new JFrame();
		frmTest.setTitle("Test");
		frmTest.setBounds(100, 100, 800, 600);
		frmTest.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTest.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JToolBar toolBar = new JToolBar();
		frmTest.getContentPane().add(toolBar, BorderLayout.SOUTH);

		JLabel lblNewLabel_1 = new JLabel("New label");
		toolBar.add(lblNewLabel_1);
		
		JPanel panel = new JPanel();
		frmTest.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.EAST);
		
		JButton btnTest = new JButton("Test");
		panel_1.add(btnTest);
		
		table = new JTable();
		panel.add(table, BorderLayout.CENTER);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		panel.add(tabbedPane, BorderLayout.SOUTH);
		
		JLabel lblNewLabel = new JLabel("New label");
		tabbedPane.addTab("New tab", null, lblNewLabel, null);
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("New tab", null, tabbedPane_1, null);
		
		JMenuBar menuBar = new JMenuBar();
		frmTest.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Datei");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmSpeichern = new JMenuItem("Speichern");
		mnNewMenu.add(mntmSpeichern);
		
		JMenuItem mntmLaden = new JMenuItem("Laden");
		mnNewMenu.add(mntmLaden);
	}

}
