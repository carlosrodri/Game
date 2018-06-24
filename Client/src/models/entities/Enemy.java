package models.entities;

import java.awt.Rectangle;

import models.dao.MyThread;

public class Enemy extends MyThread{
	private Rectangle enemy;
	private int life;
	private int id;

	public Enemy(int id) {
		super(3000);
		enemy = new Rectangle(randomX(), randomY(), 40, 40);
		life = 100;
		this.id = id;
	}

	public Rectangle getEnemy() {
		return enemy;
	}

	public int getLife() {
		return life;
	}
	
	public void setLife(int life) {
		this.life += life;
	}
	
	public void setLifeServer(int life) {
		this.life = life;
	}
	
	public void setLocation() {
		enemy.setLocation(randomX(), randomY());
	}

	private int randomY() {
		return (int)(Math.random()*800);
	}

	private int randomX() {
		return (int)(Math.random()*1000);
	}

	@Override
	public void executeTask() {
		paintLocation();
	}

	private void paintLocation() {
		System.out.println("random de y   " + randomX());
		enemy.setLocation((int)enemy.getX()-10, randomY());
	}
	
	public int getId() {
		return id;
	}

	public void setPosition(int parseDouble, int parseDouble2) {
		enemy.setLocation(parseDouble, parseDouble2);
	}
}
