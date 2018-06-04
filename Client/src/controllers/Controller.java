package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import models.dao.Game;
import structures.NodeList;
import structures.Queue;
import views.DialogAvatar;
import views.DialogInstructions;
import views.DialogLoggin;
import views.DialogMoreInfo;
import views.MainWindow;

public class Controller implements KeyListener, ActionListener{
	private Game game;
	private DialogAvatar dialogAvatar;
	private MainWindow mainWindow;
	private Timer timer;
	private Queue<Integer> actions;
	private Timer timerActions;
	private DialogLoggin dialogLoggin;
	private DialogInstructions dialogInstructions;
	private DialogMoreInfo dialogMoreInfo;
	//	private JSONFileManager jsonFileManager;

	public Controller() {
		//		jsonFileManager = new JSONFileManager(10000);
		//		jsonFileManager.start();
		actions = new Queue<>();
		dialogLoggin = new DialogLoggin(this);
		dialogInstructions = new DialogInstructions();
		dialogMoreInfo = new DialogMoreInfo();
		dialogAvatar = new DialogAvatar(this);
	}

	private void validateLoad() {
		int option = JOptionPane.showConfirmDialog(null, "do you want load the game?");
		if(option != 1) {
			System.out.println(dialogAvatar.getHero()+"   heroo");
			mainWindow = new MainWindow(this);
			game = new Game(200, 0, 0, dialogAvatar.getHero());
			//				game.setEnemyList(jsonFileManager.readFile());
			game.setDimensions(mainWindow.getWidth(), mainWindow.getHeight());
			mainWindow.setGame(game);
		}else {
			System.out.println(dialogAvatar.getHero()+"   heroo");
			mainWindow = new MainWindow(this);
			game = new Game(200, mainWindow.getWidth(), mainWindow.getHeight(), dialogAvatar.getHero());
			game.addEnenmy();
			mainWindow.setGame(game);
		}
	}

	private void initGame() {
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

		timerActions = new Timer(10, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				manageActions();
			}
		});
		timerActions.start();
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
		//		System.out.println(e.getKeyCode() + "   obtine las entradas del teclado");
		actions.enqueue(new NodeList<Integer>(e.getKeyCode()));
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//		System.out.println(e.getKeyCode() + "   released");
		//		actions.enqueue(new NodeList<Integer>(e.getKeyCode()));
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//		System.out.println(e.getKeyCode() + "   typed");
		//		actions.enqueue(new NodeList<Integer>(e.getKeyCode()));
	}

	private void manageActions() {
		if (!actions.isEmpty()) {
			switch (actions.dequeue().getInformation()) {
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
			validateLoad();
			initGame();
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