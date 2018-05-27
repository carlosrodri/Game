package persistence;

import java.awt.Rectangle;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import constants.ConstantsUI;
import models.dao.MyThread;


public class JSONFileManager extends MyThread{
	private List<Rectangle> enemyListDao;


	public JSONFileManager(int sleep) {
		super(sleep);
	}

	public List<Rectangle> readFile() throws FileNotFoundException, IOException{
		JSONParser parser = new JSONParser();  
		Object obj = null;
		try {
			obj = parser.parse(new FileReader(ConstantsUI.PATH+".json"));
		} catch (ParseException e) {
			e.printStackTrace();
		}  
		JSONArray listJSON = (JSONArray) obj;
		List<Rectangle> list = new ArrayList<>();
		for (Object object : listJSON) {
			JSONObject objCyclist = new JSONObject();

			objCyclist = (JSONObject) object;

			JSONObject o = (JSONObject) objCyclist.get("Enemy");

			list.add(new Rectangle((int)Double.parseDouble(o.get("x").toString()), (int)Double.parseDouble(o.get("y").toString()), 
					(int)Double.parseDouble(o.get("width").toString()), (int)Double.parseDouble(o.get("heigth").toString())));
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public void writeFile(String path, List<Rectangle> enemyListDao) {
		JSONObject obj = null;

		JSONObject topObj = null;

		JSONArray enemyList = new JSONArray();
		if(enemyList != null) {
			for (Rectangle enemy : enemyListDao) {
				obj = new JSONObject();
				topObj = new JSONObject();
				topObj.put("x", (int)enemy.getX());
				topObj.put("y", (int)enemy.getY());
				topObj.put("width", enemy.getWidth());
				topObj.put("heigth", enemy.getHeight());

				obj.put("Enemy", topObj);

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

	@Override
	public void executeTask() {
		if(enemyListDao != null) {
		writeFile(ConstantsUI.PATH, enemyListDao);
		}
	}

	public void setEnemyListDao(List<Rectangle> enemyListDao) {
		this.enemyListDao = enemyListDao;
	}
}