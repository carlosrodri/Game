package models.entities;

import java.awt.Rectangle;

public class Enemy extends MyThread{
	private Rectangle enemy;
	private int life;
	private int x;

	public Enemy(int x) {
		super(1000);
		enemy = new Rectangle(x, randomY(), 40, 40);
		life = 100;
		this.x = x;
	}

	public Rectangle getEnemy() {
		return enemy;
	}

	public int getLife() {
		return life;
	}
	
	public void setLife(int life) {
		life += life;
	}
	
	private int randomY() {
		return (int)Math.random()*1000;
	}

	public void initPosition() {
		enemy.setLocation(x, randomY());
	}
	
	@Override
	public void executeTask() {
		paintLocation();
	}

	private void paintLocation() {
		enemy.setLocation((int)enemy.getX()-10, randomYM());
	}

	private int randomYM() {
		return (int)(Math.random()*800);
	}

	public boolean validate() {
		if(life == 0) {
			return true;
		}else {
			return false;
		}
		
	}
}
