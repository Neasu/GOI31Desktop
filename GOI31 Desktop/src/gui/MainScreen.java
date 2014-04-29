package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.ScrollPaneConstants;

import org.json.JSONArray;
import org.json.JSONObject;

import server.ApiServerException;
import core.LogLevel;
import core.Program;
import data.Cooldown;
import data.FreeLesson;
import data.Lesson;
import data.News;
import data.NormalLesson;
import data.ProxyLesson;
import file.ImageFile;
import file.LogFile;

/**
 * 
 * @author Kevin
 * 
 */

public class MainScreen extends Screen implements core.Updateable {

	private SheduleTable table;
	private JToolBar toolBar;
	private JLabel toolBarLabel;
	private JLabel label_2;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel tab1panel;
	private JButton button_1;
	private JCheckBox checkBox_1;
	private JCheckBox checkBox_2;
	private JCheckBox checkBox_3;
	private JTextArea newsArea;

	private int rowHeight;

	private JScrollPane scroller;

	private Cooldown cooldown;
	private News news;

	public MainScreen(GUIManager guim) {
		super(guim);

		// Screenwerte setzen
		width = 1240;
		height = 720; // Alt 315
		screenName = "MainScreen";
		screenID = 2;

		cooldown = new Cooldown(1, false);

		Init();
	}

	private void Init() {

		frame = new JFrame();
		frame.setTitle(core.Core.NAME);
		frame.setBounds(100, 100, width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		frame.setIconImage(new ImageFile("res/gy31Icon_256x256.png").getImage());

		toolBar = new JToolBar();
		frame.getContentPane().add(toolBar, BorderLayout.SOUTH);

		toolBarLabel = new JLabel("");
		toolBar.add(toolBarLabel);
		toolBar.setEnabled(false);

		panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));

		panel_2 = new JPanel();
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));

		panel_3 = new JPanel();
		panel_3.setLayout(new BorderLayout());

		panel_3.add(panel_2, BorderLayout.EAST);

		button_1 = new JButton("Update");
		panel_2.add(button_1);

		// Update Button

		button_1.addActionListener(new UpdateButtonListener());

		//

		// TableModel dataModel = new AbstractTableModel() {
		// public int getColumnCount() { return 7; }
		// public int getRowCount() { return 11;}
		// public Object getValueAt(int row, int col) { return new
		// Integer(row*col); }
		// };

		// Table

		table = new SheduleTable(new ScheduleTableModel(this));
		table.getTableHeader().setReorderingAllowed(false);

		// JScrollPane scroll = new JScrollPane(table);
		// scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		panel_3.add(table, BorderLayout.CENTER);

		panel_1.add(panel_3, BorderLayout.NORTH);

		table.setEnabled(false);

		rowHeight = table.getRowHeight();

		// Die erste Spalte soll schmaler sein
		table.getColumnModel().getColumn(0).setMaxWidth(100);

		// Checkboxes

		checkBox_1 = new JCheckBox("Lehrer Anzeigen");
		panel_2.add(checkBox_1);
		checkBox_1.addActionListener(new CheckboxListener());

		checkBox_2 = new JCheckBox("Raum Anzeigen");
		panel_2.add(checkBox_2);
		checkBox_2.addActionListener(new CheckboxListener());

		checkBox_3 = new JCheckBox("Abkürzungen");
		panel_2.add(checkBox_3);
		checkBox_3.addActionListener(new CheckboxListener());

		// Checkboxen mit Werten aus der Configfile füllen
		checkBox_1.setSelected(GUIManager.getProg().getConfigFile().getBoolean("lehrer_anzeigen"));
		checkBox_2.setSelected(GUIManager.getProg().getConfigFile().getBoolean("raum_anzeigen"));
		checkBox_3.setSelected(GUIManager.getProg().getConfigFile().getBoolean("abkuerzungen"));

		new CheckboxListener().actionPerformed(null); // nur einmal die
														// ActionPerformed
														// aufrufen

		// TabbedPane

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		panel_1.add(tabbedPane, BorderLayout.CENTER);

		tab1panel = new JPanel(new BorderLayout());

		// NewsArea

		newsArea = new JTextArea();
		scroller = new JScrollPane(newsArea);

		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		tab1panel.add(scroller, BorderLayout.CENTER);

		newsArea.setEditable(false);
		newsArea.setLineWrap(true);
		newsArea.setFont(new Font("Calibri", Font.PLAIN, 14));

		// NEEEEEEEEEWWWWWWWSSSSSSS
		news = new News(newsArea);

		// newsArea.setText("Hello World! \n");

		label_2 = new JLabel("Label 2");
		tabbedPane.addTab("News", null, tab1panel, null);

//		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
//		tabbedPane.addTab("New tab", null, tabbedPane_1, null);

		// MenueBar

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		menuBar.add(label_2);

		if (GUIManager.getProg().isOnline()) {
			//label_2.setText("Eingeloggt als: " + GUIManager.getProg().getUserName());
			
			// Richtiger Name ist schöner
			label_2.setText("Eingeloggt als: " + GUIManager.getProg().getConfigFile().getString("Firstname") + " " + GUIManager.getProg().getConfigFile().getString("Lastname"));
			
			try {
				JSONArray newsArr = LogonScreen.user.getNews();

				for (int i = 0; i < newsArr.length(); i++) {
					JSONObject newsObj = newsArr.getJSONObject(i);

					news.addNews(newsObj);
				}
			} catch (ApiServerException e) {
				news.addNews("News konnte nicht geladen werden!", "Der Gerät", "Jetzt", "Die News konnte nicht geladen werden. \n" + e.getMessage());
				LogFile.getRef().textout("News couldn't been loaded due to " + e.getMessage(), LogLevel.WARNING);
			}
			
		} else {
			label_2.setText("Offline-Modus");
			news.addNews("Offline-Modus", "Das System", "Jetzt", "Im Offline-Modus sind keine News verfügbar!");
		}

		// JMenu mnNewMenu = new JMenu("Datei");
		// menuBar.add(mnNewMenu);
		//
		// JMenuItem mntmSpeichern = new JMenuItem("Speichern");
		// mnNewMenu.add(mntmSpeichern);
		//
		// JMenuItem mntmLaden = new JMenuItem("Laden");
		// mnNewMenu.add(mntmLaden);

		// centerWindowOnScreen();
		resizeToScreen(70, 70);

		update();

		frame.setVisible(true);

		Program.addUpdateable(this);

		LogFile.getRef().textout("MainScreen successfully created.", LogLevel.LOG);
	}

	private void buildToolbarLabel() {

		Lesson currLesson = GUIManager.getProg().getSche().getCurrentLesson();
		Lesson nextLesson = GUIManager.getProg().getSche().getNextLesson();

		String timeTilLessonEnd = GUIManager.getProg().getSche().getTimeTilLessonEnd();

		String temp = "";

		temp += GUIManager.getProg().getSche().getCurrTime();

		// Muss die aktuelle Stunde angezeigt werden?
		if (GUIManager.getProg().getSche().IsLessonIgnoreable(currLesson)) {
			toolBarLabel.setText(temp);
			return;
		}

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

	public void manageUpdateButton() {
		if (cooldown.isActive()) {
			button_1.setEnabled(false);
			button_1.setText(cooldown.getTimeLeft());
		} else {
			button_1.setText("Update");
			button_1.setEnabled(true);
		}
	}

	public void update() {
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

	public void setNewsText(String text) {
		newsArea.setText(text);
	}

	public News getNewsObject() {
		return news;
	}

	private void writeToConfig() {
		if (getCheckBox_1()) {
			GUIManager.getProg().getConfigFile().addPair("lehrer_anzeigen", true);
		} else {
			GUIManager.getProg().getConfigFile().addPair("lehrer_anzeigen", false);
		}

		if (getCheckBox_2()) {
			GUIManager.getProg().getConfigFile().addPair("raum_anzeigen", true);
		} else {
			GUIManager.getProg().getConfigFile().addPair("raum_anzeigen", false);
		}

		if (getCheckBox_3()) {
			GUIManager.getProg().getConfigFile().addPair("abkuerzungen", true);
		} else {
			GUIManager.getProg().getConfigFile().addPair("abkuerzungen", false);
		}
	}

	public void setRowHeight(int height) {
		for (int i = 1; i < 11; i++) {
			table.setRowHeight(i, height);
		}
	}

	public class UpdateButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			if (!cooldown.isActive()) {
				GUIManager.getProg().getSche().updateData();
//				GUIManager.getProg().getChat().start();
				try {
					GUIManager.getProg().getUser().populateProfile();
					GUIManager.getProg().getUser().populateTimetable();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				table.revalidate();
				table.repaint();

				cooldown.restart();
			}
		}
	}

	public class CheckboxListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {

			if (getCheckBox_1() && getCheckBox_2()) {
				setRowHeight(rowHeight * 3);
			} else if (getCheckBox_1() || getCheckBox_2()) {
				setRowHeight(rowHeight * 2);
			} else {
				setRowHeight(rowHeight);
			}

			table.revalidate();
			table.repaint();
			writeToConfig();
		}
	}

}
