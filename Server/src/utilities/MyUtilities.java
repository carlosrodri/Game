package utilities;

import java.util.ArrayList;

import models.entities.Game;

public class MyUtilities {
	
	private ArrayList<Game> gameList;

	public MyUtilities() {
		gameList = new ArrayList<>();
	}
	
	public void add(Game game) {
		gameList.add(game);
		for (Game games : gameList) {
			System.out.println(games.getAvatar()+ "sdbdfsgbdsvb sd");
		}
	}
	
	public ArrayList<Game> getGameList() {
		return gameList;
	}
}
