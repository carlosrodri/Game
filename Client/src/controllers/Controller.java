package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.Timer;

import constants.ConstantsNetwork;
import constants.ConstantsUI;
import network.Player;
import persistence.JSONFileManager;
import views.DialogAvatar;
import views.DialogInstructions;
import views.DialogLoggin;
import views.DialogMoreInfo;
import views.MainWindow;

public class Controller implements KeyListener, ActionListener{
	private Player player;
	private DialogAvatar dialogAvatar;
	private MainWindow mainWindow;
	private DialogLoggin dialogLoggin;
	private DialogInstructions dialogInstructions;
	private DialogMoreInfo dialogMoreInfo;
	private JSONFileManager fileManager;

	public Controller() {
		dialogLoggin = new DialogLoggin(this);
		fileManager = new JSONFileManager();
		dialogInstructions = new DialogInstructions();
		dialogMoreInfo = new DialogMoreInfo();
		dialogAvatar = new DialogAvatar(this);
	}

	private void validateLoad(String avatar) throws IOException {
		mainWindow = new MainWindow(this);
		player = new Player(mainWindow.getHeight(), mainWindow.getWidth(), avatar, dialogLoggin.getIP(),
				dialogLoggin.getPort(), dialogLoggin.getPlayerName());
		player.send(ConstantsNetwork.GAME);
		fileManager.writeFile(ConstantsUI.PATH_SEND, player.getGame());
		player.sendFile(new File(ConstantsUI.PATH_SEND+"game.json"));
		mainWindow.initPanelDrwaing();
		mainWindow.paintlevel(1);
		player.setWindow(mainWindow, fileManager);
		mainWindow.setLocalGame(player.getGame());
		new Timer(100, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				player.setDimesion(mainWindow.getHeight(), mainWindow.getWidth());
			}
		}).start();
		mainWindow.revalidate();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		try {
			player.send(ConstantsNetwork.MOVEMENT);
			player.send(player.getName());
			player.sendInt(e.getKeyCode());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		player.manageMovemenet(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (MyActions.valueOf(e.getActionCommand())) {
		case EXIT:
			System.exit(0);
			break;
		case INSTRUCTIONS:
			instruction();
			break;
		case MOREINFO:
			moreInfo();
			break;
		case PLAY:
			acceptRegistry();
			break;
		case CHOOSE:
			dialogAvatar.setVisible(false);
			try {
				validateLoad(selectAvatar(((JButton)e.getSource()).getName()));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			break;
		case ACCEPT_REGISTRY:
			play();
			break;
		}
	}

	private void play() {
		dialogLoggin.visibilityDialog(false);
		dialogAvatar.setVisible(true);
		dialogLoggin.setVisible(false);
	}

	private void acceptRegistry() {
		dialogLoggin.setVisible(false);
		dialogLoggin.visibilityDialog(true);
	}

	private String selectAvatar(String string) {
		switch (string) {
		case "cap":
			return ConstantsUI.CAP;
		case "groot":
			return ConstantsUI.GROOT;
		case "deadpool":
			return ConstantsUI.POOL;
		case "spider":
			return ConstantsUI.SPIDER;
		case "hulk":
			return ConstantsUI.HULK;
		case "green":
			return ConstantsUI.LATERN;
		}
		return ConstantsUI.CAP;
	}

	private void moreInfo() {
		dialogMoreInfo.setVisible(true);
	}

	private void instruction() {
		dialogInstructions.setVisible(true);
	}
}