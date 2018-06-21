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

	public ClientConnections(Socket newConnection) throws IOException {
		super(newConnection);
		new Timer(10, new  ActionListener() {
			
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
			default:
				this.name = option;
				break;
			}
		} catch (IOException e) {
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
		System.out.println("manda el juego");
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

	public void setServer(Server s) {
		System.out.println("setet el server");
		server = s;
	}
}
