package com.dit.chatapp.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class ServerWorker extends Thread {
	private Socket clientSocket;
	private InputStream in;
	private OutputStream out;
	private Server server;
	public ServerWorker(Socket clientSocket, Server server) throws IOException {
		this.server = server;
		this.clientSocket = clientSocket;
		in = clientSocket.getInputStream(); //Reads client data
		out = clientSocket.getOutputStream(); //writes client data
		System.out.println("New Client Comes");
	}
	@Override
	public void run() {
		//read data from the client and broadcast the data to all
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String line;
		try {
			while(true) {
				line = br.readLine(); // needs \n
				System.out.println("Line Read......"+line);
				if(line.equalsIgnoreCase("quit")) {
					break; //Particular Client chat end
				}
				//sends only to one client
				//out.write(line.getBytes()); //Client send
				
				//broadcast to all client
				for(ServerWorker serverWorker : server.workers) {
					line = line + "\n";
					serverWorker.out.write(line.getBytes());
				}
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(br != null) {
					br.close();
				}
				if(in != null) {
					in.close();
				}
				if(out != null) {
					out.close();
				}
				if(clientSocket != null) {
					clientSocket.close();
				}
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}



/*
//public class ServerWorker implements Runnable {

public class ServerWorker extends Thread {
	@Override
	public void run() {
		//job to perform
		//Logic
		for(int i=1;i<=5;i++) {
			System.out.println("Run I is "+i+" "+Thread.currentThread());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		ServerWorker job = new ServerWorker();
		job.start();

		for(int j=1;j<=5;j++) {
			System.out.println("Main "+j+" "+Thread.currentThread());
		}
	}
}
