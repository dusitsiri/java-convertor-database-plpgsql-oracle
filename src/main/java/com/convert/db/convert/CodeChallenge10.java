package com.convert.db.convert;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;
public class CodeChallenge10 {
	public static void main(String[] args) {
		try {
			URL url = new URL("https://gist.githubusercontent.com/first087/ac91f67c2022093a2e493295db9a385f/raw/266d21e3584b5b6e628e7eb621dfdfa41c6d54bc/input_round_8.json");
			BufferedReader rd = new BufferedReader(new InputStreamReader(url.openStream(), Charset.forName("UTF-8")));
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = rd.readLine()) != null) sb.append(line + "\n");rd.close();
			JSONArray data = new JSONArray(sb.toString());
			ArrayList<HashMap<String, String>> tester = new ArrayList<HashMap<String, String>>();
			HashMap<String, String> map;
			for (int i = 0; i < data.length(); i++) {
				JSONObject c = data.getJSONObject(i);
				map = new HashMap<String, String>();
				map.put("school", c.getString("school"));map.put("last", c.getJSONObject("name").getString("last"));
				map.put("first", c.getJSONObject("name").getString("first"));map.put("id", c.get("id").toString());
				map.put("age", c.get("age").toString());map.put("email", c.getString("email"));
				tester.add(map);
			}
			ArrayList<HashMap<String, String>> noTester = new ArrayList<HashMap<String, String>>();
			for (int i = 0; i < tester.size(); i++) {
				if (Integer.parseInt(tester.get(i).get("age")) < 22 || Integer.parseInt(tester.get(i).get("age")) > 28) {
					noTester.add(tester.get(i)); tester.remove(i); i--;
				}
			}
			
			for (int i = 0; i < tester.size(); i++) {
				System.out.println(tester.get(i));
			}
			System.out.println("=======================");
			for (int i = 0; i < noTester.size(); i++) {
				System.out.println(noTester.get(i));
			}
			
		
			
			
			
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}