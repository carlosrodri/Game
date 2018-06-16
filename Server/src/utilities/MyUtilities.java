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
	}
	
	public ArrayList<Game> getGameList() {
		return gameList;
	}
}
