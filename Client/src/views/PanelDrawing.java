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
	private final int POSITION_Y_STRING = 73;
	private final int POSITION_Y_HABILITY = 30;

	public PanelDrawing(Controller controller) {
		enemy = new ImageIcon(getClass().getResource(ConstantsUI.RIVAL_SHOOT_IMG)).getImage();
		gunman = new ImageIcon(getClass().getResource(ConstantsUI.GUNMAN_IMG)).getImage();
		background = new ImageIcon(getClass().getResource(ConstantsUI.BACK_IMAGE)).getImage();
		basic = new ImageIcon(getClass().getResource(ConstantsUI.BASIC_SHOOT)).getImage();
//		pasive = new ImageIcon(getClass().getResource(ConstantsUI.PASSIVE_SHOOT)).getImage();
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
		g.setColor(Color.WHITE);
		g.setFont(ConstantsUI.FONT_GAME);
		g.drawString("Habilities: " + game.getLife(), (getWidth()/5), 15);
		g.drawImage(basic, (getWidth()/5), POSITION_Y_HABILITY, ConstantsUI.SIZE_ICON_HABILITY, ConstantsUI.SIZE_ICON_HABILITY, this);
		g.drawString("E", (getWidth()/5)+12 , POSITION_Y_STRING);
//		g.drawImage(pasive, (getWidth()/4), POSITION_Y_HABILITY, ConstantsUI.SIZE_ICON_HABILITY, ConstantsUI.SIZE_ICON_HABILITY, this);
//		g.drawString("R" + game.getLife(), (getWidth()/4), POSITION_Y_STRING);
//		g.drawImage(ulti, (getWidth()/3), POSITION_Y_HABILITY, ConstantsUI.SIZE_ICON_HABILITY, ConstantsUI.SIZE_ICON_HABILITY, this);
//		g.drawString("T" + game.getLife(), (getWidth()/3), POSITION_Y_STRING);
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
