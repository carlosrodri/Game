package models.dao;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.Timer;

import constants.ConstantsUI;
import models.entities.Shoot;

public class Game extends MyThread{
	private Rectangle player;
	private List<Rectangle> enemyList;
	private List<Shoot> shootList;
	private String avatar;
	private int x, y;
	private int life, level;
	private Timer timer;
	private String background;

	public Game(int sleep, int x, int y, String avatar) {
		super(sleep);
		this.x = x;
		this.y = y;
		this.avatar = avatar;
		player = new Rectangle(0, 0, 50, 50);
		shootList = new ArrayList<>();
		enemyList = new ArrayList<>();
		life = 100;
		level = 1;

		timer = new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				paintEnemy();
			}
		});
		timer.start();
	}

	public String getAvatar() {
		return avatar;
	}

	private int randomPositionY() {
		return (int)(Math.random()*(y-50))+100;
	}

	private void quitLife() {
		life -= 5;
	}

	public int getLife() {
		return life;
	}

	@Override
	public void executeTask() {
		paintShoot();
	}

	private void paintEnemy() {
		for (Rectangle rectangle : enemyList) {
			rectangle.setLocation(randomLocation());
		}
	}

	private Point randomLocation() {
		return new Point((int)(Math.random()*1200)+20, (int)(Math.random()*900)+10);
	}

	private void paintShoot() {
		for (Shoot shoot : shootList) {
			shoot.getRectangle().setLocation((int)shoot.getRectangle().getX()+15, 
					(int)shoot.getRectangle().getY());
		}
	}

	public void moveleft() {
		player.setLocation((int)player.getX()-20, (int)player.getY());
	}

	public void moveRigth() {
		player.setLocation((int)player.getX()+20, (int)player.getY());
	}

	public void moveUp() {
		player.setLocation((int)player.getX(), (int)player.getY()-20);
	}

	public void moveDown() {
		player.setLocation((int)player.getX(), (int)player.getY()+20);
	}

	public void addEnenmy() {
		generateMap(level);
		if(level != 5) {
			for (int i = 0; i < level*3; i++) {
				enemyList.add(new Rectangle(x+10, randomPositionY(), 50, 50));
			}
			level ++;
		}else {
			enemyList.add(new Rectangle(x, randomPositionY(), 200, 200));
		}
	}

	private void generateMap(int level) {
		switch (level) {
		case 1:
			background = ConstantsUI.LEVEL1;
			break;
		case 2:
			background = ConstantsUI.LEVEL2;
			break;
		case 3:
			background = ConstantsUI.LEVEL3;
			break;
		case 4:
			background = ConstantsUI.LEVEL4;
			break;
		}
	}

	public String getBackground() {
		return background;
	}

	public void manageShoot(int key) {
		switch (key) {
		case KeyEvent.VK_E:
			shootList.add(new Shoot(new Rectangle((int)player.getX(), (int)player.getY(), ConstantsUI.SIZE_BASIC, 
					ConstantsUI.SIZE_BASIC), Hability.BASIC, 20, ConstantsUI.BASIC_SHOOT));
			break;
		case KeyEvent.VK_R:
			manageLife();
			break;
		case KeyEvent.VK_T:
			shootList.add(new Shoot(new Rectangle((int)player.getX(), (int)player.getY(), 40, 40), Hability.ULTI, 80, ConstantsUI.ULTI_SHOOT));
			break;
		}
	}

	private void manageLife() {
		if(life < 100) {
			life += 5;
		}
	}

	public List<Rectangle> getEnemyList(){
		return enemyList;
	}

	public List<Shoot> getList(){
		return shootList;
	}

	public Rectangle getPlayer() {
		return player;
	}

	public void validate() {
		for (Iterator<Shoot> shoot =  shootList.iterator(); shoot.hasNext();) {
			Shoot s = shoot.next();
			for (Iterator<Rectangle> enemy =  enemyList.iterator(); enemy.hasNext();) {
				Rectangle e = enemy.next();
				if(s.getRectangle().intersects(e) && e.getWidth() == 50) {
					enemy.remove();
				}
			}
		}
	}

	public boolean validateLife() {
		for (Iterator<Rectangle> enemy =  enemyList.iterator(); enemy.hasNext();) {
			Rectangle e = enemy.next();
			if(player.intersects(e)) {
				enemy.remove();
				quitLife();
			}
		}
		if(life <= 0) {
			return true;
		}else {
			return false;
		}
	}

	public boolean validateLevel() {
		if(enemyList == null && life > 0 || enemyList.size() == 0) {
			shootList.clear();
			return true;
		}else {
			return false;
		}
	}

	//	public void setEnemyList(List<Rectangle> enemyList) {
	//		this.enemyList = enemyList;
	//	}

	public void setDimensions(int width, int height) {
		this.x = width;
		this.y = height;
	}
}
