package com.ssafy.moida.component;

import org.springframework.stereotype.Component;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@Component
public class SentimentalDataParser {

	public String JasonParser(String result) {
		
		JsonParser jsonParser = new JsonParser();
		
		JsonObject object = (JsonObject) jsonParser.parse(result);
		
		JsonObject res = (JsonObject) object.get("return_object");
		JsonArray abc = res.get("Result").getAsJsonArray();
		
		JsonArray res2 = (JsonArray) abc.get(0);
		
		for(int i=0; i<res2.size(); ++i) {
			System.out.println(res2.get(i));
		}
		
		String res_code = object.get("result_code").getAsString();
		System.out.println(res_code);
		
		
		return "1";
	}
}
