package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import models.dao.Game;
import views.MainWindow;

public class Controller implements KeyListener{
	private Game game;
	private MainWindow mainWindow;
	private Timer timer;
//	private JSONFileManager jsonFileManager;
	
	public Controller() {
//		jsonFileManager = new JSONFileManager(10000);
//		jsonFileManager.start();
		validateLoad();
		game.start();
		timer = new Timer(10, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainWindow.paint();
				validate();
			}
		});
		timer.start();
//		jsonFileManager.setEnemyListDao(game.getEnemyList());
		
	}
	
	private void validateLoad() {
		int option = JOptionPane.showConfirmDialog(null, "do you want load the game?");
		if(option != 1) {
			game = new Game(200, 0, 0);
//				game.setEnemyList(jsonFileManager.readFile());
			mainWindow = new MainWindow(this);
			game.setDimensions(mainWindow.getWidth(), mainWindow.getHeight());
			mainWindow.setGame(game);
		}else {
			mainWindow = new MainWindow(this);
			game = new Game(200, mainWindow.getWidth(), mainWindow.getHeight());
			game.addEnenmy();
			mainWindow.setGame(game);
		}
	}

	private void validate() {
		game.validate();
		if(game.validateLevel()) {
			game.pause();
			JOptionPane.showMessageDialog(null, "Next Level");
			game.addEnenmy();
			game.resume();
		}
		if(game.validateLife()) {
			JOptionPane.showMessageDialog(null, "you lose");
			System.exit(0);
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			left();
			break;
		case KeyEvent.VK_RIGHT:
			right();
			break;
		case KeyEvent.VK_UP:
			up();
			break;
		case KeyEvent.VK_DOWN:
			down();
			break;
		case KeyEvent.VK_E:
			shoot(KeyEvent.VK_E);
			break;
		case KeyEvent.VK_R:
			shoot(KeyEvent.VK_R);
			break;
		case KeyEvent.VK_T:
			shoot(KeyEvent.VK_T);
			break;
		} 
	}

	private void shoot(int key) {
		game.manageShoot(key);
	}

	private void down() {
		game.moveDown();
	}

	private void up() {
		game.moveUp();
	}

	private void right() {
		game.moveRigth();
	}

	private void left() {
		game.moveleft();
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

	public static void main(String[] args) {
		new Controller();
	}
}