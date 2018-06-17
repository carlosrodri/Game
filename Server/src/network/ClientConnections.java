package network;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import constants.ConstantsNetwork;
import constants.ConstantsUI;
import models.entities.Game;
import utilities.MyUtilities;

public class ClientConnections extends Connection{
	private String name;

	public ClientConnections(Socket newConnection) throws IOException {
		super(newConnection);
	}

	@Override
	void executeTask() {
		try {
			String option = readResquest();
			switch (option) {
			case ConstantsNetwork.GAME:
				game();
				break;
			case ConstantsNetwork.SHOOT:
//				game();
				break;
			case ConstantsNetwork.MOVEMENT:
				movement();
				break;
			default:
				this.name = option;
				break;
			}
		} catch (IOException e) {
		}
	}

	private void movement() throws IOException {
		for (Game game : Server.getList()) {
			if(game.getName().equals(readResquest())) {
				System.out.println(" accion encoladaddadada");
				game.manageMovement(readRequestInt());
				Server.sendMessageALL();
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
