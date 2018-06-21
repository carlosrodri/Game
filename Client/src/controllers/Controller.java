package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

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

	private void validateLoad() throws IOException {
		mainWindow = new MainWindow(this);
		player = new Player(mainWindow.getHeight(), mainWindow.getWidth(), dialogAvatar.getHero(), mainWindow.getIP(),
				mainWindow.getPort(), mainWindow.getPlayerName());
		player.send(ConstantsNetwork.GAME);
		fileManager.writeFile(ConstantsUI.PATH_SEND, player.getGame());
		player.sendFile(new File(ConstantsUI.PATH_SEND));
		mainWindow.initPanelDrwaing();
		player.setWindow(mainWindow, fileManager);
		mainWindow.setLocalGame(player.getGame());
		new Timer(10, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				player.setDimesion(mainWindow.getHeight(), mainWindow.getWidth());
			}
		}).start();
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
			play();
			break;
		case CHOOSE:
			dialogAvatar.setVisible(false);
			try {
				validateLoad();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			break;
		}
	}

	private void moreInfo() {
		dialogMoreInfo.setVisible(true);
	}

	private void instruction() {
		dialogInstructions.setVisible(true);
	}

	private void play() {
		dialogAvatar.setVisible(true);
		dialogLoggin.setVisible(false);
	}
}