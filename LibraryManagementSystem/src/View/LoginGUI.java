package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import Helper.*;
import Model.Librarian;

public class LoginGUI extends JFrame {

	private JPanel w_pane;
	private JTextField fld_userid;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField fld_librarianID;
	private JPasswordField fld_librarianPass;
	private JPasswordField fld_userpass;
	private DBConnection conn = new DBConnection();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginGUI() {
		setResizable(false);
		setTitle("Library Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 360);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setToolTipText("");
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JTabbedPane w_tabpane = new JTabbedPane(JTabbedPane.TOP);
		w_tabpane.setBounds(10, 94, 474, 238);
		w_pane.add(w_tabpane);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		w_tabpane.addTab("User Login Page", null, panel_2, null);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Identity Number:");
		lblNewLabel.setFont(new Font("Will&Grace", Font.PLAIN, 17));
		lblNewLabel.setBounds(43, 11, 133, 54);
		panel_2.add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Will&Grace", Font.PLAIN, 17));
		lblPassword.setBounds(43, 76, 96, 54);
		panel_2.add(lblPassword);
		
		fld_userid = new JTextField();
		fld_userid.setBounds(209, 31, 205, 20);
		panel_2.add(fld_userid);
		fld_userid.setColumns(10);
		
		JButton btn_usersignin = new JButton("Sign in");
		btn_usersignin.setBounds(209, 163, 89, 23);
		panel_2.add(btn_usersignin);
		
		JButton btn_usersignup = new JButton("Sign up");
		btn_usersignup.setBounds(325, 163, 89, 23);
		panel_2.add(btn_usersignup);
		
		fld_userpass = new JPasswordField();
		fld_userpass.setBounds(209, 96, 205, 20);
		panel_2.add(fld_userpass);
		
		JPanel w_librarianlogin = new JPanel();
		w_librarianlogin.setBackground(Color.WHITE);
		w_tabpane.addTab("Librarian Login Page", null, w_librarianlogin, null);
		w_librarianlogin.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(234, 5, 1, 1);
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		w_librarianlogin.add(panel);
		
		JLabel label_1 = new JLabel("Identity Number:");
		label_1.setFont(new Font("Will&Grace", Font.PLAIN, 17));
		label_1.setBounds(43, 11, 133, 54);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("Password:");
		label_2.setFont(new Font("Will&Grace", Font.PLAIN, 17));
		label_2.setBounds(43, 76, 96, 54);
		panel.add(label_2);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(209, 31, 205, 20);
		panel.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(209, 96, 205, 20);
		panel.add(textField_1);
		
		JButton button = new JButton("Sign in");
		button.setBounds(209, 163, 89, 23);
		panel.add(button);
		
		JButton button_1 = new JButton("Sign up");
		button_1.setBounds(325, 163, 89, 23);
		panel.add(button_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(0, 0, 469, 210);
		w_librarianlogin.add(panel_1);
		
		JLabel label_3 = new JLabel("Identity Number:");
		label_3.setFont(new Font("Will&Grace", Font.PLAIN, 17));
		label_3.setBounds(43, 11, 133, 54);
		panel_1.add(label_3);
		
		JLabel label_4 = new JLabel("Password:");
		label_4.setFont(new Font("Will&Grace", Font.PLAIN, 17));
		label_4.setBounds(43, 76, 96, 54);
		panel_1.add(label_4);
		
		fld_librarianID = new JTextField();
		fld_librarianID.setColumns(10);
		fld_librarianID.setBounds(209, 31, 205, 20);
		panel_1.add(fld_librarianID);
		
		JButton btn_librariansignin = new JButton("Sign in");
		btn_librariansignin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_librarianID.getText().length() == 0 || fld_librarianPass.getText().length() == 0) {
					Helper.showMsg("fill");
				}else {
					try {
					Connection con = conn.connDb();
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery("SELECT * FROM user");
					while(rs.next()) {
						if(fld_librarianID.getText().equals(rs.getString("identitynumber")) && fld_librarianPass.getText().equals(rs.getString("password"))) {
							Librarian lib = new Librarian();
							lib.setPassword("password");
							lib.setIdentitynumber(rs.getString("identitynumber"));
							lib.setName(rs.getString("name"));
							lib.setUser(rs.getString("user"));
							LibrarianGUI libGUI = new LibrarianGUI(lib);
							libGUI.setVisible(true);
							dispose();
						}
						
					}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btn_librariansignin.setBounds(209, 163, 205, 23);
		panel_1.add(btn_librariansignin);
		
		fld_librarianPass = new JPasswordField();
		fld_librarianPass.setBounds(209, 96, 205, 20);
		panel_1.add(fld_librarianPass);
		
		JLabel label = new JLabel("WELCOME TO LIBRARY MANAGEMENT SYSTEM");
		label.setFont(new Font("Will&Grace", Font.PLAIN, 17));
		label.setBounds(42, 12, 400, 71);
		w_pane.add(label);
	}
}
