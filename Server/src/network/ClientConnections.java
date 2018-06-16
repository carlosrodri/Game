package network;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import constants.ConstantsNetwork;
import constants.ConstantsUI;
import persistence.JSONFileManagerServer;
import utilities.MyUtilities;

public class ClientConnections extends Connection{
	private String name;
	private MyUtilities myUtilities;
	private JSONFileManagerServer fileManagerServer;

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
//				game();
				break;
			default:
				this.name = option;
				break;
			}
		} catch (IOException e) {
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
