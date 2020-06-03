package com.ssafy.moida.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;
import com.ssafy.moida.component.SentimentalDataParser;
import com.ssafy.moida.service.account.AccountService;
import com.ssafy.moida.web.dto.sentimental.SentimentalRequestDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = {"6. sentimental"})
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class SentimentalController {
	
	private final SentimentalDataParser sentimentalDataParser;
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 후 Access 토큰 필요", required = true, dataType = "String", paramType = "header")
	})
	@ApiOperation(value = "감정분석", httpMethod = "POST", notes = "감정분석을위해 description을 날려주세요")
	//@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@PostMapping(value = "/sentimental")
	public ResponseEntity<HttpStatus> sentimentalAnalyzer(@RequestBody SentimentalRequestDto requestDto) throws IOException{
		
		String[] sentences = requestDto.getDescription().split("\\n");
		String url = "http://api.adams.ai/datamixiApi/omAnalysis?key=8013557318792994835&query=";
		
		int[][] array;
		
		for(int i=0; i<sentences.length; ++i) {
			url = url+URLEncoder.encode(sentences[i], "UTF-8")+"&type=1";
			System.out.println(url);
			URL reqeustUrl = new URL(url);
			HttpURLConnection con = (HttpURLConnection) reqeustUrl.openConnection();
			
			con.setRequestMethod("GET");
			con.setDoOutput(true);

			int responseCode = con.getResponseCode();
			if(responseCode == 400) {
				System.out.println("400:: 해당 명령을 실행할 수 없음");
			}
			else if(responseCode==500) {
				System.out.println("500:: 서버에러");
			}
			else {
				BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
				StringBuilder sb = new StringBuilder();
				String line = "";
				while((line=br.readLine())!=null) {
					sb.append(line);
				}
				System.out.println(sb.toString());
				sentimentalDataParser.JasonParser(sb.toString());
			}
		}
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
}
