package network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import constants.ConstantsNetwork;
import models.entities.Game;
import persistence.JSONFileManagerServer;
import utilities.MyUtilities;

public class Server {

	private ServerSocket serverSocket;
	private static MyUtilities myUtilities;
	private static JSONFileManagerServer fileManagerServer;
	private static boolean state;

	public static ArrayList<ClientConnections> clientConnections;

	public Server() throws IOException {
		clientConnections = new ArrayList<>();
		serverSocket = new ServerSocket(2001);
		myUtilities = new MyUtilities();
		fileManagerServer = new JSONFileManagerServer();
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

	public static void sendMessageALL() throws IOException{
		if(fileManagerServer != null && myUtilities != null) {
			fileManagerServer.writeGameList(myUtilities.getGameList());
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
	}

	public static void main(String[] args) {
		try {
			new Server();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void game(ClientConnections clientConnections2) {
		fileManagerServer = new JSONFileManagerServer();
		try {
			clientConnections2.saveFile();
			myUtilities.add(fileManagerServer.readGame());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<Game> getList(){
		return myUtilities.getGameList();
	}

	public static boolean isState() {
		return state;
	}

	public static void setState(boolean state) {
		Server.state = state;
	}
}