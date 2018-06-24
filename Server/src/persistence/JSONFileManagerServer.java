package persistence;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import constants.ConstantsUI;
import models.entities.Enemy;
import models.entities.Game;
import models.entities.Shoot;
import network.Server;


public class JSONFileManagerServer{
	private Server server;

	public JSONFileManagerServer(Server server) {
		this.server = server;
	}

	public Game readGame() {
		JSONParser parser = new JSONParser();  
		Object obj = null;
		try {
			obj = parser.parse(new FileReader(ConstantsUI.PATH_SEND));
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		JSONObject o = (JSONObject) obj;

		Game g = new Game(200, Integer.parseInt(o.get("x").toString()), Integer.parseInt(o.get("y").toString()),
				o.get("avatar").toString(),o.get("nombre").toString());
		g.setLife(100);
		g.setBackground(ConstantsUI.LEVEL1);
		return g;
	}

	public synchronized String writeGameList(ArrayList<Game> gameList) {
		String format = "";
		for (Game game : gameList) {
			format += game.getPlayer().getX()+"=";
			format += game.getPlayer().getY()+"=";
			format += game.getAvatar()+"=";
			if(game.getList().size() > 0) {
				try {
					for (Iterator<Shoot> shoot =  game.getList().iterator(); shoot.hasNext();) {
						Shoot s = shoot.next();
						format += s.getRectangle().getX()+"_";
						format += s.getRectangle().getY()+"_";
						format += s.getTypeOfHablility().toString()+"_";
						format += "%";
					}
				} catch (Exception e) {
					format += "=";
					if(server.getEnemylist().size() > 0) {
						for (Enemy enemy : server.getEnemylist()) {
							format += enemy.getId()+"_";
							format += enemy.getEnemy().getX()+"_";
							format += enemy.getEnemy().getY()+"_";
							format += enemy.getLife()+"_";
							format += "%";
						}
					}else {
						format += "-";
					}
					format += "=";
					format += game.getName()+"#";
					break;
				}
			}else {
				format += "-";
			}
			format += "=";
			if(server.getEnemylist().size() > 0) {
				for (Enemy enemy : server.getEnemylist()) {
					format += enemy.getId()+"_";
					format += enemy.getEnemy().getX()+"_";
					format += enemy.getEnemy().getY()+"_";
					format += enemy.getLife()+"_";
					format += "%";
				}
			}else {
				format += "-";
			}
			format += "=";
			format += game.getName()+"#";
		}
		return format;
	}
}