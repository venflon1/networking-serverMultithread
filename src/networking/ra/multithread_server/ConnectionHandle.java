package networking.ra.multithread_server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Logger;

public class ConnectionHandle implements Runnable{
	//private Logger logger = Logger.getLogger(ConnectionHandle.class.getClass());
	
	private Socket socketClient;
	
	private BufferedReader in;
	private BufferedWriter out;

	public ConnectionHandle(Socket socketClient) {
		this.socketClient = socketClient;
		
		try {
			this.in = new BufferedReader(new InputStreamReader(this.socketClient.getInputStream()));
			System.out.println(this.in);
			this.out = new BufferedWriter(new OutputStreamWriter(this.socketClient.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public BufferedReader getInputStream() {
		return in;
	}

	public BufferedWriter getOutputStream() {
		return out;
	}

	@Override
	public void run() {
		try {
			String s;
			do {
				s = this.in.readLine();
				System.out.println(s);
			}
			while( !s.equalsIgnoreCase("quit"));
				
			this.in.close();
			this.out.close();
			this.socketClient.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}