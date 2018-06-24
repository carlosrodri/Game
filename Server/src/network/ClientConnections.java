package network;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.Socket;

import javax.swing.Timer;

import constants.ConstantsNetwork;
import constants.ConstantsUI;
import models.entities.Game;

public class ClientConnections extends Connection{
	private String name;
	private Server server;

	public ClientConnections(Socket newConnection, Server server) throws IOException {
		super(newConnection);
		this.server = server;
		new Timer(100, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					server.sendMessageALL();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}).start();
	}

	@Override
	void executeTask() {
		try {
			String option = readResquest();
			switch (option) {
			case ConstantsNetwork.GAME:
				game();
				break;
			case ConstantsNetwork.MOVEMENT:
				enqueue();
				break;
			case ConstantsNetwork.ENEMY:
				quitLifeEnemy();
				break;
			default:
				this.name = option;
				break;
			}
		} catch (IOException e) {
		}
	}

	private void quitLifeEnemy() {
		try {
			server.quitLifeEnemy(readRequestInt());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void enqueue() throws IOException {
		String name = readResquest();
		int action = readRequestInt();
		for (Game game : server.getList()) {
			if(game.getName().equals(name)) {
				game.enqueueActions(action);
			}
		}
	}

	private void game() {
		server.game(this);
	}

	public void sendFile(){
		sendFile(new File(ConstantsUI.PATH_FILE));
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
