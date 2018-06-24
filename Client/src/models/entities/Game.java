package models.entities;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import constants.ConstantsUI;
import models.dao.Hability;
import models.dao.MyThread;

public class Game extends MyThread{
	private Rectangle player;
	private List<Enemy> enemyList;
	private List<Shoot> shootList;
	private String avatar;
	private int x, y;
	private int life;
	private String background;
	private String name;

	public Game(int x, int y, String avatar, String name) {
		super(90);
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

	public int getLife() {
		return life;
	}

	public String getBackground() {
		return background;
	}

	public List<Enemy> getEnemyList(){
		return enemyList;
	}

	public List<Shoot> getList(){
		return shootList;
	}

	public Rectangle getPlayer() {
		return player;
	}

	public void setDimensions(int width, int height) {
		this.x = width;
		this.y = height;
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

	public void setPosition(int x, int y) {
		player.setLocation(x, y);
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

	public void manageActions(int action) {
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

	@Override
	public void executeTask() {
		validateMap();
		paintShoot();
		validateShoot();
		validateLife();
	}

	public boolean validateLife() {
		for (Iterator<Enemy> enemy =  enemyList.iterator(); enemy.hasNext();) {
			Enemy e = enemy.next();
			System.out.println("intersecta");
			if(player.intersects(e.getEnemy())) {
				life -= 5;
			}
		}
		if(life <= 0) {
			return true;
		}else {
			return false;
		}
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

	private void paintShoot() {
		for (Shoot shoot : shootList) {
			shoot.getRectangle().setLocation((int)shoot.getRectangle().getX()+15, 
					(int)shoot.getRectangle().getY());
		}
	}

	public void setShootList(ArrayList<Shoot> shoots) {
		this.shootList = shoots;
	}
	
	private void validateShoot() {
		if(shootList.size() > 0) {
			for (Iterator<Shoot> shoot =  shootList.iterator(); shoot.hasNext();) {
				Shoot s = shoot.next();
				if(s.getRectangle().getX() > x) {
					System.out.println("remueve en   "+ x);
					shoot.remove();
				}
			}
		}
	}

	public void setEnemyList(ArrayList<Enemy> enemyList) {
		this.enemyList = enemyList;
	}
}
