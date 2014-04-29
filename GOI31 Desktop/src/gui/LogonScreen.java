package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import student.Profile;
import core.LogLevel;
import file.ImageFile;
import file.LogFile;

/**
 * 
 * @author Kevin
 *
 */

public class LogonScreen extends Screen {

	// Vars
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JButton btnLogIn;
	private JButton btnStartOffline;
	private JLabel lblErrorMsg;
	public static Profile user;

	// Constructors
	public LogonScreen(GUIManager guim) {
		super (guim);

		// Screen-Werte definieren
		width = 450;
		height = 300;
		screenName = "Logonscreen";
		screenID = 3;
		
		Init ();

	}

	// Methods
	private void Init() {

		frame = new JFrame(core.Core.NAME);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, width, height);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		frame.setContentPane(contentPane);
		frame.setIconImage(new ImageFile("res/gy31Icon_256x256.png").getImage());

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new GridLayout(1, 0, 0, 0));

		btnLogIn = new JButton("Login");
		
		// Reagiert auf die ENTER-Taste
		contentPane.getRootPane().setDefaultButton(btnLogIn);
		
		panel.add(btnLogIn);

		btnStartOffline = new JButton("Start Offline");
		btnStartOffline.setEnabled(false);
		panel.add(btnStartOffline);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		// New
		lblErrorMsg = new JLabel ("");
		contentPane.add (lblErrorMsg, BorderLayout.NORTH);
		
		textField = new JTextField();
		
		textField.setText (GUIManager.getProg().getConfigFile().getString("Username"));
		
		textField.setBounds(189, 78, 87, 20);
		panel_1.add(textField);
		textField.setColumns(15);

		JLabel lblName = new JLabel("E-Mail:");
		lblName.setBounds(111, 81, 46, 14);
		panel_1.add(lblName);

		passwordField = new JPasswordField();
		passwordField.setColumns(15);
		passwordField.setBounds(189, 109, 87, 20);
		panel_1.add(passwordField);

		JLabel lblNewLabel = new JLabel("Passwort:");
		lblNewLabel.setBounds(111, 112, 68, 14);
		panel_1.add(lblNewLabel);

		btnStartOffline.addActionListener(new startOfflineButtonListener());
		btnLogIn.addActionListener(new logInButtonListener());

		centerWindowOnScreen();
		frame.setResizable(false);
		setVisible(true);
		
		LogFile.getRef().textout("LogonScreen has been successfully created.", LogLevel.LOG);
	}

	public class startOfflineButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			guim.getMainScreen();
			GUIManager.getProg().setOnline(false);
			setVisible(false);
			GUIManager.getProg().getConfigFile().addPair("Online", false);
			GUIManager.getProg().getConfigFile().addPair("Username", textField.getText());
			LogFile.getRef().textout("Application is running in Offline-Mode.", LogLevel.LOG);
		}
	}
	
	public class logInButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			// TODO Login attempt checken!
			// DONE Valentin war hier!
			
			user = new Profile (textField.getText(), String.copyValueOf(passwordField.getPassword()));
			
			GUIManager.getProg().getConfigFile().addPair("Username", textField.getText());
			
			try {
				user.login ();
				GUIManager.getProg().getConfigFile().addPair("Online", true);
			} catch (Exception ex) {
				lblErrorMsg.setText (ex.getMessage());
				GUIManager.getProg().getConfigFile().addPair("Online", false);
				return;
			}
			
			GUIManager.getProg().setOnline(true);
			GUIManager.getProg().setUserName(textField.getText());
			
			// User Profile einrichten
			try {
				user.populateProfile();
				user.populateTimetable();
				GUIManager.getProg().setUser(user);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// Settings müssen vor Erscheinen der GUI gesichert werden
			GUIManager.getProg().getConfigFile().addPair("Online", true);
			GUIManager.getProg().getConfigFile().addPair("Username", textField.getText());
			GUIManager.getProg().getConfigFile().addPair("Firstname", user.getFirstname());
			GUIManager.getProg().getConfigFile().addPair("Lastname", user.getLastname());
			
			guim.getMainScreen();
			setVisible(false);
			
			LogFile.getRef().textout("Application is running in ONLINE-Mode.", LogLevel.LOG);
			
		}
	}

}
