package views;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import constants.ConstantsUI;
import controllers.Controller;
import models.entities.Game;
import models.entities.Shoot;

public class PanelDrawing extends JPanel{

	private static final long serialVersionUID = 1L;
	private ArrayList<Game> gameList;
	private Image enemy, background, ulti, basic;
	private final int POSITION_Y_STRING = 73;
	private final int POSITION_Y_HABILITY = 30;

	public PanelDrawing(Controller controller) {
		enemy = new ImageIcon(getClass().getResource(ConstantsUI.RIVAL_SHOOT_IMG)).getImage();
		basic = new ImageIcon(getClass().getResource(ConstantsUI.BASIC_SHOOT)).getImage();
		ulti = new ImageIcon(getClass().getResource(ConstantsUI.ULTI_SHOOT)).getImage();
		this.addKeyListener(controller);
	}

	@Override
	public void paint(Graphics g) {
		background = new ImageIcon(getClass().getResource(gameList.get(0).getBackground())).getImage();
		super.paint(g);
		g.drawImage(background, 0, 0, getWidth(), getHeight(),this);
		if(gameList != null) {
			for (Game game : gameList) {
				paintPlayer(g, game);
				paintEnemy(g, game);
				paintShoot(g, game);
				paintLife(g, game);
				paintHabilities(g);
			}
		}
	}

	private void paintHabilities(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(ConstantsUI.FONT_GAME);
		g.drawString("Habilities: ", (getWidth()/5), 15);
		g.drawImage(basic, (getWidth()/5), POSITION_Y_HABILITY, ConstantsUI.SIZE_ICON_HABILITY, ConstantsUI.SIZE_ICON_HABILITY, this);
		g.drawString("E", (getWidth()/5)+12 , POSITION_Y_STRING);
		g.drawImage(ulti, (getWidth()/3)-50, POSITION_Y_HABILITY, ConstantsUI.SIZE_ICON_HABILITY, ConstantsUI.SIZE_ICON_HABILITY, this);
		g.drawString("T", (getWidth()/3)-38, POSITION_Y_STRING);
	}

	private void paintLife(Graphics g, Game game) {
		g.setColor(Color.YELLOW);
		g.fillRect(getWidth()/2, 25, game.getLife(), 20);
		g.drawRect(getWidth()/2, 25, 100, 20);
		g.setColor(Color.BLACK);
		g.drawString("LIFE: " + game.getLife(), (getWidth()/2)+25, 40);
	}

	private void paintPlayer(Graphics g, Game game) {
		g.drawImage(new ImageIcon(getClass().getResource(game.getAvatar())).getImage(),(int)game.getPlayer().getX(), (int)game.getPlayer().getY(), (int)game.getPlayer().getWidth(),
				(int)game.getPlayer().getHeight(), this);
	}

	private void paintShoot(Graphics g, Game game) {
		g.setColor(Color.RED);
		if(game.getList() != null && game.getList().size() != 0) {
			for (Shoot shoot : game.getList()) {
				g.drawImage(new ImageIcon(getClass().getResource(shoot.getImage())).getImage(),
						(int)shoot.getRectangle().getX(), (int)shoot.getRectangle().getY(), 
						(int)shoot.getRectangle().getWidth(), (int)shoot.getRectangle().getHeight(), this);
			}
		}
	}

	public void paintEnemy(Graphics g, Game game) {
		if(game.getEnemyList() != null && game.getEnemyList().size() != 0) {
			for (Rectangle rectangle : game.getEnemyList()) {
				g.drawImage(enemy,(int)rectangle.getX(), (int)rectangle.getY(), (int)rectangle.getWidth(), (int)rectangle.getHeight(), this);
			}
		}
	}

	public void setGame(ArrayList<Game> gamelist) {
		this.gameList = gamelist;
		background = new ImageIcon(getClass().getResource(gamelist.get(0).getBackground())).getImage();
	}
}
