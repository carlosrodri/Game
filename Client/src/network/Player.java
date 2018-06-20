package network;


import java.io.FileNotFoundException;
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

	public Game getGame() {
		return game;
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