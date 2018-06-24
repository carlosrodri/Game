package utilities;

import java.util.ArrayList;

import models.entities.Enemy;
import models.entities.Game;

public class MyUtilities {
	
	private ArrayList<Game> gameList;
	private ArrayList<Enemy> enemyList;

	public MyUtilities() {
		gameList = new ArrayList<>();
		enemyList = new ArrayList<>();
	}
	
	public void add(Game game) {
		gameList.add(game);
		for (Game games : gameList) {
			System.out.println(games.getAvatar()+ "sdbdfsgbdsvb sd");
		}
	}
	
	public void addEnemy(Enemy enemy) {
		enemyList.add(enemy);
	}
	
	public ArrayList<Enemy> getEnemyList() {
		return enemyList;
	}
	
	public ArrayList<Game> getGameList() {
		return gameList;
	}
}
