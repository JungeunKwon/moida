package com.ssafy.moida.component;

import org.springframework.stereotype.Component;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ssafy.moida.web.dto.sentimental.SentimentalResponseDto;


@Component
public class SentimentalDataParser {

	public SentimentalResponseDto JasonParser(String result) {
		SentimentalResponseDto responseDto = null;
		
		
		JsonParser jsonParser = new JsonParser();
		
		JsonObject object = (JsonObject) jsonParser.parse(result);
		
		JsonObject res = (JsonObject) object.get("return_object");
		JsonArray res2 = res.get("Result").getAsJsonArray();
		
		JsonArray realvalue = (JsonArray) res2.get(0);
		
		String res_code = object.get("result_code").getAsString();
		String score = realvalue.get(0).getAsString();
		String sentimental = realvalue.get(1).getAsString();
		
		if(res_code.equals("success")) {
			responseDto = SentimentalResponseDto.builder()
			.sentimental(sentimental)
			.score(Double.parseDouble(score))
			.build();
		}
		else {
			responseDto = SentimentalResponseDto.builder().sentimental("실패").score(0.0).build();
		}
		return responseDto;
	}
}
