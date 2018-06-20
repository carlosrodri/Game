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


	public JSONFileManager() {
	}

	public ArrayList<Game> readFile() throws FileNotFoundException, IOException{
		JSONParser parser = new JSONParser();  
		Object obj = null;
		try {
			obj = parser.parse(new FileReader(ConstantsUI.PATH));
		} catch (ParseException e) {
			e.printStackTrace();
		}  
		JSONArray listJSON = (JSONArray) obj;
		ArrayList<Game> list = new ArrayList<>();
		for (Object object : listJSON) {
			JSONObject objCyclist = new JSONObject();

			objCyclist = (JSONObject) object;

			JSONObject o = (JSONObject) objCyclist.get("player");
			Game g = new Game( 
					Integer.parseInt(o.get("x").toString()),
					Integer.parseInt(o.get("y").toString()),
					o.get("avatar").toString(),
					o.get("nombre").toString());
			g.setLife(Integer.parseInt(o.get("life").toString()));
			g.setBackground(o.get("background").toString());
			g.setPosition(Integer.parseInt(o.get("posx").toString()), Integer.parseInt(o.get("posy").toString()));
			list.add(g);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public void writeFile(String path, Game game) throws IOException {
		JSONObject object = new JSONObject();

		object.put("x", new Integer(game.getX()));
		object.put("y", new Integer(game.getY()));
		object.put("avatar", game.getAvatar());
		object.put("nombre", game.getName());
		object.put("background", game.getBackground());
		object.put("life", new Integer(game.getLife()));

		FileWriter writer = new FileWriter(path);
		writer.write(object.toJSONString());
		writer.flush();
		writer.close();
	}
}