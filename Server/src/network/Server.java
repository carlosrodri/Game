package network;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import constants.ConstantsNetwork;

public class Server {

	private ServerSocket serverSocket;
	public static ArrayList<ClientConnections> clientConnections;

	public Server() throws IOException {
		clientConnections = new ArrayList<>();
		serverSocket = new ServerSocket(2001);
		new Thread(){
			@Override
			public void run() {
				try {
					while (true) {
						System.out.println("Server online...");
						Socket newConnection = serverSocket.accept();
						System.out.println("aceptado");
						clientConnections.add(new ClientConnections(newConnection));
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

	public static ArrayList<ClientConnections> getClientConnections() {
		return clientConnections;
	}

	public static void sendMessageALL(){
		for (ClientConnections clientConnections2 : clientConnections) {
			try {
				if (clientConnections2.getSocket().isConnected()) {
					clientConnections2.send(ConstantsNetwork.FILE);
					clientConnections2.sendFile();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		try {
			new Server();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String files(File file) {
		String letter = "";
		File[] f = file.listFiles();
		for (int i = 0; i < f.length; i++) {
			if(f[i].isFile()) {
				letter += f[i].getName() + "#";
			}
		}
		return letter;
	}
}