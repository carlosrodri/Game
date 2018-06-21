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
	private Game game;
	private int n = 0;

	public PanelDrawing(Controller controller) {
		enemy = new ImageIcon(getClass().getResource(ConstantsUI.RIVAL_SHOOT_IMG)).getImage();
		basic = new ImageIcon(getClass().getResource(ConstantsUI.BASIC_SHOOT)).getImage();
		ulti = new ImageIcon(getClass().getResource(ConstantsUI.ULTI_SHOOT)).getImage();
		background = new ImageIcon(getClass().getResource(ConstantsUI.LEVEL1)).getImage();
		this.addKeyListener(controller);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(background, 0, 0, getWidth(), getHeight(),this);
		if(gameList != null) {
			background = new ImageIcon(getClass().getResource(ConstantsUI.LEVEL1)).getImage();
			int n = 0;
			for (Game game : gameList) {
				paintPlayer(g, game);
				paintEnemy(g, game);
				paintShoot(g, game, n);
				paintLife(g, game, n);
				paintHabilities(g, n);
				n++;
			}
		}
	}

	private void paintHabilities(Graphics g, int n) {
		if(n == 0) {
			g.setColor(Color.WHITE);
			g.setFont(ConstantsUI.FONT_GAME);
			g.drawString("Habilities: ", (getWidth()/5), 15);
			g.drawImage(basic, (getWidth()/5), POSITION_Y_HABILITY, ConstantsUI.SIZE_ICON_HABILITY, ConstantsUI.SIZE_ICON_HABILITY, this);
			g.drawString("E", (getWidth()/5)+12 , POSITION_Y_STRING);
			g.drawImage(ulti, (getWidth()/3)-50, POSITION_Y_HABILITY, ConstantsUI.SIZE_ICON_HABILITY, ConstantsUI.SIZE_ICON_HABILITY, this);
			g.drawString("T", (getWidth()/3)-38, POSITION_Y_STRING);
		}
	}

	private void paintLife(Graphics g, Game game, int n) {
		if(n == 0) {
			g.setColor(Color.YELLOW);
			g.fillRect(getWidth()/2, 25, game.getLife(), 20);
			g.drawRect(getWidth()/2, 25, 100, 20);
			g.setColor(Color.BLACK);
			g.drawString("LIFE: " + game.getLife(), (getWidth()/2)+25, 40);
		}
	}

	private void paintPlayer(Graphics g, Game game) {
		if(!game.getName().equals(this.game.getName()) && this.game != null) {
			g.drawImage(new ImageIcon(getClass().getResource(game.getAvatar())).getImage(),(int)game.getX(), (int)game.getY(), (int)game.getPlayer().getWidth(),
					(int)game.getPlayer().getHeight(), this);
		}else {
			g.drawImage(new ImageIcon(getClass().getResource(this.game.getAvatar())).getImage(),(int)this.game.getPlayer().getX(), (int)this.game.getPlayer().getY(), 50,
					50, this);
		}
	}

	private void paintShoot(Graphics g, Game game, int n) {
		if(n == 0) {
			g.setColor(Color.RED);
			if(game.getList() != null && game.getList().size() != 0) {
				for (Shoot shoot : game.getList()) {
					g.drawImage(new ImageIcon(getClass().getResource(shoot.getImage())).getImage(),
							(int)shoot.getRectangle().getX(), (int)shoot.getRectangle().getY(), 
							(int)shoot.getRectangle().getWidth(), (int)shoot.getRectangle().getHeight(), this);
				}
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
		System.out.println("setea el juego  " + n  + "   veces");
		for (Game game : gamelist) {
			System.out.println(game.getX() + "   jajaja");
		}
		n++;
		this.gameList = gamelist;
//		if(gamelist.size() != 0) {
//			background = new ImageIcon(getClass().getResource(gamelist.get(0).getBackground())).getImage();
//		}
	}

	public void setLocalGame(Game game) {
		this.game = game;
	}
}
