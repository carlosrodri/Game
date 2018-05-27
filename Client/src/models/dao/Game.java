package models.dao;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import constants.ConstantsUI;
import models.entities.Shoot;

public class Game extends MyThread{
	private Rectangle player;
	private List<Rectangle> enemyList;
	private List<Shoot> shootList;

	private int x, y;
	private int life, level;

	public Game(int sleep, int x, int y) {
		super(sleep);
		this.x = x;
		this.y = y;
		player = new Rectangle(0, 0, 50, 50);
		shootList = new ArrayList<>();
		enemyList = new ArrayList<>();
		life = 100;
		level = 1;
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
		paintEnemy();
		paintShoot();
	}

	private void paintEnemy() {
		for (Rectangle rectangle : enemyList) {
			if(!(rectangle.getWidth()>50)) {
				rectangle.setLocation((int)rectangle.getX()-15, (int)rectangle.getY());
			}else {
				rectangle.setLocation((int)rectangle.getX()-15, (int)rectangle.getY());
			}
		}
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
		if(level != 5) {
			for (int i = 0; i < level*5; i++) {
				enemyList.add(new Rectangle(x+10, randomPositionY(), 50, 50));
			}
			level ++;
		}else {
			enemyList.add(new Rectangle(x, randomPositionY(), 200, 200));
		}
	}

	public void manageShoot(int key) {
		switch (key) {
		case KeyEvent.VK_E:
			shootList.add(new Shoot(new Rectangle((int)player.getX(), (int)player.getY(), ConstantsUI.SIZE_BASIC, 
					ConstantsUI.SIZE_BASIC), Hability.BASIC, 20, ConstantsUI.BASIC_SHOOT));
			break;
		case KeyEvent.VK_R:
			life += 5;
//			shootList.add(new Shoot(new Rectangle((int)player.getX(), (int)player.getY(), 10, 10), Hability.PASIVE, 12, ConstantsUI.PASSIVE_SHOOT));
			break;
		case KeyEvent.VK_T:
			shootList.add(new Shoot(new Rectangle((int)player.getX(), (int)player.getY(), 10, 10), Hability.ULTI, 80, ConstantsUI.ULTI_SHOOT));
			break;
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

	public void setEnemyList(List<Rectangle> enemyList) {
		this.enemyList = enemyList;
	}

	public void setDimensions(int width, int height) {
		this.x = width;
		this.y = height;
	}
}
