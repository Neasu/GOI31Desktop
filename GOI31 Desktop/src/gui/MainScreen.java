package gui;

import java.awt.BorderLayout;

import javax.swing.JButton;
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
import data.FreeLesson;
import data.Lesson;
import data.NormalLesson;
import data.ProxyLesson;
import file.LogFile;

public class MainScreen extends Screen implements core.Updateable{

	public JTable table;
	public JToolBar toolBar;
	public JLabel toolBarLabel;
	public JLabel label_2;
	public JPanel panel_1;
	public JPanel panel_2;
	public JButton button_1;

	public MainScreen(GUIManager guim) {
		super (guim);
		
		// Screenwerte setzen
		width = 1240;
		height = 720; // Alt 315
		screenName = "MainScreen";
		screenID = 2;
		
		Init ();
	}

	private void Init() {
		
		width = 1240;
		height = 720; // Alt 315
		screenName = "MainScreen";
		screenID = 2;
		
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

		button_1 = new JButton("Button 1");
		panel_2.add(button_1);

		//

		// TableModel dataModel = new AbstractTableModel() {
		// public int getColumnCount() { return 7; }
		// public int getRowCount() { return 11;}
		// public Object getValueAt(int row, int col) { return new
		// Integer(row*col); }
		// };

		table = new JTable(new ScheduleTableModel());
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

		centerWindowOnScreen();

		frame.setVisible(true);
		
		Program.addUpdateable(this);
		
		LogFile.getRef().textout("MainScreen successfully created.", LogLevel.LOG);
	}
	
	private void buildToolbarLabel () {
		
		Lesson currLesson = GUIManager.getProg().getSche().getCurrentLesson();
		
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
		
		toolBarLabel.setText(temp);
	}
	
	public void update () {
		buildToolbarLabel();
	}

}
