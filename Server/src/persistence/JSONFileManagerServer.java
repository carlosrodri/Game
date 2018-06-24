package persistence;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import constants.ConstantsUI;
import models.entities.Enemy;
import models.entities.Game;
import models.entities.Shoot;
import network.Server;


public class JSONFileManagerServer{

	public JSONFileManagerServer() {
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

	public String writeGameList(ArrayList<Game> gameList) throws IOException {
		String format = "";
		for (Game game : gameList) {
			format += game.getPlayer().getX()+"=";
			format += game.getPlayer().getY()+"=";
			format += game.getAvatar()+"=";
			if(game.getList().size() > 0) {
				for (Shoot shoot : game.getList()) {
					format += shoot.getRectangle().getX()+"_";
					format += shoot.getRectangle().getY()+"_";
					format += shoot.getTypeOfHablility().toString()+"_";
					format += "%";
				}
			}else {
				format += "-";
			}
			format += "=";
			if(Server.getEnemylist().size() > 0) {
				System.out.println("entra al server");
				for (Enemy enemy : Server.getEnemylist()) {
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