package views;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import controllers.Controller;
import models.entities.Game;

public class MainWindow extends JFrame{
	private static final long serialVersionUID = 1L;
	private PanelDrawing panelDrawing;

	public MainWindow(Controller controller) {
		this.addKeyListener(controller);
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		panelDrawing = new PanelDrawing(controller);

		add(panelDrawing, BorderLayout.CENTER);
		setVisible(true);
	}

	public void paint() {
		this.repaint();
	}

	public void setGame(ArrayList<Game> gameList) {
		panelDrawing.setGame(gameList);
	}

	public String getIP() {
		return JOptionPane.showInputDialog("IP");
	}

	public int getPort() {
		return Integer.parseInt(JOptionPane.showInputDialog("Port"));
	}
	
	public String getPlayerName() {
		return JOptionPane.showInputDialog("Name");
	}
}