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

public class LogonScreen extends Screen {

	// Vars
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JButton btnLogIn;
	private JButton btnStartOffline;

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

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new GridLayout(1, 0, 0, 0));

		btnLogIn = new JButton("Log in");
		panel.add(btnLogIn);

		btnStartOffline = new JButton("Start Offline");
		panel.add(btnStartOffline);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);

		textField = new JTextField();
		textField.setBounds(189, 78, 87, 20);
		panel_1.add(textField);
		textField.setColumns(15);

		JLabel lblName = new JLabel("Name:");
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

		centerWindowOnScreen();
		frame.setResizable(false);
		setVisible(true);
	}

	public class startOfflineButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			guim.getMainScreen();
			setVisible(false);
		}
	}

}