package com.dit.chatapp.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.dit.chatapp.network.Client;
import com.dit.chatapp.utils.UserInfo;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;

public class ClientChatScreen extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextArea textArea;
	private Client client;

	/**
	 * Launch the application.
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException, IOException {
		ClientChatScreen frame = new ClientChatScreen();
		frame.setVisible(true);
	}
	
	private void sendIt() {
		String message = textField.getText();
		try {
			client.sendMessage(UserInfo.USER_NAME+"-"+message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public ClientChatScreen() throws UnknownHostException, IOException {
		textArea = new JTextArea();
		client = new Client(textArea);
		setTitle("Chit - Chat");
		setBackground(Color.WHITE);
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 588, 363);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 153, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(19, 17, 549, 245);
		contentPane.add(scrollPane);
		
		textArea.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		textArea.setBounds(34, 26, 520, 216);
		scrollPane.setViewportView(textArea);
		
		textField = new JTextField();
		textField.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		textField.setBounds(15, 274, 435, 48);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton sendit = new JButton("Send Message");
		sendit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendIt();
			}
		});
		sendit.setForeground(new Color(0, 102, 51));
		sendit.setBackground(Color.WHITE);
		sendit.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		sendit.setBounds(451, 274, 117, 48);
		contentPane.add(sendit);
		setVisible(true);
	}
}
