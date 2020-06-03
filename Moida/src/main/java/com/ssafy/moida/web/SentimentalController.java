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
import com.ssafy.moida.web.dto.sentimental.SentimentalResponseDto;

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
	public ResponseEntity<SentimentalResponseDto> sentimentalAnalyzer(@RequestBody SentimentalRequestDto requestDto) throws IOException{
		
		String[] sentences = requestDto.getDescription().split("\\n");
		String url = "http://api.adams.ai/datamixiApi/omAnalysis?key=8013557318792994835&query=";
		String[] senti = {"기쁨", "신뢰", "공포", "기대", "놀라움", "슬픔", "혐오", "분노"};
		int[][] result = new int[8][2];
		for(int i=0; i<sentences.length; ++i) {
			String recentUrl = "";
			recentUrl = url+URLEncoder.encode(sentences[i], "UTF-8")+"&type=1";
			System.out.println(recentUrl);
			URL reqeustUrl = new URL(recentUrl);
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
				SentimentalResponseDto responseDto = sentimentalDataParser.JasonParser(sb.toString());
				
				switch(responseDto.getSentimental()) {
				case "기쁨":
					result[0][0] += responseDto.getScore()*100;
					result[0][1]++;
					break;
				case "신뢰":
					result[1][0] += responseDto.getScore()*100;
					result[1][1]++;
					break;
				case "공포":
					result[2][0] += responseDto.getScore()*100;
					result[2][1]++;
					break;
				case "기대":
					result[3][0] += responseDto.getScore()*100;
					result[3][1]++;
					break;
				case "놀라움":
					result[4][0] += responseDto.getScore()*100;
					result[4][1]++;
					break;
				case "슬픔":
					result[5][0] += responseDto.getScore()*100;
					result[5][1]++;
					break;
				case "혐오":
					result[6][0] += responseDto.getScore()*100;
					result[6][1]++;
					break;
				case "분노":
					result[7][0] += responseDto.getScore()*100;
					result[7][1]++;
					break;
				case "실패":
					break;
				}
			}
		}
		int index = 0;
		int max = Integer.MIN_VALUE;
		for(int i=0; i<result.length; ++i) {
			if(max<result[i][1]) {
				max = result[i][1];
				index = i;
			}
		}
		int score2 = 0;
		if(result[index][1]!=0) {
			score2 = (int)(result[index][0]/result[index][1]);
		}
		SentimentalResponseDto responseDto = SentimentalResponseDto.builder().sentimental(senti[index]).score2(score2).build();
		return new ResponseEntity<SentimentalResponseDto>(responseDto, HttpStatus.OK);
	}
}
