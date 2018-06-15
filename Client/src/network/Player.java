package network;


import java.io.IOException;

import constants.ConstantsNetwork;
import models.dao.Game;

public class Player extends Connection{

	private String name;
	private Game game;

	public Player(int sleep, int x, int y, String avatar, String ip, int port, String name) throws IOException{
		super(ip, port);
		this.name = name;
		game = new Game(sleep, x, y, avatar);
		send(name);
	}

	@Override
	void executeTask() {
		try {
			switch (readResponse()) {
			case ConstantsNetwork.FILE:
				readFile();
				break;
			}
		} catch (IOException e) {
		}
	}

	public String getName() {
		return name;
	}
	
	private void readFile() {
		try {
			saveFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void addEnenmy() {
		game.addEnenmy();
	}

	public void validate() {
		game.validate();
	}

	public boolean validateLevel() {
		return game.validateLevel();
	}

	public boolean validateLife() {
		return game.validateLife();
	}

	public void manageMovement(int action) {
		game.manageMovement(action);
	}

	public void manageShoot(int key) {
		game.manageShoot(key);
	}
	
}