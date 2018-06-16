package persistence;

import java.io.FileNotFoundException;
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

public class JSONFileManager{


	public JSONFileManager(int sleep) {
	}

	public ArrayList<Game> readFile() throws FileNotFoundException, IOException{
		JSONParser parser = new JSONParser();  
		Object obj = null;
		try {
			obj = parser.parse(new FileReader(ConstantsUI.PATH+".json"));
		} catch (ParseException e) {
			e.printStackTrace();
		}  
		JSONArray listJSON = (JSONArray) obj;
		ArrayList<Game> list = new ArrayList<>();
		for (Object object : listJSON) {
			JSONObject objCyclist = new JSONObject();

			objCyclist = (JSONObject) object;

			JSONObject o = (JSONObject) objCyclist.get("Game");

			list.add(new Game(Integer.parseInt(o.get("time").toString()), Integer.parseInt(o.get("x").toString()), Integer.parseInt(o.get("y").toString()),
					o.get("avatar").toString()));
		}
		return list;
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
				topObj.put("time", (int)game.getSleep());
				topObj.put("x", (int)game.getX());
				topObj.put("y", game.getY());
				topObj.put("avatar", game.getAvatar());
				
				obj.put("Game", topObj);

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