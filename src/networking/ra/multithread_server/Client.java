package networking.ra.multithread_server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
//  private Logger logger = Logger.getLogger(Client.class.getLogger());

	private Socket socket;
	private int port;
	private String serverAddress;
	
	private BufferedReader in;
	private PrintWriter out;
	
	public Client(String serverAddress, int port) {
		this.serverAddress = serverAddress;
		this.port = port;
		
		try {
			this.socket = new Socket(serverAddress, port);
			this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			this.out = new PrintWriter(this.socket.getOutputStream());
			System.out.println(this.out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public BufferedReader getInputStream() {
		return in;
	}

	public PrintWriter getOutputStream() {
		return out;
	}
	
	public void startClient() {
		try {
			Scanner in = new Scanner(System.in);
			this.out.println(in.next());
			
			this.in.close();
			this.out.close();
			this.socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Client client = new Client("localhost", 9999);
		client.startClient();
	}
}
