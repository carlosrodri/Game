package network;

import java.awt.Rectangle;
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
	private int level;
	private MyUtilities myUtilities;
	private JSONFileManagerServer fileManagerServer;
	private Server s = this;

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
						ClientConnections c = new ClientConnections(newConnection);
						c.setServer(s);
						clientConnections.add(c);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

	private void addEnemies() {
		for (Game game : myUtilities.getGameList()) {
			for (int i = 0; i < level*5; i++) {
				game.addEnenmy(new Rectangle(randomX(), randomY(), 50, 50));
			}
		}
	}
	
	private int randomY() {
		return (int)Math.random()*700;
	}

	private int randomX() {
		return (int)Math.random()*1000;
	}

	public static ArrayList<ClientConnections> getClientConnections() {
		return clientConnections;
	}

	public void sendMessageALL() throws IOException{
		if(fileManagerServer != null && myUtilities != null) {
//			fileManagerServer.writeGameList(myUtilities.getGameList());
			for (ClientConnections clientConnections2 : clientConnections) {
				try {
					if (clientConnections2.getSocket().isConnected()) {
						clientConnections2.send(ConstantsNetwork.LIST);
						clientConnections2.send(fileManagerServer.writeGameList(myUtilities.getGameList()));
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

	public void game(ClientConnections clientConnections2) {
		fileManagerServer = new JSONFileManagerServer();
		try {
			clientConnections2.saveFile();
			Game g = fileManagerServer.readGame();
			g.start();
			myUtilities.add(g);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			sendMessageALL();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Game> getList(){
		return myUtilities.getGameList();
	}
}