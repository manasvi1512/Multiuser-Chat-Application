package com.dit.chatapp.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import com.dit.chatapp.dao.userDAO;
import com.dit.chatapp.dto.userDTO;
import com.dit.chatapp.utils.UserInfo;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class UserScreen extends JFrame {
	private JTextField useridtxt;
	private JPasswordField passwordField;

	public static void main(String[] args) {
					UserScreen window = new UserScreen();
	}
	userDAO usr = new userDAO(); //common for both the function login and register
	
	private void doLogin() {
		String userid = useridtxt.getText();
		char[] password = passwordField.getPassword(); 
		//userDAO usr = new userDAO();
		userDTO usr1 = new userDTO(userid, password);
		try {
			String message = "";
			if(usr.isLogin(usr1)) {
				message = "Welcome "+userid;
				UserInfo.USER_NAME = userid;
				JOptionPane.showMessageDialog(this, message);
				setVisible(false);
				dispose();
				DashBoard dashBoard = new DashBoard(message);
				dashBoard.setVisible(true);
			}else {
				message = "Invalid UserID or Password";
				JOptionPane.showMessageDialog(this, message);
			}
			//JOptionPane.showMessageDialog(this, message);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void register() {
		String userid = useridtxt.getText();
		char[] password = passwordField.getPassword(); 
		userDTO usr1 = new userDTO(userid, password);
		try {
			int result = usr.add(usr1);
			if(result>0) {
				JOptionPane.showMessageDialog(this, "Registered Successfully....");
				//System.out.println("Record added to database.....");
			}else {
				JOptionPane.showMessageDialog(this, "Register Failed....");
				//System.out.println("Record not added to database.....");
			}
		}
		catch(ClassNotFoundException | SQLException ex) {
			System.out.println("DB Issue....");
			ex.printStackTrace(); //where is the exception
		}
		catch(Exception ex) {
			System.out.println("Some generic exception raised....");
			ex.printStackTrace(); //where is the exception
		}
		System.out.println("userID:"+userid+" password:"+password);
	}

	/**
	 * Create the application.
	 */
	public UserScreen() {
		setTitle("LOGIN");
		setResizable(false);
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("LOGIN");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 40));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(259, 33, 146, 48);
		getContentPane().add(lblNewLabel);
		
		useridtxt = new JTextField();
		useridtxt.setBounds(275, 128, 304, 42);
		getContentPane().add(useridtxt);
		useridtxt.setColumns(10);
		
		JLabel useridlbl = new JLabel("UserID");
		useridlbl.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		useridlbl.setBounds(82, 134, 73, 36);
		getContentPane().add(useridlbl);
		
		JLabel pwdlbl = new JLabel("Password");
		pwdlbl.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		pwdlbl.setBounds(82, 215, 96, 36);
		getContentPane().add(pwdlbl);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(275, 209, 304, 42);
		getContentPane().add(passwordField);
		
		JButton loginbt = new JButton("Login");
		loginbt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doLogin();
			}
		});
		loginbt.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		loginbt.setBounds(184, 311, 117, 36);
		getContentPane().add(loginbt);
		
		JButton registerbt = new JButton("Register");
		registerbt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				register();
			}
		});
		registerbt.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		registerbt.setBounds(331, 311, 117, 36);
		getContentPane().add(registerbt);
		setSize(658, 419);
		setLocationRelativeTo(null); //window will appear always at the centre of the screen
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}
}
