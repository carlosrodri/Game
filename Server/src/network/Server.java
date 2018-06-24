package network;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import constants.ConstantsNetwork;
import models.entities.Enemy;
import models.entities.Game;
import persistence.JSONFileManagerServer;
import utilities.MyUtilities;
import views.MainWindow;

public class Server {

	private ServerSocket serverSocket;
	private static int level;
	private static MyUtilities myUtilities;
	private static JSONFileManagerServer fileManagerServer;
	private MainWindow mainWindow;
	private int p;

	public static ArrayList<ClientConnections> clientConnections;

	public Server() throws IOException {
		level = 0;
		p = Integer.parseInt(JOptionPane.showInputDialog("port of server"));
		clientConnections = new ArrayList<>();
		try {
			serverSocket = new ServerSocket(p);
			JOptionPane.showMessageDialog(null, "has been creaded in port number: " + serverSocket.getLocalPort());
		} catch (Exception e) {
			serverSocket = new ServerSocket(generatePort());
			JOptionPane.showMessageDialog(null, "has been creaded in port number: " + serverSocket.getLocalPort());
		}
		myUtilities = new MyUtilities();
		fileManagerServer = new JSONFileManagerServer();
		mainWindow = new MainWindow();
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
						mainWindow.setVisible(true);
					}
				} catch (IOException e) {
				}
			}

		}.start();
		
		new Timer(300, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				validateLevel();
			}
		}).start();
	}

	private void validateLevel() {
		System.out.println(myUtilities.getEnemyList().size() + "     tamañoooo");
		if (myUtilities.getEnemyList().size() == 0) {
			System.out.println("validaaaaaaaaa");
			level++;
			for (ClientConnections clientConnections2 : clientConnections) {
				try {
					clientConnections2.send(ConstantsNetwork.LEVEL);
					clientConnections2.send(String.valueOf(level));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			addEnemies();
		}
		
	}
	
	private int generatePort() {
		p++;
		return (int)(Math.random()*10000)+p;
	}

	private static void addEnemies() {
		for (int i = 0; i < level*5; i++) {
			Enemy enemy = new Enemy(1250, i, 100, 1000);
			enemy.initPosition();
			enemy.start();
			myUtilities.addEnemy(enemy);
		}
		Enemy enemy = new Enemy(1250, 1000, 250, 300);
		enemy.start();
		myUtilities.addEnemy(enemy);
	}

	public static ArrayList<ClientConnections> getClientConnections() {
		return clientConnections;
	}

	public static void sendMessageALL() throws IOException{
		if(fileManagerServer != null && myUtilities != null && myUtilities.getGameList().size() > 0) {
			//			fileManagerServer.writeGameList(myUtilities.getGameList());
			for (Iterator<ClientConnections> c =  clientConnections.iterator(); c.hasNext();) {
				ClientConnections conection = c.next();
				try {
					if (conection.getSocket().isConnected()) {
						conection.send(ConstantsNetwork.LIST);
						conection.send(fileManagerServer.writeGameList(myUtilities.getGameList()));
					}else {
						JOptionPane.showMessageDialog(null, "the player " + conection.getName() + "  has been desconnected");
						conection.close();
					}
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "the player " + conection.getName() + "  has been desconnected");
					conection.close();
					deeleteConection(conection.getName());
					c.remove();
				}
			}
		}
	}

	private static void deeleteConection(String name) {
		Game g = null;
		for (Game game : myUtilities.getGameList()) {
			if(game.getName().equals(name)) {
				g = game;
			}
		}
		myUtilities.getGameList().remove(g);
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

	public static ArrayList<Enemy> getEnemylist(){
		return myUtilities.getEnemyList();
	}

	public static void quitLifeEnemy(int id) {
		for (Iterator<Enemy> enemy = myUtilities.getEnemyList().iterator(); enemy.hasNext();) {
			Enemy s = enemy.next();
			if(s.getId() == id) {
				s.setLife(-10);
			}
			if(s.getLife() == 0) {
				enemy.remove();
			}
		}
	}
}