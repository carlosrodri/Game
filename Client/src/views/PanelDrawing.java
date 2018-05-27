package views;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import constants.ConstantsUI;
import controllers.Controller;
import models.dao.Game;
import models.entities.Shoot;

public class PanelDrawing extends JPanel{

	private static final long serialVersionUID = 1L;
	private Game game;
	private Image enemy, gunman, background, pasive, ulti, basic;

	public PanelDrawing(Controller controller) {
		enemy = new ImageIcon(getClass().getResource(ConstantsUI.ENEMY_IMG)).getImage();
		gunman = new ImageIcon(getClass().getResource(ConstantsUI.GUNMAN_IMG)).getImage();
		background = new ImageIcon(getClass().getResource(ConstantsUI.BACK_IMAGE)).getImage();
		basic = new ImageIcon(getClass().getResource(ConstantsUI.BASIC_SHOOT)).getImage();
		this.addKeyListener(controller);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(background, 0, 0, getWidth(), getHeight(),this);
		if(game != null) {
			paintPlayer(g);
			paintEnemy(g);
			paintShoot(g);
			paintLife(g);
			paintHabilities(g);
		}
	}

	private void paintHabilities(Graphics g) {
		g.drawString("Habilities: " + game.getLife(), (getWidth()/5), 20);
		g.drawImage(basic, (getWidth()/5), 40, ConstantsUI.SIZE_ICON_HABILITY, ConstantsUI.SIZE_ICON_HABILITY, this);
	}

	private void paintLife(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillRect(getWidth()/2, 25, game.getLife(), 20);
		g.drawRect(getWidth()/2, 25, 100, 20);
		g.setColor(Color.BLACK);
		g.drawString("LIFE: " + game.getLife(), (getWidth()/2)+25, 40);
	}

	private void paintPlayer(Graphics g) {
		g.drawImage(gunman,(int)game.getPlayer().getX(), (int)game.getPlayer().getY(), (int)game.getPlayer().getWidth(),
				(int)game.getPlayer().getHeight(), this);
	}

	private void paintShoot(Graphics g) {
		g.setColor(Color.RED);
		if(game.getList() != null && game.getList().size() != 0) {
			for (Shoot shoot : game.getList()) {
				g.drawImage(new ImageIcon(getClass().getResource(shoot.getImage())).getImage(),
						(int)shoot.getRectangle().getX(), (int)shoot.getRectangle().getY(), 
						(int)shoot.getRectangle().getWidth(), (int)shoot.getRectangle().getHeight(), this);
			}
		}
	}

	public void paintEnemy(Graphics g) {
		g.setColor(Color.BLUE);
		if(game.getEnemyList() != null && game.getEnemyList().size() != 0) {
			for (Rectangle rectangle : game.getEnemyList()) {
				g.drawImage(enemy,(int)rectangle.getX(), (int)rectangle.getY(), (int)rectangle.getWidth(), (int)rectangle.getHeight(), this);
			}
		}
	}

	public void setGame(Game game) {
		this.game = game;
	}
}
