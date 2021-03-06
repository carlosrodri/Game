package models.entities;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import constants.ConstantsUI;

public class Game extends MyThread{
	private Rectangle player;
	private List<Enemy> enemyList;
	private List<Shoot> shootList;
	private String avatar;
	private int x, y;
	private int life;
	private String background;
	private String name;
	private int sleep;

	public Game(int sleep, int x, int y, String avatar, String name) {
		super(sleep);
		this.sleep = sleep;
		this.name = name;
		this.x = x;
		this.y = y;
		this.avatar = avatar;
		player = new Rectangle(0, 0, 50, 50);
		shootList = new ArrayList<>();
		enemyList = new ArrayList<>();
		life = 100;
	}

	public String getAvatar() {
		return avatar;
	}

	private void quitLife() {
		life -= 5;
	}

	public int getLife() {
		return life;
	}

	@Override
	public void executeTask() {
		validateMap();
		paintShoot();
		validateLife();
	}

	private void validateMap() {
		if(player.getX() >= x) {
			player.setLocation(x-5, (int)player.getY());
		}else if(player.getX() <= 0) {
			player.setLocation(1, (int)player.getY());
		}else if(player.getY() >= y) {
			player.setLocation((int)player.getX(), y-5);
		}else if(player.getY() <= 0) {
			player.setLocation((int)player.getX(), 1);
		}
	}

	private synchronized void paintShoot() {
		for (Iterator<Shoot> shoot =  shootList.iterator(); shoot.hasNext();) {
			Shoot s = shoot.next();
			s.getRectangle().setLocation((int)s.getRectangle().getX()+15, 
					(int)s.getRectangle().getY());
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

	public void addEnenmy(Enemy enemy) {
		System.out.println(enemy.getLife() + "    enemigo de shhh");
		enemyList.add(enemy);
	}

	public String getBackground() {
		return background;
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
			for (Iterator<Enemy> enemy =  enemyList.iterator(); enemy.hasNext();) {
				Enemy e = enemy.next();
				if(s.getRectangle().intersects(e.getEnemy()) && e.getEnemy().getWidth() == 50) {
					enemy.remove();
				}
			}
		}
	}

	public boolean validateLife() {
		for (Iterator<Enemy> enemy =  enemyList.iterator(); enemy.hasNext();) {
			Enemy e = enemy.next();
			if(player.intersects(e.getEnemy())) {
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

	public void setDimensions(int width, int height) {
		this.x = width;
		this.y = height;
	}

	public synchronized void manageActions(int action) {
		switch (action) {
		case KeyEvent.VK_UP:
			moveUp();
			break;
		case KeyEvent.VK_DOWN:
			moveDown();
			break;
		case KeyEvent.VK_LEFT:
			moveleft();
			break;
		case KeyEvent.VK_RIGHT:
			moveRigth();
			break;
		case KeyEvent.VK_E:
			shootList.add(new Shoot(new Rectangle((int)player.getX(), (int)player.getY(), ConstantsUI.SIZE_BASIC, 
					ConstantsUI.SIZE_BASIC), Hability.BASIC));
			break;
		case KeyEvent.VK_T:
			shootList.add(new Shoot(new Rectangle((int)player.getX(), (int)player.getY(), 40, 40), Hability.ULTI));
			break;
		}
	}

	public int getSleep() {
		return sleep;
	}

	public int getY() {
		return y;
	}

	public int getX() {
		return x;
	}

	public String getName() {
		return name;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public void setBackground(String background) {
		this.background = background;
	}

	public void enqueueActions(int keyCode) {
		manageActions(keyCode);
	}
	
	public List<Enemy> getEnemyList() {
		return enemyList;
	}
}
