package views;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import controllers.Controller;
import models.dao.Game;

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

	public void setGame(Game game) {
		panelDrawing.setGame(game);
	}
}