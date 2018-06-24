package persistence;

import java.awt.Rectangle;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONObject;
import models.dao.Hability;
import models.entities.Enemy;
import models.entities.Game;
import models.entities.Shoot;

public class JSONFileManager{
	private ArrayList<Enemy> enemiesList;

	public JSONFileManager() {
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
			enemiesList = new ArrayList<>();
			String g[] = p[i].split("=");
			Game game = new Game((int)Double.parseDouble(g[0]), (int)Double.parseDouble(g[1]), g[2], g[5]);
			if(!g[3].equals("-") ) {
				String shoot[] = g[3].split("%");
				for (int j = 0; j < shoot.length; j++) {
					String c[] = shoot[j].split("_");
					shootList.add(new Shoot(new Rectangle((int)Double.parseDouble(c[0]), 
							(int)Double.parseDouble(c[1]), 40, 40),
							hability(c[2])));
				}
			}
			if(!g[4].equals("-") ) {
				String shoot[] = g[4].split("%");
				for (int j = 0; j < shoot.length; j++) {
					String c[] = shoot[j].split("_");
					Enemy enemy = new Enemy(Integer.parseInt(c[0]));
					enemy.setPosition((int)Double.parseDouble(c[1]), (int)Double.parseDouble(c[2]));
					enemy.setLifeServer(Integer.parseInt(c[3]));
					enemiesList.add(enemy);
				}
			}
//			game.setEnemyList(enemyList);
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
	
	public ArrayList<Enemy> getEnemiesList() {
		for (Enemy enemy : enemiesList) {
			System.out.println(enemy.getId() + "   id del enemigo");
		}
		return enemiesList;
	}
}