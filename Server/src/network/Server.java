package network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import constants.ConstantsNetwork;
import models.entities.Enemy;
import models.entities.Game;
import persistence.JSONFileManagerServer;
import utilities.MyUtilities;

public class Server {

	private ServerSocket serverSocket;
	private static int level;
	private static MyUtilities myUtilities;
	private static JSONFileManagerServer fileManagerServer;
	private int p;

	public static ArrayList<ClientConnections> clientConnections;

	public Server() throws IOException {
		level = 1;
		p = Integer.parseInt(JOptionPane.showInputDialog("port of server"));
		clientConnections = new ArrayList<>();
		try {
			serverSocket = new ServerSocket(2000);
			JOptionPane.showMessageDialog(null, "has been creaded in port number: " + serverSocket.getLocalPort());
		} catch (Exception e) {
			serverSocket = new ServerSocket(generatePort());
			JOptionPane.showMessageDialog(null, "has been creaded in port number: " + serverSocket.getLocalPort());
		}
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
						clientConnections.add(c);
					}
				} catch (IOException e) {

				}
			}
		}.start();
	}

	private int generatePort() {
		p++;
		return (int)(Math.random()*10000)+p;
	}

	private static void addEnemies() {
		System.out.println("enemigos     ");
		for (Game game : myUtilities.getGameList()) {
			for (int i = 0; i < level*5; i++) {
				Enemy enemy = new Enemy(1250);
				enemy.initPosition();
				enemy.start();
				game.addEnenmy(enemy);
			}
		}
	}

	public static ArrayList<ClientConnections> getClientConnections() {
		return clientConnections;
	}

	public static void sendMessageALL() throws IOException{
		if(fileManagerServer != null && myUtilities != null) {
			//			fileManagerServer.writeGameList(myUtilities.getGameList());
			for (ClientConnections clientConnections2 : clientConnections) {
				try {
					if (clientConnections2.getSocket().isConnected()) {
						clientConnections2.send(ConstantsNetwork.LIST);
						clientConnections2.send(fileManagerServer.writeGameList(myUtilities.getGameList()));
					}else {
						JOptionPane.showMessageDialog(null, "the player " + clientConnections2.getName() + "  has been desconnected");
						clientConnections2.close();
					}
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "the player " + clientConnections2.getName() + "  has been desconnected");
					clientConnections2.close();
					deeleteConection(clientConnections2);
				}
			}
		}
	}

	private static void deeleteConection(ClientConnections clientConnections2) {
		clientConnections.remove(clientConnections2);
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
			Game g = fileManagerServer.readGame();
			g.start();
			myUtilities.add(g);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			addEnemies();
			sendMessageALL();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<Game> getList(){
		return myUtilities.getGameList();
	}

}