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

		System.out.println(" ajajjajajaja");
		System.out.println(" ajajjajajaj" + o.get("nombre"));
		
		Game g = new Game(Integer.parseInt(o.get("sleep").toString()), Integer.parseInt(o.get("x").toString()), Integer.parseInt(o.get("y").toString()),
				o.get("avatar").toString(),
				o.get("nombre").toString());
		
		g.setLife(Integer.parseInt(o.get("life").toString()));
		g.setBackground(o.get("background").toString());

		return g;
	}

	@SuppressWarnings("unchecked")
	public void writeGameList(ArrayList<Game> gameList) throws IOException {
		
		JSONArray array = new JSONArray();
		JSONObject o  = new JSONObject();

		System.out.println("escribe lista e el servidor para enviaarla");
		
		for (Game game : gameList) {
			JSONObject object = new JSONObject();
			object.put("sleep", new Integer(game.getSleep()));
			object.put("nombre", game.getName());
			object.put("avatar", game.getAvatar());
			object.put("x", new Integer(game.getX()));
			object.put("y", new Integer(game.getY()));
			object.put("life", new Integer(game.getLife()));
			object.put("background", game.getBackground());
			o.put("player", object);
			array.add(o);
		}

		FileWriter writer = new FileWriter(ConstantsUI.PATH_FILE);
		writer.write(array.toJSONString());
		writer.flush();
		writer.close();
	}
	@SuppressWarnings("unchecked")
	public void writeFile(String path, ArrayList<Game> gameList) {
		JSONObject obj = null;

		JSONObject topObj = null;

		JSONArray enemyList = new JSONArray();
			for (Game game : gameList) {
				obj = new JSONObject();
				topObj = new JSONObject();
				topObj.put("name", game.getName());
				topObj.put("x", (int)game.getX());
				topObj.put("y", (int)game.getY());
				topObj.put("avatar", game.getAvatar());
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

		try {

			FileWriter file = new FileWriter(path + ".json", false);
			file.write(enemyList.toJSONString());
			file.flush();
			file.close();


		} catch (IOException e) {
		}
	}
}