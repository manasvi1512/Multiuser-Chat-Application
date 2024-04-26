package com.dit.chatapp.network;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JTextArea;

import com.dit.chatapp.utils.ConfigReader;

public class Client {
	Socket s;
	InputStream in;
	OutputStream out;
	ClientWorker worker;
	JTextArea textArea;
	public Client(JTextArea textArea) throws UnknownHostException, IOException {
		int PORT = Integer.parseInt(ConfigReader.getValue("PORTNO"));
		s = new Socket(ConfigReader.getValue("SERVER_IP"), PORT);
		out = s.getOutputStream();
		in = s.getInputStream();
		this.textArea = textArea;
		readMessage();
		
		/*
		System.out.println("Client comes.....");
		System.out.println("Enter the message to be send to the Server....");
		Scanner sc = new Scanner(System.in);
		String message = sc.nextLine(); //takes input till enter is not pressed
		OutputStream out = s.getOutputStream(); //write bytes on the network
		out.write(message.getBytes());
		System.out.println("Message send to the Server....");
		sc.close();
		out.close();
		s.close();
		*/
	}
	public void sendMessage(String message) throws IOException {
		message = message + "\n";
		out.write(message.getBytes());
	}
	public void readMessage() {
		worker = new ClientWorker(in, textArea); //calling a read thread
		worker.start();
	}
	
	/*
	public static void main(String[] args) throws UnknownHostException, IOException {
		Client c = new Client();
	}
	*/
}
