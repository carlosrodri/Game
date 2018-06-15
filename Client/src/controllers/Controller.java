package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import network.Player;
import structures.NodeList;
import structures.Queue;
import views.DialogAvatar;
import views.DialogInstructions;
import views.DialogLoggin;
import views.DialogMoreInfo;
import views.MainWindow;

public class Controller implements KeyListener, ActionListener{
	private Player player;
	private DialogAvatar dialogAvatar;
	private MainWindow mainWindow;
	private Timer timer;
	private Queue<Integer> actions;
	private Timer timerActions;
	private DialogLoggin dialogLoggin;
	private DialogInstructions dialogInstructions;
	private DialogMoreInfo dialogMoreInfo;

	public Controller() {
		actions = new Queue<>();
		dialogLoggin = new DialogLoggin(this);
		dialogInstructions = new DialogInstructions();
		dialogMoreInfo = new DialogMoreInfo();
		dialogAvatar = new DialogAvatar(this);
	}

	private void validateLoad() throws IOException {
			mainWindow = new MainWindow(this);
			player = new Player(200, mainWindow.getWidth(), mainWindow.getHeight(), dialogAvatar.getHero(), mainWindow.getIP(),
					mainWindow.getPort(), mainWindow.getPlayerName());
			player.addEnenmy();
	}

	private void initGame() {
		player.start();
		timer = new Timer(10, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainWindow.paint();
				validate();
			}
		});
		timer.start();
		timerActions = new Timer(10, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				manageActions();
			}
		});
		timerActions.start();
	}

	private void validate() {
		player.validate();
		if(player.validateLevel()) {
			player.pause();
			JOptionPane.showMessageDialog(null, "Next Level");
			player.addEnenmy();
			player.resume();
		}
		if(player.validateLife()) {
			JOptionPane.showMessageDialog(null, "you lose");
			System.exit(0);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		actions.enqueue(new NodeList<Integer>(e.getKeyCode()));
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	private void manageActions() {
		if (!actions.isEmpty()) {
			switch (actions.dequeue().getInformation()) {
			case KeyEvent.VK_LEFT:
				manageMovement(KeyEvent.VK_LEFT);
				break;
			case KeyEvent.VK_RIGHT:
				manageMovement(KeyEvent.VK_RIGHT);
				break;
			case KeyEvent.VK_UP:
				manageMovement(KeyEvent.VK_UP);
				break;
			case KeyEvent.VK_DOWN:
				manageMovement(KeyEvent.VK_DOWN);
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
			try {
				validateLoad();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			initGame();
			break;
		}
	}
	
	private void manageMovement(int action) {
		player.manageMovement(action);
	}

	private void shoot(int key) {
		player.manageShoot(key);
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