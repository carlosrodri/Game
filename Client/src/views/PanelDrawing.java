package views;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import constants.ConstantsUI;
import controllers.Controller;
import models.dao.Hability;
import models.entities.Enemy;
import models.entities.Game;
import models.entities.Shoot;

public class PanelDrawing extends JPanel{

	private static final long serialVersionUID = 1L;
	private ArrayList<Game> gameList;
	private Image enemyI, background, ulti, basic, enemyB;
	private final int POSITION_Y_STRING = 73;
	private final int POSITION_Y_HABILITY = 30;
	private Game game;

	public PanelDrawing(Controller controller) {
		enemyI = new ImageIcon(getClass().getResource(ConstantsUI.RIVAL_SHOOT_IMG)).getImage();
		enemyB = new ImageIcon(getClass().getResource(ConstantsUI.BOSS_IMG)).getImage();
		basic = new ImageIcon(getClass().getResource(ConstantsUI.BASIC_SHOOT)).getImage();
		ulti = new ImageIcon(getClass().getResource(ConstantsUI.ULTI_SHOOT)).getImage();
		this.addKeyListener(controller);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(background, 0, 0, getWidth(), getHeight(),this);
		if(gameList != null) {
			for (Game game : gameList) {
				paintPlayer(g, game);
				paintShoot(g, game);
			}
		}
		paintEnemy(g);
		paintLife(g);
		paintHabilities(g);
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

	private void paintLife(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillRect(getWidth()/2, 25, game.getLife(), 20);
		g.drawRect(getWidth()/2, 25, 100, 20);
		g.setColor(Color.BLACK);
		g.drawString("LIFE: " + game.getLife(), (getWidth()/2)+25, 40);
		g.setColor(Color.WHITE);
		g.drawString(game.getName(), (getWidth()/2)+25, 65);
	}

	private void paintPlayer(Graphics g, Game game) {
		if(!game.getName().equals(this.game.getName()) && this.game != null) {
			g.drawImage(new ImageIcon(getClass().getResource(this.game.getAvatar())).getImage(),(int)game.getX(), (int)game.getY(), (int)game.getPlayer().getWidth(),
					(int)game.getPlayer().getHeight(), this);
			g.setColor(Color.WHITE);
			g.drawString(game.getName(), game.getX()+5, game.getY()-5);
		}else {
			g.drawImage(new ImageIcon(getClass().getResource(this.game.getAvatar())).getImage(),(int)this.game.getPlayer().getX(), (int)this.game.getPlayer().getY(), 50,
					50, this);
			g.setColor(Color.WHITE);
			g.drawString(this.game.getName(), this.game.getX()+5, game.getY()-5);
		}
	}

	private void paintShoot(Graphics g, Game game) {
		if(game.getList().size() > 0 && !game.getName().equals(this.game.getName())) {
			if(game.getList() != null && game.getList().size() != 0) {
				for (Shoot shoot : game.getList()) {
					g.drawImage(new ImageIcon(getClass().getResource(getImage(shoot.getTypeOfHablility()))).getImage(),
							(int)shoot.getRectangle().getX(), (int)shoot.getRectangle().getY(), 
							getSize(shoot.getTypeOfHablility()), getSize(shoot.getTypeOfHablility()), this);
				}
			}
		}
		if(this.game != null && this.game.getList().size() > 0) {
			for (Shoot shoot : this.game.getList()) {
				g.drawImage(new ImageIcon(getClass().getResource(getImage(shoot.getTypeOfHablility()))).getImage(),
						(int)shoot.getRectangle().getX(), (int)shoot.getRectangle().getY(), 
						(int)shoot.getRectangle().getWidth(), (int)shoot.getRectangle().getHeight(), this);
			}
		}
	}

	private int getSize(Hability typeOfHablility) {
		if(typeOfHablility.toString().equals("BASIC")) {
			return 20;
		}else {
			return 40;
		}
	}

	private String getImage(Hability typeOfHablility) {
		switch (typeOfHablility.toString()) {
		case "ULTI":
			return ConstantsUI.ULTI_SHOOT;
		case "BASIC":
			return ConstantsUI.BASIC_SHOOT;
		case "PASSIVE":
			return ConstantsUI.PASSIVE_SHOOT;
		}
		return ConstantsUI.BASIC_SHOOT;
	}

	public void paintEnemy(Graphics g) {
		if(game.getEnemyList() != null && game.getEnemyList().size() != 0) {
			for (Enemy enemy : game.getEnemyList()) {
				if(enemy.getId() == 1000) {
					g.drawImage(enemyB,(int)enemy.getEnemy().getX(), (int)enemy.getEnemy().getY(), 50, 50, this);
				}else {
					g.drawImage(enemyI,(int)enemy.getEnemy().getX(), (int)enemy.getEnemy().getY(), 50, 50, this);
				}
			}
		}
	}

	public void setGame(ArrayList<Game> gamelist) {
		this.gameList = gamelist;
	}

	public void setLocalGame(Game game) {
		this.game = game;
	}

	public void setBack(int i) {
		switch (i) {
		case 1:
			background = new ImageIcon(getClass().getResource(ConstantsUI.LEVEL1)).getImage();
			break;
		case 2:
			background = new ImageIcon(getClass().getResource(ConstantsUI.LEVEL2)).getImage();
			break;
		case 3:
			background = new ImageIcon(getClass().getResource(ConstantsUI.LEVEL3)).getImage();
			break;
		case 4:
			background = new ImageIcon(getClass().getResource(ConstantsUI.LEVEL4)).getImage();
			break;
		default:
			background = new ImageIcon(getClass().getResource(ConstantsUI.LEVEL1)).getImage();
			break;
		}
	}
}
