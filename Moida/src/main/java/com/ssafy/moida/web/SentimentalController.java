package com.ssafy.moida.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 후 Access 토큰 필요", required = true, dataType = "String", paramType = "header")
	})
	@ApiOperation(value = "감정분석", httpMethod = "POST", notes = "감정분석을위해 description을 날려주세요")
	//@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@PostMapping(value = "/sentimental")
	public ResponseEntity<HttpStatus> sentimentalAnalyzer(@RequestBody SentimentalRequestDto requestDto) throws IOException{
		
		String[] sentences = requestDto.getDescription().split("\\n");
		String url = "http://api.adams.ai/datamixiApi/omAnalysis?key=8013557318792994835&query=";
		for(int i=0; i<sentences.length; ++i) {
			url = url+sentences[i]+"&type=1";
			System.out.println(url);
			URL reqeustUrl = new URL(url);
			HttpURLConnection con = (HttpURLConnection) reqeustUrl.openConnection();
			
			con.setRequestMethod("GET");
			con.setRequestProperty("Accept-Charset", "UTF-8");
			con.setRequestProperty("Content-Type", "text/plain; charset=utf-8");
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			String resultXmlText = "";
			while((inputLine = br.readLine())!=null) {
				resultXmlText+=inputLine;
			}
			
			br.close();
			con.disconnect();
			
			System.out.println(resultXmlText);
			
		}
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
}
