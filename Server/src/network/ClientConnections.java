package network;

import java.io.File;
import java.io.IOException;
import java.net.Socket;

import org.json.simple.parser.ParseException;

import constants.ConstantsNetwork;
import constants.ConstantsUI;
import models.entities.Game;
import persistence.JSONFileManagerServer;
import utilities.MyUtilities;

public class ClientConnections extends Connection{
	private String name;
	private MyUtilities myUtilities;
	private JSONFileManagerServer fileManager;

	public ClientConnections(Socket newConnection) throws IOException {
		super(newConnection);
		myUtilities = new MyUtilities();
		fileManager = new JSONFileManagerServer();
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
		try {
			saveFile();
			myUtilities.add
			(fileManager.
					readGame());
			for (Game iterable_element : myUtilities.getGameList()) {
				System.out.println(iterable_element.getAvatar() + "      avatarrrrrrrr");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		update();
	}

	private void update() {
		try {
			fileManager.writeGameList(myUtilities.getGameList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		Server.sendMessageALL();
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
