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
	private Timer timer;

	public ClientConnections(Socket newConnection) throws IOException {
		super(newConnection);
		timer = new Timer(50, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(Server.isState()) {
						Server.sendMessageALL();
						Server.setState(false);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		timer.start();
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
		Server.setState(true);
		String name = readResquest();
		int action = readRequestInt();
		for (Game game : Server.getList()) {
			if(game.getName().equals(name)) {
				System.out.println(" accion encoladaddadada" + "    nombre del man  " + name + "actionnn   " + action);
				game.enqueueActions(action);
			}
		}
	}

	private void game() {
		Server.game(this);
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
