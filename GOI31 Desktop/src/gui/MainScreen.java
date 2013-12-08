package gui;

import java.awt.BorderLayout;

import javax.swing.*;

public class MainScreen extends Screen {

	public JFrame frame;
	public JTable table;
	public JToolBar toolBar;
	public JLabel label_1;
	public JLabel label_2;
	public JPanel panel_1;
	public JPanel panel_2;
	public JButton button_1;
	

	public MainScreen() {

	}

	public void Init() {
		frame = new JFrame();
		frame.setTitle(core.Core.NAME);
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		toolBar = new JToolBar();
		frame.getContentPane().add(toolBar, BorderLayout.SOUTH);

		label_1 = new JLabel("Label 1");
		toolBar.add(label_1);
		
		panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.EAST);
		
		button_1 = new JButton("Button 1");
		panel_2.add(button_1);
		
		//
		
		table = new JTable();
		panel_1.add(table, BorderLayout.CENTER);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		panel_1.add(tabbedPane, BorderLayout.SOUTH);
		
		label_2 = new JLabel("Label 2");
		tabbedPane.addTab("New tab", null, label_2, null);
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("New tab", null, tabbedPane_1, null);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Datei");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmSpeichern = new JMenuItem("Speichern");
		mnNewMenu.add(mntmSpeichern);
		
		JMenuItem mntmLaden = new JMenuItem("Laden");
		mnNewMenu.add(mntmLaden);
		
		frame.setVisible(true);
	}

}
