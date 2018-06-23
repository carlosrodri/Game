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
			if(game.getEnemyList().size() > 0) {
				for (Enemy enemy : game.getEnemyList()) {
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
		//		JSONArray array = new JSONArray();
		//
		//		for (Game game : gameList) {
		//			JSONObject object = new JSONObject();
		//			JSONObject o  = new JSONObject();
		//			object.put("sleep", new Integer(game.getSleep()));
		//			object.put("nombre", game.getName());
		//			object.put("avatar", game.getAvatar());
		//			object.put("x", new Integer(game.getX()));
		//			object.put("y", new Integer(game.getY()));
		//			object.put("life", new Integer(game.getLife()));
		//			object.put("background", game.getBackground());
		//			object.put("posx", new Integer((int) game.getPlayer().getX()));
		//			object.put("posy", new Integer((int) game.getPlayer().getY()));
		//			o.put("player", object);
		//			array.add(o);
		//		}
		//
		//		FileWriter writer = new FileWriter(ConstantsUI.PATH_FILE, false);
		//		writer.write(array.toJSONString());
		//		writer.flush();
		//		writer.close();
	}
	//	public void writeFile(String path, ArrayList<Game> gameList) {
	//		JSONObject obj = null;
	//
	//		JSONObject topObj = null;
	//
	//		JSONArray enemyList = new JSONArray();
	//		for (Game game : gameList) {
	//			obj = new JSONObject();
	//			topObj = new JSONObject();
	//			topObj.put("name", game.getName());
	//			topObj.put("x", (int)game.getX());
	//			topObj.put("y", (int)game.getY());
	//			topObj.put("avatar", game.getAvatar());
	//			JSONArray enemy = new JSONArray();
	//			for (Rectangle rectangle : game.getEnemyList()) {
	//				enemy.add(new JSONObject().put("x", rectangle.getX()));
	//				enemy.add(new JSONObject().put("y", rectangle.getY()));
	//			}
	//			topObj.put("enemyList", enemy);
	//			JSONArray shootList = new JSONArray();
	//			for (Shoot shoot : game.getList()) {
	//				shootList.add(new JSONObject().put("image", shoot.getImage()));
	//				shootList.add(new JSONObject().put("x", shoot.getRectangle().getX()));
	//				shootList.add(new JSONObject().put("y", shoot.getRectangle().getY()));
	//			}
	//			topObj.put("shootList", shootList);
	//
	//			obj.put("Player", topObj);
	//
	//			enemyList.add(obj);
	//		}
	//
	//		try {
	//
	//			FileWriter file = new FileWriter(path + ".json", false);
	//			file.write(enemyList.toJSONString());
	//			file.flush();
	//			file.close();
	//
	//
	//		} catch (IOException e) {
	//		}
	//	}
}