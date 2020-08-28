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
			while ((line = rd.readLine()) != null) {
				sb.append(line + "\n");
			}
			rd.close();

			String json = sb.toString();
			JSONArray data = new JSONArray(json.toString());
			ArrayList<HashMap<String, String>> myArrList = new ArrayList<HashMap<String, String>>();
			HashMap<String, String> map;
			for (int i = 0; i < data.length(); i++) {
				JSONObject c = data.getJSONObject(i);
				map = new HashMap<String, String>();
				map.put("school", c.getString("school"));
				map.put("last", c.getJSONObject("name").getString("last"));
				map.put("first", c.getJSONObject("name").getString("first"));
				map.put("id", c.get("id").toString());
				map.put("age", c.get("age").toString());
				map.put("email", c.getString("email"));
				myArrList.add(map);
			}

			for (int i = 0; i < myArrList.size(); i++) {
				String id = myArrList.get(i).get("id").toString();
				String age = myArrList.get(i).get("age").toString();
				String school = myArrList.get(i).get("school").toString();
				String first = myArrList.get(i).get("first").toString();
				String last = myArrList.get(i).get("last").toString();
				String email = myArrList.get(i).get("email").toString();
				System.out.println("id = " + id);
				System.out.println("age = " + age);
				System.out.println("school = " + school);
				System.out.println("first = " + first);
				System.out.println("last = " + last);
				System.out.println("email = " + email);
				System.out.println("=========================");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}