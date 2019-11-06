package networking.ra.multithread_server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class MultiThreadServer{
	//private Logger logger = Logger.getLogger(MultiThreadServer.class.getClass());
	
	private ServerSocket serverSocket;
	private final static int SERVER_PORT = 9999;

	private Socket socketClient;
	
	public MultiThreadServer() throws IOException {
		try {
			this.serverSocket = new ServerSocket(SERVER_PORT);
			if(serverSocket == null) System.out.println("serverSocket null");
		//	logger.info("serverSocket created with port: " + SERVER_PORT);
		} catch (IOException e) {
		//	logger.info(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void acceptConnection() throws IOException {
//		while(true) {
			try {
				this.socketClient = this.serverSocket.accept();
				System.out.println(this.socketClient);
				ConnectionHandle connectionHandle = new ConnectionHandle(this.socketClient);
				Thread thread = new Thread(connectionHandle);
				thread.start();
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				if(this.serverSocket != null)
					this.serverSocket.close();
			}
//		}
	}
	
	public static void main(String[] args) throws IOException {
		MultiThreadServer server = new MultiThreadServer();
		try {
			server.acceptConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
