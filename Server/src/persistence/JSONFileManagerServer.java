package persistence;

import java.awt.Rectangle;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import constants.ConstantsUI;
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
		
		return new Game(Integer.parseInt(o.get("sleep").toString()), Integer.parseInt(o.get("x").toString()), Integer.parseInt(o.get("y").toString()),
				o.get("avatar").toString(),
				o.get("nombre").toString());
	}

	@SuppressWarnings("unchecked")
	public void writeGameList(ArrayList<Game> gameList) throws IOException {
		JSONObject object = new JSONObject();

		for (Game game : gameList) {
			object.put("nombre", game.getName());
			object.put("avatar", game.getAvatar());
			object.put("x", new Integer(game.getX()));
			object.put("y", new Integer(game.getY()));
			object.put("sleep", new Integer(game.getSleep()));
		}
		

		FileWriter writer = new FileWriter(ConstantsUI.PATH_FILE);
		writer.write(object.toJSONString());
		writer.flush();
		writer.close();
	}
	@SuppressWarnings("unchecked")
	public void writeFile(String path, ArrayList<Game> gameList) {
		JSONObject obj = null;

		JSONObject topObj = null;

		JSONArray enemyList = new JSONArray();
		if(enemyList != null) {
			for (Game game : gameList) {
				obj = new JSONObject();
				topObj = new JSONObject();
				topObj.put("name", game.getName());
				topObj.put("avatar", game.getAvatar());
				topObj.put("x", (int)game.getX());
				topObj.put("y", (int)game.getY());
				JSONArray enemy = new JSONArray();
				for (Rectangle rectangle : game.getEnemyList()) {
					enemy.add(new JSONObject().put("x", rectangle.getX()));
					enemy.add(new JSONObject().put("y", rectangle.getY()));
				}
				topObj.put("enemyList", enemy);
				JSONArray shootList = new JSONArray();
				for (Shoot shoot : game.getList()) {
					shootList.add(new JSONObject().put("image", shoot.getImage()));
					shootList.add(new JSONObject().put("x", shoot.getRectangle().getX()));
					shootList.add(new JSONObject().put("y", shoot.getRectangle().getY()));
				}
				topObj.put("shootList", shootList);

				obj.put("Player", topObj);

				enemyList.add(obj);
			}
		}

		try {

			FileWriter file = new FileWriter(path + ".json", false);
			file.write(enemyList.toJSONString());
			file.flush();
			file.close();


		} catch (IOException e) {
		}
	}


}