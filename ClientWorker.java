package com.dit.chatapp.network;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JTextArea;

public class ClientWorker extends Thread {
	private InputStream in;
	private JTextArea textArea;
	public ClientWorker(InputStream in, JTextArea textArea) {
		this.in = in;
		this.textArea = textArea;
	}
	@Override
	public void run() {
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String line;
		try {
			while(true) {
				line = br.readLine();
				System.out.println("Line Read......"+line);
				textArea.setText(textArea.getText()+line+"\n");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if(in != null) {
					in.close();
				}
			}catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
