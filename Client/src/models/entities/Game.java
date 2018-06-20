package models.entities;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class Game{
	private Rectangle player;
	private List<Rectangle> enemyList;
	private List<Shoot> shootList;
	private String avatar;
	private int x, y;
	private int life, level;
	private String background;
	private String name;

	public Game(int x, int y, String avatar, String name) {
		this.name = name;
		this.x = x;
		this.y = y;
		this.avatar = avatar;
		player = new Rectangle(0, 0, 50, 50);
		shootList = new ArrayList<>();
		enemyList = new ArrayList<>();
		life = 100;
		level = 1;

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

	public List<Rectangle> getEnemyList(){
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
	
	public void setLevel(int level) {
		this.level = level;
	}
}
