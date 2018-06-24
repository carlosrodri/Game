package views;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.Timer;
import controllers.Controller;
import models.entities.Game;

public class MainWindow extends JFrame{
	private static final long serialVersionUID = 1L;
	private PanelDrawing panelDrawing;
	private Controller controller;

	public MainWindow(Controller controller) {
		this.controller = controller;
		this.addKeyListener(controller);
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(600, 900);
		
		new Timer(10, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(panelDrawing != null) {
					repaint();
				}
			}
		}).start();
		
		setVisible(true);
	}

//	public void paint() {
//		panelDrawing.repaint();
//		panelDrawing.revalidate();
//		this.repaint();
//	}

	public void setGame(ArrayList<Game> gameList) {
		panelDrawing.setGame(gameList);
	}

	public void initPanelDrwaing() {
		panelDrawing = new PanelDrawing(controller);
		add(panelDrawing, BorderLayout.CENTER);
	}
	
	public void setLocalGame(Game game) {
		panelDrawing.setLocalGame(game);
	}
}