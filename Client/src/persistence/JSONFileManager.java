package persistence;

import java.awt.Rectangle;
import java.io.File;
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
import models.dao.Hability;
import models.entities.Game;
import models.entities.Shoot;

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

		File f = new File(path);
		if(f.exists()) {
			FileWriter writer = new FileWriter(path+"game.json");
			writer.write(object.toJSONString());
			writer.flush();
			writer.close();
		}else {
			f.mkdirs();
			FileWriter writer = new FileWriter(path+"game.json");
			writer.write(object.toJSONString());
			writer.flush();
			writer.close();
		}
	}

	public ArrayList<Game> readList(String readResponse) {
		ArrayList<Game> list = new ArrayList<>();
		String p[] = readResponse.split("#");
		for (int i = 0; i < p.length; i++) {
			ArrayList<Shoot> shootList = new ArrayList<>();
			String g[] = p[i].split("=");
			Game game = new Game((int)Double.parseDouble(g[0]), (int)Double.parseDouble(g[1]), g[2], g[4]);
			if(!g[3].equals("-") ) {
				String shoot[] = g[3].split("%");
				for (int j = 0; j < shoot.length; j++) {
					String c[] = shoot[j].split("_");
					shootList.add(new Shoot(new Rectangle((int)Double.parseDouble(c[0]), 
							(int)Double.parseDouble(c[1]), 40, 40),
							hability(c[2])));
				}
			}
			game.setShootList(shootList);
			list.add(game);
		}
		return list;
	}

	private Hability hability(String string) {
		switch (string.toUpperCase()) {
		case "BASIC":
			return Hability.BASIC;
		case "ULTI":
			return Hability.ULTI;
		case "PASIVE":
			return Hability.PASIVE;
		}
		return Hability.BASIC;
	}
}