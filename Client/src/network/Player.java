package network;


import java.io.IOException;

import constants.ConstantsNetwork;
import models.entities.Game;
import persistence.JSONFileManager;
import views.MainWindow;

public class Player extends Connection{

	private Game game;
	private MainWindow mainWindow;
	private JSONFileManager fileManager;

	public Player(int x, int y, String avatar, String ip, int port, String name) throws IOException{
		super(ip, port);
		game = new Game(x, y, avatar, name);
		game.start();
		send(name);
	}

	@Override
	void executeTask() {
		validateEnemy();
		try {
			switch (readResponse()) {
			case ConstantsNetwork.LEVEL:
				level(Integer.parseInt(readResponse()));
				break;
			case ConstantsNetwork.LIST:
				if(fileManager != null) {
					mainWindow.setGame(fileManager.readList(readResponse()));
					game.setEnemyList(fileManager.getEnemiesList());
				}
				break;
			}
		} catch (IOException e) {
		}
	}

	private void level(int level) {
		mainWindow.paintlevel(level);
	}

	private void validateEnemy() {
		if(game != null) {
			int validate = game.validateEnemy();
			if(validate > -1) {
				try {
					send(ConstantsNetwork.ENEMY);
					sendInt(validate);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public String getName() {
		return game.getName();
	}

	public Game getGame() {
		return game;
	}

	public void setWindow(MainWindow mainWindow, JSONFileManager fileManager) {
		this.mainWindow = mainWindow;
		this.fileManager = fileManager;
	}

	public void manageMovemenet(int key) {
		game.manageActions(key);
	}

	public void setDimesion(int height, int width) {
		game.setDimensions(width, height);
	}
}