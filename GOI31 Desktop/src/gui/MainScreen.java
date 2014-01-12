package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JToolBar;

import core.LogLevel;
import core.Program;
import data.Cooldown;
import data.FreeLesson;
import data.Lesson;
import data.NormalLesson;
import data.ProxyLesson;
import file.LogFile;

public class MainScreen extends Screen implements core.Updateable{

	private JTable table;
	private JToolBar toolBar;
	private JLabel toolBarLabel;
	private JLabel label_2;
	private JPanel panel_1;
	private JPanel panel_2;
	private JButton button_1;
	private JCheckBox checkBox_1;
	private JCheckBox checkBox_2;
	private JCheckBox checkBox_3;

	
	private Cooldown cooldown;

	public MainScreen(GUIManager guim) {
		super (guim);
		
		// Screenwerte setzen
		width = 1240;
		height = 720; // Alt 315
		screenName = "MainScreen";
		screenID = 2;
		
		cooldown = new Cooldown(1,  false);
		
		Init ();
	}

	private void Init() {
		
		frame = new JFrame();
		frame.setTitle(core.Core.NAME);
		frame.setBounds(100, 100, width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		toolBar = new JToolBar();
		frame.getContentPane().add(toolBar, BorderLayout.SOUTH);

		toolBarLabel = new JLabel("");
		toolBar.add(toolBarLabel);
		toolBar.setEnabled(false);

		panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));

		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.EAST);
		
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));

		button_1 = new JButton("Update");
		panel_2.add(button_1);
		
		checkBox_1 = new JCheckBox("Lehrer Anzeigen");
		panel_2.add(checkBox_1);
		checkBox_1.addActionListener(new CheckboxListener());
		
		checkBox_2 = new JCheckBox("Raum Anzeigen");
		panel_2.add(checkBox_2);
		checkBox_2.addActionListener(new CheckboxListener());
		
		checkBox_3 = new JCheckBox("Abkürzungen");
		panel_2.add(checkBox_3);
		checkBox_3.addActionListener(new CheckboxListener());
		
		button_1.addActionListener(new UpdateButtonListener());

		//

		// TableModel dataModel = new AbstractTableModel() {
		// public int getColumnCount() { return 7; }
		// public int getRowCount() { return 11;}
		// public Object getValueAt(int row, int col) { return new
		// Integer(row*col); }
		// };

		table = new JTable(new ScheduleTableModel(this));
		panel_1.add(table, BorderLayout.CENTER);
		
		table.setEnabled(false);
		
		// Die erste Spalte soll schmaler sein
		table.getColumnModel().getColumn(0).setMaxWidth(100);

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

		centerWindowOnScreen();

		frame.setVisible(true);
		
		Program.addUpdateable(this);
		
		LogFile.getRef().textout("MainScreen successfully created.", LogLevel.LOG);
	}
	
	private void buildToolbarLabel () {
		
		Lesson currLesson = GUIManager.getProg().getSche().getCurrentLesson();
		Lesson nextLesson = GUIManager.getProg().getSche().getNextLesson();
		
		String timeTilLessonEnd = GUIManager.getProg().getSche().getTimeTilLessonEnd();
		
		String temp = "";
		
		temp += GUIManager.getProg().getSche().getCurrTime();
		
		temp += " | ";
		
		temp += "Aktuelle Stunde: ";
		
		if (currLesson.getClass().equals(FreeLesson.class)) {
			
			FreeLesson fl = (FreeLesson) currLesson;
			
			if (fl.isEntfall()) {
				temp += "Entfall";
			} else {
				temp += "Frei";
			}
			
		} else if (currLesson.getClass().equals(NormalLesson.class)) {
			
			NormalLesson nl = (NormalLesson) currLesson;
			
			temp += nl.getName();
			
		} else if (currLesson.getClass().equals(ProxyLesson.class)) {
			
			ProxyLesson pl = (ProxyLesson) currLesson;
			
			temp += pl.getName();
			
			temp += " Vertretung!";
		}
		
		if (!timeTilLessonEnd.equals("")) {
			temp += " | ";
			temp += "Zeit bis Stundenende: ";
			temp += timeTilLessonEnd;
		}
		
		if (nextLesson.getClass().equals(NormalLesson.class)) {
			
			NormalLesson nl = (NormalLesson) nextLesson;
			
			temp += " | ";
			temp += "Nächste Stunde: ";
			
			temp += nl.getName();
		} else if (currLesson.getClass().equals(ProxyLesson.class)) {
			
			ProxyLesson pl = (ProxyLesson) currLesson;
			
			temp += " | ";
			temp += "Nächste Stunde: ";
			
			temp += pl.getName();
			
			temp += " Vertretung!";
		}
		
		toolBarLabel.setText(temp);
	}
	
	public void manageUpdateButton () {
		if (cooldown.isActive()) {
			button_1.setEnabled(false);
			button_1.setText(cooldown.getTimeLeft());
		} else {
			button_1.setText("Update");
			button_1.setEnabled(true);
		}
	}
	
	public void update () {
		buildToolbarLabel();
		manageUpdateButton();
	}
	
	public boolean getCheckBox_1() {
		return checkBox_1.isSelected();
	}

	public boolean getCheckBox_2() {
		return checkBox_2.isSelected();
	}
	
	public boolean getCheckBox_3() {
		return checkBox_3.isSelected();
	}

	public class UpdateButtonListener implements ActionListener {
		public void actionPerformed (ActionEvent ev) {			
			if (!cooldown.isActive()) {
				GUIManager.getProg().getSche().updateData();
				cooldown.restart();
			}
		}
	}
	
	public class CheckboxListener implements ActionListener {
		public void actionPerformed (ActionEvent ev) {
			table.revalidate();
			table.repaint();
		}
	}

}
