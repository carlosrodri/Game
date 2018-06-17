package network;


import java.io.FileNotFoundException;
import java.io.IOException;

import constants.ConstantsNetwork;
import models.entities.Game;
import persistence.JSONFileManager;
import views.MainWindow;

public class Player extends Connection{

	private String name;
	private Game game;
	private MainWindow mainWindow;
	private JSONFileManager fileManager;

	public Player(int sleep, int x, int y, String avatar, String ip, int port, String name) throws IOException{
		super(ip, port);
		this.name = name;
		game = new Game(sleep, x, y, avatar, name);
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
		return game.getName();
	}
	
	private void readFile() {
		try {
			saveFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			if(mainWindow != null && fileManager != null) {
			mainWindow.setGame(fileManager.readFile());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
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

	public Game getGame() {
		return game;
	}

	public void startGame() {
		game.start();
	}

	public void setWindow(MainWindow mainWindow, JSONFileManager fileManager) {
		this.mainWindow = mainWindow;
		this.fileManager = fileManager;
		try {
			mainWindow.setGame(fileManager.readFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}